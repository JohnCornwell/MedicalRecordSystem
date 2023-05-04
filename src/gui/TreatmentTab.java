package gui;

import java.sql.SQLException;
import java.util.ArrayList;

import connection.MedicalRecord;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
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

public class TreatmentTab extends VBox {
	private MedicalRecord record;
	private TableView<Treatment> treatmentTable;

	public TreatmentTab(MedicalRecord record, Stage primaryStage) {
		this.record = record;
		this.setPadding(new Insets(10, 50, 10, 50));
		this.setSpacing(40);
		this.setAlignment(Pos.TOP_CENTER);
		treatmentTable = new TableView<>();
		// add columns to table
		TableColumn<Treatment, String> treatmentIDCol = new TableColumn<>("Treatment ID");
		treatmentIDCol.setCellValueFactory(new PropertyValueFactory<>("treatmentID")); // calls treatment getter
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
		this.getChildren().add(treatmentTable);
		Button selectButton = new Button("Select");
		selectButton.setMinWidth(50);
		this.getChildren().add(selectButton);

		EventHandler<ActionEvent> selectEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				Treatment selectedTreatment = treatmentTable.getSelectionModel().getSelectedItem();
				if (selectedTreatment == null) { // there was nothing selected
					Alert nothingSelected = new Alert(AlertType.WARNING);
					nothingSelected.setContentText("You must select a treatment.");
					nothingSelected.show();
				} else { // show appointment information
					generateInfo(selectedTreatment, primaryStage);
				}
			}
		};

		selectButton.setOnAction(selectEvent);

		// populate the table
		populateTreatment();
	}

	public void generateInfo(Treatment selectedTreatment, Stage primaryStage) {
		final Stage treatmentView = new Stage();
		treatmentView.initModality(Modality.APPLICATION_MODAL); // cannot interact with main application
		treatmentView.initOwner(primaryStage);
		VBox treatmentVBox = new VBox();
		treatmentVBox.setPadding(new Insets(10, 50, 10, 50));
		treatmentVBox.setSpacing(30);
		Label treatInfo = new Label("Treatment Information");
		Label treatmentIDLabel = new Label("Treatment ID");
		Label doctorIDLabel = new Label("Doctor ID");
		Label patientIDLabel = new Label("Patient ID");
		Label monthLabel = new Label("Month");
		Label dayLabel = new Label("Day");
		Label yearLabel = new Label("Year");
		Label initialsLabel = new Label("Initials");
		TextField treatmentIDField = new TextField(Integer.toString(selectedTreatment.getTreatmentID()));
		TextField doctorIDField = new TextField(Integer.toString(selectedTreatment.getDoctorID()));
		TextField patientIDField = new TextField(Integer.toString(selectedTreatment.getPatientID()));
		TextField monthField = new TextField(selectedTreatment.getDate().substring(0, 2));
		TextField dayField = new TextField(selectedTreatment.getDate().substring(3, 5));
		TextField yearField = new TextField(selectedTreatment.getDate().substring(6));
		TextField initialsField = new TextField(selectedTreatment.getInitials());
		treatmentIDField.setDisable(true);
		doctorIDField.setDisable(true);
		patientIDField.setDisable(true);
		monthField.setDisable(true);
		dayField.setDisable(true);
		yearField.setDisable(true);
		initialsField.setDisable(true);
		GridPane treatmentInfo = new GridPane();
		treatmentInfo.add(treatInfo, 1, 0);
		treatmentInfo.add(treatmentIDLabel, 1, 2);
		treatmentInfo.add(doctorIDLabel, 2, 2);
		treatmentInfo.add(patientIDLabel, 3, 2);
		treatmentInfo.add(monthLabel, 1, 4);
		treatmentInfo.add(dayLabel, 2, 4);
		treatmentInfo.add(yearLabel, 3, 4);
		treatmentInfo.add(initialsLabel, 4, 4);
		treatmentInfo.setAlignment(Pos.CENTER);
		treatmentInfo.add(treatmentIDField, 1, 3);
		treatmentInfo.add(doctorIDField, 2, 3);
		treatmentInfo.add(patientIDField, 3, 3);
		treatmentInfo.add(monthField, 1, 5);
		treatmentInfo.add(dayField, 2, 5);
		treatmentInfo.add(yearField, 3, 5);
		treatmentInfo.add(initialsField, 4, 5);
		treatmentInfo.getRowConstraints().add(new RowConstraints(50));
		Label notesLabel = new Label("Notes");
		TextArea notes = new TextArea(selectedTreatment.getNotes());
		notes.setMinSize(200, 200);
		notes.setDisable(true);
		treatmentVBox.getChildren().add(treatmentInfo);
		treatmentVBox.getChildren().add(notesLabel);
		treatmentVBox.getChildren().add(notes);
		// add elements to scene
		Scene dialogScene = new Scene(treatmentVBox);
		treatmentView.setTitle("Appointment Information");
		treatmentView.setScene(dialogScene);
		treatmentView.show();
	}

	public void populateTreatment() {
		ArrayList<Treatment> treatments = null;
		try {
			treatments = record.getTreatments();
		} catch (SQLException e) {
			System.out.println("There was an SQLException when querying all treatments.");
			e.printStackTrace();
		} catch (MedicalRecordException e) {
			System.out.println("There was a MedicalRecordException when creating the treatment list.");
			e.printStackTrace();
		}
		for (Treatment ele : treatments) {
			treatmentTable.getItems().add(ele);
		}
	}

	public void clear(TableView<Treatment> t) {
		// clear all values from the table
		t.getItems().clear();
	}
}
