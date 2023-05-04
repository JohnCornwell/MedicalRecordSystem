package connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.Appointment;
import entities.Doctor;
import entities.Patient;
import entities.Treatment;
import exceptions.MedicalRecordException;

public class MedicalRecord {

	private static Database db;

	public MedicalRecord() {
		db = Database.getInstance();
	}

	public void connect() {
		try {
			db.connect();
		} catch (SQLException e) {
			System.out.println("Connection failed");
			e.printStackTrace();
		}
		System.out.println("Connected");
	}

	public void disconnect() {
		try {
			db.disconnect();
		} catch (SQLException e) {
			System.out.println("Disconnect failed");
			e.printStackTrace();
		}
		System.out.println("Disconnected");
	}

	public ArrayList<Doctor> getDoctors() throws SQLException, MedicalRecordException {
		ResultSet set = db.getDoctors();
		ArrayList<Doctor> results = new ArrayList<Doctor>();
		while (set.next()) {
			Doctor d = new Doctor(set.getInt("DoctorID"), set.getString("FirstName"), set.getString("MiddleName"),
					set.getString("LastName"), set.getString("Specialty"));
			results.add(d);
		}
		return results;
	}

	public ArrayList<Doctor> getBillingDoc() throws SQLException, MedicalRecordException {
		ResultSet set = db.getBillingDoc();
		ArrayList<Doctor> results = new ArrayList<Doctor>();
		while (set.next()) {
			Doctor d = new Doctor(set.getInt("DoctorID"), set.getString("FirstName"), set.getString("MiddleName"),
					set.getString("LastName"), set.getString("Specialty"));
			d.setBillingHours(set.getInt("BillingHours"));
			results.add(d);
		}
		return results;
	}

	public ArrayList<Doctor> getTreatmentDoc() throws MedicalRecordException, SQLException {
		ResultSet set = db.getTreatmentDoc();
		ArrayList<Doctor> results = new ArrayList<Doctor>();
		while (set.next()) {
			Doctor d = new Doctor(set.getInt("DoctorID"), set.getString("FirstName"), set.getString("MiddleName"),
					set.getString("LastName"), set.getString("Specialty"));
			d.setTreatmentsPerformed(set.getInt("numTreatments"));
			results.add(d);
		}
		return results;
	}

	public ArrayList<Patient> getDoctorPatients(int doctorID) throws MedicalRecordException, SQLException {
		ResultSet set = db.getDoctorPatients(doctorID);
		ArrayList<Patient> results = new ArrayList<Patient>();
		while (set.next()) {
			Patient p = new Patient(set.getInt("PatientID"), set.getString("FirstName"), set.getString("MiddleName"),
					set.getString("LastName"), set.getInt("birthMonth"), set.getInt("birthDay"),
					set.getInt("birthYear"), set.getString("Insurance"));
			results.add(p);
		}
		return results;
	}

	public ArrayList<Patient> getPatients() throws MedicalRecordException, SQLException {
		ResultSet set = db.getPatients();
		ArrayList<Patient> results = new ArrayList<Patient>();
		while (set.next()) {
			Patient p = new Patient(set.getInt("PatientID"), set.getString("FirstName"), set.getString("MiddleName"),
					set.getString("LastName"), set.getInt("birthMonth"), set.getInt("birthDay"),
					set.getInt("birthYear"), set.getString("Insurance"));
			results.add(p);
		}
		return results;
	}

	public ArrayList<Treatment> getPatientTreats(int patientID) throws MedicalRecordException, SQLException {
		ResultSet set = db.getPatientTreats(patientID);
		ArrayList<Treatment> results = new ArrayList<Treatment>();
		while (set.next()) {
			Treatment t = new Treatment(set.getInt("TreatmentID"), set.getInt("DoctorID"), set.getInt("PatientID"),
					set.getInt("Month"), set.getInt("Day"), set.getInt("Year"), set.getString("Initials"),
					set.getString("Notes"));
			results.add(t);
		}
		return results;
	}

	public int getTotalTreatments(int patientID) throws SQLException {
		ResultSet set = db.getTotalTreatments(patientID);
		return set.getInt("count(*)");
	}

	public ArrayList<Appointment> getAppointments() throws MedicalRecordException, SQLException {
		ResultSet set = db.getAppointments();
		ArrayList<Appointment> results = new ArrayList<Appointment>();
		while (set.next()) {
			Appointment a = new Appointment(set.getInt("AppointmentNum"), set.getInt("DoctorID"),
					set.getInt("PatientID"), set.getString("Notes"), set.getInt("Month"), set.getInt("Day"),
					set.getInt("Year"), set.getInt("StartHour"), set.getInt("StartMinute"), set.getInt("EndHour"),
					set.getInt("EndMinute"));
			results.add(a);
		}
		return results;
	}

	public ArrayList<Treatment> getTreatments() throws MedicalRecordException, SQLException {
		ResultSet set = db.getTreatments();
		ArrayList<Treatment> results = new ArrayList<Treatment>();
		while (set.next()) {
			Treatment t = new Treatment(set.getInt("TreatmentID"), set.getInt("DoctorID"), set.getInt("PatientID"),
					set.getInt("Month"), set.getInt("Day"), set.getInt("Year"), set.getString("Initials"),
					set.getString("Notes"));
			results.add(t);
		}
		return results;
	}

	public void updatePatient(Patient p) throws SQLException {
		db.updatePatient(p);
	}

	public void deleteAppointment(Appointment a) throws SQLException {
		db.deleteAppointment(a);
	}

	public void insertAppointment(Appointment a) throws SQLException {
		db.insertAppointment(a);
	}
}
