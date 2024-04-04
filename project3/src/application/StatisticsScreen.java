package application;

import java.util.Date;

import javafx.scene.control.cell.PropertyValueFactory;
import java.util.Set;

import application.SortedCircularDoublyLinkedList.LocationNode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.Chart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class StatisticsScreen extends Stage {

	BorderPane bpane = new BorderPane();
	Button nextPage, summaryButton;
	FlowPane flowpane = new FlowPane();
	Stage sta;
	Scene Screen;
	SummaryReportRow summary;
	public static TableView<SummaryReportRow> tableView;

	StatisticsScreen() {

	}

	public Stage getStatisticsScreen() {
		summaryButton = new Button("Generate Summary Report");
		TableColumn<SummaryReportRow, Integer> c0 = new TableColumn<>("location");
		c0.setCellValueFactory(new PropertyValueFactory<>("location"));

		
		TableColumn<SummaryReportRow, Integer> c1 = new TableColumn<>("Numbers Of Martyrs");
		c1.setCellValueFactory(new PropertyValueFactory<>("numbersOfMartyrs"));

		TableColumn<SummaryReportRow, Integer> c2 = new TableColumn<>("1st AVL level-by-level");
		c2.setCellValueFactory(new PropertyValueFactory<>("level_by_level"));
		TableColumn<SummaryReportRow, Integer> c9 = new TableColumn<>(" height of the 1st AVL tree.");
		c9.setCellValueFactory(new PropertyValueFactory<>("heightAVlOne"));
		TableColumn<SummaryReportRow, Double> c3 = new TableColumn<>(" height of the 2ed AVL tree.");
		c3.setCellValueFactory(new PropertyValueFactory<>("heightAVlTwo"));

		TableColumn<SummaryReportRow, Integer> c4 = new TableColumn<>("Date That Has The Maximum Number Of Martyrs");
		c4.setCellValueFactory(new PropertyValueFactory<>("maximumNumber"));
		TableColumn<SummaryReportRow, Integer> c5 = new TableColumn<>("Traverse the 2nd AVL backward");
		c5.setCellValueFactory(new PropertyValueFactory<>("backward"));

		tableView = new TableView<>();
		tableView.getColumns().addAll(c0, c1, c2, c9, c3, c4, c5);

		ObservableList<SummaryReportRow> statsList = FXCollections.observableArrayList();

		int numLocations = Main.locationsList.size();
		for (int i = 0; i < numLocations; i++) {
		    LocationNode current = Main.locationsList.get(i);
		    SummaryReportRow stats = new SummaryReportRow(current);
		    statsList.add(stats);
		}

		tableView.setItems(statsList);
		tableView.setMaxSize(1410, 700);
		// Add the columns to the TableView

		c2.setPrefWidth(260);
		c3.setPrefWidth(170);
		c4.setPrefWidth(300);
		c5.setPrefWidth(380);

		c9.setPrefWidth(170);

		VBox bpane = new VBox(5);
		Main.comboBox.setMaxSize(100, 200);
		summaryButton.setAlignment(Pos.CENTER);
		HBox h = new HBox(10);
		Font fonts = Font.font(25);
		// Color color = new Color(0.9, 0.95, 0.90, 0.65);
		summaryButton.setFont(fonts);
		// summaryButton.setTextFill(color);
		Image image = new Image("nex.png");
		ImageView imageView = new ImageView(image);

		imageView.setFitWidth(70);
		imageView.setFitHeight(70);
		Color color = new Color(0.75, 0.125, 0.50, 1);
		summaryButton.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 25));// type of The Text
		summaryButton.setStyle("-fx-background-color: white");// To determine the Color background for Button
		summaryButton.setMaxWidth(600);
		summaryButton.setMaxHeight(100);
		summaryButton.setAlignment(Pos.CENTER);
		summaryButton.setTextFill(color);
		// create a button and set its graphic to the image view
		Button next = new Button();
		Button prev = new Button();

		Image image2 = new Image("pri.png");
		ImageView imageView2 = new ImageView(image2);
		imageView2.setFitWidth(70);
		imageView2.setFitHeight(70);

		next.setGraphic(imageView);
		prev.setGraphic(imageView2);

		nextPage = new Button();
		Image image3 = new Image("goodbye3.jfif");
		ImageView imageView3 = new ImageView(image3);
		nextPage.setGraphic(imageView3);
		imageView3.setFitWidth(150);
		imageView3.setFitHeight(90);
		nextPage.setOnAction(e -> {
			System.exit(0);
		});
		HBox h2 = new HBox(5);
		h2.getChildren().addAll(prev, next);
		h.setPadding(new Insets(10, 10, 10, 10));
		h2.setAlignment(Pos.CENTER);
		h.getChildren().addAll(nextPage);
		h.setAlignment(Pos.CENTER);
		next.setOnAction(event -> {
			// Get the index of the currently selected item in the comboBox
			int currentIndex = Main.comboBox.getSelectionModel().getSelectedIndex();
			// Calculate the index of the next item by incrementing the currentIndex and
			// using modulo operator to wrap around
			int nextIndex = (currentIndex + 1) % Main.comboBox.getItems().size();
			// Select the next item in the comboBox
			Main.comboBox.getSelectionModel().select(nextIndex);
			// Get the selected brand name
			String brandName = Main.comboBox.getSelectionModel().getSelectedItem();
			// Update the table view with the data for the selected brand
			// updateTableView2(brandName);
		});

		prev.setOnAction(event -> {
			// Get the index of the currently selected item in the comboBox
			int currentIndex = Main.comboBox.getSelectionModel().getSelectedIndex();
			// Calculate the index of the previous item by decrementing the currentIndex,
			// adding the size of the comboBox items, and using modulo operator to wrap
			// around
			int previousIndex = (currentIndex - 1 + Main.comboBox.getItems().size()) % Main.comboBox.getItems().size();
			// Select the previous item in the comboBox
			Main.comboBox.getSelectionModel().select(previousIndex);
			// Get the selected brand name
			String brandName = Main.comboBox.getSelectionModel().getSelectedItem();
			// Update the table view with the data for the selected brand
			// updateTableView2(brandName);
		});
		Main.comboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal != null) {
				// Update the table view with the data for the selected item
				updateTableView2(newVal);
			}
		});
		bpane.getChildren().addAll(summaryButton, tableView, h);
		bpane.setAlignment(Pos.CENTER);
		bpane.setPadding(new Insets(15, 15, 15, 15));
		Screen = new Scene(bpane, 1500, 600);
		sta = new Stage();
		sta.setTitle("Statistics Screen");
		sta.setScene(Screen);
		sta.show();
		return sta;
	}

	private void updateTableView2(String locat) {
		// Clear the current items in the TableView
		tableView.getItems().clear();

		// Retrieve the new data based on the selected brand name
		
		ObservableList<SummaryReportRow> statsList = FXCollections.observableArrayList();

		int numLocations = Main.locationsList.size();
		for (int i = 0; i < numLocations; i++) {
			LocationNode curr = Main.locationsList.searchLocationNode3(locat);
		    SummaryReportRow stats = new SummaryReportRow(curr);
		    statsList.add(stats);
		}

		// Set the new data in the TableView
		tableView.setItems(statsList);

	}

}