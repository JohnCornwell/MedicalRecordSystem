package entities;

import datePackage.Date;
import exceptions.MedicalRecordException;
import exceptions.TreatmentException;
import timePackage.Time;

public class Appointment {

	private int appointmentNum;
	private Date appointmentDate;
	private Time startTime;
	private Time endTime;
	private int patientID;
	private int doctorID;
	private String notes;

	public Appointment(int appointmentNum, int doctorID, int patientID, String notes, int month, int day, int year,
			int startHour, int startMin, int endHour, int endMin) throws MedicalRecordException {
		try {
			setAppointmentNum(appointmentNum);
			setDate(month, day, year);
			setStartTime(startHour, startMin);
			setEndTime(endHour, endMin);
			setDoctorID(doctorID);
			setPatientID(patientID);
			setNotes(notes);
		} catch (Exception e) {
			throw new TreatmentException(e.getMessage());
		}
	}

	public void setAppointmentNum(int appointmentNum) {
		this.appointmentNum = appointmentNum;
	}

	public void setDoctorID(int doctorID) throws MedicalRecordException {
		this.doctorID = doctorID;
	}

	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}

	/**
	 * This method returns the class attributes doctorID, treatmentDate, startTime,
	 * and endTime in a formatted String Date: treatmentDate Start: startTime End:
	 * endTime.
	 * 
	 * @return formattedString A String with the class attributes treatmentDate,
	 *         startTime, and endTime in a formatted String Date: treatmentDate
	 *         Start: startTime End: endTime.
	 */

	public String reportState() {
		String formattedString; // the formatted String that is comprised of the class attributes
		formattedString = " Date: " + appointmentDate.getDate() + " Start: " + startTime + " End: " + endTime
				+ " Doctor ID: " + doctorID;
		return formattedString;
	}

	public int getAppointmentNum() {
		return appointmentNum;
	}

	/**
	 * This method returns the class attribute doctorID.
	 * 
	 * @return doctorID The int doctorID.
	 */

	public int getDoctorID() {
		return doctorID;
	}

	public int getPatientID() {
		return patientID;
	}

	/**
	 * This method overrides the Object class' toString method. It has the same
	 * functionality as the reportState method and will return a formatted String of
	 * the class attributes.
	 * 
	 * @return formattedString A String with the class attributes treatmentDate,
	 *         startTime, and endTime in a formatted String Date: treatMentDate
	 *         Start: startTime End: endTime.
	 */

	public String toString() {
		return reportState();
	}

	/**
	 * This method returns the class attribute Date in a formatted String.
	 * 
	 * @return treatmentDate The Date object stored by the class converted to a
	 *         String formatted as MM/DD/YYYY.
	 */

	public String getDate() {
		return appointmentDate.toString();
	}

	/**
	 * This method returns the start time that is stored by the class in a formatted
	 * String.
	 * 
	 * @return startTime The startTime Time object stored by the class as a String
	 *         formatted as HH:MM.
	 */

	public String getStartTime() {
		return startTime.toString();
	}

	/**
	 * This method returns the end time stored by the class in a formatted String.
	 * 
	 * @return endTime The endTime Time object stored by the class as a String
	 *         formatted as HH:MM.
	 */

	public String getEndTime() {
		return endTime.toString();
	}

	public String getNotes() {
		return notes;
	}

	public void setDate(int month, int day, int year) throws MedicalRecordException {
		try {
			appointmentDate = new Date(month, day, year);
		} catch (Exception dateE) {
			throw new TreatmentException(dateE.getMessage());
		}
	}

	public void setStartTime(int startHour, int startMin) throws MedicalRecordException {
		try {
			this.startTime = new Time(startHour, startMin);
		} catch (Exception timeE) {
			throw new TreatmentException(timeE.getMessage());
		}

	}

	public void setEndTime(int endHour, int endMin) throws MedicalRecordException {
		try {
			this.endTime = new Time(endHour, endMin);
		} catch (Exception timeE) {
			throw new TreatmentException(timeE.getMessage());
		}
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
}