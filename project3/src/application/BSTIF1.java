package application;

import javafx.collections.ObservableList;

public interface BSTIF1 extends BinaryTree1 {

	void insert(Martyr data);

	void deleteNode(Martyr data);

	void printInOrder();

	Node1 findNode(String name);

	void updateNode(String name, Martyr newData);

}
