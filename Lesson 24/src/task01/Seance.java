package task01;

import java.util.Objects;

public class Seance implements Comparable<Seance> {

	private Movie movie;
	private Time startTime;
	private Time endTime;

	public Seance(Movie movie, Time startTime) {
		super();
		this.movie = movie;
		this.startTime = startTime;
		this.endTime = Time.sumTime(startTime, movie.getDuration());
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	public static Seance inputSeance(Movie movie) {
		System.out.println("Seance starts - ");
		Time startTime = Time.inputTime();

		return new Seance(movie, startTime);
	}

	@Override
	public int hashCode() {
		return Objects.hash(endTime, movie, startTime);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Seance other = (Seance) obj;
		return Objects.equals(endTime, other.endTime) && Objects.equals(movie, other.movie)
				&& Objects.equals(startTime, other.startTime);
	}

	@Override
	public int compareTo(Seance o) {
		if (this.startTime.compareTo(o.getStartTime()) > 0) {
			return 1;
		} else if (this.startTime.compareTo(o.getStartTime()) < 0) {
			return -1;
		}
		return 0;
	}

	@Override
	public String toString() {
		return "Seance: " + movie + ", startTime = " + startTime + ", endTime = " + endTime + "\n";
	}

}