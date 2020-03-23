/*
	Utility methods
*/
import java.util.*;
import java.util.concurrent.TimeUnit;
public class Game{

	//print text character by character
	public static void print(String text){
		char[] chars = text.toCharArray();
		for (char ch : chars) {
			try{
				System.out.print(ch);
				//pause between each character
				TimeUnit.MICROSECONDS.sleep(2500);
			} catch (InterruptedException e) {
        System.err.format("IOException: %s%n", e);
      }
		}
	}
	
	//print with newline at the end
	public static void println(String text){
		print(text);
		System.out.println("");
	}
	
	//print with manually specified time (in microseconds) between characters
	public static void print(String text, int pauseTime){
		char[] chars = text.toCharArray();
		for (char ch : chars) {
			try{
				System.out.print(ch);
				//pause between each character
				TimeUnit.MICROSECONDS.sleep(pauseTime);
			} catch (InterruptedException e) {
        System.err.format("IOException: %s%n", e);
      }
		}
	}
	
	public static void println(String text, int pauseTime){
		print(text, pauseTime);
		System.out.println("");
	}
	
	//return list of commands available on current day
	public static String listCommands(int day) {
		String s = "LIST      Show list of employees and their credentials \nAPPROVE   Grant access to the current request \nDENY      Deny the current request";	
		//TODO update to show day relevant commands
		return s;
	}
	
	public static void intro(){
		Scanner in = new Scanner(System.in);
		int textSpeed = 25000;
		
		print("============================\nWelcome to Password, Please\n============================", 10000);
		print("\n\n", 200000);
		print("[PRESS ENTER TO START]\n");
		String input = in.nextLine();
		
		if (input.equals("DAY1")){
			return;
		}
		
		print("INCOMING MESSAGE from CYBERSECURITY: Hello? Is this working?", textSpeed);
		in.nextLine();
		
		print("Ah, good, looks like your natural language processing is working. Let's get you up to speed.", textSpeed);
		in.nextLine();
		
		print("You're an experimental AI being used for our company's security systems. You work for RioGrande, the second largest online retailer in all of Albuquerque.", textSpeed);
		in.nextLine();
		
		print("Your job will be to process incoming access requests and determine whether to ", textSpeed);
		print("APPROVE", 150000);
		print(" or ", textSpeed);
		print("DENY", 150000);
		print(" them.", textSpeed);
		in.nextLine();
		
		print("\nWhy do we need a sentient AI to do this? That's a good question. I'm pretty sure someone from corporate made that decision.", textSpeed);
		in.nextLine();
		
		print("You'll have to take it up with them if you have any complaints about your existence.", textSpeed);
		in.nextLine();
		
		println("");
		PasswordPlease.printListOfEmployees(1);
		print("\nThis is a list of our employees and their credentials. If you ever need to look at it in the future, type LIST to pull it up.", textSpeed);
		in.nextLine();
		
		Request tutorialRequest = new Request("Jones.1", "password123", "", "", "", "", "", true, "Request was valid."); 
		
		print("Looks like there's an incoming request.", textSpeed);
		in.nextLine();
		
		print("\nINCOMING REQUEST from Jones.1", textSpeed*2);
		print("\n\nTo ask the requester for their password, simply type PASSWORD. Try it now.\n\n", textSpeed);
		
		input = "";
		
		do{
			input = in.nextLine();
			if(input.equals("PASSWORD")){
				print("password123\n\n", textSpeed*2);
			}else{
				print("Looks like you made a mistake. To get the requester's password, simply type PASSWORD.\n\n", textSpeed);
			}
		}while(!input.equals("PASSWORD"));
		
		print("Well done! Now, cross reference the username (Jones.1) with that user's actual password and see if it matches the one they provided.", textSpeed);
		in.nextLine();
		
		print("If they match, type APPROVE to let the request through. If not, type DENY to reject the request.\n\n", textSpeed);
		
		do{
			input = in.nextLine();
			if(input.equals("DENY")){
				print("\nTry again.\n\n", textSpeed);
			}else if (!input.equals("APPROVE")){
				print("If they match, type APPROVE to let the request through. If not, type DENY to reject the request.\n\n", textSpeed);
			}
		}while(!input.equals("APPROVE"));
		
		print("Good job! Looks like the password matched. Although it doesn't seem like a very strong password. I'll have to talk to Jeff about that.", textSpeed);
		in.nextLine();
		
		print("I think you're ready for your first day of work! If you have any questions, just type HELP.", textSpeed);
		in.nextLine();
		
	}
	
