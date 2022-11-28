package task01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;

public class Time implements Comparable<Time> {

	int min;
	int hour;

	public Time(int hour, int min) {
		super();
		this.min = min;
		this.hour = hour;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public static Time sumTime(Time time, Time sumTime) {
		int minutesSum = time.hour * 60 + time.min + sumTime.hour * 60 + sumTime.min;
		if (minutesSum > 1440) {
			minutesSum = minutesSum % 1440;
		}

		return new Time(minutesSum / 60, minutesSum % 60);
	}

	public static Time inputTime() {
		List<Integer> time = new ArrayList<Integer>(Arrays.asList(null, null));
		Scanner scanner = new Scanner(System.in).useDelimiter("[:,./\\s]");

		System.out.println("Input time (hh/mm):");
		for (int i = 0; scanner.hasNextInt(); i++) {
			if (scanner.hasNextInt())
				time.add(i, scanner.nextInt());
		}

		if (time.size() - 2 < 2) {
			System.err.println("Time input incorrect");
		}

		int hour = Optional.ofNullable(time.get(0)).orElse(0);
		int min = Optional.ofNullable(time.get(1)).orElse(0);

		return new Time(hour, min);

	}

	@Override
	public int compareTo(Time o) {
		if (this.hour > o.getHour()) {
			return 1;
		} else if (this.hour < o.getHour()) {
			return -1;
		} else {
			if (this.min > o.getMin()) {
				return 1;
			} else if (this.min < o.getMin()) {
				return -1;
			}
		}
		return 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(hour, min);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Time other = (Time) obj;
		return hour == other.hour && min == other.min;
	}

	@Override
	public String toString() {
		return String.format("%2d", hour) + ":" + String.format("%02d", min);
	}

}