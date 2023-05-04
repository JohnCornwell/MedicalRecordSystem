package exceptions;

public class PatientException extends MedicalRecordException {
	public PatientException() {
		super("Patient Exception");
	}

	public PatientException(String message) {
		super(message);
	}

}
