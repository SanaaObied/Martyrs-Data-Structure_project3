package application;



import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;

import application.Queue.NodeQ;
import application.SortedCircularDoublyLinkedList.LocationNode;
import application.Stack.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import java.util.Queue;
public class SortedCircularDoublyLinkedList {

	Stage s = new Stage();
	private LocationNode first;
	private LocationNode last;
	private int size;

	public SortedCircularDoublyLinkedList() {
		first = null;
		last = null;
		size = 0;
	}

	// Node class representing each location
	public class LocationNode {
		AVLTree1 avlOne;
		AVLTree2 avlTwo;
		Location locat;
		private LocationNode prev;
		private LocationNode next;

		public LocationNode(Location location) {
			avlOne = new AVLTree1();
			avlTwo = new AVLTree2();
			this.locat = location;
			this.prev = null;
			this.next = null;
		}

		public LocationNode getPrev() {
			return prev;
		}

		public Location getLocation() {
			return locat;
		}

		public void setLocat(Location locat) {
			this.locat = locat;
		}

		public AVLTree1 getAvlOne() {
			return avlOne;
		}

		public void setAvlOne(AVLTree1 avlOne) {
			this.avlOne = avlOne;
		}

		public AVLTree2 getAvlTwo() {
			return avlTwo;
		}

		public void setAvlTwo(AVLTree2 avlTwo) {
			this.avlTwo = avlTwo;
		}

		public void setPrev(LocationNode prev) {
			this.prev = prev;
		}

		public LocationNode getNext() {
			return next;
		}

		public void setNext(LocationNode next) {
			this.next = next;
		}

		public void setLocation(Location location) {
			this.locat = location;
		}
	}

	// Insert a new location node in a sorted order
	public void insertLocationNode(Location location) {
		LocationNode newNode = new LocationNode(location);
		if (size == 0) {
			// If list is empty, make the new node the first and last node
			first = newNode;
			last = newNode;
			first.setPrev(last);
			first.setNext(last);
			last.setPrev(first);
			last.setNext(first);
		} else {
			LocationNode current = first;
			while (current != last && location.compareTo(current.getLocation()) > 0) {
				current = current.getNext();
			}
			if (location.compareTo(current.getLocation()) > 0) {
				// Insert at the end of the list
				current.setNext(newNode);
				newNode.setPrev(current);
				newNode.setNext(first);
				first.setPrev(newNode);
				last = newNode;
			} else if (current == first) {
				// Insert at the beginning of the list
				newNode.setPrev(last);
				newNode.setNext(current);
				current.setPrev(newNode);
				first = newNode;
				last.setNext(newNode);
			} else {
				// Insert in the middle of the list
				current.getPrev().setNext(newNode);
				newNode.setPrev(current.getPrev());
				newNode.setNext(current);
				current.setPrev(newNode);
			}
		}
		size++;
	}

	// Update the name of a location node
	public void updateLocationNode(String oldName, String newName) {
		LocationNode current = first;
		do {
			if (current.getLocation().getName().equals(oldName)) {
				// Update the name of the location
				current.getLocation().setName(newName);
				return;
			}
			current = current.getNext();
		} while (current != first);
	}

	// Delete a location node by name
	public boolean deleteLocationNode(String element) {
		if (size == 0)
			return false;
		if (element.equals(first.getLocation().getName())) {
			// Delete the first node
			if (size == 1) {
				first = last = null;
			} else {
				LocationNode c = first;
				first = first.getNext();
				first.setPrev(last);
				c = null;
			}
			size--;
			return true;
		}
		if (element.equals(last.getLocation().getName())) {
			// Delete the last node
			if (size == 1) {
				first = last = null;
			} else {
				LocationNode current = first;
				for (int i = 0; i < size; i++) {
					current = current.getNext();
				}
				last.setPrev(current.getPrev());
				last = current;
				last.setNext(first);
			}
			size--;
			return true;
		} else {
			LocationNode current = first.getNext();
			for (int i = 0; i < size; i++) {
				if (current.getLocation().getName().equals(element)) {
					return remove(i);
				}
			}
		}
		return false;
	}

