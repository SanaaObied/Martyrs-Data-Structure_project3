package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import application.SortedCircularDoublyLinkedList.LocationNode;
import application.Stack.Node;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class AVLTree1 extends BST1 {
	static StringBuilder sb = new StringBuilder();
    // Override insertNode method to update the height and rebalance the tree

	@Override
	public Node1 insertNode(Martyr data, Node1 node) {
		node = super.insertNode(data, node);
		updateHeight(node);
		return reBalance(node);
	}
    // Rebalance the tree based on the balance factor of the node

	private Node1 reBalance(Node1 node) {
		int bf = balanceFactor(node);
		if (bf > 1) {
			if (balanceFactor(node.left) >= 0) {
				node = rotateRight(node);
			} else {
				node.left = rotateLeft(node.left);
				node = rotateRight(node);
			}
		}
		if (bf < -1) {
			if (balanceFactor(node.right) <= 0) {
				node = rotateLeft(node);
			} else {
				node.right = rotateRight(node.right);
				node = rotateLeft(node);
			}
		}
		return node;
	}
    // Rotate the node to the left
	private Node1 rotateLeft(Node1 node) {
		if (node == null || node.right == null) {
			return node;
		}
		Node1 x = node.right;
		node.right = x.left;
		x.left = node;
		updateHeight(node);
		updateHeight(x);
		return x;
	}

    // Rotate the node to the right
	private Node1 rotateRight(Node1 node) {
		if (node == null || node.left == null) {
			return node;
		}
		Node1 x = node.left;
		node.left = x.right;
		x.right = node;
		updateHeight(node);
		updateHeight(x);
		return x;
	}
    // Calculate the balance factor of the node

	private int balanceFactor(Node1 node) {
		if (node == null) {
			return 0;
		}
		int bf = height(node.left) - height(node.right);
		return bf;
	}

	// In-order traversal of AVLTree1
	public void inOrderTraversal() {
		inOrderTraversal(root, "");
	}

	private void inOrderTraversal(Node1 node, String location) {
		if (node != null) {
			inOrderTraversal(node.left, location);
            // Perform necessary operations on each node
			System.out.println(node.data.toString());

			inOrderTraversal(node.right, location);
		}
	}
    // Update the height of a node
	private void updateHeight(Node1 node) {
		if (node != null) {
			int leftChildHeight = height(node.left);
			int rightChildHeight = height(node.right);
			node.height = Math.max(leftChildHeight, rightChildHeight) + 1;
		}
	}
    // Get the height of a node
	private int height(Node1 node) {
		return node != null ? node.height : -1;
	}
    // Override deleteNode method to update the height and rebalance the tree
	@Override
	public Node1 deleteNode(Martyr data, Node1 node) {
		node = super.deleteNode(data, node);
		updateHeight(node);
		return reBalance(node);
	}
    // Override updateNode method to update the height and rebalance the tree
	@Override
	public void updateNode(String name, Martyr newData) {
		super.updateNode(name, newData);
		updateHeight(root); // Update the height after updating the node
		root = reBalance(root); // Rebalance the tree after updating the node
	}

	@Override
	public Node1 findNode(String name) {
		Node1 node = super.findNode(name);
		updateHeight(node); // Update the height of the found node
		return reBalance(node); // Rebalance the tree after finding the node
	}

	
	public int getHeight() {
	    // Call the private helper method to calculate the height of the tree
        return getHeight(root);
    }

    private int getHeight(Node1 node) {
        // Check if the current node is null
        if (node == null) {
            return -1;
        }
        // Check if the current node is a leaf node
        if (node.left == null && node.right == null) {
            return 0; // Node is a leaf, so its height is 0
        }
        // Recursively calculate the height of the left and right subtrees
        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);
        // Return the maximum height of the left and right subtrees plus 1
        return Math.max(leftHeight, rightHeight) + 1;
    }

	
  
	public int countMartyrsInLocation(String location) {
	    // Search for the location node in the locations list
	    LocationNode node = Main.locationsList.searchLocationNode3(location);
	    // Check if the location node is found
	    if (node != null) {
	        AVLTree1 avlOne = node.getAvlOne();
	        // Check if AVL one exists and has nodes
	        if (avlOne != null && avlOne.getRoot() != null) {
	            return avlOne.size();
	        } else {
	            // AVL one is empty for this location
	            return 0;
	        }
	    } else {
	        // Location not found
	        return 0;
	    }
	}
	public int getHeightAVL1(String location) {
	    // Search for the location node in the locations list
	    LocationNode node = Main.locationsList.searchLocationNode3(location);
	    if (node == null) {
	        return -1; // Return -1 when the node is null
	    } else {
	        return node.avlOne.height(root);
	    }
	}
	public void printLocationHeightAVL1(String location) {
	    // Search for the location in the locationsList
	    LocationNode node = Main.locationsList.searchLocationNode3(location);
	    if (node == null) {
	        System.out.println("Location not found.");
	    } else {
	        // Retrieve the height of the AVL tree in the node
	        int height = node.avlOne.height(node.avlOne.root);
	        System.out.println("Location: " + location + " | Height: " + height);
	    }
	}

	public void printHeight() {
	    // Print the height of the AVL tree rooted at the root node
	    System.out.println("Height of the AVL tree: " + getHeight(root));
	}

	

	

	
	public String printInOrderHelper(ObservableList<Martyr> martyrList) {
	    // Perform an in-order traversal of the AVL tree rooted at the root node
		printInOrderHelper(root, martyrList, sb);
		return sb.toString();
	}
	// Helper method to perform in-order traversal of the AVL tree
	void printInOrderHelper(Node1 node, ObservableList<Martyr> martyrList, StringBuilder sb) {
		if (node != null) {
	        // Traverse the left subtree
			printInOrderHelper(node.left, martyrList, sb);
	        // Process the current node
			Martyr martyr = (Martyr) node.data;
			martyrList.add(martyr);
			sb.append(martyr.getName()).append(",").append(martyr.getAge()).append(",")
					.append(martyr.getLocation().toString()).append(",").append(martyr.getDateOfDeath()).append(",")
					.append(martyr.getGender()).append("\n");
	        // Traverse the right subtree
			printInOrderHelper(node.right, martyrList, sb);
		}
	}

	public void inOrderTraversalToFile(BufferedWriter writer) throws IOException {
	    // Perform in-order traversal of the AVL tree and write data to a file
		inOrderTraversalToFile(root, writer);
	}
	// Helper method to perform in-order traversal and write data to a file
	private void inOrderTraversalToFile(Node1 node, BufferedWriter writer) throws IOException {
		if (node != null) {
	        // Traverse the left subtree
			inOrderTraversalToFile(node.left, writer);
	        // Process the current node
			Martyr martyr = (Martyr) node.data;
			LocalDate dateOfDeath;
			try {
				dateOfDeath = martyr.getDateOfDeath();
			} catch (DateTimeParseException e) {
				System.out.println("Invalid date format: " + martyr.getDateOfDeath());
				return; // Skip writing the invalid data to the file
			}

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
			String formattedDate = dateOfDeath.format(formatter);
	        // Write martyr data to the file
			writer.write(martyr.getName() + "," + martyr.getAge() + "," + martyr.getLocation().toString() + ","
					+ formattedDate + "," + martyr.getGender());
			writer.newLine();
	        // Traverse the right subtree
			inOrderTraversalToFile(node.right, writer);
		}
	}

	public void inOrderTraversal(List<Martyr> martyrList) {
		if (root == null)
			return;

		Queue stack = new Queue();
		Node1 current = root;

		while (current != null || !stack.isEmpty()) {
			while (current != null) {
	            // Traverse the left subtree and enqueue nodes
				stack.enqueue(current);
				current = current.left;
			}

			current = stack.dequeue();
	        // Process the current node
			martyrList.add(current.data);
			current = current.left;
		}
	}

}
