package task01;

import java.util.Scanner;

public enum Days {

	MONDAY(1), TUESDAY(2), WEDNESDAY(3), THURSDAY(4), FRIDAY(5), SATURDAY(6), SUNDAY(7);

	int serialNumber;

	Days(int serialNumber) {
		this.serialNumber = serialNumber;
	}

	public int getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}

	public static Days InputDay() {
		Scanner scanner = new Scanner(System.in);
		Integer returnedNumber = 0;
		Days foundDay = null;

		System.out.println("Enter the day number of the week:");
		if (scanner.hasNext()) {
			int nextInt = scanner.nextInt();
			if (nextInt > 0 && nextInt <= Days.values().length) {
				returnedNumber = nextInt;
			} else {
				System.err.println("The number should be in the range of 1-7!");
			}
		}

		for (Days day : Days.values()) {
			if (day.getSerialNumber() == returnedNumber) {
				foundDay = day;
			}
		}
		return foundDay;
	}
}