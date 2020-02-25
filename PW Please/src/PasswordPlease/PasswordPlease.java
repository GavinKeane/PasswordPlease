//package pwplease;
import java.util.Scanner;

public class PasswordPlease {

	public static void main(String[] args) {
		Employee testEmployee = new Employee();
		Employee testEmployee2 = new Employee();
		testEmployee.setPassword("pass123");
		testEmployee2.setPassword("pass456");
		System.out.println(testEmployee.getPassword() + " " + testEmployee2.getPassword());
		System.out.println("Welcome to Password, Please");
		Scanner in = new Scanner(System.in);
		String command = in.nextLine();
		while (!command.equals("QUIT")) {
			//interpret command input
			switch(command){
				case "HELP":
					printCommands();
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
