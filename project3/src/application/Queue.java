package application;

import java.util.LinkedList;
import java.util.NoSuchElementException;

import application.Queue.NodeQ;
import application.SortedCircularDoublyLinkedList.LocationNode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Queue {
	 private NodeQ front; // points to the front of the queue
	    private NodeQ rear; // points to the rear of the queue
	    private int size; // number of elements in the queue

	    public Queue() {
	        front = null;
	        rear = null;
	        size = 0;
	    }

	    public boolean isEmpty() {
	        return size == 0;
	    }

	    public int size() {
	        return size;
	    }

	    public void enqueue(Node1 data) {
	        NodeQ newNode = new NodeQ(data);

	        if (isEmpty()) {
	            front = newNode;
	            rear = newNode;
	        } else {
	            rear.next = newNode;
	            rear = newNode;
	        }

	        size++;
	    }

	    public Node1 dequeue() {
	        if (isEmpty()) {
	            throw new NoSuchElementException("Queue is empty.");
	        }

	        Node1 data = front.data;

	        if (front == rear) {
	            front = null;
	            rear = null;
	        } else {
	            front = front.next;
	        }

	        size--;
	        return data;
	    }

	   

	   static class NodeQ {
	    	Node1 data;
	        NodeQ next;

	        public NodeQ(Node1 node2) {
	            this.data = node2;
	            this.next = null;
	        }
	    }
}
