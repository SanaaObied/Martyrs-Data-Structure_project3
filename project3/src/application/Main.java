package application;
	
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;

import application.SortedCircularDoublyLinkedList.LocationNode;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class Main extends Application {
	// Buttons
	Button recbtn = new Button("Export Location");
	Button reptbtn = new Button("Statistics Report");
	Button LoadFile = new Button("Load File");
	
	Stage s = new Stage();
	static ComboBox<String> comboBox = new ComboBox<>(FXCollections.observableArrayList());
	// List to store locations
	public static SortedCircularDoublyLinkedList locationsList;
	LocationScreen LocationScene;
	StatisticsScreen sta;

	@Override
	public void start(Stage primaryStage) {
		try {

			locationsList = new SortedCircularDoublyLinkedList();
			// Create the main window
			Scene scene = new Scene(getMainPage(), 700, 600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Project lll");
			primaryStage.show();
			// Add event handlers to buttons
			reptbtn.addEventHandler(ActionEvent.ACTION, new myEvent());
			recbtn.addEventHandler(ActionEvent.ACTION, new myEvent());
			LoadFile.addEventHandler(ActionEvent.ACTION, new myEvent());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Method to create the main page layout
	private VBox getMainPage() {
		HBox h = new HBox(10);
		VBox spane = new VBox(15);
		Label title = new Label("Project [3]");// The Main Screen title

		Font f = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 40);// type of The Text Main Screen
																						// title
	
		title.setPadding(new Insets(15, 15, 15, 15));
		title.setAlignment(Pos.TOP_CENTER);// to put title in Top of Center
		ImageView image2 = new ImageView(new Image("proj3Pic.png"));

		Font fonts = Font.font(25);
		Color color = new Color(0.75, 0.125, 0.50, 1);
		title.setFont(fonts);
		recbtn.setFont(fonts);
		reptbtn.setFont(fonts);
		LoadFile.setFont(fonts);
        title.setTextFill(color);
		recbtn.setTextFill(color);
		reptbtn.setTextFill(color);
		LoadFile.setTextFill(color);

		reptbtn.setMaxWidth(230);
		LoadFile.setMaxWidth(200);
		recbtn.setMaxWidth(230);

		image2.setFitWidth(550);
		image2.setFitHeight(400);

		recbtn.setDisable(true);
		reptbtn.setDisable(true);

		h.setAlignment(Pos.CENTER);
		recbtn.setStyle("-fx-background-color: white");// To determine the Color background for every button
		reptbtn.setStyle("-fx-background-color: white");// To determine the Color background for every button
		LoadFile.setStyle("-fx-background-color: white");// To determine the Color background for every button
		spane.setStyle("-fx-background-color:#D3D3D3");// To determine the Color background
		h.getChildren().addAll(LoadFile, recbtn, reptbtn);
		spane.setAlignment(Pos.CENTER);
		spane.getChildren().addAll(title, image2, h);
		spane.setPadding(new Insets(15,15,15,15));
		return spane;
	}

	
public void showAlert(String title, String message) {
	    Alert alert = new Alert(AlertType.ERROR);
	    alert.setTitle(title);
	    alert.setHeaderText(null);
	    alert.setContentText(message);
	    alert.showAndWait();
	}
	class myEvent implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			if (((Button) event.getSource()).getText().equals("Export Location")) {
				LocationScene = new LocationScreen();
				LocationScene.getLocationScreen();
				LocationScene.setLocationField(locationsList.getFirst().getLocation().getName());
			} else if (((Button) event.getSource()).getText().equals("Statistics Report")) {
				sta = new StatisticsScreen();

				sta.getStatisticsScreen();
				StatisticsScreen.tableView.refresh();

			} else if (((Button) event.getSource()).getText().equals("Load File")) {
				loadFile5();

				recbtn.setDisable(false);
				reptbtn.setDisable(false);
			}
		}

	}private LocalDate parseDate(String dateStr) {
	    LocalDate parsedDate = null;

	    // Format 1: M/d/yyyy
	    DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("M/d/yyyy");
	    try {
	        parsedDate = LocalDate.parse(dateStr, formatter1);
	    } catch (DateTimeParseException e1) {
	        // Format 2: M/dd/yyyy
	        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("M/dd/yyyy");
	        try {
	            parsedDate = LocalDate.parse(dateStr, formatter2);
	        } catch (DateTimeParseException e2) {
	            // Format 3: MM/d/yyyy
	            DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("MM/d/yyyy");
	            try {
	                parsedDate = LocalDate.parse(dateStr, formatter3);
	            } catch (DateTimeParseException e3) {
	                // Format 4: MM/dd/yyyy
	                DateTimeFormatter formatter4 = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	                try {
	                    parsedDate = LocalDate.parse(dateStr, formatter4);
	                } catch (DateTimeParseException e4) {
	                    // Format 5: M/yyyy
	                    DateTimeFormatter formatter5 = DateTimeFormatter.ofPattern("M/yyyy");
	                    try {
	                        YearMonth yearMonth = YearMonth.parse(dateStr, formatter5);
	                        parsedDate = yearMonth.atDay(1);
	                    } catch (DateTimeParseException e5) {
	                        System.out.println("Invalid date format: " + dateStr);
	                        // Handle the exception or return null
	                        return null;
	                    }
	                }
	            }
	        }
	    }

	    return parsedDate;
	}private boolean isValidDateFormat(String dateStr) {
	    try {
	        parseDate(dateStr);
	        return true;
	    } catch (DateTimeParseException e) {
	        return false;
	    }
	}
	public void loadFile5() {
	    FileChooser chooser = new FileChooser();
	    chooser.setTitle("Open Martyrs File");
	    File file = chooser.showOpenDialog(s);

	    if (file != null) {
	        try (Scanner scanner = new Scanner(file)) {
	            boolean emptyList = true;

	            while (scanner.hasNextLine()) {
	                String line = scanner.nextLine();
	                Scanner lineScanner = new Scanner(line);
	                lineScanner.useDelimiter(",");

	                if (lineScanner.hasNext()) {
	                    String name = lineScanner.next();
	                    int age = lineScanner.hasNextInt() ? lineScanner.nextInt() : 0;
	                    String location = lineScanner.hasNext() ? lineScanner.next() : "";
	                    LocalDate dateOfDeath = null;
	                    char gender = '\0';

	                    if (lineScanner.hasNext()) {
	                        String dateStr = lineScanner.next();

	                        if (!isValidDateFormat(dateStr)) {
	                            showAlert("Invalid Date Format", "Skipping line: " + line);
	                            continue; // Skip to the next line
	                        }

	                        dateOfDeath = parseDate(dateStr);

	                        if (dateOfDeath == null) {
	                            showAlert("Invalid Date Format", "Skipping line: " + line);
	                            continue; // Skip to the next line
	                        }
	                    } else {
	                        showAlert("Invalid Date Format", "Skipping line: " + line);
	                        continue; // Skip to the next line
	                    }

	                    if (lineScanner.hasNext()) {
	                        gender = lineScanner.next().charAt(0);
	                    }

	                    Martyr newMartyr = new Martyr(name, age, dateOfDeath, gender, location);
	                    Location newLocation = new Location(location);

	                    LocationNode locationNode = locationsList.searchLocationNode3(location);
	                    if (locationNode == null) {
	                        if (!comboBox.getItems().contains(location)) {
	                            comboBox.getItems().add(location);
	                        }

	                        AVLTree1 avlOne = new AVLTree1();
	                        avlOne.insert(newMartyr);

	                        AVLTree2 avlTwo = new AVLTree2();
	                        DateStack dateStack = new DateStack(dateOfDeath);
	                        dateStack.push(newMartyr);
	                        avlTwo.insertNode(dateStack);

	                        locationsList.insertLocationNode(newLocation);
	                    } else {
	                        locationNode.getAvlOne().insert(newMartyr);

	                        DateStack dateStack = new DateStack(dateOfDeath);
	                        Node2 avlTwoNode = locationNode.getAvlTwo().findNode(dateStack);

	                        if (avlTwoNode == null) {
	                            dateStack.push(newMartyr);
	                            locationNode.getAvlTwo().insertNode(dateStack);
	                        } else {
	                            avlTwoNode.getData().push(newMartyr);
	                        }

	                        updateIntermediateNodes(avlTwoNode);
	                    }

	                    emptyList = false;
	                }
	            }

	            if (emptyList) {
	                showAlert("Empty List", "List is empty.");
	            } else {
	                locationsList.print4();
	                locationsList.print3();
	                // locationsList.getFirst().avlTwo.print2();

	            }

	            System.out.println("---------------------------------");
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
private void updateIntermediateNodes(Node2 node) {
	    if (node != null) {
	        updateIntermediateNodes(node.left);
	        updateIntermediateNodes(node.right);

	        if (node.left != null && node.right != null) {
	            Stack leftDataStack = node.left.data.martyrs;
	            Stack rightDataStack = node.right.data.martyrs;

	            while (!leftDataStack.isEmpty() && !rightDataStack.isEmpty()) {
	                Martyr leftMartyr = leftDataStack.pop();
	                Martyr rightMartyr = rightDataStack.pop();

	                if (leftMartyr.getDateOfDeath().isEqual(rightMartyr.getDateOfDeath())) {
	                    node.data.martyrs.push(leftMartyr);
	                    node.data.martyrs.push(rightMartyr);
	                } else {
	                    leftDataStack.push(leftMartyr);
	                    rightDataStack.push(rightMartyr);
	                    break;
	                }
	            }

	            while (!leftDataStack.isEmpty()) {
	                node.data.martyrs.push(leftDataStack.pop());
	            }

	            while (!rightDataStack.isEmpty()) {
	                node.data.martyrs.push(rightDataStack.pop());
	            }
	        }
	    }
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
