package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.Appointment;
import entities.Patient;

public class Database {
	private String url = "jdbc:sqlite:MedicalRecord.db";

	private static final Database INSTANCE = new Database();

	private Connection connection;

	private Database() {
	}

	public static Database getInstance() {
		return INSTANCE;
	}

	public void connect() throws SQLException {
		connection = DriverManager.getConnection(url);
	}

	public void disconnect() throws SQLException {
		connection.close();
	}

	public ResultSet runQuery(String query) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(query);
		ResultSet set = statement.executeQuery();
		return set;
	}

	// queries for doctorTab
	public ResultSet getDoctors() throws SQLException {
		// return all of the doctors in the database
		String query = "SELECT * FROM Doctor ORDER BY LastName Asc, FirstName Asc";
		PreparedStatement statement = connection.prepareStatement(query);
		ResultSet set = statement.executeQuery();
		return set;
	}

	public ResultSet getBillingDoc() throws SQLException {
		// return the doctors with above average billing hours
		String query = "SELECT Doctor.DoctorID, Doctor.FirstName, Doctor.MiddleName, Doctor.LastName, Doctor.Specialty, (sum((Appointment.EndHour * 60) \r\n"
				+ "+ Appointment.EndMinute) - sum( (Appointment.StartHour * 60) + Appointment.StartMinute)) / 60 AS BillingHours\r\n"
				+ "FROM Doctor JOIN AssignedTo\r\n" + "JOIN Appointment ON Doctor.DoctorID = AssignedTo.DoctorID \r\n"
				+ "AND AssignedTo.AppointmentNum = Appointment.AppointmentNum\r\n"
				+ "GROUP BY Doctor.DoctorID, Doctor.FirstName, Doctor.MiddleName, Doctor.LastName, Doctor.Specialty\r\n"
				+ "HAVING sum((Appointment.EndHour * 60) + Appointment.EndMinute) - sum((Appointment.StartHour * 60) + Appointment.StartMinute) > \r\n"
				+ "    (SELECT sum(AptMins.mins) / (SELECT count(*) FROM Doctor)\r\n"
				+ "     FROM (SELECT sum((Appointment.EndHour * 60) + Appointment.EndMinute) - sum((Appointment.StartHour * 60) + \r\n"
				+ "     Appointment.StartMinute) AS mins\r\n" + "           FROM Doctor JOIN AssignedTo\r\n"
				+ "           JOIN Appointment \r\n" + "           ON Doctor.DoctorID = AssignedTo.DoctorID \r\n"
				+ "           AND AssignedTo.AppointmentNum = Appointment.AppointmentNum\r\n"
				+ "           GROUP BY Doctor.DoctorID) AS AptMins)\r\n" + "ORDER BY BillingHours Desc";
		PreparedStatement statement = connection.prepareStatement(query);
		ResultSet set = statement.executeQuery();
		return set;
	}

	public ResultSet getTreatmentDoc() throws SQLException {
		String query = "SELECT Doctor.DoctorID, Doctor.FirstName, Doctor.MiddleName, Doctor.LastName, Doctor.Specialty, count(*) AS numTreatments\r\n"
				+ "FROM Doctor JOIN Prescribed\r\n" + "JOIN Treatment\r\n"
				+ "ON Doctor.DoctorID = Prescribed.DoctorID \r\n"
				+ "AND Prescribed.TreatmentID = Treatment.TreatmentId\r\n"
				+ "GROUP BY Doctor.DoctorID, Doctor.FirstName, Doctor.LastName, Doctor.Specialty\r\n"
				+ "ORDER BY numTreatments Desc, LastName Asc\r\n" + "LIMIT 3\r\n" + "OFFSET 2";
		PreparedStatement statement = connection.prepareStatement(query);
		ResultSet set = statement.executeQuery();
		return set;
	}

	public ResultSet getDoctorPatients(int doctorID) throws SQLException {
		String query = "SELECT Patient.PatientID, Patient.FirstName, Patient.MiddleName, Patient.LastName, Patient.BirthMonth, Patient.BirthDay, Patient.BirthYear, Patient.Insurance\r\n"
				+ "FROM Doctor JOIN Treats\r\n" + "JOIN Patient\r\n" + "ON Doctor.DoctorID = Treats.DoctorID\r\n"
				+ "AND Treats.PatientID = Patient.PatientID\r\n" + "WHERE Doctor.DoctorID = ?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, doctorID);
		ResultSet set = statement.executeQuery();
		return set;
	}

	// queries for patientTab
	public ResultSet getPatients() throws SQLException {
		String query = "SELECT * FROM Patient ORDER BY LastName Asc, FirstName Asc";
		PreparedStatement statement = connection.prepareStatement(query);
		ResultSet set = statement.executeQuery();
		return set;
	}

	public ResultSet getPatientTreats(int patientID) throws SQLException {
		String query = "SELECT t.TreatmentID, t.DoctorID, t.PatientID, t.Initials, t.Notes, t.Notes, t.Month, t.Day, t.Year\r\n"
				+ "FROM Patient JOIN Received\r\n" + "JOIN Treatment AS t\r\n"
				+ "ON Patient.PatientID = Received.PatientID \r\n" + "AND Received.TreatmentID = t.TreatmentID\r\n"
				+ "WHERE Patient.PatientID = ?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, patientID);
		ResultSet set = statement.executeQuery();
		return set;
	}

	public ResultSet getTotalTreatments(int patientID) throws SQLException {
		String query = "SELECT count(*)\r\n" + "FROM Patient JOIN Received\r\n" + "JOIN Treatment\r\n"
				+ "ON Patient.PatientID = Received.PatientID \r\n"
				+ "AND Received.TreatmentID = Treatment.TreatmentId\r\n" + "WHERE Patient.PatientID = ?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, patientID);
		ResultSet set = statement.executeQuery();
		return set;
	}

	// query for AppointmentTab
	public ResultSet getAppointments() throws SQLException {
		String query = "SELECT * FROM Appointment ORDER BY Year Desc, Month Asc, Day Asc";
		PreparedStatement statement = connection.prepareStatement(query);
		ResultSet set = statement.executeQuery();
		return set;
	}

	// query for treatmentTab
	public ResultSet getTreatments() throws SQLException {
		String query = "SELECT * FROM Treatment ORDER BY Year Desc, Month Asc, Day Asc";
		PreparedStatement statement = connection.prepareStatement(query);
		ResultSet set = statement.executeQuery();
		return set;
	}

	public void updatePatient(Patient p) throws SQLException {
		String input = "UPDATE Patient "
				+ "SET FirstName = ?, MiddleName = ?, LastName = ?, BirthMonth = ?, BirthDay = ?, BirthYear = ?, Insurance = ? "
				+ "WHERE Patient.PatientID = ?";
		PreparedStatement statement = connection.prepareStatement(input);
		statement.setString(1, p.getFirstName());
		statement.setString(2, p.getMiddleName());
		statement.setString(3, p.getLastName());
		statement.setInt(4, Integer.parseInt(p.getDOB().substring(0, 2), 10));
		statement.setInt(5, Integer.parseInt(p.getDOB().substring(3, 5), 10));
		statement.setInt(6, Integer.parseInt(p.getDOB().substring(6), 10));
		statement.setString(7, p.getInsurance());
		statement.setInt(8, p.getPatientID());
		statement.executeUpdate();
	}

	public void insertAppointment(Appointment a) throws SQLException {
		String input = "INSERT INTO Appointment (DoctorID, PatientID, Notes, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement statement = connection.prepareStatement(input);
		statement.setInt(1, a.getDoctorID());
		statement.setInt(2, a.getPatientID());
		statement.setString(3, a.getNotes());
		statement.setInt(4, Integer.parseInt(a.getDate().substring(0, 2), 10));
		statement.setInt(5, Integer.parseInt(a.getDate().substring(3, 5), 10));
		statement.setInt(6, Integer.parseInt(a.getDate().substring(6), 10));
		statement.setInt(7, Integer.parseInt(a.getStartTime().substring(0, 2), 10));
		statement.setInt(8, Integer.parseInt(a.getStartTime().substring(3), 10));
		statement.setInt(9, Integer.parseInt(a.getEndTime().substring(0, 2), 10));
		statement.setInt(10, Integer.parseInt(a.getEndTime().substring(3), 10));
		statement.executeUpdate();
		String query = "SELECT max(AppointmentNum) " + "FROM Appointment";
		PreparedStatement statement2 = connection.prepareStatement(query);
		ResultSet set = statement2.executeQuery();
		int AppointmentNum = set.getInt("max(AppointmentNum)");
		String input2 = "INSERT INTO Scheduled (AppointmentNum, PatientID)" + "VALUES (?, ?);";
		String input3 = "INSERT INTO AssignedTo (AppointmentNum, DoctorID)" + "VALUES (?, ?);";
		PreparedStatement statement3 = connection.prepareStatement(input2);
		PreparedStatement statement4 = connection.prepareStatement(input3);
		statement3.setInt(1, AppointmentNum);
		statement3.setInt(2, a.getPatientID());
		statement4.setInt(1, AppointmentNum);
		statement4.setInt(2, a.getDoctorID());
		statement3.executeUpdate();
		statement4.executeUpdate();
	}

	public void deleteAppointment(Appointment a) throws SQLException {
		String input = "DELETE FROM Appointment " + "WHERE AppointmentNum = ?";
		PreparedStatement statement = connection.prepareStatement(input);
		statement.setInt(1, a.getAppointmentNum());
		statement.executeUpdate();
	}
}
