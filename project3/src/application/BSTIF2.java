package application;

import java.time.LocalDate;

public interface BSTIF2 extends BinaryTree2{

	void insertNode(DateStack data);

	void inOrderTraversal();

	public Node2 findNode(DateStack data);

	Node2 findNode(LocalDate date);

	// void deleteNode(LocalDate date, Martyr martyr);
	void updateNode(LocalDate date, Martyr oldMartyr, Martyr newMartyr);

	Martyr searchMartyrByName(String name);

	void deleteNode(LocalDate date, String martyrName);

}
