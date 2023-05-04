package timePackage;
/*
*  Purpose:  Guarantee students have valid method signatures
*            in Date class for testing.
*/

import exceptions.TimeException;

public interface TimeInterface
{
    public int getHours();
	public int getMinutes();
	public String getTime();

    public void setHours( int hours ) throws TimeException;
    public void setMinutes( int minutes ) throws TimeException;

    public void setTime( String strTime ) throws TimeException;
}