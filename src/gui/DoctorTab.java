package gui;

import java.sql.SQLException;
import java.util.ArrayList;

import connection.MedicalRecord;
import entities.Doctor;
import entities.Patient;
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
 * 
 *         The doctor screen is responsible for showing all doctors on file, the
 *         doctors with the third through fifth most treatments performed, and
 *         the doctors with above average billing hours.
 */

public class DoctorTab extends VBox {
	private MedicalRecord record;
	private TableView<Doctor> doctorTable;
	private TableView<Doctor> billingDoctors;
	private TableView<Doctor> treatmentDoctors;

	public DoctorTab(MedicalRecord record, Stage primaryStage) {
		// create UI elements and then call populate() to fill table from database.
		this.record = record;
		this.setPadding(new Insets(10, 50, 10, 50));
		this.setSpacing(20);
		this.setAlignment(Pos.TOP_CENTER);
		// create table element
		doctorTable = new TableView<>();
		// add columns to table
		TableColumn<Doctor, String> doctorIDCol = new TableColumn<>("Doctor ID");
		doctorIDCol.setCellValueFactory(new PropertyValueFactory<>("doctorID")); // calls doctor getter
		TableColumn<Doctor, String> firstNameCol = new TableColumn<>("First Name");
		firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName")); // calls doctor getter
		TableColumn<Doctor, String> midNameCol = new TableColumn<>("Middle Name");
		midNameCol.setCellValueFactory(new PropertyValueFactory<>("middleName")); // calls doctor getter
		TableColumn<Doctor, String> lastNameCol = new TableColumn<>("Last Name");
		lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName")); // calls doctor getter
		TableColumn<Doctor, String> specialtyCol = new TableColumn<>("Specialty");
		specialtyCol.setCellValueFactory(new PropertyValueFactory<>("specialty")); // calls doctor getter
		doctorTable.getColumns().add(doctorIDCol);
		doctorTable.getColumns().add(firstNameCol);
		doctorTable.getColumns().add(midNameCol);
		doctorTable.getColumns().add(lastNameCol);
		doctorTable.getColumns().add(specialtyCol);
		// has columns fill width of table
		doctorTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		this.getChildren().add(doctorTable);
		Button selectButton = new Button("Select");
		this.getChildren().add(selectButton);
//		HBox buttonBox = new HBox(selectButton);
//		buttonBox.setAlignment(Pos.CENTER);

		EventHandler<ActionEvent> selectEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				Doctor selectedDoctor = doctorTable.getSelectionModel().getSelectedItem();
				if (selectedDoctor == null) { // there was nothing selected
					Alert nothingSelected = new Alert(AlertType.WARNING);
					nothingSelected.setContentText("You must select a doctor.");
					nothingSelected.show();
				} else { // show appointment information
					generateInfo(selectedDoctor, primaryStage);
				}
			}
		};

		selectButton.setOnAction(selectEvent);

		// create labels for advanced tables
		Label labelText1 = new Label("Doctors with above average billing hours:");
		Label labelText2 = new Label("Third through fifth place on treatments:");
		labelText1.setAlignment(Pos.CENTER);
		labelText2.setAlignment(Pos.CENTER);
		HBox tables = new HBox();
		tables.setPadding(new Insets(10, 50, 10, 50));
		tables.getChildren().add(labelText1);
		tables.getChildren().add(labelText2);
		tables.setSpacing(150);
		tables.setAlignment(Pos.CENTER);

		// tables for advanced queries
		billingDoctors = new TableView<>();
		TableColumn<Doctor, String> billingFirst = new TableColumn<>("First Name");
		billingFirst.setCellValueFactory(new PropertyValueFactory<>("firstName")); // calls doctor getter
		TableColumn<Doctor, String> billingLast = new TableColumn<>("Last Name");
		billingLast.setCellValueFactory(new PropertyValueFactory<>("lastName")); // calls doctor getter
		TableColumn<Doctor, String> billingHours = new TableColumn<>("Billing Hours");
		billingHours.setCellValueFactory(new PropertyValueFactory<>("billingHours")); // calls doctor getter
		billingDoctors.getColumns().add(billingFirst);
		billingDoctors.getColumns().add(billingLast);
		billingDoctors.getColumns().add(billingHours);
		treatmentDoctors = new TableView<>();
		TableColumn<Doctor, String> treatmentFirst = new TableColumn<>("First Name");
		treatmentFirst.setCellValueFactory(new PropertyValueFactory<>("firstName")); // calls doctor getter
		TableColumn<Doctor, String> treatmentLast = new TableColumn<>("Last Name");
		treatmentLast.setCellValueFactory(new PropertyValueFactory<>("lastName")); // calls doctor getter
		TableColumn<Doctor, String> treatmentsPerformed = new TableColumn<>("Treatments");
		treatmentsPerformed.setCellValueFactory(new PropertyValueFactory<>("treatmentsPerformed")); // calls doctor
																									// getter
		treatmentDoctors.getColumns().add(treatmentFirst);
		treatmentDoctors.getColumns().add(treatmentLast);
		treatmentDoctors.getColumns().add(treatmentsPerformed);
		// has columns fill width of table
		billingDoctors.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		// has columns fill width of table
		treatmentDoctors.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		VBox table1 = new VBox();
		table1.setPadding(new Insets(10, 50, 10, 50));
		table1.setSpacing(10);
		table1.getChildren().add(labelText1);
		labelText1.maxWidthProperty().bind(table1.widthProperty());
		labelText1.setWrapText(true);
		table1.getChildren().add(billingDoctors);
		VBox table2 = new VBox();
		table2.setPadding(new Insets(10, 50, 10, 50));
		table2.setSpacing(10);
		table2.getChildren().add(labelText2);
		labelText2.maxWidthProperty().bind(table2.widthProperty());
		labelText2.setWrapText(true);
		table2.getChildren().add(treatmentDoctors);
		tables.getChildren().add(table1);
		tables.getChildren().add(table2);

		this.getChildren().add(tables);

		// populate the tables
		populateDoctor();
		populateBillingDoc();
		populateTreatDoc();
	}

	public void populateDoctor() {
		// fill table with doctors from the database
		ArrayList<Doctor> doctors = null;
		try {
			doctors = record.getDoctors();
		} catch (SQLException e) {
			System.out.println("There was an SQLException when querying all doctors.");
			e.printStackTrace();
		} catch (MedicalRecordException e) {
			System.out.println("There was a MedicalRecordException when creating the doctor list.");
			e.printStackTrace();
		}
		for (Doctor ele : doctors) {
			doctorTable.getItems().add(ele);
		}
	}

	public void generateInfo(Doctor selectedDoctor, Stage primaryStage) {
		final Stage doctorView = new Stage();
		doctorView.initModality(Modality.APPLICATION_MODAL); // cannot interact with main application
		doctorView.initOwner(primaryStage);
		VBox doctorVBox = new VBox();
		doctorVBox.setPadding(new Insets(10, 50, 10, 50));
		doctorVBox.setSpacing(30);
		doctorVBox.getChildren().add(new Text(
				"Patients Treated by Dr. " + selectedDoctor.getFirstName() + " " + selectedDoctor.getLastName() + ":"));

		TableView<Patient> patientTable = new TableView<>();
		// add columns to table
		TableColumn<Patient, String> patientIDCol = new TableColumn<>("Patient ID");
		patientIDCol.setCellValueFactory(new PropertyValueFactory<>("patientID")); // calls treatment getter
		TableColumn<Patient, String> firstNameCol = new TableColumn<>("First Name");
		firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName")); // calls treatment getter
		TableColumn<Patient, String> middleNameCol = new TableColumn<>("Middle Name");
		middleNameCol.setCellValueFactory(new PropertyValueFactory<>("middleName")); // calls treatment getter
		TableColumn<Patient, String> lastNameCol = new TableColumn<>("Last Name");
		lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName")); // calls treatment getter
		patientTable.getColumns().add(patientIDCol);
		patientTable.getColumns().add(firstNameCol);
		patientTable.getColumns().add(middleNameCol);
		patientTable.getColumns().add(lastNameCol);
		// has columns fill width of table
		patientTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		doctorVBox.getChildren().add(patientTable);

		// allow user to view doctor info
		TextField firstNameField = new TextField(selectedDoctor.getFirstName());
		TextField middleNameField = new TextField(selectedDoctor.getMiddleName());
		TextField lastNameField = new TextField(selectedDoctor.getLastName());
		TextField specialtyField = new TextField(selectedDoctor.getSpecialty());
		firstNameField.setDisable(true);
		middleNameField.setDisable(true);
		lastNameField.setDisable(true);
		specialtyField.setDisable(true);
		Label fName = new Label("First Name");
		Label mName = new Label("Middle Name");
		Label lName = new Label("Last Name");
		Label specialty = new Label("Specialty");
		GridPane doctorInfo = new GridPane();
		doctorInfo.setAlignment(Pos.CENTER);
		doctorInfo.add(fName, 1, 1);
		doctorInfo.add(mName, 2, 1);
		doctorInfo.add(lName, 3, 1);
		doctorInfo.add(specialty, 4, 1);
		doctorInfo.add(firstNameField, 1, 2);
		doctorInfo.add(middleNameField, 2, 2);
		doctorInfo.add(lastNameField, 3, 2);
		doctorInfo.add(specialtyField, 4, 2);
		doctorVBox.getChildren().add(doctorInfo);
		doctorVBox.setAlignment(Pos.CENTER_LEFT);

		// populate table
		populateDoctorPatients(selectedDoctor.getDoctorID(), patientTable);

		// add elements to scene
		Scene dialogScene = new Scene(doctorVBox);
		doctorView.setTitle("Patient Information");
		doctorView.setScene(dialogScene);
		doctorView.show();
	}

	public void populateBillingDoc() {
		ArrayList<Doctor> doctors = null;
		try {
			doctors = record.getBillingDoc();
		} catch (SQLException e) {
			System.out.println("There was an SQLException when querying billing hours.");
			e.printStackTrace();
		} catch (MedicalRecordException e) {
			System.out.println("There was a MedicalRecordException when creating the billing hours list.");
			e.printStackTrace();
		}
		for (Doctor ele : doctors) {
			billingDoctors.getItems().add(ele);
		}
	}

	public void populateTreatDoc() {
		ArrayList<Doctor> doctors = null;
		try {
			doctors = record.getTreatmentDoc();
		} catch (SQLException e) {
			System.out.println("There was an SQLException when querying doctor treatments.");
			e.printStackTrace();
		} catch (MedicalRecordException e) {
			System.out.println("There was a MedicalRecordException when creating the treatment number list.");
			e.printStackTrace();
		}
		for (Doctor ele : doctors) {
			treatmentDoctors.getItems().add(ele);
		}
	}

	public void populateDoctorPatients(int doctorID, TableView<Patient> patientTable) {
		ArrayList<Patient> patients = null;
		try {
			patients = record.getDoctorPatients(doctorID);
		} catch (SQLException e) {
			System.out.println("There was an SQLException when querying doctor patients.");
			e.printStackTrace();
		} catch (MedicalRecordException e) {
			System.out.println("There was a MedicalRecordException when creating the doctor patients list.");
			e.printStackTrace();
		}
		for (Patient ele : patients) {
			patientTable.getItems().add(ele);
		}
	}

	public void resetBillingDoc() {
		clear(billingDoctors);
		populateBillingDoc();
	}

	public void clear(TableView<Doctor> t) {
		// clear all values from the table
		t.getItems().clear();
	}

}