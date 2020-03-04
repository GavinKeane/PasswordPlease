<<<<<<< Updated upstream
import java.util.*;
/**
 * Commands for the user.
 *
 * @author Jeremiah Lee
 * 01MAR2020
 */
public class Commands
{
    public String[] commandList = new String[5];

    public Commands()
    {
        commandList[0] = "HELP: shows list of commands\nLIST: shows list of users and their credentials\nAPPROVE: approve request\nDENY: deny request\nPASSWORD: shows password the user has sent\nTIME: displays remaining time in the day\n";
        commandList[1] = "HELP: shows list of commands\nLIST: shows list of users and their credentials\nAPPROVE: approve request\nDENY: deny request\nPASSWORD: shows password the user has sent\nENCRYPTED PASSWORD:shows the encrypted password\nDECRYPT: decrypts string\nTIME: displays remaining time in the day\n";
        commandList[2] = "HELP: shows list of commands\nLIST: shows list of users and their credentials\nAPPROVE: approve request\nDENY: deny request\nPASSWORD: shows password the user has sent\nENCRYPTED PASSWORD:shows the encrypted password\nDECRYPT: decrypts string\nSCAN FACE: returns face\nTIME: displays remaining time in the day\n";
        commandList[3] = "HELP: shows list of commands\nLIST: shows list of users and their credentials\nAPPROVE: approve request\nDENY: deny request\nPASSWORD: shows password the user has sent\nENCRYPTED PASSWORD:shows the encrypted password\nDECRYPT: decrypts string\nSCAN FACE: returns face\nSEND TOKEN: send 2FA code to employee's phone #\nTIME: displays remaining time in the day\n";
        commandList[4] = "HELP: shows list of commands\nLIST: shows list of users and their credentials\nAPPROVE: approve request\nDENY: deny request\nPASSWORD: shows password the user has sent\nENCRYPTED PASSWORD:shows the encrypted password\nDECRYPT: decrypts string\nSCAN FACE: returns face\nSEND TOKEN: send 2FA code to employee's phone #\n\nSECURITY QUESTION: returns question and answer\nTIME: displays remaining time in the day\n";
        commandList[5] = "HELP: shows list of commands\nLIST: shows list of users and their credentials\nAPPROVE: approve request\nDENY: deny request\nPASSWORD: shows password the user has sent\nENCRYPTED PASSWORD:shows the encrypted password\nDECRYPT: decrypts string\nSCAN FACE: returns face\nSEND TOKEN: send 2FA code to employee's phone #\n\nSECURITY QUESTION: returns question and answer\nROLES: shows list of roles and what systems can be accessed\nTIME: displays remaining time in the day\n";
    }

    /**
     * Method to remove whitespace from input stream and handle lowercase entries.
     */

    private String cleanseInput(String input)
    {
        String command = input.replaceAll("\\s+","");
        command.toUpperCase();
        return command;
    }

