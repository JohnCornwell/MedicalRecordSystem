package exceptions;

public class DoctorException extends MedicalRecordException {
	public DoctorException() {
		super("Doctor Exception");
	}

	public DoctorException(String message) {
		super(message);
	}

}
