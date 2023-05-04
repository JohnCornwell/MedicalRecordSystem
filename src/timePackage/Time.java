package timePackage;

import java.time.LocalTime;

import exceptions.TimeException;

public class Time {
	private int hours;
	private int minutes;

	/**
	 * This constructor is the default constructor that will set the class
	 * attributes hours and minutes to the local hour and minute because no input
	 * was given to the class.
	 */

	public Time() {
		LocalTime time = LocalTime.now(); // creates a new LocalTime object with the current time.
		hours = time.getHour();
		minutes = time.getMinute();
	}

	/**
	 * This constructor takes in two int values, hour and minute, and sets them to
	 * the corresponding class attributes if they fall into the proper range (hour -
	 * 0 to 23) and (minute - 0 to 59).
	 * 
	 * @param hour   The hour to be set to the class attribute hour.
	 * @param minute The minute to be set to the class attribute minute.
	 * @throws TimeException If the hour and/or minute is outside of their
	 *                       acceptable range, an TimeException is thrown.
	 */

	public Time(int hour, int minute) throws TimeException {
		try {
			setHours(hour);
			setMinutes(minute);
		} catch (Exception e) {
			LocalTime time = LocalTime.now(); // creates a new LocalTime object with the current time.
			hours = time.getHour();
			minutes = time.getMinute();
			throw new TimeException(e.getMessage());
		}

	}

	/**
	 * This constructor takes in a String with the format HH:MM. If the sTring is
	 * formatted properly, the HH is a valid hour, and the MM is a valid minute, the
	 * constructor will set the hour and minute attributes to the input given. If
	 * the String is not formatted correctly or if the HH or MM are not valid, the
	 * constructor will throw an TimeException.
	 * 
	 * @param inputTime A String that is formatted HH:MM and is to modify the hours
	 *                  and minutes class attributes.
	 * @throws TimeException If the String is not formatted correctly or if the HH
	 *                       or MM are not valid, the constructor will throw an
	 *                       TimeException.
	 */

	public Time(String inputTime) throws TimeException {
		if (inputTime == null || inputTime.equals("")) {
			throw new TimeException("The time cannot be null");
		}
		if (inputTime.length() != 5) { // checks for the correct length.
			throw new TimeException("<" + inputTime + " - The input time is not the correct length (HH:MM).>");
		}
		if (inputTime.charAt(2) != ':') { // checks for format with a colon.
			throw new TimeException("<" + inputTime + " - The input time must be formatted with a colon (HH:MM).>");
		}
		try { // attempts to modify class attributes.
			setHours(Integer.parseInt(inputTime.substring(0, 2)));
			setMinutes(Integer.parseInt(inputTime.substring(3)));
		} catch (TimeException wrongTime) { // sets the hours and minutes to the current time.
			LocalTime time = LocalTime.now();
			hours = time.getHour();
			minutes = time.getMinute();
			throw wrongTime;
		}
	}

	/**
	 * This method returns the valid hour stored in the class attribute hours.
	 * 
	 * @return hours The hour value stored by the class.
	 */

	public int getHours() {
		return hours;
	}

	/**
	 * This method returns the minute value stored by the class attribute minutes.
	 * 
	 * @return minutes The minute value stored by the class.
	 */

	public int getMinutes() {
		return minutes;
	}

	/**
	 * This method returns the current time that is stored by the class attributes
	 * hours and minutes. It will return the time in the format HH:MM with zeroes in
	 * front of any single-digit value.
	 * 
	 * @return currentTime The current time according to the class attributes that
	 *         is formatted as HH:MM.
	 */

	public String getTime() {
		String currentTime = "";
		if (hours < 10 && minutes < 10) { // If the hours and minutes are 1 digit the program adds a 0 to each
			currentTime += "0" + hours + ":" + "0" + minutes;
		} else if (hours < 10) { // If hours is one digit.
			currentTime += "0" + hours + ":" + minutes;
		} else if (minutes < 10) { // If minutes is one digit.
			currentTime += hours + ":" + "0" + minutes;
		} else {
			currentTime += hours + ":" + minutes;
		}
		return currentTime;
	}

	/**
	 * This method replaces the java.lang toString method and is used by the user to
	 * return the valid time that is stored by the class attributes in the format
	 * HH:MM. This method uses the getTime method to perform this task.
	 * 
	 * @return currentTime The current time according to the class attributes that
	 *         is formatted as HH:MM.
	 */

	public String toString() {
		return getTime();
	}

	/**
	 * This method takes in a int value and attempts to set the class attribute
	 * hours to it. If the input value is not a valid hour, the method throws an
	 * TimeException.
	 * 
	 * @param hours The value that is set to the class attribute hours.
	 * @throws TimeException If the input value is not a valid hour, the method
	 *                       throws an TimeException.
	 */

	public void setHours(int hours) throws TimeException {
		if (hours > 23 || hours < 0) { // hours is outside of range 0 to 23.
			throw new TimeException("<" + hours + " - The input was not a valid hour in the range 0 to 23.>");
		}
		this.hours = hours;
	}

	/**
	 * This method sets the class attribute minutes to the input if the input is
	 * within the range 0 to 59. If the input is outside the range 0 to 59, the
	 * method throws an TimeException.
	 * 
	 * @param minutes The value that is set to the class attribute minutes.
	 * @throws TimeException If the input is outside the range 0 to 59, the method
	 *                       throws an TimeException.
	 */

	public void setMinutes(int minutes) throws TimeException {
		if (minutes > 59 || minutes < 0) { // minutes is outside of range 0 to 59.
			throw new TimeException("<" + minutes + " - The input minute is outside of the range 0 to 59.>");
		}
		this.minutes = minutes;
	}

	/**
	 * This method sets the class attributes hours and minutes to the input time
	 * HH:MM. If the String that is input is not formatted HH:MM or if the hours or
	 * minutes are not valid, the method throws an TimeException. Before the
	 * exception is thrown, the method restores the class attributes to their
	 * previous values.
	 * 
	 * @param strTime The String containing the time to set the class attributes to.
	 * @throws TimeException If the String that is input is not formatted HH:MM or
	 *                       if the hours or minutes are not valid, the method
	 *                       throws an TimeException.
	 */

	public void setTime(String strTime) throws TimeException {
		int currentHours = hours; // data preservation
		int currentMinutes = minutes; // data preservation
		if (strTime.length() != 5) { // checks for the correct length.
			throw new TimeException("<" + strTime + " - The input time is not the correct length (HH:MM).>");
		}
		if (strTime.charAt(2) != ':') { // checks for format with a colon.
			throw new TimeException("<" + strTime + " - The input time must be formatted with a colon (HH:MM).>");
		}
		try { // attempts to modify class attributes.
			setHours(Integer.parseInt(strTime.substring(0, 2)));
			setMinutes(Integer.parseInt(strTime.substring(3)));
		} catch (TimeException wrongTime) { // restores previous values.
			hours = currentHours;
			minutes = currentMinutes;
			throw wrongTime;
		}
	}

}
