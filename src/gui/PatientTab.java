package gui;

import java.sql.SQLException;
import java.util.ArrayList;

import connection.MedicalRecord;
import entities.Patient;
import entities.Treatment;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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

public class PatientTab extends VBox {

	private MedicalRecord record;
	TableView<Patient> patientTable;

	public PatientTab(MedicalRecord record, Stage primaryStage) {
		// create UI elements and then call populate() to fill table from database.
		this.record = record;
		this.setPadding(new Insets(10, 50, 10, 50));
		this.setSpacing(40);
		// create table element
		patientTable = new TableView<>();
		// add columns to table
		TableColumn<Patient, String> patientIDCol = new TableColumn<>("Patient ID");
		patientIDCol.setCellValueFactory(new PropertyValueFactory<>("patientID")); // calls patient getter
		TableColumn<Patient, String> firstNameCol = new TableColumn<>("First Name");
		firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName")); // calls patient getter
		TableColumn<Patient, String> midNameCol = new TableColumn<>("Middle Name");
		midNameCol.setCellValueFactory(new PropertyValueFactory<>("middleName")); // calls patient getter
		TableColumn<Patient, String> lastNameCol = new TableColumn<>("Last Name");
		lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName")); // calls patient getter
		TableColumn<Patient, String> birthdayCol = new TableColumn<>("Date of Birth");
		birthdayCol.setCellValueFactory(new PropertyValueFactory<>("DOB")); // calls patient getter
		TableColumn<Patient, String> insuranceCol = new TableColumn<>("Insurance");
		insuranceCol.setCellValueFactory(new PropertyValueFactory<>("insurance")); // calls patient getter
		patientTable.getColumns().add(patientIDCol);
		patientTable.getColumns().add(firstNameCol);
		patientTable.getColumns().add(midNameCol);
		patientTable.getColumns().add(lastNameCol);
		patientTable.getColumns().add(birthdayCol);
		patientTable.getColumns().add(insuranceCol);
		// has columns fill width of table
		patientTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		this.getChildren().add(patientTable);
		// add elements to select a patient
		Button selectButton = new Button("Select");
		selectButton.setMinWidth(50);
		HBox button = new HBox(selectButton);
		button.setAlignment(Pos.CENTER);
		this.getChildren().add(button);

		// create and set event handler for selectButton
		EventHandler<ActionEvent> selectEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				Patient selectedPatient = patientTable.getSelectionModel().getSelectedItem();
				if (selectedPatient == null) { // there was nothing selected
					Alert nothingSelected = new Alert(AlertType.WARNING);
					nothingSelected.setContentText("You must select a patient.");
					nothingSelected.show();
				} else { // show patient information
					generatePopup(selectedPatient, primaryStage);
				}
			}
		};

		selectButton.setOnAction(selectEvent);
		// populate the table
		populatePatient();
	}

	public void populatePatient() {
		ArrayList<Patient> patients = null;
		try {
			patients = record.getPatients();
		} catch (SQLException e) {
			System.out.println("There was an SQLException when querying all patients.");
			e.printStackTrace();
		} catch (MedicalRecordException e) {
			System.out.println("There was a MedicalRecordException when creating the patient list.");
			e.printStackTrace();
		}
		for (Patient ele : patients) {
			patientTable.getItems().add(ele);
		}
	}

	public void populatePatientTreats(int patientID, TableView<Treatment> treatmentTable) {
		ArrayList<Treatment> treatments = null;
		try {
			treatments = record.getPatientTreats(patientID);
		} catch (SQLException e) {
			System.out.println("There was an SQLException when querying patient treatments.");
			e.printStackTrace();
		} catch (MedicalRecordException e) {
			System.out.println("There was a MedicalRecordException when creating the patient treatment list.");
			e.printStackTrace();
		}
		for (Treatment ele : treatments) {
			treatmentTable.getItems().add(ele);
		}
	}

	public int getTotalTreatments(int patientID) {
		int total = -1;
		try {
			total = record.getTotalTreatments(patientID);
		} catch (SQLException e) {
			System.out.println("There was an SQLException when querying total patient treatments.");
			e.printStackTrace();
		}
		return total;
	}

	public void generatePopup(Patient selectedPatient, Stage primaryStage) {
		final Stage patientView = new Stage();
		patientView.initModality(Modality.APPLICATION_MODAL); // cannot interact with main application
		patientView.initOwner(primaryStage);
		VBox patientVBox = new VBox();
		patientVBox.setPadding(new Insets(10, 50, 10, 50));
		patientVBox.setSpacing(30);
		patientVBox.getChildren().add(new Text("Patient Treatment History"));
		patientVBox.getChildren().add(new Text(
				"This patient has received " + getTotalTreatments(selectedPatient.getPatientID()) + " treatments:"));
		TableView<Treatment> treatmentTable = new TableView<>();
		// add columns to table
		TableColumn<Treatment, String> treatmentIDCol = new TableColumn<>("Treatment ID");
		treatmentIDCol.setCellValueFactory(new PropertyValueFactory<>("treatmentID")); // calls treatment
																						// getter
		TableColumn<Treatment, String> docIDCol = new TableColumn<>("Doctor ID");
		docIDCol.setCellValueFactory(new PropertyValueFactory<>("doctorID")); // calls treatment getter
		TableColumn<Treatment, String> patientIDCol = new TableColumn<>("Patient ID");
		patientIDCol.setCellValueFactory(new PropertyValueFactory<>("patientID")); // calls treatment getter
		TableColumn<Treatment, String> dateCol = new TableColumn<>("Date");
		dateCol.setCellValueFactory(new PropertyValueFactory<>("date")); // calls treatment getter
		TableColumn<Treatment, String> initialsCol = new TableColumn<>("Initials");
		initialsCol.setCellValueFactory(new PropertyValueFactory<>("initials")); // calls treatment getter
		treatmentIDCol.setVisible(false); // not for the user to see
		treatmentTable.getColumns().add(treatmentIDCol);
		treatmentTable.getColumns().add(docIDCol);
		treatmentTable.getColumns().add(patientIDCol);
		treatmentTable.getColumns().add(dateCol);
		treatmentTable.getColumns().add(initialsCol);
		// has columns fill width of table
		treatmentTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		patientVBox.getChildren().add(treatmentTable);

		// allow user to update patient
		TextField firstNameField = new TextField(selectedPatient.getFirstName());
		TextField middleNameField = new TextField(selectedPatient.getMiddleName());
		TextField lastNameField = new TextField(selectedPatient.getLastName());
		TextField birthMonthField = new TextField(selectedPatient.getDOB().substring(0, 2));
		TextField birthDayField = new TextField(selectedPatient.getDOB().substring(3, 5));
		TextField birthYearField = new TextField(selectedPatient.getDOB().substring(6));
		TextField insuranceField = new TextField(selectedPatient.getInsurance());
		Label fName = new Label("First Name");
		Label mName = new Label("Middle Name");
		Label lName = new Label("Last Name");
		Label bMonth = new Label("Birth Month");
		Label bDay = new Label("Birth Day");
		Label bYear = new Label("Birth Year");
		Label insuranceLabel = new Label("Insurance");
		GridPane patientInfo = new GridPane();
		patientInfo.setAlignment(Pos.CENTER);
		patientInfo.add(fName, 1, 1);
		patientInfo.add(mName, 2, 1);
		patientInfo.add(lName, 3, 1);
		patientInfo.add(bMonth, 4, 1);
		patientInfo.add(bDay, 5, 1);
		patientInfo.add(bYear, 6, 1);
		patientInfo.add(insuranceLabel, 7, 1);
		Button updateButton = new Button("Update");
		patientInfo.add(firstNameField, 1, 2);
		patientInfo.add(middleNameField, 2, 2);
		patientInfo.add(lastNameField, 3, 2);
		patientInfo.add(birthMonthField, 4, 2);
		patientInfo.add(birthDayField, 5, 2);
		patientInfo.add(birthYearField, 6, 2);
		patientInfo.add(insuranceField, 7, 2);
		patientInfo.add(updateButton, 8, 2);
		patientVBox.getChildren().add(patientInfo);
		patientVBox.setAlignment(Pos.CENTER_LEFT);

		EventHandler<ActionEvent> updateEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (firstNameField.getText() == null || lastNameField.getText() == null
						|| birthMonthField.getText() == null || birthDayField.getText() == null
						|| birthYearField.getText() == null) { // there was nothing selected
					Alert missingField = new Alert(AlertType.WARNING);
					missingField.setContentText("The patient needs a first name, last name, and birthday.");
					missingField.show();
				} else { // update patient info and close pop up
					// update the patient table
					int birthMonth = -1;
					int birthDay = -1;
					int birthYear = -1;
					try {
						birthMonth = Integer.parseInt(birthMonthField.getText(), 10);
						birthDay = Integer.parseInt(birthDayField.getText(), 10);
						birthYear = Integer.parseInt(birthYearField.getText(), 10);
					} catch (Exception exception) {
						Alert birthdayAlert = new Alert(AlertType.WARNING);
						birthdayAlert.setContentText("The birthday must contain only numbers.");
						birthdayAlert.show();
					}
					try {
						record.updatePatient(new Patient(selectedPatient.getPatientID(), firstNameField.getText(),
								middleNameField.getText(), lastNameField.getText(), birthMonth, birthDay, birthYear,
								insuranceField.getText()));
					} catch (MedicalRecordException exception) {
						Alert birthdayAlert = new Alert(AlertType.WARNING);
						birthdayAlert.setContentText(exception.getMessage());
						birthdayAlert.show();
						exception.printStackTrace();
					} catch (SQLException exception) {
						System.out.println("There was an SQLException when updating the patient.");
						exception.printStackTrace();
					}
					clear(patientTable); // clear the patient table
					populatePatient(); // query the database for all patients
					patientView.close();
				}
			}
		};
		updateButton.setOnAction(updateEvent);

		// populate table
		populatePatientTreats(selectedPatient.getPatientID(), treatmentTable);

		// add elements to scene
		Scene dialogScene = new Scene(patientVBox);
		patientView.setTitle("Patient Information");
		patientView.setScene(dialogScene);
		patientView.show();
	}

	public void clear(TableView<Patient> t) {
		// clear all values from the table
		t.getItems().clear();
	}
}
