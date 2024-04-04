package application;

import java.util.EmptyStackException;

public class Stack {
	class Node {
        Martyr data;
        Node next;
      
        public Node(Martyr data) {
            this.data = data;
           
        }
    }

    private Node top;

    public void push(Martyr martyrs) {
        Node newNode = new Node(martyrs);
        newNode.next = top;
        top = newNode;
    }

    public Martyr pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        Martyr poppedData = top.data;
        top = top.next;
        return poppedData;
    }
    public Node pop2() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        Node poppedNode = top;
        top = top.next;
        return poppedNode;
    }
    public Martyr peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return top.data;
    }
    public Node peekNode() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return top;
    }
    public boolean isEmpty() {
        return top == null;
    }

    public void clear() {
        top = null;
    }
    public String printStack() {
        StringBuilder stackString = new StringBuilder();
        Node current = top;
        
        while (current != null) {
            stackString.append(current.data.toString()).append("\n");
            current = current.next;
        }
        
        return stackString.toString();
    }
    
}
