package exceptions;

import exceptions.MedicalRecordException;

public class DateException extends MedicalRecordException {

	public DateException()
	{
		super("Date Exception");
	}
	public DateException( String message )
	{
		super(message);
	}
}