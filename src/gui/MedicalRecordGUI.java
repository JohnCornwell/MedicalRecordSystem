package gui;

import connection.MedicalRecord;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MedicalRecordGUI extends Application {

	private static MedicalRecord record; // handles interfacing with database.

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		record = new MedicalRecord();
		record.connect();
		TabPane tabPane = new TabPane();

		DoctorTab docTab = new DoctorTab(record, primaryStage);
		Tab tab1 = new Tab("Doctors", docTab);
		Tab tab2 = new Tab("Patients", new PatientTab(record, primaryStage));
		Tab tab3 = new Tab("Appointments", new AppointmentTab(record, primaryStage, docTab));
		Tab tab4 = new Tab("Treatments", new TreatmentTab(record, primaryStage));
		tab1.setClosable(false);
		tab2.setClosable(false);
		tab3.setClosable(false);
		tab4.setClosable(false);

		tabPane.getTabs().add(tab1);
		tabPane.getTabs().add(tab2);
		tabPane.getTabs().add(tab3);
		tabPane.getTabs().add(tab4);

		VBox vBox = new VBox(tabPane);
		Scene scene = new Scene(vBox);

		primaryStage.setScene(scene);
		primaryStage.setMinHeight(500);
		primaryStage.setMinWidth(500);
		primaryStage.setTitle("Medical Record System");
		primaryStage.setX(0);
		primaryStage.setY(0);
		primaryStage.show();
	}

	public void stop() throws Exception {
		record.disconnect();
	}
}
