
//package pwplease;
import java.util.*;


public class PasswordPlease {

	public static void main(String[] args) {
		// generate random employee list
		List<Employee> employees = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			Employee e = new Employee();
		}
		
		//test manual employee creation
		Employee jeff = new Employee("Jones", "Jeffrey", "IT", "password", "(o W o)", "6143591872", "What is the name of your first pet?", "Bandit");	
		Employee.employees.sort(Comparator.comparing(Employee::getUsername));
	
		//play day 1
		day1();
		
		System.out.println("Welcome to Password, Please");

		//printListOfEmployees(1);
		// DAY ONE

	}

	private static void printListOfEmployees(int day) {
		int largestName = 0;
		int largestUsername = 0;
		int largestFace = 0;
		int largestRole = 0;
		int largestPassword = 0;
		int largestPhone = 0;

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
		}
		
		
		for (int i = 0; i < Employee.employees.size(); i++) {
			int spacing = 3;
			Employee e = Employee.employees.get(i);

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
	
	//return list of commands available on current day
	private static String listCommands(int day) {
		String s = "LIST      Show list of employees and their credentials \nAPPROVE   Grant access to the current request \nDENY      Deny the current request";	
		//TODO update to show day relevant commands
		return s;
	}
	
	//Play day 1
	private static void day1() {
		Scanner in = new Scanner(System.in);
		int score = 0;
		
		//generate requests for day
		ArrayList<Request> requests = new ArrayList<Request>();
		for (int i = 0; i < 10; i++){
			Request r = new Request(1);
			requests.add(r);
		}
	
		//play each request
		for(int i = 0; i < requests.size(); i++){
			Request r = requests.get(i);
			System.out.println("Incoming request from " + r.getUsername());
		
			//get user input until user approves or denies request
			boolean decisionMade = false;
			while (!decisionMade){
				System.out.println("");
				String command = in.nextLine();
				switch(command.toUpperCase()){
					case "LIST":
						printListOfEmployees(1);
						break;
					case "HELP":
						System.out.println(listCommands(1));
						break;
					case "PASSWORD":
						System.out.println(r.getPassword());
						break;
					case "APPROVE":
						decisionMade = true;
						if(r.getValid()){
							score = score + 10; //add to score
						}else{
							score = score - 10;
							System.out.println("\nNOTICE: " + r.getFailureText() + "\nYour balance has been deducted.\n");
						}
						System.out.println("SCORE: " + score);
						break;
					case "DENY":
						decisionMade = true;
						if(!r.getValid()){	
							score = score + 10;
						}else{
							score = score-10;
							System.out.println("\nNOTICE: " + r.getFailureText() + "\nYour balance has been deducted.\n");
						}
						System.out.println("SCORE: " + score);
						break;
					default:
						System.out.println("Command not recognized. Type \"HELP\" for list of commands.");
				}
			}
		}
		
		
		in.close();
	}
}
