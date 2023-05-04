package datePackage;

import java.time.LocalDate;

import exceptions.DateException;

public class Date implements DateInterface {

	private int month;
	private int day;
	private int year;
	LocalDate cdate = LocalDate.now();

	/**
	 * This is the default constructor for the Date class. It will set the month,
	 * day, and year variables to the current date.
	 */

	public Date() {
		month = cdate.getMonthValue(); // current month
		day = cdate.getDayOfMonth(); // current day
		year = cdate.getYear(); // current year
	}

	/**
	 * This is another constructor that takes in a month, date, and year value. If
	 * these values form a valid date, they become the new month, day, and year
	 * variables. If the values are invalid, the current date is what the class
	 * variables become.
	 * 
	 * @param month The tested month value.
	 * @param day   The tested day value.
	 * @param year  The tested year value.
	 * @throws DateException If the date is invalid, an DateException is thrown.
	 */

	public Date(int month, int day, int year) throws DateException {
		try {
			if (year > 9999 || year < 1000) {
				throw new DateException("<" + year + " - Year is outside of acceptable range 1000 to 9999.");
			}
			this.year = year;
			if (month < 1 || month > 12) { // If the month number is invalid, the method throws an DateException.
				throw new DateException("<" + month + " - Month is not between 1 and 12.>");
			} else {
				this.month = month;
			}
			setDay(day);
		} catch (DateException e) {
			month = cdate.getMonthValue();
			day = cdate.getDayOfMonth();
			year = cdate.getYear();
			throw e;
		}
	}

	/**
	 * A String date is input with the format mm/dd/yyyy. If the String is a valid
	 * date, the class variables are set to this date. If the String is not a valid
	 * date, the class variables are set to the current date.
	 * 
	 * @param date The date that is to be parsed into three ints.
	 * @throws DateException If the date is invalid, an DateException is thrown.
	 */

	public Date(String date) throws DateException {
		int strMonth;
		int strDay;
		int strYear;
		if (date == null || date.equals("")) {
			throw new DateException("The Date cannot be null");
		}
		if (date.length() == 10 && date.charAt(2) == '/' && date.charAt(5) == '/') { // checks for correct length of
																						// each field.
			try { // attempts to set the month, day, and year fields, but will set them to the
					// current date if the attempt fails.
				strMonth = Integer.parseInt(date.substring(0, 2));
				strDay = Integer.parseInt(date.substring(3, 5));
				strYear = Integer.parseInt(date.substring(6, 10));
				if (strYear > 9999 || strYear < 1000) {
					throw new DateException("<" + strYear + " - Year is outside of acceptable range 1000 to 9999.");
				}
				this.year = strYear;
				if (strMonth < 1 || strMonth > 12) { // If the month number is invalid, the method throws an
														// DateException.
					throw new DateException("<" + strMonth + " - Month is not between 1 and 12.>");
				} else {
					this.month = strMonth;
				}
				setDay(strDay);
			} catch (DateException invalidDate) {
				month = cdate.getMonthValue();
				day = cdate.getDayOfMonth();
				year = cdate.getYear();
				throw new DateException(invalidDate.getMessage());
			}
		} else {
			throw new DateException("< " + date + " - The date must be formatted MM/DD/YYYY.>");
		}
	}

	/**
	 * This method returns the current month value.
	 * 
	 * @return month The current month.
	 */

	public int getMonth() {
		return month;
	}

	/**
	 * This method returns the current day value.
	 * 
	 * @return day The current day.
	 */

	public int getDay() {
		return day;
	}

	/**
	 * This method returns the current year value.
	 * 
	 * @return year The current year.
	 */

	public int getYear() {
		return year;
	}

	/**
	 * This method returns the date stored in the class attributes as a String. It
	 * is formatted as mm/dd/yyyy. If the day or month is one digit, a 0 will be
	 * placed at the front. The date is guaranteed to be valid by the other mutator
	 * and methods in the class.
	 * 
	 * @return ans The date stored in the attributes as a String in the form
	 *         mm/dd/yyyy.
	 */

	public String getDate() {
		String ans = "";
		if (day < 10 && month < 10) { // If the day and month are 1 digit the program adds a 0 to each
			ans += "0" + month + "/" + "0" + day + "/" + year;
		} else if (month < 10) { // If the day is one digit.
			ans += "0" + month + "/" + day + "/" + year;
		} else if (day < 10) { // If the month is one digit.
			ans += month + "/" + "0" + day + "/" + year;
		} else {
			ans += month + "/" + day + "/" + year;
		}
		return ans;
	}

	/**
	 * This method overrides the toString method in the java.lang class. The method
	 * is the same as the getDate method and is used by the user.
	 * 
	 * @return date The date stored in the attributes as a String in the form
	 *         mm/dd/yyyy.
	 */

	public String toString() {
		return getDate();
	}

	/**
	 * This method is used to set the month. If the month input is not a valid
	 * number or the month cannot correspond to the day currently stored in the
	 * attributes, an DateException is thrown.
	 * 
	 * @param month The month that is meant to be stored in the class attributes.
	 * @throws DateException If the month input is not a valid number or the month
	 *                       cannot correspond to the day currently stored in the
	 *                       attributes, an DateException is thrown.
	 */

