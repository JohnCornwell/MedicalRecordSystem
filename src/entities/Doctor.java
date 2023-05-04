package entities;

import exceptions.DoctorException;
import exceptions.MedicalRecordException;
import exceptions.PersonException;
import javafx.beans.property.SimpleStringProperty;

/**
 * This class has a toString() method. This class implements the
 * ReportStateInterface, and catches various MedicalRecordExceptions and will
 * potentially throw additional MedicalRecordExceptions with appropriate and
 * accurate messages.
 * 
 * @author John
 * @version 1
 */

public class Doctor {

	private int doctorID;
	private String specialty;
	private String firstName;
	private String middleName;
	private String lastName;
	private int billingHours;
	private int treatmentsPerformed;

	/**
	 * This constructor takes in a String firstName, lastName, and specialty and
	 * sets them to their corresponding class attributes. It also takes in a Date
	 * dateOfBirth and hireDate and sets these to their appropriate class
	 * attributes. After all of the class values are set, the constructor adds 1 to
	 * the static attribute numberObjects which tracks the total number of objects
	 * instantiated, and then sets this new number to the id which is unique for
	 * each object. If the dateOfBirth, hireDate, or String values are null, a
	 * DoctorException will be thrown. If the specialty is not valid, a
	 * DoctorException will be thrown.
	 * 
	 * @param firstName   The String to set the class attribute firstName to.
	 * @param lastName    The String to set the class attribute lastName to.
	 * @param dateOfBirth The Date to set the class attribute dateOfBirth to.
	 * @param specialty   The String to set the class attribute specialty to.
	 * @param hireDate    The Date to set the class attribute hireDate to.
	 * @throws MedicalRecordException If the dateOfBirth, hireDate, or String values
	 *                                are null, a DoctorException will be thrown. If
	 *                                the specialty is not valid, a DoctorException
	 *                                will be thrown.
	 */

	public Doctor(int doctorID, String firstName, String middleName, String lastName, String specialty)
			throws MedicalRecordException {
		try {
			setDoctorID(doctorID);
			setFirstName(firstName);
			setMiddleName(middleName);
			setLastName(lastName);
			setSpecialty(specialty);
		} catch (Exception e) {
			throw new DoctorException(e.getMessage());
		}
	}

	public int getDoctorID() {
		return doctorID;
	}

	/**
	 * This method returns the firstName attribute of the class.
	 * 
	 * @return firstName The first name of the person.
	 */

	public String getFirstName() {
		return firstName;
	}

	/**
	 * This method returns the middleName attribute of the class.
	 * 
	 * @return firstName The first name of the person.
	 */

	public String getMiddleName() {
		return middleName;
	}

	/**
	 * This method returns the lastName attribute of the class.
	 * 
	 * @return lastName The last name of the person.
	 */

	public String getLastName() {
		return lastName;
	}

	public int getBillingHours() {
		return billingHours;
	}

	public int getTreatmentsPerformed() {
		return treatmentsPerformed;
	}

	public void setDoctorID(int doctorID) {
		this.doctorID = doctorID;
	}

	/**
	 * This method returns the lastName and firstName attributes with a space and
	 * comma in the middle.
	 *
	 * @return fullName The lastName and firstName attributes with a space and comma
	 *         in the middle.
	 */

	public SimpleStringProperty getFullName() {
		return new SimpleStringProperty(firstName + middleName + lastName);
	}

	/**
	 * This method takes in a String firstName and sets it to the class attribute
	 * firstName.
	 * 
	 * @param firstName The String that is set to the class attribute firstName.
	 * @throws MedicalRecordException If the input is null, the method will throw a
	 *                                PersonException.
	 */

	public void setFirstName(String firstName) throws MedicalRecordException {
		if (firstName == null || firstName.equals("")) {
			throw new PersonException("<The firstName cannot be null.>");
		}
		this.firstName = firstName;
	}

	/**
	 * This method takes in a String middleName and sets it to the class attribute
	 * firstName.
	 * 
	 * @param middleName The String that is set to the class attribute middleName.
	 */

	public void setMiddleName(String middleName) throws MedicalRecordException {
		this.middleName = middleName;
	}

	/**
	 * This method takes in a String lastName and sets it to the class attribute
	 * lastName.
	 * 
	 * @param lastName The String that is set to the class attribute lastName.
	 * @throws MedicalRecordException If the input is null, the method will throw a
	 *                                PersonException.
	 */

	public void setLastName(String lastName) throws MedicalRecordException {
		if (lastName == null || firstName.equals("")) {
			throw new PersonException("<The lastName cannot be null.>");
		}
		this.lastName = lastName;
	}

	/**
	 * This method returns the class attribute specialty.
	 * 
	 * @return specialty The class attribute specialty that describes the doctor's
	 *         specific skill.
	 */

	public String getSpecialty() {
		return specialty;
	}

	/**
	 * This method takes in a String specialty and sets it to the class attribute
	 * specialty. It will automatically make the first letter in every word capital.
	 * 
	 * @param specialty The String to set the class attribute specialty to.
	 * @throws MedicalRecordException If the input is null or less than 3
	 *                                characters, the method throws a
	 *                                DoctorException.
	 */

	public void setSpecialty(String specialty) throws MedicalRecordException {
		if (specialty == null) {
			throw new DoctorException("<The specialty cannot be null.>");
		} else if (specialty.length() < 2) {
			throw new DoctorException("<The specialty must be at least 3 characters long.>");
		}
		String finalSpecialty = "";
		char[] specialtyChars = new char[specialty.length()];

		for (int i = 0; i < specialty.length(); i++) {
			specialtyChars[i] = Character.toLowerCase(specialty.charAt(i));
			// adds each character to str1 and makes it lower case.
		}

		specialtyChars[0] = Character.toUpperCase(specialtyChars[0]);
		for (int i = 0; i < specialty.length(); i++) {
			if (specialtyChars[i] == ' ') {
				specialtyChars[i + 1] = Character.toUpperCase(specialtyChars[i + 1]);
			}
		}
		for (int j = 0; j < specialty.length(); j++) {
			finalSpecialty += specialtyChars[j];
		}
		this.specialty = finalSpecialty;
	}

	public void setBillingHours(int billingHours) {
		this.billingHours = billingHours;
	}

	public void setTreatmentsPerformed(int treatmentsPerformed) {
		this.treatmentsPerformed = treatmentsPerformed;
	}

	/**
	 * This method overrides the Object class' toString method. It implements the
	 * reportState method to return a formatted String using all of the class
	 * attributes to inform the user of the Doctor's name, date of birth, specialty,
	 * and hire date.
	 * 
	 * @return formattedString A formatted String containing all of the class's
	 *         attributes.
	 */

	public String toString() {
		return reportState();
	}

	/**
	 * This method takes the class attributes and returns them in a formatted String
	 * that describes the Doctor. Ex: Doctor: Washington, Peter Specialty: Internal
	 * Medicine
	 * 
	 * @return formattedString The String containing the class attributes.
	 */
	public String reportState() {
		String formattedString = String.format("%-11s", "Doctor:");
		formattedString += String.format("%-25s", getFullName());
		formattedString += String.format("%10s", " ");
		formattedString += String.format("%-11s", "Specialty:");
		formattedString += String.format("%-25s", getSpecialty());
		return formattedString;
	}
}