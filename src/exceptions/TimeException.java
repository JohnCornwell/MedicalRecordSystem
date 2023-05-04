package exceptions;

import exceptions.MedicalRecordException;

public class TimeException extends MedicalRecordException {
	public TimeException() 
	{
		super("Time Exception");
	}
	
	public TimeException( String message ) {
		super(message);
	}

}
