package application;

public class BST1 extends BinaryBaseTree1 implements BSTIF1 {

	@Override
	public void insert(Martyr data) {
		root = insertNode(data, root);
	}

	public Node1 insertNode(Martyr data, Node1 node) {
		if (node == null) {
			return new Node1(data);
		}
		if (data.getName().compareTo(node.data.getName()) < 0) {
			node.left = insertNode(data, node.left);
		} else {
			node.right = insertNode(data, node.right);
		}

		return node;
	}

	@Override
	public void deleteNode(Martyr data) {
		root = deleteNode(data, root);
	}

	public Node1 deleteNode(Martyr data, Node1 node) {
		if (node == null) {
			return null;
		}

		Martyr martyr = (Martyr) data;
		int comparison = martyr.compareTo2((Martyr) node.data); // Compare by name
		if (comparison > 0) {
			node.right = deleteNode(data, node.right);
		} else if (comparison < 0) {
			node.left = deleteNode(data, node.left);
		} else if (node.left == null && node.right == null) {
			node = null;
		} else if (node.left == null) {
			node = node.right;
		} else if (node.right == null) {
			node = node.left;
		} else {
			deleteNodeWithTwoChildren(node);
		}
		return node;
	}

	private void deleteNodeWithTwoChildren(Node1 node) {
		Node1 inOrderSuccess = findMin(node.right);
		node.data = inOrderSuccess.data;
		node.right = deleteNode(inOrderSuccess.data, inOrderSuccess);
	}

	private Node1 findMin(Node1 node) {
		if (node == null) {
			return null;
		} else if (node.left == null) {
			return node;
		}
		return findMin(node.left);
	}

	@Override
	public String toString() {
		return "BST [root=" + root + ", getRoot()=" + getRoot() + ", toString()=" + super.toString() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + "]";
	}

	public void printInOrder() {
		printInOrder(root);
	}

	private void printInOrder(Node1 node) {
		if (node != null) {
			printInOrder(node.left);
			Martyr martyr = (Martyr) node.data;
			System.out.println(martyr.getName() + "," + martyr.getAge() + "," + martyr.getLocation().toString() + ","
					+ martyr.getDateOfDeath() + "," + martyr.getGender());
			printInOrder(node.right);
		}
	}

	@Override
	public Node1 findNode(String name) {
		return findNode(name, root);
	}

	public Node1 findNode(String name, Node1 node) {
		if (node == null || ((Martyr) node.data).getName().equals(name)) {
			return node;
		}

		int comparison = name.compareTo(((Martyr) node.data).getName());
		if (comparison < 0) {
			return findNode(name, node.left);
		} else {
			return findNode(name, node.right);
		}
	}

	@Override
	public void updateNode(String name, Martyr newData) {
		Node1 node = findNode(name);
		if (node != null) {
			node.data = newData;
		}
	}

}

