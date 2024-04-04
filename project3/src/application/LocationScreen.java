package application;

import java.util.Optional;



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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class LocationScreen extends Stage {
	// Declare all the necessary UI elements
	private Label locationLabel, newLocation, addLocatin;
	static TextField newLocatField, addL;
	static TextField locationField;
	static Label massege;
	public static Button addLocationButton, updateLocationButton, deleteLocationButton, searchLocationButton;
	static Button ng, pg;
	public static LocationNode searchednode;
	static Button next;
	private GridPane g = new GridPane();
	Stage location;
	ImageView imageView;
	MartyrsScreen s;
	Scene LocationScreen2;

	public LocationScreen() {

	}

	public Stage getLocationScreen() {
		HBox h2 = new HBox(10);
		ng = new Button();
		Image image2 = new Image("nextst.jpg");
		ImageView imageView2 = new ImageView(image2);
		ng.setGraphic(imageView2);
		pg = new Button();
		Image image3 = new Image("previosst.jpg");
		ImageView imageView3 = new ImageView(image3);
		pg.setGraphic(imageView3);
		Image image = new Image("109-1090359_next-page-button.png");
		ImageView imageView = new ImageView(image);

		h2.getChildren().addAll(pg, ng);
		pg.setOnAction(e -> {
			Main.locationsList.backwardButtonActionPerformed();
		});
		ng.setOnAction(e -> {
			Main.locationsList.forward();
		});
		h2.setAlignment(Pos.CENTER);
		// create a button and set its graphic to the image view
		next = new Button();
		next.setGraphic(imageView);
		locationLabel = new Label("Location:");
		newLocation = new Label("New Location");
		addLocatin = new Label("Add Location:");
		addL = new TextField();
		locationField = new TextField();
		Button add = new Button("Click Add");
		add.setVisible(false);
		Button up = new Button("click Updat");
		up.setVisible(false);
		newLocatField = new TextField();
		addLocationButton = new Button("Add Location");
		updateLocationButton = new Button("Update Location");
		deleteLocationButton = new Button("Delete Location");
		searchLocationButton = new Button("Search Location");
		addL.setVisible(false);
		addLocatin.setVisible(false);

		newLocation.setVisible(false);
		newLocatField.setVisible(false);

		massege = new Label("");
		massege.setStyle("-fx-text-fill:red");

		HBox h = new HBox(5);
		h.setAlignment(Pos.CENTER);

		g.add(locationLabel, 0, 1);
		g.add(locationField, 1, 1);
		g.add(newLocation, 0, 2);
		g.add(newLocatField, 1, 2);
		g.add(up, 2, 2);
		g.add(add, 2, 3);
		g.add(addL, 1, 3);
		g.add(addLocatin, 0, 3);

		g.add(searchLocationButton, 1, 4);

		g.add(addLocationButton, 1, 5);

		g.add(updateLocationButton, 1, 6);
		g.add(deleteLocationButton, 1, 7);
		g.add(h2, 1, 8);

		h.setAlignment(Pos.BOTTOM_LEFT);
		h.getChildren().addAll(searchLocationButton, addLocationButton, updateLocationButton, deleteLocationButton);
		g.add(h, 1, 10);
		g.add(next, 3, 12);

		g.setHgap(10);
		g.setVgap(10);

		addLocatin.setVisible(false);
		addL.setVisible(false);

		searchLocationButton.setMaxWidth(190);
		searchLocationButton.setMaxHeight(100);
		addLocationButton.setMaxWidth(190);
		addLocationButton.setMaxHeight(100);
		updateLocationButton.setMaxWidth(190);
		updateLocationButton.setMaxHeight(100);
		deleteLocationButton.setMaxWidth(190);
		deleteLocationButton.setMaxHeight(100);
		imageView.setFitWidth(60);
		imageView.setFitHeight(60);
		imageView2.setFitWidth(60);
		imageView2.setFitHeight(60);
		imageView3.setFitWidth(60);
		imageView3.setFitHeight(60);
		next.setDisable(false);
		next.setMaxWidth(90);// To determine the dimensions of image
		next.setMaxHeight(90);
		next.setAlignment(Pos.BOTTOM_RIGHT);
		next.setOnAction(e -> {
			s = new MartyrsScreen();
			s.getMartyrsScene();
			location.hide();
			next.setDisable(true);
		});

		addLocationButton.setOnAction(e -> {
			addLocatin.setVisible(true);
			addL.setVisible(true);
			add.setVisible(true);
			System.out.println(addL.getText());
			add.setOnAction(ev -> {
				Main.locationsList.insertLocationNode(new Location(addL.getText()));
				Main. comboBox.getItems().add(addL.getText());

			});
			// Main.locationsList.print();

		});
		updateLocationButton.setOnAction(e -> {
			newLocation.setVisible(true);
			newLocatField.setVisible(true);
			up.setVisible(true);

			up.setOnAction(ev -> {
				Main.locationsList.updateLocationNode(locationField.getText(), newLocatField.getText());
				Main. comboBox.getItems().remove(locationField.getText());
				Main. comboBox.getItems().add(newLocatField.getText());

;
			});

		});
		deleteLocationButton.setOnAction(e -> {
			System.out.println(locationField.getText());
			Main.locationsList.print3();
			Main.locationsList.deleteLocationNode(locationField.getText());
			Main. comboBox.getItems().remove(locationField.getText());

			locationField.clear();

			//Main.locationsList.print2();
		});
		searchLocationButton.setOnAction(e -> {
			searchednode = Main.locationsList.searchLocationNode3(LocationScreen.locationField.getText());

			// Warning.warn(SortedCircularDoublyLinkedList.massege);
		});
		locationLabel.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 13));// type of The Text
		searchLocationButton.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 12));// type of The Text
		newLocation.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 12));// type of The Text
		addLocationButton.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 12));// type of The Text
		addLocatin.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 12));// type of The Text
		updateLocationButton.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 12));// type of The Text

		deleteLocationButton.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 12));// type of The Text
		locationLabel.setPadding(new Insets(10, 15, 10, 10));// To determine the dimensions of the sides

		updateLocationButton.setStyle("-fx-background-color: white");// To determine the Color background for Button
		searchLocationButton.setStyle("-fx-background-color: white");// To determine the Color background for Button
		addLocationButton.setStyle("-fx-background-color: white");// To determine the Color background for Button
		deleteLocationButton.setStyle("-fx-background-color: white");// To determine the Color background for Button

		updateLocationButton.setDisable(false);// to be able to press it

		g.setStyle("-fx-background-color:#D3D3D3");// To determine the Color background
		g.setAlignment(Pos.CENTER);
		LocationScreen2 = new Scene(g, 800, 500);
		location = new Stage();
		location.setTitle("Location Screen");// There is a title for the window
		location.setScene(LocationScreen2);
		location.show();
		return location;

	}

	public void r() {
		// Refresh the scene and stage
		Scene LocationScreen2 = new Scene(g, 800, 500);
		location = new Stage();
		location.setTitle("Location Screen");// There is a title for the window
		location.setScene(LocationScreen2);
		location.show();
		return;
	}

	public Label getNewLocation() {
		return newLocation;
	}

	public void setNewLocation(Label newLocation) {
		this.newLocation = newLocation;
	}

	public static TextField getNewLocatField() {
		return newLocatField;
	}

	public static void setNewLocatField(TextField newLocatField) {
		LocationScreen.newLocatField.setText(null);
		;
	}

	public Button getAddLocationButton() {
		return addLocationButton;
	}

	public void setAddLocationButton(Button addLocationButton) {
		this.addLocationButton = addLocationButton;
	}

	public Button getDeleteLocationButton() {
		return deleteLocationButton;
	}

	public void setDeleteLocationButton(Button deleteLocationButton) {
		this.deleteLocationButton = deleteLocationButton;
	}

	public static void setLocationField1(TextField locationField) {
		LocationScreen.locationField = locationField;
	}

	public static Button getNg() {
		return ng;
	}

	public static void setNg(Button ng) {
		LocationScreen.ng = ng;
	}

	public static Button getPg() {
		return pg;
	}

	public static void setPg(Button pg) {
		LocationScreen.pg = pg;
	}

	public static TextField getLocationField() {
		return locationField;
	}

	public static void setLocationField(String string) {
		LocationScreen.locationField.setText(string);
	}

	public void setNext(Button next) {
		this.next = next;
	}
}