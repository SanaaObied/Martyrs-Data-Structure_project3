package application;

import java.time.LocalDate;

import application.SortedCircularDoublyLinkedList.LocationNode;

public class AVLTree2 extends BST2 {

	
    // Override insertNode method to update the height and rebalance the tree
	 @Override
	    public void insertNode(DateStack data) {
	        super.insertNode(data);
	        updateHeight(root);
	        root = reBalance(root);
	 }
	    private Node2 reBalance(Node2 node) {
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
	    private Node2 rotateLeft(Node2 node) {
	        if (node == null || node.right == null) {
	            return node;
	        }
	        Node2 x = node.right;
	        node.right = x.left;
	        x.left = node;
	        updateHeight(node);
	        updateHeight(x);
	        return x;
	    }

	    // Rotate the node to the right
	    private Node2 rotateRight(Node2 node) {
	        if (node == null || node.left == null) {
	            return node;
	        }
	        Node2 x = node.left;
	        node.left = x.right;
	        x.right = node;
	        updateHeight(node);
	        updateHeight(x);
	        return x;
	    }

	    // Calculate the balance factor of the node
	    private int balanceFactor(Node2 node) {
	        if (node == null) {
	            return 0;
	        }
	        return height(node.left) - height(node.right);
	    }
	   

	    private int updateHeight(Node2 node) {
	        if (node == null) {
	            return -1; // Base case for an empty subtree
	        }

	        int leftHeight = updateHeight(node.left);
	        int rightHeight = updateHeight(node.right);

	        // Update the height of the current node
	        node.height = Math.max(leftHeight, rightHeight) + 1;

	        return node.height;
	    }

	    // Get the height of a node
	    private int height(Node2 node) {
	        return node != null ? node.height : -1;
	    }
	   
	    @Override
	    public void deleteNode(LocalDate date,String m) {
	        super.deleteNode(date, m);
	    }

	   

	    @Override
	    public Node2 findNode(DateStack data) {
	        Node2 node = super.findNode(data);
	        if (node != null) {
	            updateHeight(node);
	            return reBalance(node);
	        }
	        return null;
	    }
		public void inOrderTraversal() {
		    super.inOrderTraversal(root);
		}
	   

		public int getHeight() {
		    // Call the private helper method to calculate the height of the tree
	        return getHeight(root);
	    }

	    private int getHeight(Node2 node) {
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
		public Martyr searchMartyrByName(String name) {
		    return super.searchMartyrByName(root, name);
		}

	   

	    public String reportMaxMartyrsDate() {
	        Node2 maxMartyrsNode = findMaxMartyrsNode(root);
	        if (maxMartyrsNode != null) {
	            LocalDate date = maxMartyrsNode.data.date;
	            return  date.toString();
	        } else {
	        	return "No martyrs found in the tree.";
	        }
	    }

	    private Node2 findMaxMartyrsNode(Node2 node) {
	        if (node == null) {
	            return null;
	        }

	        Node2 maxMartyrsNode = node;

	        Node2 leftMaxMartyrsNode = findMaxMartyrsNode(node.left);
	        if (leftMaxMartyrsNode != null && leftMaxMartyrsNode.data.getMartyrCount() > maxMartyrsNode.data.getMartyrCount()) {
	            maxMartyrsNode = leftMaxMartyrsNode;
	        }

	        Node2 rightMaxMartyrsNode = findMaxMartyrsNode(node.right);
	        if (rightMaxMartyrsNode != null && rightMaxMartyrsNode.data.getMartyrCount() > maxMartyrsNode.data.getMartyrCount()) {
	            maxMartyrsNode = rightMaxMartyrsNode;
	        }

	        return maxMartyrsNode;
	    }
	    public void print2() {
	        print2(root);
	    }

	    private void print2(Node2 node) {
	        if (node != null) {
	            print2(node.left);
	            System.out.println("Node: " + node.data);
	            printMartyrs(node.data.getMartyrs());
	            print2(node.right);
	        }
	    }

	    private void printMartyrs(Stack martyrs) {
	        while (!martyrs.isEmpty()) {
	            System.out.println(" martyrs=" + martyrs.pop());
	        }
	    }

}

