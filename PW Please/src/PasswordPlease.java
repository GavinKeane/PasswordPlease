
//package pwplease;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PasswordPlease {

	public static void main(String[] args) {
		List<Employee> e1 = new ArrayList<Employee>();
		for (int i = 0; i < 20; i++) {
			e1.add(new Employee());
		}
		for (int i = 0; i < e1.size(); i++) {
			System.out.println(e1.get(i).getFace(0));
		}
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
