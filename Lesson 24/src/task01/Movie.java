package task01;

import java.util.Objects;
import java.util.Scanner;

public class Movie {

	private String title;
	private Time duration;

	public Movie(String title, Time duration) {
		super();
		this.title = title;
		this.duration = duration;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Time getDuration() {
		return duration;
	}

	public void setDuration(Time duration) {
		this.duration = duration;
	}

	public static Movie inputMovie() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter movie title:");
		String title = scanner.nextLine().toUpperCase();
		if (title.isEmpty()) {
			System.err.println("The movie title can't be empty!");
		}

		System.out.println("Enter duration of movie:");
		Time duration = Time.inputTime();

		return new Movie(title, duration);
	}

	@Override
	public int hashCode() {
		return Objects.hash(duration, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		return Objects.equals(duration, other.duration) && Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		return "Movie " + title + " , duration =" + duration + "";
	}

}