	public void setMonth(int month) throws DateException {
		if (month < 1 && month > 12) { // If the month number is invalid, the method throws an DateException.
			if (month == 4 || month == 6 || month == 9 || month == 11) { // months with 30 days
				if (day < 31) {
					this.month = month;
				} else { // The stored day must be 31 so these months aren't valid.
					throw new DateException("<" + month + " - The input month does not have 31 days>");
				}
			} else if (month == 2) { // Checks for a leap year if it is February
				if (day == 29) {
					if (isLeapYear(year)) {
						this.month = month;
					} else if (day > 29) { // Feb. can't have more than 29 days
						throw new DateException("<" + month + " - February can have 29 days at most.>");
					} else if (day < 29) {// days under 29 are always valid for this month.
						this.month = month;
					} else { // this means that the day is 29 but it isn't a leap year
						throw new DateException("<" + month + " - It is not a leap year.>");
					}
				}
			} else {// The stored day is always valid for any month and the months that weren't
					// checked can hold 31.
				this.month = month;
			}
		} else { // This is the exception thrown if the first conditional is false.
			throw new DateException("<" + month + " - Month is not between 1 and 12.>");
		}
	}

	/**
	 * This method takes in an int value and attempts to set it to the day
	 * attribute. If the day is not valid for the stored month, an DateException is
	 * thrown.
	 * 
	 * @param day The day value to be stored.
	 * @throws DateException If the day is not valid for the stored month, an
	 *                       DateException is thrown.
	 */

	public void setDay(int day) throws DateException {
		if (day > 31 || day < 1) { // invalid days for any month.
			throw new DateException("<" + day + " - Day must be between 1 and 31.>");
		}
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {// months
																												// with
																												// 31
																												// days
			this.day = day;
		} else if (month == 4 || month == 6 || month == 9 || month == 11) { // months with 30 days
			if (day == 31) { // too large
				throw new DateException("<" + day + " - Day is too large for current month.>");
			} else {
				this.day = day;
			}
		} else if (month == 2) { // February
			if (day > 29) { // too large for February
				throw new DateException("<" + day + " - Day is too large for February.>");
			} else if (day < 29) { // These days are always valid
				this.day = day;
			} else if (day == 29) { // must check for leap year if day is 29
				if (isLeapYear(year)) {
					this.day = day;
				} else { // not leap year
					throw new DateException("<" + day + " - It is not a leap year.>");
				}
			}
		}
	}

	/**
	 * This method takes in an int value and attempts to set it to the class
	 * attribute.
	 * 
	 * @param year The year value to be stored.
	 * @throws DateException If the year is outside the range 1000 to 9999 an
	 *                       DateException is thrown.
	 */

	public void setYear(int year) throws DateException {
		if (year > 9999 || year < 1000) {
			throw new DateException("<" + year + " - Year is outside of acceptable range 1000 to 9999.");
		}
		this.year = year;
	}

	public void setDate(String strDate) throws DateException {
		int strMonth;
		int strDay;
		int strYear;
		int currentMonth = this.month;
		int currentDay = this.day;
		int currentYear = this.year;
		if (strDate.charAt(2) == '/' && strDate.charAt(5) == '/' && strDate.length() == 10) { // checks for correct
																								// length of each field.
			try { // attempts to modify stored values
				strMonth = Integer.parseInt(strDate.substring(0, 2));// extracts month from String
				strDay = Integer.parseInt(strDate.substring(3, 5));// extracts day from String
				strYear = Integer.parseInt(strDate.substring(6));// extracts year from String
				setYear(strYear);
				setMonth(strMonth);
				setDay(strDay);
			} catch (DateException invalidDate) { // if any values from the String were invalid, the previous values are
													// restored
				this.month = currentMonth;
				this.day = currentDay;
				this.year = currentYear;
				throw new DateException("<" + strDate
						+ " - The date entered had an incorrect field mm/dd/yyyy. Check to make sure the day can exist in the month.");
			}
		} else {
			throw new DateException("<" + strDate
					+ " - The date entered wasn't the correct format mm/dd/yyyy. Make sure single digit days and months have a 0 at the front.");
		}
	}

	/**
	 * This method checks to see if the input year is a leap year. If an invalid
	 * year is input an DateException is thrown.
	 * 
	 * @param aYear The year that is checked.
	 * @return boolean Will return true if it is a leap year or false if not.
	 * @throws DateException If an invalid year is input an DateException is thrown.
	 */

	public boolean isLeapYear(int aYear) throws DateException {
		if (aYear < 1000 || aYear > 9999) {
			throw new DateException("<" + aYear + " - Year is outside of the acceptable range 1000 to 9999");
		}
		if (aYear % 4 == 0) { // if year is divisible by 4
			if (aYear % 100 == 0) { // if year is divisible by 100
				if (aYear % 400 == 0) { // if year is divisible by 400
					return true;
				}
				return false;
			}
			return true;
		}
		return false;
	}
}