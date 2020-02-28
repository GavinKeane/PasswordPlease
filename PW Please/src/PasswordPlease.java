
//package pwplease;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PasswordPlease {

	public static void main(String[] args) {
		//generate random employee list
		for(int i = 0; i < 19; i++){
			Employee e = new Employee();
			System.out.println(e.getFace(0) + "  " + e.getFace(1) + "  " + e.getFace(2));
		}
		
		//test manual employee creation
		Employee jeff = new Employee("Jones", "Jeffrey", "IT", "password", "(o W o)", "6143591872", "What is the name of your first pet?", "Bandit");
		
		System.out.println("Welcome to Password, Please");
		Scanner in = new Scanner(System.in);
		String command = in.nextLine();
		while (!command.equals("QUIT")) {
			//interpret command input
			switch(command){
				case "HELP":
					printCommands();
					break;
				case "LIST":
					System.out.println(Employee.employees);
					break;
				case "QUIT":
					return;
				default:
					System.out.println("INVALID COMMAND. TYPE \"HELP\" FOR LIST OF COMMANDS.");
			}
			command = in.nextLine();
		}
		in.close();
	}

	private static void printCommands() {
		System.out.println(
				"HELP, APPROVE, DENY, LIST, PASSWORD, EXCRYPTED PASSWORD, DECRYPT, SEND TOKEN, SCAN FACE, SECURITY QUESTION, TIME");
	}

}