    /**
     * Method to handle all commands
     *
     * @param  command, the user inputted command
     * @param  day, the day that the user is on. this will be used to limit the information the user receives upon requesting info
     * 
     */
    public boolean HandleCommands(String command, Request r, int day, Score score)
    {
        boolean decisionMade = false;
        String cleansedCmd = cleanseInput(command);
        switch(cleansedCmd){
            case "HELP":
            System.out.println(commandList[day-1]);
            break;
            case "LIST":
            switch(day){
                case 1:
                for (Employee e: Employee.employees)
                {
                    System.out.println(e.getFirstname() + ' ' + e.getLastname() + " - " + "Username: " + e.getUsername() + " Password: " + e.getPassword() + "\n");
                }
                break;
                case 2:
                for (Employee e: Employee.employees)
                {
                    System.out.println(e.getFirstname() + ' ' + e.getLastname() + " - " + "Username: " + e.getUsername() + " Password: " + e.getPassword() + "Encrypted Password: " + e.getEncryptedPassword() + "\n");
                }
                break;
                case 3:
                for (Employee e: Employee.employees)
                {
                    System.out.println(e.getFirstname() + ' ' + e.getLastname() + " - " + "Username: " + e.getUsername() + " Password: " + e.getPassword() + "Encrypted Password: " + e.getEncryptedPassword() + "Face: " + e.getFace(0) + "\n");
                }
                break;
                case 4:
                for (Employee e: Employee.employees)
                {
                    System.out.println(e.getFirstname() + ' ' + e.getLastname() + " - " + "Username: " + e.getUsername() + " Password: " + e.getPassword() + "Encrypted Password: " + e.getEncryptedPassword() + "Face: " + e.getFace(0) + "Phone: " + e.getPhone() + "\n");
                }
                break;
                case 5:
                for (Employee e: Employee.employees)
                {
                    System.out.println(e.getFirstname() + ' ' + e.getLastname() + " - " + "Username: " + e.getUsername() + " Password: " + e.getPassword() + "Encrypted Password: " + e.getEncryptedPassword() + "Face: " + e.getFace(0) + "Phone: " + e.getPhone() + "\n");
                    System.out.print("Security Question: " + e.getSecurityQuestion() + "Security Answer: " + e.getSecurityAnswer() + "\n");
                }
                break;
                case 6:
                for (Employee e: Employee.employees)
                {
                    System.out.println(e.getFirstname() + ' ' + e.getLastname() + " - " + "Username: " + e.getUsername() + " Password: " + e.getPassword() + "Encrypted Password: " + e.getEncryptedPassword() + "Face: " + e.getFace(0) + "Phone: " + e.getPhone() + "\n");
                    System.out.print("Security Question: " + e.getSecurityQuestion() + "Security Answer: " + e.getSecurityAnswer() + "\n");
                    System.out.print("Role: " + e.getRole() + "\n");
                }
                break;
            }
            break;
            case "PASSWORD":
            System.out.println(r.getPassword());
            break;
            case "APPROVE":
            decisionMade = true;
            if(r.getValid()){
                Score.ScoreCorrect(); //add to score
            }else{
                score.ScoreIncorrect(); //subtract from score
                System.out.println("\nNOTICE: " + r.getFailureText() + "\nYour balance has been deducted.\n");
            }
            System.out.println("SCORE: " + score.DisplayScore());
            break;
            case "DENY":
            decisionMade = true;
            if(r.getValid()){
                Score.ScoreCorrect(); //add to score
            }else{
                score.ScoreIncorrect(); //subtract from score
                System.out.println("\nNOTICE: " + r.getFailureText() + "\nYour balance has been deducted.\n");
            }
            System.out.println("SCORE: " + score.DisplayScore());
            break;
            case "ENCRYPTEDPASSWORD":
            if (day > 1){
                System.out.println(r.getEncryptedPassword());
            }
            break;
            case "DECRYPT":
            if (day > 1){
                System.out.print("Enter encrypted password: ");
                Scanner in = new Scanner(System.in);
                String encPass = in.nextLine();
                //Update when encrypted password functionality is added*****************
                System.out.println("Feature coming soon");
            }
            break;
            case "SCANFACE":
            if (day > 2){
                System.out.println(r.getFace());
            }
            break;
            case "SENDTOKEN":
            if (day > 3){
                System.out.print("Enter phone number to send token to: ");
                Scanner in = new Scanner(System.in);
                String phoneNum = in.nextLine();
                //how were we planning on doing this?
                System.out.println("Feature coming soon");
            }
            break;
            case "SECURITYQUESTIONS":
            if (day > 4){
                System.out.println("Question: "); //+ e.getSecurityQuestion());
                System.out.print("Answer: " + r.getSecurityAnswer());
                Scanner in = new Scanner(System.in);
                String phoneNum = in.nextLine();
                //how were we planning on doing this?
                System.out.println("Feature coming soon");
            }
            break;
            case "ROLE":
            if (day > 5){
                
                System.out.println("Feature coming soon");
            }
            break;
            default:
            System.out.println("Command not recognized. Type \"HELP\" for list of commands.");
        }
        return decisionMade;
    }
=======
import java.util.Scanner;

public class Commands {
  //Array of LIST outputs depending on the day. Day 1 is commandList[0]. Day 2 is commandList[1]. Day n is commandList[n-1];
  public static String[] commandList = {"HELP: shows list of commands\nLIST: shows list of users and their credentials\nAPPROVE: approve request\nDENY: deny request\nPASSWORD: shows password the user has sent\nTIME: displays remaining time in the day\n", 
  "HELP: shows list of commands\nLIST: shows list of users and their credentials\nAPPROVE: approve request\nDENY: deny request\nPASSWORD: shows password the user has sent\nENCRYPTED PASSWORD:shows the encrypted password\nDECRYPT: decrypts string\nTIME: displays remaining time in the day\n",
  "HELP: shows list of commands\nLIST: shows list of users and their credentials\nAPPROVE: approve request\nDENY: deny request\nPASSWORD: shows password the user has sent\nENCRYPTED PASSWORD:shows the encrypted password\nDECRYPT: decrypts string\nSCAN FACE: returns face\nTIME: displays remaining time in the day\n",
  "HELP: shows list of commands\nLIST: shows list of users and their credentials\nAPPROVE: approve request\nDENY: deny request\nPASSWORD: shows password the user has sent\nENCRYPTED PASSWORD:shows the encrypted password\nDECRYPT: decrypts string\nSCAN FACE: returns face\nSEND TOKEN: send 2FA code to employee's phone #\nTIME: displays remaining time in the day\n",
  "HELP: shows list of commands\nLIST: shows list of users and their credentials\nAPPROVE: approve request\nDENY: deny request\nPASSWORD: shows password the user has sent\nENCRYPTED PASSWORD:shows the encrypted password\nDECRYPT: decrypts string\nSCAN FACE: returns face\nSEND TOKEN: send 2FA code to employee's phone #\n\nSECURITY QUESTION: returns question and answer\nTIME: displays remaining time in the day\n",
  "HELP: shows list of commands\nLIST: shows list of users and their credentials\nAPPROVE: approve request\nDENY: deny request\nPASSWORD: shows password the user has sent\nENCRYPTED PASSWORD:shows the encrypted password\nDECRYPT: decrypts string\nSCAN FACE: returns face\nSEND TOKEN: send 2FA code to employee's phone #\n\nSECURITY QUESTION: returns question and answer\nROLES: shows list of roles and what systems can be accessed\nTIME: displays remaining time in the day\n"
};
  
