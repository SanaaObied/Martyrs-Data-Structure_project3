package application;

public class Node2 {
DateStack data;
Node2 left,right;
int height;
static int count;
public Node2(DateStack data2) {
	super();
	this.data = data2;
	count=1;
	}
@Override
public String toString() {
	return "Node [data=" + data + ", left=" + left + ", right=" + right + "]";
}

public int getSize() {
	 int leftSize = (left != null) ? left.getSize() : 0;
	    int rightSize = (right != null) ? right.getSize() : 0;
	    return 1 + leftSize + rightSize;
}
public DateStack getData() {
	return data;
}
public void setData(DateStack data) {
	this.data = data;
}
public Node2 getLeft() {
	return left;
}
public void setLeft(Node2 left) {
	this.left = left;
}
public Node2 getRight() {
	return right;
}
public void setRight(Node2 right) {
	this.right = right;
}
public int getHeight() {
	return height;
}
public void setHeight(int height) {
	this.height = height;
}
public static int getCount() {
	return count;
}
public static void setCount(int count) {
	Node2.count = count;
}
}
 


