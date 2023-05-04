package entities;

import datePackage.Date;
import exceptions.MedicalRecordException;
import exceptions.TreatmentException;

/**
 * The Treatment class is for storing data about a Patient’s medical treatments.
 * Treatments are stored in the ArrayList of treatments in the Patient class.
 * The attributes for this class include treatment date (Date), the start time
 * (Time), the end time (Time), and a String doctorID. This class has a
 * toString() method. This class has the appropriate getter and setter methods.
 * This class implements the TreatmentInterface and the ReportStateInterface.
 * Input to the constructor(s) will be the date (Date), start time (Time), end
 * time (Time) (or all 3 can be strings), and a String doctorID. However, if
 * date is not passed to the constructor, then the current date is used. The
 * class also has a unique id that is given to each object upon instantiation,
 * as well as a static int that tracks the total number of Treatments that have
 * been instantiated. The class also stores a doctor The Treatment class catches
 * various MedicalRecordExceptions and will potentially throw additional
 * MedicalRecordExceptions with appropriate and accurate messages.
 * 
 * @author John
 * @version 1
 */

public class Treatment {

	private int treatmentID;
	private Date treatmentDate;
	private int doctorID;
	private int patientID;
	private String initials;
	private String notes;

	public Treatment(int treatmentID, int doctorID, int patientID, int month, int day, int year, String initials,
			String notes) throws MedicalRecordException {
		try {
			setTreatmentID(treatmentID);
			setDoctorID(doctorID);
			setPatientID(patientID);
			setDate(month, day, year);
			setInitials(initials);
			setNotes(notes);
		} catch (Exception e) {
			throw new TreatmentException(e.getMessage());
		}
	}

	public void setTreatmentID(int treatmentID) {
		this.treatmentID = treatmentID;
	}

	/**
	 * This method validates the doctorID and sets it to the class attribute
	 * doctorID. If the doctorID has letters, is not 5 characters, or parses to
	 * zero, a TreatmentException is thrown.
	 * 
	 * @param doctorID A String containing five numbers that are to be set to the
	 *                 class attribute doctorID.
	 * @throws MedicalRecordException If the doctorID has letters, is not 5
	 *                                characters, or parses to zero, a
	 *                                TreatmentException is thrown.
	 */

	public void setDoctorID(int doctorID) throws MedicalRecordException {
		this.doctorID = doctorID;
	}

	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}

	public void setInitials(String initials) {
		this.initials = initials;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public void setDate(int month, int day, int year) throws MedicalRecordException {
		try {
			treatmentDate = new Date(month, day, year);
		} catch (Exception dateE) {
			throw new TreatmentException(dateE.getMessage());
		}
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
		formattedString = " Date: " + treatmentDate.getDate() + " Initaials: " + initials + " Notes: " + notes + " ID: "
				+ doctorID;
		return formattedString;
	}

	public int getTreatmentID() {
		return treatmentID;
	}

	/**
	 * This method returns the class attribute doctorID.
	 * 
	 * @return doctorID The String doctorID that stores a unique five character ID
	 *         consisting of only numbers.
	 */

	public int getDoctorID() {
		return doctorID;
	}

	public int getPatientID() {
		return patientID;
	}

	public String getInitials() {
		return initials;
	}

	public String getNotes() {
		return notes;
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
		return treatmentDate.toString();
	}
}