	// Remove a location node by index
	public boolean remove(int index) {
		LocationNode prev = first;

		if (index <= 0 || index > size) {
			return false;
		} else {
			LocationNode current = first;
			for (int i = 1; i < index; i++) {
				prev = current;
				current = current.getNext();
			}
			prev.setNext(current.getNext());
			current.getNext().setPrev(prev);
			size--;
			return true;
		}
	}

	// Search for a location node by name
	public LocationNode searchLocationNode3(String name) {
		if (first == null) {
			return null;
		}
		LocationNode current = first;
		do {
			if (current.locat != null && current.locat.getName().trim() != null && current.locat.getName().trim().equals(name.trim())) {
				return current;
			}
			current = current.next;
		} while (current != first); // Check if the loop has come back to the first node
		return null;
	}

	
	
	
	public void print3() {
	    if (size == 0) {
	        System.out.println("List is empty.");
	    } else {
	        LocationNode current = first;
	        int i = 1; // Initialize the loop variable
	        do {
	            System.out.println("Location: " + current.getLocation().getName());
	            AVLTree1 avlOne = current.getAvlOne();
	            if (avlOne != null && avlOne.getRoot() != null) {
	                avlOne.inOrderTraversal();
	            } else {
	                System.out.println("No martyrs found in AVL one for this location.");
	            }
	            
	            current = current.getNext();
	            i++; // Increment the loop variable
	        } while (current != first);
	    }
	}
	public void print4() {
	    if (size == 0) {
	        System.out.println("List is empty.");
	    } else {
	        LocationNode current = first;
	        int i = 1; // Initialize the loop variable
	        do {
	            System.out.println("Location: " + current.getLocation().getName());
	            AVLTree2 avlOne = current.getAvlTwo();
	            if (avlOne != null && avlOne.getRoot() != null) {
	                avlOne.inOrderTraversal();
	            } else {
	                System.out.println("No martyrs found in AVL one for this location.");
	            }
	            
	            current = current.getNext();
	            i++; // Increment the loop variable
	        } while (current != first);
	    }
	}
	// Get the size of the list
	public int size() {
		if (first == null) {
			return 0;
		}
		int count = 0;
		LocationNode current = first;
		do {
			count++;
			current = current.next;
		} while (current != first);
		return count;
	}

	// Move forward to the next location node
	public void forward() {
		if (first == null) {
			LocationScreen.getNg().setDisable(true);
			return; // Empty list
		}
		first = first.getNext();
		Location data = first.locat;
		LocationScreen.locationField.setText(data.getName());
	}

	// Move backward to the previous location node
	public void backwardButtonActionPerformed() {
		if (first == null) {
			LocationScreen.getPg().setDisable(true);
			return; // Empty list
		}
		first = first.getPrev(); // Move to previous node
		if (first == null) {
			// At the end of the list, move to the last node
			LocationNode last = getLastNode();
			if (last != null) {
				first = last;
			} else {
				LocationScreen.locationField.setText("");
				return;
			}
		}
		Location data = first.locat;
		LocationScreen.locationField.setText(data.getName());
	}

	private LocationNode getLastNode() {
		if (first == null) {
			return null;
		}
		LocationNode current = first.getPrev();
		while (current != null && current != first) {
			if (current.locat != null) {
				return current;
			}
			current = current.getPrev();
		}
		return null;
	}

	
	public void printToFile() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save Data");
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Text Files", "*.txt"));
		Stage stage = new Stage();
		File outputFile = fileChooser.showSaveDialog(stage);

