package application;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import application.Main.myEvent;

import application.SortedCircularDoublyLinkedList.LocationNode;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MartyrsScreen extends Stage {
	private Button addMartyrButton, updateMartyrButton, deleteMartyrButton, searchMartyrButton, cancel, ng;
	static TableView<Martyr> tableView;
	static ObservableList<Martyr> martyrList;

	private Button next;
	Stage martry;
	Scene martyrs;

	reportMat r = new reportMat();
	reportMat r2 = new reportMat();

	public Stage getMartyrsScene() {
		
		martyrList = FXCollections.observableArrayList(); // Initialize martyrList
		updateTableView2();

		// Create table columns
		TableColumn<Martyr, String> LocatioColumn = new TableColumn<>("Location");
		LocatioColumn.setCellValueFactory(new PropertyValueFactory<>("Location"));
		
		TableColumn<Martyr, String> nameColumn = new TableColumn<>("Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

		TableColumn<Martyr, Integer> ageColumn = new TableColumn<>("Age");
		ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));

		TableColumn<Martyr, String> genderColumn = new TableColumn<>("Gender");
		genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));

		TableColumn<Martyr, LocalDate> dateColumn = new TableColumn<>("Date of Death");
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfDeath"));

		// Create table view and set columns
		tableView = new TableView<>();
		tableView.getColumns().addAll(LocatioColumn,nameColumn, ageColumn, genderColumn, dateColumn);
		dateColumn.setPrefWidth(120);
		nameColumn.setPrefWidth(120);
		ageColumn.setPrefWidth(120);
		genderColumn.setPrefWidth(120);
		tableView.setMaxSize(600, 500);
		tableView.setItems(martyrList);
		// print2();
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		dateColumn.setCellFactory(column -> new TableCell<Martyr, LocalDate>() {
			@Override
			protected void updateItem(LocalDate date, boolean empty) {
				super.updateItem(date, empty);
				if (date == null || empty) {
					setText(null);
				} else {
					setText(dateFormatter.format(date));
				}
			}
		});
		//updateTableView2();
		addMartyrButton = new Button("Add Martyr");
		updateMartyrButton = new Button("Update Martyr");
		deleteMartyrButton = new Button("Delete Martyr");
		searchMartyrButton = new Button("Search Martyr");
		cancel = new Button("");
		Image image6 = new Image("homePage.jpg");

		ImageView imageView6 = new ImageView(image6);
		cancel.setGraphic(imageView6);

		Button save = new Button();
		Image image4 = new Image("saveFile.png");
		ImageView imageView4 = new ImageView(image4);
		save.setGraphic(imageView4);
		// Create layout containers
		HBox buttonBox = new HBox(10);
		buttonBox.getChildren().addAll(searchMartyrButton, addMartyrButton, updateMartyrButton, deleteMartyrButton,
				save, cancel);
		buttonBox.setAlignment(Pos.CENTER);
		cancel.setOnAction(e -> {
			martry.close();
		});
		VBox root = new VBox(10);

		HBox h = new HBox(10);
		Button nextButton = new Button();
		Button previousButton = new Button();
		Image image3 = new Image("nex.png");
		ImageView imageView3 = new ImageView(image3);
		nextButton.setGraphic(imageView3);

		Image image2 = new Image("pri.png");
		ImageView imageView2 = new ImageView(image2);
		previousButton.setGraphic(imageView2);
		h.setAlignment(Pos.CENTER);
		h.getChildren().addAll(previousButton, nextButton);
		root.getChildren().addAll( tableView, buttonBox);
		root.setPadding(new Insets(10));
		imageView3.setFitWidth(60);
		imageView3.setFitHeight(60);

		imageView2.setFitWidth(60);
		imageView2.setFitHeight(60);

		imageView4.setFitWidth(100);
		imageView4.setFitHeight(80);

		imageView6.setFitWidth(100);
		imageView6.setFitHeight(80);

		Image image = new Image("109-1090359_next-page-button.png");
		ImageView imageView = new ImageView(image);

		// create a button and set its graphic to the image view
		next = new Button();
		next.setGraphic(imageView);
		imageView.setFitWidth(60);
		imageView.setFitHeight(60);

		next.setMaxWidth(90);// To determine the dimensions of image
		next.setMaxHeight(90);
		next.setAlignment(Pos.BOTTOM_RIGHT);

		searchMartyrButton.setMaxWidth(190);
		searchMartyrButton.setMaxHeight(110);
		updateMartyrButton.setMaxWidth(190);
		updateMartyrButton.setMaxHeight(110);
		addMartyrButton.setMaxWidth(190);
		addMartyrButton.setMaxHeight(110);
		deleteMartyrButton.setMaxWidth(190);
		deleteMartyrButton.setMaxHeight(110);

		deleteMartyrButton.setOnAction(e -> {
		    List<Martyr> selectedMartyrs = new ArrayList<>(tableView.getSelectionModel().getSelectedItems());

		    for (Martyr selectedMartyr : selectedMartyrs) {
		        String location = selectedMartyr.getLocation();
		        LocationNode locationNode = Main.locationsList.searchLocationNode3(location);

		        if (locationNode != null) {
		            AVLTree1 avlOne = locationNode.getAvlOne();
		            AVLTree2 avlTwo = locationNode.getAvlTwo();

		            LocalDate martyrDate = selectedMartyr.getDateOfDeath();

		            // Delete the martyr from AVLTree1
		            avlOne.deleteNode(selectedMartyr);

		            // Delete the martyr from AVLTree2
		            avlTwo.deleteNode(martyrDate, selectedMartyr.getName());

		            // Remove the martyr from martyrList
		            martyrList.remove(selectedMartyr);

		            System.out.println("Deleted martyr: " + selectedMartyr.getName());
		        }
		    }

		    // Print the updated AVLTree2
		    Main.locationsList.print4();
		});
		next.setOnAction(e -> {
			

			martry.hide();
		});
		searchMartyrButton.setOnAction(e -> {
		    Martyr selectedMartyr = tableView.getSelectionModel().getSelectedItem();
		    if (selectedMartyr != null) {
		        String martyrName = selectedMartyr.getName();
		        String location = selectedMartyr.getLocation();

		        AVLTree1 avlOne = Main.locationsList.searchLocationNode3(location).getAvlOne();
		        AVLTree2 avlTwo = Main.locationsList.searchLocationNode3(location).getAvlTwo();

		        Node1 foundNode1 = avlOne.findNode(martyrName);
		        Martyr foundMartyr2 = avlTwo.searchMartyrByName(martyrName);

		        if (foundNode1 != null) {
		            System.out.println("Matching node found in AVLTree1 for Search Martyrs by Name: " + martyrName);
		        } else {
		            System.out.println("No matching node found in AVLTree1 for Search Martyrs by Name: " + martyrName);
		        }

		        if (foundMartyr2 != null) {
		            System.out.println("Matching martyr found in AVLTree2 for Search Martyrs by Name: " + martyrName);
		        } else {
		            System.out.println("No matching martyr found in AVLTree2 for Search Martyrs by Name: " + martyrName);
		        }

		        Alert alert = new Alert(Alert.AlertType.INFORMATION);
		        alert.setTitle("Search Result");
		        alert.setHeaderText(null);
		        alert.setContentText("Matching martyrs found for Search Martyrs by Name: " + martyrName);
		        alert.showAndWait();
		    }
		});

		
		updateMartyrButton.setOnAction(e -> {
			r.getupdate();
		});
		addMartyrButton.setOnAction(e -> {
			r2.getAdd();
		});

		searchMartyrButton.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 12));// type of The Text
		addMartyrButton.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 12));// type of The Text
		updateMartyrButton.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 12));// type of The Text
		deleteMartyrButton.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 12));
		cancel.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 12));

		
		
		// Create layout
		save.setOnAction(e -> {
			Main.locationsList.printToFile();
		});
		cancel.setMaxHeight(200);
		previousButton.setMaxHeight(200);
		nextButton.setMaxHeight(200);
		searchMartyrButton.setStyle("-fx-background-color: white");// To determine the Color background for Button
		cancel.setStyle("-fx-background-color: white");// To determine the Color background for Button
		addMartyrButton.setStyle("-fx-background-color: white");// To determine the Color background for Button
		updateMartyrButton.setStyle("-fx-background-color: white");// To determine the Color background for Button
		deleteMartyrButton.setStyle("-fx-background-color: white");// To determine the Color background for Button
		save.setStyle("-fx-background-color: white");// To determine the Color background for Button

		root.setStyle("-fx-background-color:#D3D3D3");// To determine the Color background
		root.setAlignment(Pos.CENTER);
		martyrs = new Scene(root, 900, 600);
		martry = new Stage();
		martry.setTitle("Martyrs Screen");// There is a title for the window
		martry.setScene(martyrs);
		martry.show();
		return martry;

	}

	
	public void updateTableView2() {
	    martyrList.clear(); // Clear the martyrList before adding new data

	    LocationNode current = Main.locationsList.getFirst();
        int i = 1; // Initialize the loop variable
        do {
            AVLTree1 avlOne = current.getAvlOne();
            if (avlOne != null && avlOne.getRoot() != null) {
           
                avlOne.inOrderTraversal(martyrList);
            }

            current = current.getNext();
            i++; // Increment the loop variable
        } while (current != Main.locationsList.getFirst());


		    if (tableView == null) {
		        tableView = new TableView<>();
		        TableColumn<Martyr, String> nameColumn = new TableColumn<>("Name");
		        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

		        TableColumn<Martyr, Integer> ageColumn = new TableColumn<>("Age");
		        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));

		        TableColumn<Martyr, String> genderColumn = new TableColumn<>("Gender");
		        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));

		        TableColumn<Martyr, LocalDate> dateColumn = new TableColumn<>("Date of Death");
		        dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfDeath"));

		        tableView.getColumns().addAll(nameColumn, ageColumn, genderColumn, dateColumn);
		    }

		    tableView.setItems(martyrList);
		    tableView.getSelectionModel().selectFirst();}
	

}