  //Removes whitespace before and after (including tabs) and returns the command in uppercase 
  private static String cleanseInput(String input) {
    String command = input.replaceAll("\\s+", "");
    return command.toUpperCase();

  }
  
  //Method that actually handles commands given the request and the day
  public static boolean HandleCommands(Request r, int day) {
    Scanner in = new Scanner(System.in);

    //variable that marks the end of a request
    boolean decisionMade = false;

    String cleansedCmd = cleanseInput(in.nextLine());

    //Switch case for different command options
    switch (cleansedCmd) {
      case "HELP":
        Game.println("" + commandList[day - 1]);
        break;

      case "LIST":
        printListOfEmployees(day);
        Game.println("");
        break; 

      case "PASSWORD":
        Game.println(r.getPassword());
        break;

      case "APPROVE":
        if (r.getValid()) {
          Game.println("CORRECT!");
          //Score.UpdateStreak(true);
        } else {
          //Score.UpdateStreak(false);
          Game.println("\nNOTICE: " + r.getFailureText() + "\nYour balance has been deducted.\n");
        } 
        Game.println("SCORE: " + Score.DisplayScore());
        decisionMade = true;
        break;

      case "DENY":
        
        if (r.getValid()) {
          Game.println("CORRECT!");
          //Score.UpdateStreak(true);
        } else {
          //Score.UpdateStreak(false);
          Game.println("\nNOTICE: " + r.getFailureText() + "\nYour balance has been deducted.\n");
        } 
        //Game.println("SCORE: " + Score.UpdateScore(10));
        decisionMade = true;
        break;

      case "ENCRYPTEDPASSWORD":
        if (day > 1)
          Game.println(r.getEncryptedPassword()); 
        break;
      case "DECRYPT":
        if (day > 1) {
          Game.print("Enter encrypted password: ");
          
          String encPass = in.nextLine();
          Game.println("Feature coming soon");
        } 
        break;
      case "SCANFACE":
        if (day > 2)
          Game.println(r.getFace()); 
        break;
      case "SENDTOKEN":
        if (day > 3) {
          Game.print("Enter phone number to send token to: ");

          String phoneNum = in.nextLine();
          Game.println("Feature coming soon");
        } 
        break;
      case "SECURITYQUESTIONS":
        if (day > 4) {
          Game.println("Question: ");
          Game.print("Answer: " + r.getSecurityAnswer());

          String phoneNum = in.nextLine();
          Game.println("Feature coming soon");
        } 
        break;
      case "ROLE":
        if (day > 5)
          Game.println("Feature coming soon"); 
        break;
        default:
        Game.println("Command not recognized. Type \"HELP\" for list of commands.");
    } 
    return decisionMade;
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

			if (day > 1) {
				// Print Role
				int roleSize = e.getRole().length();
				Game.print(e.getRole());
				printSpaces(largestRole - roleSize + spacing);
			}

			if (day > 2) {
				// Print Face
				// IMPORTANT - Faces must be printed LAST because of uniform-width problems
				int faceSize = e.getFace(0).length();
				Game.print(e.getFace(0));
				printSpaces(largestFace - faceSize + spacing);
			}

			Game.println("");
		}
	}

	private static void printSpaces(int numberOfSpaces) {
		for (int i = 0; i < numberOfSpaces; i++) {
			Game.print(" ");
		}
	}
>>>>>>> Stashed changes
}
