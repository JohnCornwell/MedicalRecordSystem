package datePackage;
/*
*  Purpose:  Guarantee students have valid method signatures
*            in Date class for testing.
*/

import exceptions.DateException;

public interface DateInterface
{
    public abstract int getMonth();
	public abstract int getDay();
	public abstract int getYear();
	public abstract String getDate();

    public abstract void setMonth( int month ) throws DateException;
    public abstract void setDay( int day ) throws DateException;
    public abstract void setYear( int year ) throws DateException;
    public abstract void setDate( String strDate ) throws DateException;

    public abstract boolean isLeapYear( int aYear ) throws DateException;
}