		if (outputFile != null) {
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
				if (size == 0) {
					writer.write("List is empty.");
				} else {
					LocationNode current = first;
					int i = 1; // Initialize the loop variable
					do {
						AVLTree1 avlOne = current.getAvlOne();
						if (avlOne != null && avlOne.getRoot() != null) {
							// writer.write("Location: " + current.getLocation().toString());
							// writer.newLine();
							avlOne.inOrderTraversalToFile(writer);
						}

						current = current.getNext();
						i++; // Increment the loop variable
					} while (current != first);
				}

				writer.close();
				showAlert("Data saved to file successfully.", AlertType.INFORMATION);
			} catch (IOException e) {
				showAlert("Error occurred while saving data to file: " + e.getMessage(), AlertType.ERROR);
			}
		} else {
			showAlert("File not selected.", AlertType.WARNING);
		}
	}

	void showAlert(String message, AlertType alertType) {
		Alert alert = new Alert(alertType);
		alert.setTitle("Information");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

	public LocationNode getFirst() {
		return first;
	}

	public LocationNode get(Location x) {
		LocationNode current = getFirst();
		while (current != null) {
			String name = x.getName();
			if (current.getLocation().equals(name)) {
				return current;
			}
			current = current.getNext();
		}
		return null;
	}

	public void setFirst(LocationNode newNode) {
		this.first = newNode;

	}

	public Location getLocation(int index) {
		int i = 0;
		LocationNode current = first;
		while (current != null && current != first && i < index) {
			current = current.getNext();
			i++;
		}
		if (i == index && current != null && current.getLocation() != null) {
			return current.getLocation();
		}
		return null;
	}

	private LocationNode current; // Keep track of the current node

	public LocationNode getNextLocationNode() {
		if (current == null) {
			current = first;
		} else {
			current = current.getNext();
			if (current == first) {
				current = null; // Reached the end of the list
			}
		}
		return current;
	}

	public Location getLocation(String name) {
		LocationNode node = searchLocationNode3(name);
		if (node != null) {
			return node.getLocation();
		}
		return null;
	}

	public LocationNode get(int i) {
	    if (i < 0 || i >= size) {
	        return null;
	    }
	    LocationNode current = first;
	    int count = 0;
	    while (count < i) {
	        current = current.getNext();
	        count++;
	    }
	    return current;
	} public  int getNumOfMartyrsInLocation(String locationName) {
        LocationNode locationNode = searchLocationNode3(locationName);
        if (locationNode != null) {
            AVLTree1 avlOne = locationNode.getAvlOne();
            if (avlOne != null) {
                return avlOne.size();
            }
        }
        return 0;
    }
	/*public String traverseLevelOrder2(String location) {
	    StringBuilder result = new StringBuilder();

	    if (size == 0) {
	        result.append("List is empty.");
	        return result.toString();
	    }

	    LocationNode current = first;
	    int level = 1;
	    int maxResultSize = 100000; // Maximum number of rows before truncation

	    while (current != null) {
	        if (current.getLocation().equals(location)) {
	            AVLTree1 avlOne = current.getAvlOne();
	            if (avlOne != null && avlOne.getRoot() != null) {
	                Node1 root = avlOne.getRoot();
	                int currentSize = 1;
	                int nextLevelSize = 0;

	                while (root != null) {
	                    result.append("Level ").append(level).append(":");

	                    for (int i = 0; i < currentSize; i++) {
	                        result.append("\t").append(root.data.toString());
	                        if (i < currentSize - 1) {
	                            result.append("\t|");
	                        }
	                        if (root.left != null) {
	                            root = root.left;
	                            nextLevelSize++;
	                        } else if (root.right != null) {
	                            root = root.right;
	                            nextLevelSize++;
	                        } else {
	                            root = null;
	                        }
	                    }

	                    result.append("\n");
	                    currentSize--;

	                    if (currentSize == 0) {
	                        currentSize = nextLevelSize;
	                        nextLevelSize = 0;
	                        level++;

	                        if (getLineCount(result.toString()) >= maxResultSize) {
	                            // Clear the StringBuilder and add a message indicating truncation
	                            result.setLength(0);
	                            result.append("Result truncated due to size limitations.");
	                            break;
	                        }
	                    }

	                    if (getLineCount(result.toString()) >= maxResultSize) {
	                        // Clear the StringBuilder and add a message indicating truncation
	                        result.setLength(0);
	                        result.append("Result truncated due to size limitations.");
	                        break;
	                    }
	                }
	            }
	        }

	        current = current.getNext();
	    }

	    return result.toString();
	}

	private int getLineCount(String text) {
	    return text.split("\r\n|\r|\n").length;
	}*/
	

	
	

	
}