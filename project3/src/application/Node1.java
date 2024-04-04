package application;

public class Node1 {
	Martyr data;
Node1 left,right;
int height;
public Node1(Martyr data) {
	super();
	this.data = data;
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

}
