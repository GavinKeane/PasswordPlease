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
}
