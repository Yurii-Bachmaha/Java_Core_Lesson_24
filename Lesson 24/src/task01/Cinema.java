package task01;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class Cinema {

	TreeMap<Days, Schedule> schedules;

	private ArrayList<Movie> moviesLibrary;
	private String name;
	private Time openTime;
	private Time closeTime;

	public Cinema(String name) {
		super();
		this.name = name;
	}

	public Cinema(String name, Time openTime, Time closeTime) {
		super();
		this.name = name;
		this.moviesLibrary = new ArrayList<>();
		this.openTime = openTime;
		this.closeTime = closeTime;
		this.schedules = new TreeMap<>();
		{
			schedules.put(Days.SUNDAY, new Schedule());
			schedules.put(Days.MONDAY, new Schedule());
			schedules.put(Days.TUESDAY, new Schedule());
			schedules.put(Days.WEDNESDAY, new Schedule());
			schedules.put(Days.THURSDAY, new Schedule());
			schedules.put(Days.FRIDAY, new Schedule());
			schedules.put(Days.SATURDAY, new Schedule());
		}
	}

	public TreeMap<Days, Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(TreeMap<Days, Schedule> schedules) {
		this.schedules = schedules;
	}

	public ArrayList<Movie> getMoviesLibrary() {
		return moviesLibrary;
	}

	public void setMoviesLibrary(ArrayList<Movie> moviesLibrary) {
		this.moviesLibrary = moviesLibrary;
	}

	public Time getOpenTime() {
		return openTime;
	}

	public void setOpenTime(Time openTime) {
		this.openTime = openTime;
	}

	public Time getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(Time closeTime) {
		this.closeTime = closeTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static Cinema createCinema() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Input name of cinema:");
		String name = scanner.nextLine().toUpperCase();
		if (name.equals("")) {
			System.err.println("Name of cinema cant be empty");
		}
		System.out.println("Enter the opening time of the cinema:");
		Time openTime = Time.inputTime();
		System.out.println("Enter the closing time of the cinema:");
		Time closeTime = Time.inputTime();

		if (openTime.getHour() * 60 >= closeTime.getHour() * 60) {
			System.err.println("The closing time is shorter or equal to the opening time!");
		} else {
			System.out.println("Cinema " + name + " added and works from" + openTime + " till " + closeTime + ".");
		}
		return new Cinema(name, openTime, closeTime);
	}

	public void addMovieToLibrary() {
		Movie movie = Movie.inputMovie();
		moviesLibrary.add(movie);
		System.out.println(movie + " successfully added to library.");
	}

	public void removeMovieFromLibrary() {
		if (!moviesLibrary.isEmpty()) {
			System.out.println("Enter a movie from the list below:");
			showMoviesLibrary();
			Movie movie = Movie.inputMovie();
			moviesLibrary.remove(movie);
			System.out.println(movie + " successfully removed.");
		} else {
			System.out.println("The movie library is empty.");
		}
	}

	public boolean addSeanceByDay() {
		Days day = Days.InputDay();
		if (day == null)
			return false;

		Optional<Entry<Days, Schedule>> findFirst = schedules.entrySet().stream()
				.filter(entry -> entry.getKey().equals(day)).findFirst();

		if (findFirst.isPresent()) {
			Movie movie = Movie.inputMovie();
			Seance addingSeance = Seance.inputSeance(movie);

			if ((addingSeance.getStartTime().getHour() * 60+ addingSeance.getStartTime().getMin() <= openTime.getHour() * 60 + openTime.getMin())
					|| (addingSeance.getEndTime().getHour() * 60+ addingSeance.getEndTime().getMin() >= closeTime.getHour() * 60 + closeTime.getMin())) {
				System.out.println("Cinema is closed from"+openTime + " to "+closeTime+"!!!");
			} else {
				findFirst.get().getValue().addSeance(addingSeance);
				moviesLibrary.add(movie);
			}
		}
		return false;
	}

	public boolean removeSeanceByDay() {
		Days day = Days.InputDay();
		if (day == null)
			return false;

		Optional<Entry<Days, Schedule>> findFirst = schedules.entrySet().stream()
				.filter(entry -> entry.getKey().equals(day)).findFirst();

		if (findFirst.isPresent()) {
			Movie movie = Movie.inputMovie();
			Seance removingSeance = Seance.inputSeance(movie);
			isCinemaOpen(removingSeance);
			if (isCinemaOpen(removingSeance)) {
				boolean isDone = findFirst.get().getValue().removeSeance(removingSeance);
				System.out.println(removingSeance + "successfully removed.");

				if (isDone)
					return true;
				else
					return false;
			}

		}

		return false;

	}

	public void removeMovieFromLibraryAndSchedule() {
		Movie movie = Movie.inputMovie();

		for (Schedule schedule : schedules.values()) {
			Set<Seance> seances = schedule.getSeances();
			for (Seance seance : seances) {
				if (seance.getMovie().getTitle().toString().equalsIgnoreCase(movie.getTitle())) {
					schedule.removeSeance(seance);
				}
			}
		}

		Iterator<Movie> iterator = moviesLibrary.iterator();
		while (iterator.hasNext()) {
			Movie value = iterator.next();
			if (value.getTitle().equalsIgnoreCase(movie.getTitle())) {
				iterator.remove();
			}
		}

	}

	public void showShedules() {
		schedules.entrySet().stream().forEach(System.out::println);
	}

	public void showMoviesLibrary() {
		if (!moviesLibrary.isEmpty()) {
			moviesLibrary.stream().forEach(System.out::println);
		} else {
			System.out.println("The movie library is empty.");
		}
	}

	public boolean isCinemaOpen(Seance seance) {
		if (seance.getStartTime().getHour() * 60 + seance.getStartTime().getMin() <= openTime.getHour() * 60+ openTime.getMin()
				|| (seance.getEndTime().getHour() * 60 + seance.getEndTime().getMin() >= closeTime.getHour() * 60+ closeTime.getMin())) {
		}
		return true;
	}

	@Override
	public String toString() {
		return "Cinema [name=" + name + ", openTime=" + openTime + ", closeTime=" + closeTime + "]";
	}
}