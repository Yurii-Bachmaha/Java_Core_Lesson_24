package task01;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;

public class Schedule {

	Set<Seance> seances = new TreeSet<Seance>();

	public Schedule(Set<Seance> seances) {
		super();
		this.seances = seances;
	}

	public Schedule() {
		super();
	}

	public Set<Seance> getSeances() {
		return seances;
	}

	public void setSeances(Set<Seance> seances) {
		this.seances = seances;
	}

	public Optional<Seance> findSeance(Seance seance2) {
		Predicate<Seance> isEqual = seance -> seance.getMovie().getTitle().equalsIgnoreCase(seance2.getMovie().getTitle())
				&& seance.getStartTime().equals(seance2.getStartTime())
				&& seance.getEndTime().equals(seance2.getEndTime());
		Optional<Seance> seanceFound = seances.stream().filter(isEqual).findFirst();

		return seanceFound;
	}

	public Optional<Seance> getMovieFromSet(Movie movie) {
		Optional<Seance> seanceFound = seances.stream().filter(entry -> entry.getMovie().equals(movie)).findFirst();

		return seanceFound;
	}

	public void addSeance(Seance seance) {
		seances.add(seance);
		System.out.println(seance + "successfully added.");
	}

	public boolean removeSeance(Seance removingSeance) {
		Optional<Seance> removingSeanceFound = findSeance(removingSeance);

		if (removingSeanceFound.isPresent()) {
			seances.remove(removingSeanceFound.get());
		}else {
			System.out.println("There is no such seance in the schedule");
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(seances);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Schedule other = (Schedule) obj;
		return Objects.equals(seances, other.seances);
	}

	@Override
	public String toString() {
		return "Schedule:" + "\n" + seances + "";
	}

}