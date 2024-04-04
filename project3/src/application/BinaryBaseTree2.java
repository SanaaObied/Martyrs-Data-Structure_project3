package application;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import application.SortedCircularDoublyLinkedList.LocationNode;
import application.Stack.Node;

public class BinaryBaseTree2 implements BinaryTree2 {
	Node2 root;
	StringBuilder backward = new StringBuilder();

	public Node2 getRoot() {
		return root;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();

		appendTreeToString(builder, root);
		return builder.toString();
	}

	void appendTreeToString(StringBuilder builder, Node2 node) {
        if (node == null) {
            return;
        }

        builder.append("Node: ").append(node.data);

        if (node.left != null) {
            builder.append(", Left: ").append(node.left.data);
        } else {
            builder.append(", Left: null"+"\n");
        }

        if (node.right != null) {
            builder.append(", Right: ").append(node.right.data);
        } else {
            builder.append(", Right: null"+"\n");
        }

        builder.append("\n");

        appendTreeToString(builder, node.left);
        appendTreeToString(builder, node.right);
    }
	void apendNode(StringBuilder builder, Node2 node) {
		builder.append(node.data);
	}

	
	public  int countMartyrsInLocation(String location) {
    	LocationNode node=Main.locationsList.searchLocationNode3(location);
    	if(node != null) {
    		return node.avlTwo.size();
    	}else
    		return 0;
    }
    

    public int size() {
    	return size(root);
    }
    private int size(Node2 node) {
        if (node == null) {
            return 0;
        }
        return node.getSize();
    }
   /* public String traverseBackwardAndGetTable(String location) {
        StringBuilder backward = new StringBuilder();
        LocationNode locationNode = Main.locationsList.searchLocationNode3(location);
        if (locationNode != null) {
            appendMartyrInfoBackward(backward, locationNode.getAvlTwo().getRoot(), new HashSet<>());
        }
        return backward.toString();
    }

    private void appendMartyrInfoBackward(StringBuilder builder, Node2 node, Set<Martyr> processedMartyrs) {
        if (node == null) {
            return;
        }

        appendMartyrInfoBackward(builder, node.getRight(), processedMartyrs);

        Stack martyrs = node.getData().getMartyrs();

        Stack tempStack = new Stack();

        // Reverse the order of martyrs in the stack using a temporary stack
        while (!martyrs.isEmpty()) {
            tempStack.push(martyrs.pop());
        }

        // Process the martyrs in the correct order
        while (!tempStack.isEmpty()) {
            Martyr currentMartyr = tempStack.pop();

            if (!processedMartyrs.contains(currentMartyr)) {
                builder.append(currentMartyr.toString()).append("\n");
                processedMartyrs.add(currentMartyr);
            }
        }

        appendMartyrInfoBackward(builder, node.getLeft(), processedMartyrs);
    }*/
    public String traverseBackwardAndGetTable(String location) {
        StringBuilder backward = new StringBuilder();
        LocationNode locationNode = Main.locationsList.searchLocationNode3(location);
        if (locationNode != null) {
            appendMartyrInfoBackward(backward, locationNode.getAvlTwo().getRoot());
        }
        return backward.toString();
    }

    private void appendMartyrInfoBackward(StringBuilder builder, Node2 node) {
        if (node == null) {
            return;
        }

        appendMartyrInfoBackward(builder, node.getRight());

        Stack martyrs = node.getData().getMartyrs();

        Stack tempStack = new Stack();

        // Reverse the order of martyrs in the stack using a temporary stack
        while (!martyrs.isEmpty()) {
            tempStack.push(martyrs.pop());
        }

        // Process the martyrs in the correct order
        while (!tempStack.isEmpty()) {
            Martyr currentMartyr = tempStack.pop();
            builder.append(currentMartyr.toString()).append("\n");
        }

        appendMartyrInfoBackward(builder, node.getLeft());
    }
	
   

    }
