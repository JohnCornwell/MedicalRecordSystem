package exceptions;

public class TreatmentException extends MedicalRecordException {
	
	public TreatmentException() {
		super("Treatment Exception");
	}
	
	public TreatmentException (String message ) {
		super(message);
	}
}