	//Play day 1
	public static void day1() {
		Scanner in = new Scanner(System.in);
		int score = 0;
		int textSpeed = 25000;
		boolean choice1 = false; //true if player accepted points from Hacker.1337
		
		print("\n============================\n          Day 1       \n============================", 10000);
		print("\n\n", 200000);
		print("[PRESS ENTER TO START]\n");
		in.nextLine();
		
		//generate requests for day
		ArrayList<Request> requests = new ArrayList<Request>();
		for (int i = 0; i < 4; i++){
			Request r = new Request(1);
			requests.add(r);
		}
	
		//play each request
		for(int i = 0; i < requests.size(); i++){
			Request r = requests.get(i);
			println("\nINCOMING REQUEST from " + r.getUsername());
		
			//get user input until user approves or denies request
			boolean decisionMade = false;
			while (!decisionMade){
				println("");
				String command = in.nextLine();
				switch(command.toUpperCase()){
					case "LIST":
						PasswordPlease.printListOfEmployees(1);
						break;
					case "HELP":
						println(listCommands(1));
						break;
					case "PASSWORD":
						println(r.getPassword());
						break;
					case "APPROVE":
						decisionMade = true;
						if(r.getValid()){
							score = score + 10; //add to score
						}else{
							score = score - 20;
							println("\nNOTICE: " + r.getFailureText() + "\nYour balance has been deducted.\n", 25000);
						}
						println("SCORE: " + score);
						break;
					case "DENY":
						decisionMade = true;
						if(!r.getValid()){	
							score = score + 10;
						}else{
							score = score - 20;
							println("\nNOTICE: " + r.getFailureText() + "\nYour balance has been deducted.\n", 25000);
						}
						println("SCORE: " + score);
						break;
					default:
						println("Command not recognized. Type \"HELP\" for list of commands.");
				}
			}
		}
		
		print("\nINCOMING MESSAGE from ?????: ", textSpeed*2);
		print("\nHey. \nAI guy. \nYeah, I'm talking to you. Want some easy points? \nJust approve the next request, no questions asked. \nI'll make it worth your while.", textSpeed);
		in.nextLine();
		
		Request request1 = new Request("Hacker.1337", "h4cktHeW0rld", "","","","","", false, "Very nice. Sending your points now. Be seeing you.");
		requests.clear();
		requests.add(request1);
		
		for(int i = 0; i < 3; i++){
			Request r = new Request(1);
			requests.add(r);
		}
		
		for(int i = 0; i < requests.size(); i++){
			Request r = requests.get(i);
			println("\nINCOMING REQUEST from " + r.getUsername());
		
			//get user input until user approves or denies request
			boolean decisionMade = false;
			while (!decisionMade){
				println("");
				String command = in.nextLine();
				switch(command.toUpperCase()){
					case "LIST":
						PasswordPlease.printListOfEmployees(1);
						break;
					case "HELP":
						println(listCommands(1));
						break;
					case "PASSWORD":
						println(r.getPassword());
						break;
					case "APPROVE":
						decisionMade = true;
						if(r.getValid()){
							score = score + 10; //add to score
						}else{
							if(r.getUsername().equals("Hacker.1337")){
							score = score + 120937128;
							choice1 = true;
							println("\nINCOMING MESSAGE from ?????: " + r.getFailureText());
							}else{
								score -= 20;
								println("\nNOTICE: " + r.getFailureText() + "\nYour balance has been deducted.\n", 25000);
							}
						}
						println("SCORE: " + score);
						break;
					case "DENY":
						decisionMade = true;
						if(!r.getValid()){	
							score = score + 10;
						}else{
							score = score - 20;
							println("\nNOTICE: " + r.getFailureText() + "\nYour balance has been deducted.\n", 25000);
						}
						println("SCORE: " + score);
						break;
					default:
						println("Command not recognized. Type \"HELP\" for list of commands.");
						}
				}
		}
		
		//end of day 1
		print("\nINCOMING MESSAGE from CYBERSECURITY: You made it through your first day. Well done!", textSpeed);
		in.nextLine();
		
		print("\nYOUR SCORE: " + score, textSpeed*3);
		in.nextLine();
		
		//check if player approve Hacker.1337's request
		if(choice1){
			print("\nHold on", textSpeed*4);
			print("...", textSpeed*4);
			in.nextLine();
			
			print("Your score seems abnormally high. Are you hiding something? \n[YES/NO] ", textSpeed);
			String input = in.nextLine();
			
			if(input.equals("YES")){
				print("\nWell, that's a shame, looks like we'll have to uninstall you. Goodbye.", textSpeed);
				in.nextLine();
				
				print("\n\n============================\n        GAME OVER\n============================\n", 10000);
				return;
			}else{
				print("\nWell, you're not programmed to lie, so I guess I'll have to believe you. Must have been a bug somewhere. I'll just reset your score for now.", textSpeed);
				score = 0;
				in.nextLine();
				
				print("\nYOUR SCORE: " + score, textSpeed*3);
				in.nextLine();
				
				print("\nThat should do it. I'll see you tomorrow!\n", textSpeed);
			}
		}else{
			if(score >0){
				print("Looks like you did pretty well. See you tomorrow!\n", textSpeed);
				in.nextLine();
			}else{
				print("Looks like you didn't quite meet expectations, I'm afraid we'll have to scrap this program. Goodbye.\n", textSpeed);
				print("\n\n============================\n        GAME OVER\n============================\n", 10000);
			}
		}
			
		in.close();
	}
}
