package gui;

import java.sql.SQLException;
import java.util.ArrayList;

import connection.MedicalRecord;
import entities.Appointment;
import exceptions.MedicalRecordException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author John Cornwell
 *
 *         The tab will have all of its components stacked vertically. Each
 *         component will be an HBox which will have each of its components set
 *         horizontally. Components of the HBox will include buttons, text, and
 *         tables.
 */

public class AppointmentTab extends VBox {
	private MedicalRecord record;
	private TableView<Appointment> appointmentTable;
	private DoctorTab docTab;

	public AppointmentTab(MedicalRecord record, Stage primaryStage, DoctorTab docTab) {
		// create UI elements and then call populate() to fill table from database.
		this.record = record;
		this.docTab = docTab;
		this.setPadding(new Insets(10, 50, 10, 50));
		this.setSpacing(40);
		// create table element
		appointmentTable = new TableView<>();
		// add columns to table
		TableColumn<Appointment, String> appointmentNumCol = new TableColumn<>("Appointment Number");
		appointmentNumCol.setCellValueFactory(new PropertyValueFactory<>("appointmentNum")); // calls appointment getter
		TableColumn<Appointment, String> docIDCol = new TableColumn<>("Doctor ID");
		docIDCol.setCellValueFactory(new PropertyValueFactory<>("doctorID")); // calls appointment getter
		TableColumn<Appointment, String> patientIDCol = new TableColumn<>("Patient ID");
		patientIDCol.setCellValueFactory(new PropertyValueFactory<>("patientID")); // calls appointment getter
		TableColumn<Appointment, String> dateCol = new TableColumn<>("Date");
		dateCol.setCellValueFactory(new PropertyValueFactory<>("date")); // calls appointment getter
		TableColumn<Appointment, String> startCol = new TableColumn<>("Start Time");
		startCol.setCellValueFactory(new PropertyValueFactory<>("startTime")); // calls appointment getter
		TableColumn<Appointment, String> endCol = new TableColumn<>("End Time");
		endCol.setCellValueFactory(new PropertyValueFactory<>("endTime")); // calls appointment getter
		appointmentNumCol.setVisible(false); // not for the user to see
		appointmentTable.getColumns().add(appointmentNumCol);
		appointmentTable.getColumns().add(docIDCol);
		appointmentTable.getColumns().add(patientIDCol);
		appointmentTable.getColumns().add(dateCol);
		appointmentTable.getColumns().add(startCol);
		appointmentTable.getColumns().add(endCol);
		// has columns fill width of table
		appointmentTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		this.getChildren().add(appointmentTable);
		// add elements to select/add/delete an appointment
		Button selectButton = new Button("Select");
		selectButton.setMinWidth(50);
		Button newButton = new Button("New");
		newButton.setMinWidth(50);
		Button deleteButton = new Button("Delete");
		deleteButton.setMinWidth(50);
		HBox buttons = new HBox(selectButton, newButton, deleteButton);
		buttons.setAlignment(Pos.CENTER);
		buttons.setPadding(new Insets(10, 50, 10, 50));
		buttons.setSpacing(50);
		this.getChildren().add(buttons);

		// add insert and delete functionality
		EventHandler<ActionEvent> selectEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				Appointment selectedAppointment = appointmentTable.getSelectionModel().getSelectedItem();
				if (selectedAppointment == null) { // there was nothing selected
					Alert nothingSelected = new Alert(AlertType.WARNING);
					nothingSelected.setContentText("You must select an appointment.");
					nothingSelected.show();
				} else { // show appointment information
					generateInfo(selectedAppointment, primaryStage);
				}
			}
		};

		EventHandler<ActionEvent> createEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				generatePopup(primaryStage);
			}
		};

		EventHandler<ActionEvent> deleteEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				Appointment selectedAppointment = appointmentTable.getSelectionModel().getSelectedItem();
				if (selectedAppointment == null) { // there was nothing selected
					Alert nothingSelected = new Alert(AlertType.WARNING);
					nothingSelected.setContentText("You must select an appointment.");
					nothingSelected.show();
				} else { // delete appointment
					try {
						record.deleteAppointment(selectedAppointment);
						docTab.resetBillingDoc();
					} catch (SQLException exception) {
						System.out.println("There was an SQLException when deleting the appointmnent.");
						exception.printStackTrace();
					}
				}
				clear(appointmentTable);
				populateAppointment();
			}
		};

		selectButton.setOnAction(selectEvent);
		deleteButton.setOnAction(deleteEvent);
		newButton.setOnAction(createEvent);

		// populate the table
		populateAppointment();
	}

	public void populateAppointment() {
		ArrayList<Appointment> appointments = null;
		try {
			appointments = record.getAppointments();
		} catch (SQLException e) {
			System.out.println("There was an SQLException when querying all appointments.");
			e.printStackTrace();
		} catch (MedicalRecordException e) {
			System.out.println("There was a MedicalRecordException when creating the appointment list.");
			e.printStackTrace();
		}
		for (Appointment ele : appointments) {
			appointmentTable.getItems().add(ele);
		}
	}

	public void generateInfo(Appointment selectedAppointment, Stage primaryStage) {
		final Stage appointmentView = new Stage();
		appointmentView.initModality(Modality.APPLICATION_MODAL); // cannot interact with main application
		appointmentView.initOwner(primaryStage);
		VBox appointmentVBox = new VBox();
		appointmentVBox.setPadding(new Insets(10, 50, 10, 50));
		appointmentVBox.setSpacing(30);
		Label aptInfo = new Label("Appointment Information");
		Label appointmentNumLabel = new Label("Appointment Number");
		Label doctorIDLabel = new Label("Doctor ID");
		Label patientIDLabel = new Label("Patient ID");
		Label monthLabel = new Label("Month");
		Label dayLabel = new Label("Day");
		Label yearLabel = new Label("Year");
		Label startHourLabel = new Label("Start Hour");
		Label startMinLabel = new Label("Start Minute");
		Label endHourLabel = new Label("End Hour");
		Label endMinLabel = new Label("End Minute");
		TextField appointmentNumField = new TextField(Integer.toString(selectedAppointment.getAppointmentNum()));
		TextField doctorIDField = new TextField(Integer.toString(selectedAppointment.getDoctorID()));
		TextField patientIDField = new TextField(Integer.toString(selectedAppointment.getPatientID()));
		TextField monthField = new TextField(selectedAppointment.getDate().substring(0, 2));
		TextField dayField = new TextField(selectedAppointment.getDate().substring(3, 5));
		TextField yearField = new TextField(selectedAppointment.getDate().substring(6));
		TextField startHourField = new TextField(selectedAppointment.getStartTime().substring(0, 2));
		TextField startMinField = new TextField(selectedAppointment.getStartTime().substring(3));
		TextField endHourField = new TextField(selectedAppointment.getEndTime().substring(0, 2));
		TextField endMinField = new TextField(selectedAppointment.getEndTime().substring(3));
		appointmentNumField.setDisable(true);
		doctorIDField.setDisable(true);
		patientIDField.setDisable(true);
		monthField.setDisable(true);
		dayField.setDisable(true);
		yearField.setDisable(true);
		startHourField.setDisable(true);
		startMinField.setDisable(true);
		endHourField.setDisable(true);
		endMinField.setDisable(true);
		GridPane appointmentInfo = new GridPane();
		appointmentInfo.add(aptInfo, 1, 0);
		appointmentInfo.add(appointmentNumLabel, 1, 2);
		appointmentInfo.add(doctorIDLabel, 2, 2);
		appointmentInfo.add(patientIDLabel, 3, 2);
		appointmentInfo.add(monthLabel, 1, 4);
		appointmentInfo.add(dayLabel, 2, 4);
		appointmentInfo.add(yearLabel, 3, 4);
		appointmentInfo.add(startHourLabel, 1, 6);
		appointmentInfo.add(startMinLabel, 2, 6);
		appointmentInfo.add(endHourLabel, 3, 6);
		appointmentInfo.add(endMinLabel, 4, 6);
		appointmentInfo.setAlignment(Pos.CENTER);
		appointmentInfo.add(appointmentNumField, 1, 3);
		appointmentInfo.add(doctorIDField, 2, 3);
		appointmentInfo.add(patientIDField, 3, 3);
		appointmentInfo.add(monthField, 1, 5);
		appointmentInfo.add(dayField, 2, 5);
		appointmentInfo.add(yearField, 3, 5);
		appointmentInfo.add(startHourField, 1, 7);
		appointmentInfo.add(startMinField, 2, 7);
		appointmentInfo.add(endHourField, 3, 7);
		appointmentInfo.add(endMinField, 4, 7);
		appointmentInfo.getRowConstraints().add(new RowConstraints(50));
		Label notesLabel = new Label("Notes");
		TextArea notes = new TextArea(selectedAppointment.getNotes());
		notes.setMinSize(200, 200);
		notes.setDisable(true);
		appointmentVBox.getChildren().add(appointmentInfo);
		appointmentVBox.getChildren().add(notesLabel);
		appointmentVBox.getChildren().add(notes);
		// add elements to scene
		Scene dialogScene = new Scene(appointmentVBox);
		appointmentView.setTitle("Appointment Information");
		appointmentView.setScene(dialogScene);
		appointmentView.show();
	}

	public void generatePopup(Stage primaryStage) {
		final Stage appointmentView = new Stage();
		appointmentView.initModality(Modality.APPLICATION_MODAL); // cannot interact with main application
		appointmentView.initOwner(primaryStage);
		VBox appointmentVBox = new VBox();
		appointmentVBox.setPadding(new Insets(10, 50, 10, 50));
		appointmentVBox.setSpacing(30);
		TextField doctorIDField = new TextField();
		TextField patientIDField = new TextField();
		TextField monthField = new TextField();
		TextField dayField = new TextField();
		TextField yearField = new TextField();
		TextField startHourField = new TextField();
		TextField startMinField = new TextField();
		TextField endHourField = new TextField();
		TextField endMinField = new TextField();
		Label doctorID = new Label("Doctor ID");
		Label patientID = new Label("Patient ID");
		Label notes = new Label("Notes");
		Label month = new Label("Month");
		Label day = new Label("Day");
		Label year = new Label("Year");
		Label startHour = new Label("Start Hour");
		Label startMin = new Label("Start Minute");
		Label endHour = new Label("End Hour");
		Label endMin = new Label("End Minute");
		GridPane appointmentInfo = new GridPane();
		appointmentInfo.add(new Text("Create New Appointment"), 1, 0);
		appointmentInfo.add(doctorID, 1, 2);
		appointmentInfo.add(patientID, 2, 2);
		appointmentInfo.add(month, 1, 4);
		appointmentInfo.add(day, 2, 4);
		appointmentInfo.add(year, 3, 4);
		appointmentInfo.add(startHour, 1, 6);
		appointmentInfo.add(startMin, 2, 6);
		appointmentInfo.add(endHour, 3, 6);
		appointmentInfo.add(endMin, 4, 6);
		Button createButton = new Button("Create");
		appointmentInfo.setAlignment(Pos.CENTER);
		appointmentInfo.add(doctorIDField, 1, 3);
		appointmentInfo.add(patientIDField, 2, 3);
		appointmentInfo.add(monthField, 1, 5);
		appointmentInfo.add(dayField, 2, 5);
		appointmentInfo.add(yearField, 3, 5);
		appointmentInfo.add(startHourField, 1, 7);
		appointmentInfo.add(startMinField, 2, 7);
		appointmentInfo.add(endHourField, 3, 7);
		appointmentInfo.add(endMinField, 4, 7);
		appointmentInfo.add(createButton, 5, 7);
		appointmentInfo.getRowConstraints().add(new RowConstraints(50));
		appointmentVBox.getChildren().add(appointmentInfo);
		TextArea notesField = new TextArea();
		appointmentVBox.getChildren().add(notes);
		appointmentVBox.getChildren().add(notesField);
		appointmentVBox.setAlignment(Pos.CENTER_LEFT);

		EventHandler<ActionEvent> createEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (doctorIDField.getText() == null || patientID.getText() == null || monthField.getText() == null
						|| dayField.getText() == null || yearField.getText() == null || startHourField.getText() == null
						|| startMinField.getText() == null || endHourField.getText() == null
						|| endMinField.getText() == null) {
					Alert missingField = new Alert(AlertType.WARNING);
					missingField.setContentText("The appointment needs a doctor id, patient id, month, day year, "
							+ "start hour, start minute, end hour, and end minute.");
					missingField.show();
				} else {
					int aDoctorID = -1;
					int aPatientID = -1;
					int aMonth = -1;
					int aDay = -1;
					int aYear = -1;
					int aStartHour = -1;
					int aStartMin = -1;
					int aEndHour = -1;
					int aEndMin = -1;
					try {
						aDoctorID = Integer.parseInt(doctorIDField.getText(), 10);
						aPatientID = Integer.parseInt(patientIDField.getText(), 10);
						aMonth = Integer.parseInt(monthField.getText(), 10);
						aDay = Integer.parseInt(dayField.getText(), 10);
						aYear = Integer.parseInt(yearField.getText(), 10);
						aStartHour = Integer.parseInt(startHourField.getText(), 10);
						aStartMin = Integer.parseInt(startMinField.getText(), 10);
						aEndHour = Integer.parseInt(endHourField.getText(), 10);
						aEndMin = Integer.parseInt(endMinField.getText(), 10);
					} catch (Exception exception) {
						Alert appointmentAlert = new Alert(AlertType.WARNING);
						appointmentAlert.setContentText("Ensure doctor id, patient id, month, day year, \"\r\n"
								+ "start hour, start minute, end hour, and end minute contain only positive numbers without "
								+ "leading zeroes.");
						appointmentAlert.show();
					}
					try {
						record.insertAppointment(new Appointment(-1, aDoctorID, aPatientID, notesField.getText(),
								aMonth, aDay, aYear, aStartHour, aStartMin, aEndHour, aEndMin));
					} catch (MedicalRecordException exception) {
						Alert appointmentAlert = new Alert(AlertType.WARNING);
						appointmentAlert.setContentText(exception.getMessage());
						appointmentAlert.show();
					} catch (SQLException exception) {
						System.out.println("There was an SQLException when inserting the appointment.");
						exception.printStackTrace();
					}
					clear(appointmentTable);
					populateAppointment();
					docTab.resetBillingDoc(); // clear the billing stats in DoctorTab and repopulate
					appointmentView.close();
				}
			}
		};
		createButton.setOnAction(createEvent);

		// add elements to scene
		Scene dialogScene = new Scene(appointmentVBox);
		appointmentView.setTitle("Create New Appointment");
		appointmentView.setScene(dialogScene);
		appointmentView.show();
	}

	public void clear(TableView<Appointment> t) {
		// clear all values from the table
		t.getItems().clear();
	}
}