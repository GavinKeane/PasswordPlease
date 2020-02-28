
//package pwplease;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PasswordPlease {

	public static void main(String[] args) {
		// generate random employee list
		List<Employee> employees = new ArrayList<>();
		for (int i = 0; i < 19; i++) {
			Employee e = new Employee();
			employees.add(e);
			// System.out.println(e.getFace(0) + " " + e.getFace(1) + " " + e.getFace(2));
		}

		// test manual employee creation
		Employee jeff = new Employee("Jones", "Jeffrey", "IT", "password", "(o W o)", "6143591872",
				"What is the name of your first pet?", "Bandit");

		System.out.println("Welcome to Password, Please");
		Scanner in = new Scanner(System.in);
		String command = in.nextLine();

		printListOfEmployees(2, employees);
		// DAY ONE

		in.close();
	}

	private static void printListOfEmployees(int day, List<Employee> employees) {
		int largestName = 0;
		int largestUsername = 0;
		int largestFace = 0;
		int largestRole = 0;
		int largestPassword = 0;
		int largestPhone = 0;

		for (int i = 0; i < employees.size(); i++) {
			
			//Collecting length info to coordinate uniform width of columns
			Employee e = employees.get(i);
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
		}
		
		
		for (int i = 0; i < employees.size(); i++) {
			int spacing = 3;
			Employee e = employees.get(i);

			if (day > 0) {
				
				// Print first & last name
				int nameSize = (e.getFirstname() + " " + e.getLastname()).length();
				System.out.print(e.getFirstname() + " " + e.getLastname());
				printSpaces(largestName - nameSize + spacing);

				// Print Username
				int usernameSize = e.getUsername().length();
				System.out.print(e.getUsername());
				printSpaces(largestUsername - usernameSize + spacing);

				// Print password
				int passwordSize = e.getPassword().length();
				System.out.print(e.getPassword());
				printSpaces(largestPassword - passwordSize + spacing);
			}

			if (day > 1) {
				// Print Role
				int roleSize = e.getRole().length();
				System.out.print(e.getRole());
				printSpaces(largestRole - roleSize + spacing);
			}

			if (day > 2) {
				// Print Face
				// IMPORTANT - Faces must be printed LAST because of uniform-width problems
				int faceSize = e.getFace(0).length();
				System.out.print(e.getFace(0));
				printSpaces(largestFace - faceSize + spacing);
			}

			System.out.println();
		}
	}

	private static void printSpaces(int numberOfSpaces) {
		for (int i = 0; i < numberOfSpaces; i++) {
			System.out.print(" ");
		}
	}

	private static void printCommands() {
		System.out.println(
				"HELP, APPROVE, DENY, LIST, PASSWORD, EXCRYPTED PASSWORD, DECRYPT, SEND TOKEN, SCAN FACE, SECURITY QUESTION, TIME");
	}

}
