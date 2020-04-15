//package pwplease;
import java.util.*;


public class PasswordPlease {

	public static void main(String[] args) {
		//test manual employee creation
		Employee jeff = new Employee("Jones", "Jeffrey", "IT", "password123", "(o W o)", "6143591872", "What is the name of your first pet?", "Bandit");
	
		// generate random employee list
		for (int i = 0; i < 19; i++) {
			Employee e = new Employee();
		}
		
		Employee.employees.sort(Comparator.comparing(Employee::getUsername));
		//Game.intro();
		//play day 1
		//Game.day1();
		//play day 2
		//Game.day2();
		//play day3
		Game.day3();
		//Game.day6();
		/*
		printListOfEmployees(6);
		for(int i = 0; i < 10; i++){
			Request r = new Request(6);
			System.out.println(r.getUsername() + " " + r.getPassword() + " " + r.getSystem() + " " + r.getFace() + " " + r.getHasFactor() + " " + r.getSecurityAnswer() + " " + r.getFailureText());	
		}
		*/
		//Game.demo();
	}

	public static void printListOfEmployees(int day) {
		int largestName = 0;
		int largestUsername = 0;
		int largestFace = 0;
		int largestRole = 0;
		int largestPassword = 0;
		int largestPhone = 0;
		int largestAnswer = 0;

		for (int i = 0; i < Employee.employees.size(); i++) {
			
			//Collecting length info to coordinate uniform width of columns
			Employee e = Employee.employees.get(i);
			if ((e.getFirstname() + " " + e.getLastname()).length() > largestName) {
				largestName = (e.getFirstname() + " " + e.getLastname()).length();
			}
			if (e.getUsername().length() > largestUsername) {
				largestUsername = e.getUsername().length();
			}
			if (e.getFace(0).length() > largestFace) {
				largestFace = e.getFace(0).length();
			}
			if (e.getRole().length() > largestRole) {
				largestRole = e.getRole().length();
			}
			if (e.getPassword().length() > largestPassword) {
				largestPassword = e.getPassword().length();
			}
			if (e.getPhone().length() > largestPhone) {
				largestPhone = e.getPhone().length();
			}
			if(e.getSecurityAnswer().length() > largestAnswer){
				largestAnswer = e.getSecurityAnswer().length();
			}
		}
		
		
		for (int i = 0; i < Employee.employees.size(); i++) {
			int spacing = 3;
			Employee e = Employee.employees.get(i);

			if (day > 0) {
				
				// Print first & last name
				int nameSize = (e.getFirstname() + " " + e.getLastname()).length();
				Game.print(e.getFirstname() + " " + e.getLastname());
				printSpaces(largestName - nameSize + spacing);

				// Print Username
				int usernameSize = e.getUsername().length();
				Game.print(e.getUsername());
				printSpaces(largestUsername - usernameSize + spacing);

				// Print password
			
				int passwordSize = e.getPassword().length();
				Game.print(e.getPassword());
				printSpaces(largestPassword - passwordSize + spacing);
				
			}
			if (day > 2) {
				// Print Face
				// IMPORTANT - Faces must be printed LAST because of uniform-width problems
				int faceSize = e.getFace(0).length();
				Game.print(e.getFace(0));
				printSpaces(largestFace - faceSize + spacing);
			}
			
			if(day > 3){
				//print phone number
				int phoneSize = e.getPhone().length();
				Game.print(e.getPhone());
				printSpaces(largestPhone - phoneSize + spacing);
			}
			
			if (day > 4){
				//print security answer
				int answerSize = e.getSecurityAnswer().length();
				Game.print(e.getSecurityAnswer());
				printSpaces(largestAnswer - answerSize + spacing);
			}
			
			if (day > 5) {
				// Print Role
				int roleSize = e.getRole().length();
				Game.print(e.getRole());
				printSpaces(largestRole - roleSize + spacing);
			}

			

			Game.println("");
		}
	}

	private static void printSpaces(int numberOfSpaces) {
		for (int i = 0; i < numberOfSpaces; i++) {
			Game.print(" ");
		}
	}
	
}
