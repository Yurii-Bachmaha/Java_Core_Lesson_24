package task01;

import java.util.Scanner;

public class Application {
	
	static void menu() {
		System.out.println("Enter 1 to create a cinema." + 
				"\nEnter 2 to add a movie to the library."+
				"\nEnter 3 to remove a movie from a library." + 
				"\nEnter 4 to display the movie library."+
				"\nEnter 5 to add a seance." + 
				"\nEnter 6 to remove a seance." + 
				"\nEnter 7 to display the seances schedule."+
				"\nEnter 8 to remove movie from library and schedule."+
				"\nEnter 9 to exit the program.");
	}
	
	public static void main(String[] args) {
		
		Cinema cinema = null;
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			menu();
			
			switch(scanner.next()) {
			
			case "1":{
				cinema=Cinema.createCinema();
				break;
			}
			
			case "2":{
				cinema.addMovieToLibrary();
				break;
			}
			
			case "3":{
				cinema.removeMovieFromLibrary();
				break;
			}
			
			case "4":{
				cinema.showMoviesLibrary();
				break;
			}
			
			case "5":{
				cinema.addSeanceByDay();
				break;
			}
			
			case "6":{
				cinema.removeSeanceByDay();
				break;
			}
			
			case "7":{
				cinema.showShedules();
				break;
			}
			
			case "8":{
				cinema.removeMovieFromLibraryAndSchedule();
				break;
			}
			
			case "9": {
				System.exit(0);
				break;
			}
			}
		}		
	}
}