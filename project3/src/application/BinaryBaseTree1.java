package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import application.Queue.NodeQ;
import application.SortedCircularDoublyLinkedList.LocationNode;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class BinaryBaseTree1 implements BinaryTree1 {
	Node1 root;
	static StringBuilder result = new StringBuilder();

	public Node1 getRoot() {
		return root;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();

		appendTreeToString(builder, root);
		return builder.toString();
	}

	void appendTreeToString(StringBuilder builder, Node1 node) {
        if (node == null) {
            return;
        }

        builder.append("Node: ").append(node.data);

        if (node.left != null) {
            builder.append(", Left: ").append(node.left.data);
        } else {
            builder.append(", Left: null");
        }

        if (node.right != null) {
            builder.append(", Right: ").append(node.right.data);
        } else {
            builder.append(", Right: null");
        }

        builder.append("\n");

        appendTreeToString(builder, node.left);
        appendTreeToString(builder, node.right);
    }
	void apendNode(StringBuilder builder, Node1 node) {
		builder.append(node.data);
	}

	

  
    public  int countMartyrsInLocation(String location) {
    	LocationNode node=Main.locationsList.searchLocationNode3(location);
    	if(node != null) {
    		return node.getAvlOne().size();
    	}else
    		return 0;
    }
    

    public int size() {
    	return size(root);
    }
   

    private int size(Node1 node) {
        if (node == null) {
            return 0;
        }
        int leftSize = size(node.left);
        int rightSize = size(node.right);
        return 1 + leftSize + rightSize;
    }
    public String traverseLevelOrder2(Node1 tree) {
        // Check if the tree is empty
        if (tree == null) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        application.Queue queue = new application.Queue();
        int level = 1; // Initialize the level number

        // Enqueue the root node
        queue.enqueue(tree);

        // Traverse level by level
        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            result.append("\n");

            // Process all nodes at the current level
            for (int i = 0; i < levelSize; i++) {
                Node1 node = queue.dequeue();

                // Append the martyr's full information with level number to the result StringBuilder
                result.append("Level ").append(level).append(": ").append(node.data.toString()).append("; ");
                result.append("\n");
                // Enqueue the left and right children of the current node if they exist
                if (node.left != null) {
                    queue.enqueue(node.left);
                }
                if (node.right != null) {
                    queue.enqueue(node.right);
                }
            }

            result.append("\n");
            level++;
        }

        return result.toString();
    }
    
}
