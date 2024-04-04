package application;

import java.time.LocalDate;



public class BST2 extends BinaryBaseTree2 implements BSTIF2 {
	
	private Node2 insertNode(DateStack data, Node2 node) {
	    // If the node is null, create a new node with the given data
	    if (node == null) {
	        Node2 newNode = new Node2(data);
	        Stack martyrsStack = data.getMartyrs();

	        // If the martyrsStack is not empty, push the top element to the node's data stack
	        if (!martyrsStack.isEmpty()) {
	            newNode.getData().push(martyrsStack.peek());
	        }

	        return newNode;
	    }

	    // Compare the dates of the given data and the node's data
	    if (data.getDate().compareTo(node.getData().getDate()) == 0) {
	        // If the dates are the same, push the top element of the given data's martyrs stack to the node's data stack
	        if (!node.getData().getMartyrs().isEmpty()) {
	            node.getData().push(data.getMartyrs().peek());
	        }
	    } else if (data.getDate().compareTo(node.getData().getDate()) < 0) {
	        // If the given data's date is smaller, recursively insert it to the left subtree
	        node.setLeft(insertNode(data, node.getLeft()));
	    } else {
	        // If the given data's date is greater, recursively insert it to the right subtree
	        node.setRight(insertNode(data, node.getRight()));
	    }

	    return node;
	}

	@Override
	public void deleteNode(LocalDate date, String martyrName) {
		Node2 targetNode = findNode(date);

		if (targetNode != null) {
			Stack martyrsStack = targetNode.getData().getMartyrs();

			// Create a temporary stack to store the remaining martyrs
			Stack tempStack = new Stack();

			// Flag to indicate if the martyr was found and deleted
			boolean martyrDeleted = false;

			// Iterate over the martyrs stack and remove the specified martyr
			while (!martyrsStack.isEmpty()) {
				Martyr currentMartyr = martyrsStack.pop();

				if (currentMartyr.getName().equals(martyrName)) {
					// Martyr found, set the flag to true and skip adding it to the temp stack
					martyrDeleted = true;
				} else {
					// Add the martyr to the temporary stack if it's not the one to delete
					tempStack.push(currentMartyr);
				}
			}

			// If the martyr to delete was found, remove it from the martyrs stack
			if (martyrDeleted) {
				System.out.println("Deleted martyr: " + martyrName);
			} else {
				System.out.println("Martyr not found for deletion.");
			}

			// Set the modified martyrs stack back to the target node's data
			targetNode.getData().setMartyrs(tempStack);
		}
	}

	@Override
	public void updateNode(LocalDate date, Martyr oldMartyr, Martyr newMartyr) {
		Node2 targetNode = findNode(date);

		if (targetNode != null) {
			Stack martyrsStack = targetNode.getData().getMartyrs();

			// Create a temporary stack to store the updated martyrs
			Stack tempStack = new Stack();

			// Iterate over the martyrs stack and update the specified martyr
			while (!martyrsStack.isEmpty()) {
				Martyr currentMartyr = martyrsStack.pop();

				if (currentMartyr.equals(oldMartyr)) {
					// Found the martyr to update, replace it with the new martyr
					currentMartyr.setName(newMartyr.getName());
					currentMartyr.setAge(newMartyr.getAge()); // Optionally update other fields
				}
				tempStack.push(currentMartyr);
			}

			// Push the remaining martyrs back to the original stack
			while (!tempStack.isEmpty()) {
				martyrsStack.push(tempStack.pop());
			}
		}
	}

	@Override
	public Node2 findNode(DateStack data) {
		LocalDate date = data.getDate();
		if (date == null) {
			return null; // Return null if the date is null
		}
		return findNode(date, root);
	}

	@Override
	public Node2 findNode(LocalDate date) {
		if (date == null) {
			return null; // Return null if the date is null
		}
		return findNode(date, root);
	}

	private Node2 findNode(LocalDate date, Node2 node) {
		if (node == null || node.data.getDate().equals(date)) {
			return node;
		}
		if (date.isBefore(node.data.getDate())) {
			return findNode(date, node.left);
		} else {
			return findNode(date, node.right);
		}
	}

	@Override
	public void insertNode(DateStack data) {
		// TODO Auto-generated method stub
		root = insertNode(data, root);
	}

	@Override

	public void inOrderTraversal() {
		inOrderTraversal(root);
	}

	protected void inOrderTraversal(Node2 node) {
		if (node != null) {
			inOrderTraversal(node.left);
			System.out.println("Node: " + node.data + ", Left: " + (node.left != null ? node.left.data : null)
					+ ", Right: " + (node.right != null ? node.right.data : null));
			inOrderTraversal(node.right);
		}

	}

	

	@Override
	public Martyr searchMartyrByName(String name) {
		return searchMartyrByName(root, name);
	}

	public Martyr searchMartyrByName(Node2 node, String name) {
		if (node == null) {
			return null; // Martyr not found
		}

		Stack martyrsStack = node.data.getMartyrs();

		// Iterate over the martyrs stack and search for the martyr by name
		Stack tempStack = new Stack();
		Martyr foundMartyr = null;

		while (!martyrsStack.isEmpty()) {
			Martyr currentMartyr = martyrsStack.pop();

			if (currentMartyr.getName().equals(name)) {
				foundMartyr = currentMartyr;
				break; // Martyr found, exit the loop
			}

			tempStack.push(currentMartyr);
		}

		// Push the remaining martyrs back to the original stack
		while (!tempStack.isEmpty()) {
			martyrsStack.push(tempStack.pop());
		}

		return foundMartyr;
	}

}