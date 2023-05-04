package entities;

import java.util.ArrayList;

import datePackage.Date;
import exceptions.DateException;
import exceptions.MedicalRecordException;
import exceptions.PatientException;
import exceptions.PersonException;

/**
 * The Patient class inherits from the Person class a specific type of person.
 * It contains the unique data for patients who receive treatment. This class
 * has attributes employer (String), insurance company (String), and an
 * ArrayList of Treatments. This is the most complicated of the classes;
 * especially, since it uses an ArrayList of class Treatment(s). Each Patient,
 * in this system can have unlimited treatments. It has the appropriate setters
 * (2), getters(2), and one constructor. The reportState method is used to
 * output the attribute data in a formatted String for display. The class also
 * has a unique id that is given to each object upon instantiation, as well as a
 * static int that tracks the total number of Patients that have been
 * instantiated. This class implements the PatientInterface and the
 * ReportStateInterface, and catches various MedicalRecordExceptions, and will
 * potentially throw additional MedicalRecordExceptions with appropriate and
 * accurate messages
 * 
 * @author John
 * @version 1
 */

public class Patient {

	private int patientID;
	private String insuranceCompany;
	private ArrayList<Treatment> treatments;
	private String firstName;
	private String middleName;
	private String lastName;
	private Date DOB;

	public Patient(int patientID, String firstName, String middleName, String lastName, int birthMonth, int birthDay,
			int birthYear, String insuranceCompany) throws MedicalRecordException {
		this.treatments = new ArrayList<Treatment>();
		try {
			setPatientID(patientID);
			setFirstName(firstName);
			setMiddleName(middleName);
			setLastName(lastName);
			setDOB((new Date(birthMonth, birthDay, birthYear)).toString());
			setInsurance(insuranceCompany);
		} catch (Exception e) {
			throw new PatientException(e.getMessage());
		}
	}

	public int getPatientID() {
		return patientID;
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

	/**
	 * This method returns the lastName and firstName attributes with a space and
	 * comma in the middle.
	 *
	 * @return fullName The lastName and firstName attributes with a space and comma
	 *         in the middle.
	 */

	public String getFullName() {
		return firstName + middleName + lastName;
	}

	/**
	 * This method returns the class attribute insuranceCompany.
	 * 
	 * @return insuranceCompany The String that describes the Patient's insurance
	 *         provider.
	 */

	public String getInsurance() {
		return insuranceCompany;
	}

	public String getDOB() {
		return DOB.toString();
	}

	/**
	 * This method returns a formatted String of all of the Treatment objects in the
	 * class ArrayList treatments.
	 * 
	 * @return formattedString A String containing the toString of all of the
	 *         Treatment objects in the ArrayList treatments.
	 */

	public String createFormattedAllTreatments() {
		String formattedString = "";
		for (int i = 0; i < treatments.size(); i++) {
			formattedString += "	Treatment " + (i + 1) + ": " + treatments.get(i).toString() + "\n";
		}
		return formattedString;
	}

	public void setPatientID(int patientID) {
		this.patientID = patientID;
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
	 * This method sets the class attribute insuranceCompany to the input String
	 * insuranceCompany. The method automatically makes the first letter in each
	 * word capital. If the insuranceCompany is null or less than 5 characters long,
	 * the method throws a PatientException.
	 * 
	 * @param insuranceCompany The String to set the class attribute
	 *                         insuranceCompany to.
	 * @throws MedicalRecordException If the insuranceCompany is null or less than 5
	 *                                characters long, the method throws a
	 *                                PatientException.
	 */

	public void setInsurance(String insuranceCompany) throws MedicalRecordException {
		if (insuranceCompany == null) {
			throw new PatientException("<The insurance company cannot be null.>");
		} else if (insuranceCompany.length() < 5) {
			throw new PatientException("<The insurance company must be at least 5 characters long.>");
		}
		String finalInsurance = "";
		String input = insuranceCompany.trim(); // gets rid of excess white space.
		char[] insuranceChars = new char[input.length()];
		// makes a new char array with the length being the length of the input string.
		for (int i = 0; i < input.length(); i++) {
			insuranceChars[i] = Character.toLowerCase(input.charAt(i));
			// adds each character to insuranceChars and makes it lower case.
		}
		// This will capitalize each letter after a space along with the first letter.
		if (insuranceChars[0] >= 'a' && insuranceChars[0] <= 'z'
				|| insuranceChars[0] >= 'A' && insuranceChars[0] <= 'Z') {
			insuranceChars[0] = Character.toUpperCase(insuranceChars[0]);
		}
		for (int i = 0; i < input.length(); i++) {
			// if there is a space, and the next character is a letter, it is capitalized.
			if (insuranceChars[i] == ' ') {
				if (insuranceChars[i + 1] >= 'a' && insuranceChars[i + 1] <= 'z'
						|| insuranceChars[i + 1] >= 'A' && insuranceChars[i + 1] <= 'Z') {
					insuranceChars[i + 1] = Character.toUpperCase(insuranceChars[i + 1]);
				}
			}
		}
		// adds the char array back to the string that will be set to the class String
		// insuranceCompany.
		for (int j = 0; j < input.length(); j++) {
			finalInsurance += insuranceChars[j];
		}
		this.insuranceCompany = finalInsurance;
	}

	/**
	 * This method takes in a String dateOfBirth and sets it to the class attribute
	 * dateOfBirth. If the String that is input is not a valid date, the method
	 * throws a PersonException.
	 * 
	 * @param dateOfBirth A String variable that contains a date to set the class
	 *                    attribute dateOfBirth to after it is converted to a Date.
	 * @throws MedicalRecordException If the input is null, or if the String
	 *                                dateOfBirth that was input is not formatted
	 *                                MM/DD/YYYY, the method will throw an
	 *                                PersonEception.
	 */

	public void setDOB(String dateOfBirth) throws MedicalRecordException {
		if (dateOfBirth == null) {
			throw new PersonException("<The date cannot be null.>");
		}
		// Object needs to be instantiated. The setMonth and setDay methods check the
		// Date
		// attributes that are null at this point.
		int strMonth;
		int strDay;
		int strYear;
		if (dateOfBirth.charAt(2) == '/' && dateOfBirth.charAt(5) == '/' && dateOfBirth.length() == 10) {
			// checks for correct length of each field
			strMonth = Integer.parseInt(dateOfBirth.substring(0, 2));// extracts month from String
			strDay = Integer.parseInt(dateOfBirth.substring(3, 5));// extracts day from String
			strYear = Integer.parseInt(dateOfBirth.substring(6));// extracts year from String
			try {
				this.DOB = new Date(strMonth, strDay, strYear);
			} catch (Exception e) {
				throw new PersonException(e.getMessage());
			}
		} else {
			throw new DateException("< " + dateOfBirth + " - The date must be formatted MM/DD/YYYY.>");
		}
	}

	/**
	 * This method returns all of the class attributes in a formatted String. This
	 * includes a list of all of the Patients treatments as a toString.
	 */

	public String reportState() {
		String formattedString = String.format("%-20s", "Patient:");
		formattedString += String.format("%-25s", getFullName());
		formattedString += String.format("%-20s", "Born:");

		formattedString += String.format("%-10s", "");
		formattedString += String.format("%-20s", "Insurance:");
		formattedString += String.format("%-20s", getInsurance() + "\n");
		formattedString += createFormattedAllTreatments();
		return formattedString;
	}

	/**
	 * This method replaces the Object class' toString method and has the same
	 * functionality as the reportState method.
	 */

	public String toString() {
		return reportState();
	}
}
