package exceptions;
//package exceptions;

/*
*   Allows all exceptions for the this Medical Record System
*   to have a unique superclass that will be different than other
*   java exceptions.
*/

public class MedicalRecordException extends Exception {
	/*** Constructors ***/

	public MedicalRecordException() {
		super("MedicalRecordException!");
	}

	public MedicalRecordException(String message) {
		super("MedicalRecordException: " + message);
	}
}