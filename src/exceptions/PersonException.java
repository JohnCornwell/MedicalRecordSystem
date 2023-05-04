package exceptions;

public class PersonException extends MedicalRecordException {
	public PersonException() {
		super("Person Exception");
	}

	public PersonException(String message) {
		super(message);
	}

}
