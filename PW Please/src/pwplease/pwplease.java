package pwplease;

import java.util.Scanner;

public class pwplease {

	public static void main(String[] args) {
		System.out.println("Welcome to Password, Please");
		Scanner in = new Scanner(System.in);
		String command = in.nextLine();
		while (!command.equals("QUIT")) {
			if (command.equals("HELP")) {
				printCommands();
			} else if (command.equals("SOMETHING HERE")) {

			} else if (command.equals("SOMETHING ELSE HERE")) {
				// ect ect
			} else {
				System.out.println("INVALID COMMAND");
			}
		}
		in.close();
	}

	private static void printCommands() {
		System.out.println(
				"HELP, APPROVE, DENY, LIST, PASSWORD, EXCRYPTED PASSWORD, DECRYPT, SEND TOKEN, SCAN FACE, SECURITY QUESTION, TIME");
	}

}
