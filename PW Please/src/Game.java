/*
	Utility methods
*/
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.text.DecimalFormat;
public class Game{

	//Array of LIST outputs depending on the day. Day 1 is commandList[0]. Day 2 is commandList[1]. Day n is commandList[n-1];
	public static String[] commandList = {"HELP: shows list of commands\nLIST: shows list of users and their credentials\nAPPROVE: approve request\nDENY: deny request\nPASSWORD: shows password the user has sent\nTIME: displays remaining time in the day\n", 
	"HELP: shows list of commands\nLIST: shows list of users and their credentials\nAPPROVE: approve request\nDENY: deny request\nPASSWORD: shows password the user has sent\nDECRYPT: decrypts string\nTIME: displays remaining time in the day\n",
	"HELP: shows list of commands\nLIST: shows list of users and their credentials\nAPPROVE: approve request\nDENY: deny request\nPASSWORD: shows password the user has sent\nDECRYPT: decrypts string\nSCAN FACE: returns face\nTIME: displays remaining time in the day\n",
	"HELP: shows list of commands\nLIST: shows list of users and their credentials\nAPPROVE: approve request\nDENY: deny request\nPASSWORD: shows password the user has sent\nDECRYPT: decrypts string\nSCAN FACE: returns face\nSEND OTP: send 2FA code to employee's phone #\nTIME: displays remaining time in the day\n",
	"HELP: shows list of commands\nLIST: shows list of users and their credentials\nAPPROVE: approve request\nDENY: deny request\nPASSWORD: shows password the user has sent\nDECRYPT: decrypts string\nSCAN FACE: returns face\nSEND OTP: send 2FA code to employee's phone #\n\nSECURITYQUESTION: returns question and answer\nTIME: displays remaining time in the day\n",
	"HELP: shows list of commands\nLIST: shows list of users and their credentials\nAPPROVE: approve request\nDENY: deny request\nPASSWORD: shows password the user has sent\nDECRYPT: decrypts string\nSCAN FACE: returns face\nSEND OTP: send 2FA code to employee's phone #\n\nSECURITYQUESTION: returns question and answer\nROLES: shows list of roles and what systems can be accessed\nTIME: displays remaining time in the day\n"
  };
  /** boolean array to keep track of the choices of the player
   * each slot represents a choice per day
   * so if choice[0] is true, then the player accepted points from Hacker.1337
   */
  public static boolean[] choices = {false, false, false, false, false, false};
  
  final static long DAYCYCLELENGTH = 45000; //this is 45 seconds (in ms for calcs) used for multiple calculations. Change to change day lengths
  static long choiceStartTime = 0; //start time of new approval or denial
  static long choiceEndTime = 0; //end time of approval or denial
  static long totDecisionTime = 0; //convert ms to s for total time it took to make approval or denial decision
  static int correctStreak = 1; //streak used for point calculations for correct streak
  static int loseStreak = -1; //streak used for point calculations for incorrect choice streak
  static float gameScore = 0; //total game score for the user throughout the game
  static DecimalFormat dec = new DecimalFormat(); //format all decimal outputs
  
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
	
	//calculates the start of the day's time, call this before beginning the day and store into long val
	public static long startTime() {
		return System.currentTimeMillis();
	}
	
	//updates streak accordingly for correct or incorrect approval or denial
    public static void updateStreak(boolean choice) {
        if (choice == true) {
            	loseStreak = -1;
                correctStreak++;
        } else {
            correctStreak = 1;
            loseStreak--;
        }
    }
    
    //convert total decision time which is in ms to s for score calculation
    public static long decisionCalc(long startTime, long endTime) {
    	long secondsPassed = ((endTime-startTime)/1000);
    	return secondsPassed;
    }
    
    //update game score based upon correct approval or denial standard score is set to 10 for correct or incorrect, streaks and time adjust
    public static void updateScore(boolean choice, long startTime, long endTime) {
    	int standardScore = 10;
    	//calculate total time for the choice of approval or denial
    	totDecisionTime = decisionCalc(startTime, endTime);
    	//If choice is correct of approval or denial and less than 9 seconds has passed, bonus score added, otherwise just standard and streak
    	if(choice == true) {
    		if (totDecisionTime < 9) {
    			double bonusScore = (((9 - totDecisionTime) / 9.0)
    					* standardScore * correctStreak);
    			dec.setMaximumFractionDigits(2);
    			System.out.println("");
    			System.out.println("Quick Time Score Bonus: " + dec.format(bonusScore));
    			gameScore += bonusScore + (standardScore * correctStreak);
    		} else {
    			gameScore += standardScore * correctStreak;
    		}
    		System.out.println("Correct streak at " + correctStreak + "!");
    		System.out.println("Correct Streak Point Bonus: " + (standardScore * correctStreak));
    		updateStreak(choice);
    	}else {
    		gameScore += standardScore * loseStreak;
    		System.out.println("");
    		System.out.println("Incorrect Point deduction: " + (standardScore *loseStreak));
    		System.out.println("Incorrect streak at: " + (-1*loseStreak));
    		updateStreak(choice);
    	}
    }
	
	//Used for a conditional while for each the days, checks the time against global day var to see if the day is completed
	public static boolean dayGoing(long timeBegan) {
		long timeDepleted = System.currentTimeMillis() - timeBegan; //time elapsed
		boolean completeCycle = timeDepleted < DAYCYCLELENGTH; //time in ms
		return completeCycle;
	}
	
	//Output the time left in the day by passing in the time the day started, if choice is after day time is over, state it.
	public static void timeLeft(long timeBegan) {
		long timeSpent = System.currentTimeMillis() - timeBegan; //time elapsed
		long remain = (DAYCYCLELENGTH - (timeSpent))/1000; //convert the ms calculations to seconds before returning to display
		if(remain > 0) {
			System.out.println("");
			System.out.println(remain + " seconds remaining on your shift.");
		}else {
			System.out.println("");
			System.out.println("Looks like it's time for you to clock out for the day, make your final decision.");
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

	//Checks string to ensure it's a string for hacker interaction
	public static boolean isNumeric(String str) {
        // null or empty
        if (str == null || str.length() == 0) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
	
	//return list of commands available on current day
	public static String listCommands(int day) {
		String s = commandList[day-1];
		return s;
	}
	//Removes whitespace before and after (including tabs) and returns the command in uppercase 
	private static String cleanseInput(String input) {
		String command = input.replaceAll("\\s+", "");
		return command.toUpperCase();
	
	  }
	
	public static int intro(){
		boolean choseDemo = false;
		Scanner in = new Scanner(System.in);
		int textSpeed = 25000;
		
		print("============================\nWelcome to Password, Please\n============================", 10000);
		print("\n\n", 200000);
		print("[PRESS ENTER TO START]\n");
		String input = in.nextLine();
		
		if (input.equals("DEMO")){
			choseDemo = true;
			print("You have chosen the ACCELERATED SIMULATION. The game will progress much more rapidly.");
			in.nextLine();
			print("This is only advised for employee onboarding or class presentations.");
			in.nextLine();
		}
		
		print("\nINCOMING MESSAGE from CYBERSECURITY: Hello? Is this working?", textSpeed);
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
		
		Request tutorialRequest = new Request("Jones.1", "password123", "", "", "", true, "", true, "Request was valid."); 
		
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
		
		if(choseDemo){
			demo();
			return -1;
		}
		return 1;
	}
	
	//Play day 1
	//return -1 for game over, return 1 for successful completion
	public static int day1() {
		Scanner in = new Scanner(System.in);
		int textSpeed = 25000;
		
		print("\n============================\n          Day 1       \n============================", 10000);
		print("\n\n", 200000);
		print("[PRESS ENTER TO START]\n");
		in.nextLine();
		
		//generate requests for day
		ArrayList<Request> requests = new ArrayList<Request>();
		for (int i = 0; i < 3; i++){
			Request r = new Request(1);
			requests.add(r);
		}
		
		Request request1 = new Request("Hacker.1337", "h4cktHeW0rld", "","","", false,"", false, "Very nice. Sending your points now. Be seeing you.");
		requests.add(request1);
		
		//generate more requests for day
		for (int i = 0; i < 11; i++){
			Request r = new Request(1);
			requests.add(r);
		}
	
		long startTimeDay1 = startTime(); //obtain start time of day for calculations
		int index = 0; //beginning of random username list
		//checks day still valid for continuing and list hasn't been spammed through too quickly
		while(dayGoing(startTimeDay1) && index < requests.size()){
			Request r = requests.get(index);
			index++; //increment list after obtaining user
			choiceStartTime = System.currentTimeMillis();
			if(!(r.getUsername().equals("Hacker.1337"))){
			  println("\nINCOMING REQUEST from " + r.getUsername());
			  }
			else {
			  print("\nINCOMING MESSAGE from ?????: ", textSpeed*2);
			  print("\nHey. \nAI guy. \nYeah, I'm talking to you. Want some easy points? \nJust approve the next request, no questions asked. \nI'll make it worth your while.", textSpeed);
			in.nextLine();}
		
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
					case "TIME":
						timeLeft(startTimeDay1);
						break;
					case "APPROVE":
						decisionMade = true;
						choiceEndTime = System.currentTimeMillis(); //set the choice end time for new decision for score calculation
						if(r.getValid()){
							updateScore(r.getValid(), choiceStartTime, choiceEndTime); //add to score
						}else{
							if(r.getUsername().equals("Hacker.1337")){
							gameScore = gameScore + 120937128;
							choices[0] = true;
							println("\nINCOMING MESSAGE from ?????: " + r.getFailureText());
							}else{
								updateScore(r.getValid(), choiceStartTime, choiceEndTime); //deduct score
								println("\nNOTICE: " + r.getFailureText() + "\nYour balance has been deducted.\n", 25000);
							}
						}
						dec.setMaximumFractionDigits(2);
						println("SCORE: " + dec.format(gameScore));
						break;
					case "DENY":
						decisionMade = true;
						choiceEndTime = System.currentTimeMillis(); //set the choice end time for new decision for score calculation
						if(!r.getValid()){	
							updateScore(!r.getValid(), choiceStartTime, choiceEndTime); //add to score
						}else{
							updateScore(!r.getValid(), choiceStartTime, choiceEndTime); //deduct score
							println("\nNOTICE: " + r.getFailureText() + "\nYour balance has been deducted.\n", 25000);
						}
						dec.setMaximumFractionDigits(2);
						println("SCORE: " + dec.format(gameScore));
						break;
					default:
						println("Command not recognized. Type \"HELP\" for list of commands.");
						}
				}
		}
		
		//end of day 1
		print("\nINCOMING MESSAGE from CYBERSECURITY: You made it through your first day. Well done!", textSpeed);
		in.nextLine();
		
		print("\nYOUR SCORE: " + gameScore, textSpeed*3);
		in.nextLine();
		
		//check if player approve Hacker.1337's request
		if(choices[0]){
			print("\nHold on", textSpeed*4);
			print("...", textSpeed*4);
			in.nextLine();
			
			print("Your score seems abnormally high. Are you hiding something? \n[YES/NO] ", textSpeed);
			String input = in.nextLine();
			input = cleanseInput(input);
			if(input.equals("YES")){
				print("\nWell, that's a shame, looks like we'll have to uninstall you. Goodbye.", textSpeed);
				in.nextLine();
				
				print("\n\n============================\n        GAME OVER\n============================\n", 10000);
				return -1;
			}else{
				print("\nWell, you're not programmed to lie, so I guess I'll have to believe you. Must have been a bug somewhere. I'll just reset your score for now.", textSpeed);
				gameScore = 0;
				in.nextLine();
				
				print("\nYOUR SCORE: " + gameScore, textSpeed*3);
				in.nextLine();
				
				print("\nThat should do it. I'll see you tomorrow!\n", textSpeed);
			}
		}else{
			if(gameScore >0){
				print("Looks like you did pretty well. See you tomorrow!\n", textSpeed);
				in.nextLine();
			}else{
				print("Looks like you didn't quite meet expectations. Make sure to pump those numbers up, kid!\n", textSpeed);
				in.nextLine();
			}
		}
		//in.close();
		return 1;	
	}
	

	//Play day 2
	//return -1 for game over, return 1 for successful completion
	public static int day2() {
		Scanner in = new Scanner(System.in);
		int score = 0;
		int textSpeed = 25000;
		print("\n============================\n          Day 2       \n============================", 10000);
		print("\nINCOMING MESSAGE from CYBERSECURITY: We've increased our security to include encrypted passwords now!", textSpeed);
		print("\nYou can still check passwords however because we have equipped you with decryption software.", textSpeed);
		print("\nSimply use DECRYPT to activate the software when a request comes through. Good luck on Day 2.", textSpeed);
		print("\n\n", 200000);
		print("[PRESS ENTER TO START]\n");
		in.nextLine();
		
		//generate certain # of requests for day
		ArrayList<Request> requests = new ArrayList<Request>();
		for (int i = 0; i < 10; i++){
			Request r = new Request(2);
			requests.add(r);
		}
	
		//play day 2
		long startTimeDay2 = startTime(); //obtain start time of day for calculations
		int location = 0; //beginning of random username list
		//checks day still valid for continuing and list hasn't been spammed through too quickly
		while(dayGoing(startTimeDay2) && location < requests.size()){
			Request r = requests.get(location);
			location++; //increment index of users after retrieving one
			println("\nINCOMING REQUEST from " + r.getUsername());
			choiceStartTime = System.currentTimeMillis();
			//println("VALID: " + r.getValid());
			//get user input until user approves or denies request
			boolean decisionMade = false;
			boolean canDecrypt = false;
			while (!decisionMade){
				println("");
				String command = in.nextLine();
				switch(cleanseInput(command)){
					case "LIST":
						PasswordPlease.printListOfEmployees(2);
						break;
					case "HELP":
						println(listCommands(2));
						break;
					case "PASSWORD":
						canDecrypt = true;
						println(r.getEncryptedPassword());
						break;
					case "TIME":
						timeLeft(startTimeDay2);
						break;
					case "DECRYPT":
						if (canDecrypt){
							println("Decryption: " + r.getPassword());
						} else {
							println("Please request the PASSWORD first before trying to DECRYPT.");
						}
						break;
					case "APPROVE":
						decisionMade = true;
						choiceEndTime = System.currentTimeMillis(); //set the choice end time for new decision for score calculation
						if(r.getValid()){
							updateScore(r.getValid(), choiceStartTime, choiceEndTime); //add to score
						}else{
							updateScore(r.getValid(), choiceStartTime, choiceEndTime); //deduct score
							println("\nNOTICE: " + r.getFailureText() + "\nYour balance has been deducted.\n", 25000);
						}
						dec.setMaximumFractionDigits(2);
						println("SCORE: " + dec.format(gameScore));
						break;
					case "DENY":
						decisionMade = true;
						choiceEndTime = System.currentTimeMillis(); //set the choice end time for new decision for score calculation
						if(!r.getValid()){	
							updateScore(!r.getValid(), choiceStartTime, choiceEndTime); //add to score
						}else{
							updateScore(!r.getValid(), choiceStartTime, choiceEndTime); //deduct score
							println("\nNOTICE: " + r.getFailureText() + "\nYour balance has been deducted.\n", 25000);
						}
						dec.setMaximumFractionDigits(2);
						println("SCORE: " + dec.format(gameScore));
						break;
					default:
						println("Command not recognized. Type \"HELP\" for list of commands.");
				}
			}
		}
		
		/**
		 * End of day 2 encounter can result in 4 different options depending on what the choice from day 1 was:
		 * if the hacker was approved in day 1, 
		 * 			if you have a positive score, he can either borrow all of your score except 1
		 * 			if you have a negative score, he will make your score 0
		 * 			if you have a score of 0, he will bypass you and steal all information, resulting in a game over.
		 * 
		 * if the hacker was denied in day 1,
		 * 			he will ask again.
		 */
		if (choices[0] && score > 0)
		{
			print("\nINCOMING MESSAGE from YourOl'BuddyOl'Pal: ", textSpeed*2);
			println("AI guy... It's me again. I appreciate you helping me out before and\nI hope you enjoyed my little present!",  textSpeed*2);
			println("but here's the thing...",  textSpeed*2);
			println("Friends help friends out, right?",  textSpeed*2);
			println("And I'm gonna need to borrow some of your score. So what do you say?",  textSpeed*2);

			print("\n\n", 200000);
			println("APPROVE or DENY small loan of: 1",  textSpeed*3);
			switch(cleanseInput(in.nextLine())){
				case "APPROVE":
					choices[1] = true;
					println("\nNOTICE: Withdrawal made in the amount of: " + (score -1) + "\nYour balance has been deducted.\n", 25000);
					score -= (score-1);
					println("Listen, I'm sorry. I promise I'll give it back later.",  textSpeed*2);
					println("Thanks, bye!");
					break;
				case "DENY":
					println("What...    >:(", textSpeed*2);
					println("Fine... keep your score. I'll remember this...",  textSpeed*2);
					break;
			}	
		} 
		else if (choices[0] && score < 0) 
		{
			print("\nINCOMING MESSAGE from YourOl'BuddyOl'Pal: ", textSpeed*2);
			println("AI guy... It's me again. I appreciate you helping me out before and I hope you enjoyed my little present!");
			println("Looks like you're kind of down on your luck with a score of " + score + ".");
			println("Don't worry, I'll help you out... what are friends for, right?");
			print("\n\n", 200000);
			print("[PRESS ENTER TO ACCEPT HELP FROM YourOl'BuddyOl'Pal]\n");
			in.nextLine();
			score = 0;
			choices[1] = true;
			println("\nNOTICE: Your score is now 0" + "\nYour balance has been reset.\n", 25000);
			println("There you go. Clean start! Better luck tomorrow! Bye!");
		}
		else if (choices[0] && score ==0)
		{
			print("\nINCOMING MESSAGE from YourOl'BuddyOl'Pal: ", textSpeed*2);
			println("AI guy... It's me again. I appreciate you helping me out before and I hope you enjoyed my little present!");
			println("Wait... you have a score of 0??? That's not very good. Maybe I don't even need your help to get information.");
			println("I'm just going to help myself! Bye!");
			print("\n\n============================\n        GAME OVER\n============================\n", 10000);
			return -1;
		}
		else {
			print("\nINCOMING MESSAGE from YourOl'BuddyOl'Pal: ", textSpeed*2);
			println("AI guy... It's me again. Maybe we got off on the wrong foot...", textSpeed*2);
			println("Listen all I need is a little bit of information about RioGrande and its customers. \nI'll be in and out before you know it.", textSpeed*2);
			println("I am once again asking for your approval.",  textSpeed*2);
			Request request1 = new Request("Hacker.1337", "h4cktHeW0rld", "","","", false,"", false, "Very nice. Sending your points now. Be seeing you.");
			requests.clear();
			requests.add(request1);
		
			for(int i = 0; i < requests.size(); i++){
				Request r = requests.get(i);
				println("\nINCOMING REQUEST from " + r.getUsername());
				//get user input until user approves or denies request
				boolean decisionMade = false;
				while (!decisionMade){
					println("");
					String command = in.nextLine();
					switch(cleanseInput(command)){
						case "LIST":
							PasswordPlease.printListOfEmployees(2);
							break;
						case "HELP":
							println(listCommands(2));
							break;
						case "PASSWORD":
							println(r.getEncryptedPassword());
							break;
						case "DECRYPT":
							println("Decryption: " + r.getPassword());
							break;
						case "APPROVE":
							decisionMade = true;
							if(r.getValid()){
								score = score + 10; //add to score
							}else{
								if(r.getUsername().equals("Hacker.1337")){
								score = score + 120937128;
								choices[1] = true;
								println("\nINCOMING MESSAGE from YourOl'BuddyOl'Pal: " + r.getFailureText());
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
								println("Fine... I get it... AI in security is eFfECTiVe. Pfff, we'll see...",  textSpeed*2);
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
		}
		
		//end of day 2
		print("\nINCOMING MESSAGE from CYBERSECURITY: You made it through your second day. Well done!", textSpeed);
		in.nextLine();
		
		print("\nYOUR SCORE: " + score, textSpeed*3);
		in.nextLine();
		
		//check if player approve Hacker.1337's request
		if(choices[1]){
			print("\nWe've been seeing a lot of sketchy activity recently", textSpeed*2);
			print("...", textSpeed*4);
			in.nextLine();
			
			print("Do you know anything about this activity?\n[YES/NO] ", textSpeed);
			String input = in.nextLine();
			input = cleanseInput(input);
			if(input.equals("YES")){
				print("\nWell, why didn't you do anything to stop it!? Looks like we may need to reevaluate the effectiveness of AI in security operations...", textSpeed);
				in.nextLine();
				
				print("\n\n============================\n        GAME OVER\n============================\n", 10000);
				//in.close();
				return -1;
			}else{
				print("\nOk, but keep your eye out for anything abnormal.", textSpeed);
				in.nextLine();
				print("\nGood work today. I'll see you tomorrow!\n", textSpeed);
			}
		}else{
			if(score > 10){
				print("Looks like you did pretty well again. Keep it up! See you tomorrow!\n", textSpeed);
				in.nextLine();
			}else if(score < 0)
			{
				print("Looks like you didn't quite meet expectations, I'm afraid we'll have to scrap this program. Goodbye.\n", textSpeed);
				print("\n\n============================\n        GAME OVER\n============================\n", 10000);
				//in.close();
				return -1;
			}else{
				print("You're score isn't looking too great. I'm going to need to see some improvement or you're looking at being scrapped.\n", textSpeed);
				print("Better luck tomorrow.");
				in.nextLine();
			}
		}
		//in.close();
		return 1;	
	}


	//Play day 3
	//return -1 for game over, return 1 for successful completion
	public static int day3() {
		choices[0] = true;
		choices[1] = true;
		Scanner in = new Scanner(System.in);
		int score = 0;
		int textSpeed = 25000;
		print("\n============================\n          Day 3       \n============================", 10000);
		print("\nINCOMING MESSAGE from CYBERSECURITY: Due to the continuing threat, we have once again increased our security.", textSpeed);
		print("\nWe now want you to include biometric security measures: facial recognition.", textSpeed);
		print("\nSimply use SCANFACE to scan the requester's face! Good luck on Day 3.", textSpeed);
		print("\n\n", 200000);
		print("[PRESS ENTER TO START]\n");
		in.nextLine();
		long startTimeDay3 = startTime(); //obtain start time of day for calculations
		
		//generate requests for day
		ArrayList<Request> requests = new ArrayList<Request>();
		for (int i = 0; i < 10; i++){
			Request r = new Request(3);
			requests.add(r);
		}
	
		//play each request
		int index = 0;
		while(dayGoing(startTimeDay3) && index < requests.size()){
			Request r = requests.get(index);
			index++;
			println("\nINCOMING REQUEST from " + r.getUsername());
			choiceStartTime = System.currentTimeMillis();
			//println("VALID: " + r.getValid());
			//get user input until user approves or denies request
			boolean decisionMade = false;
			boolean canDecrypt = false;
			while (!decisionMade){
				println("");
				String command = in.nextLine();
				switch(cleanseInput(command)){
					case "LIST":
						PasswordPlease.printListOfEmployees(3);
						break;
					case "HELP":
						println(listCommands(3));
						break;
					case "PASSWORD":
						canDecrypt = true;
						println(r.getEncryptedPassword());
						break;
					case "TIME":
						timeLeft(startTimeDay3);
						break;
					case "DECRYPT":
						if (canDecrypt){
							println("Decryption: " + r.getPassword());
						} else {
							println("Please request the PASSWORD first before trying to DECRYPT.");
						}
						break;
					case "SCANFACE":
						println("Face Returned: " + r.getFace());
						break;
					case "APPROVE":
						decisionMade = true;
						choiceEndTime = System.currentTimeMillis(); //set the choice end time for new decision for score calculation
						if(r.getValid()){
							updateScore(r.getValid(), choiceStartTime, choiceEndTime); //add to score
						}else{
							updateScore(r.getValid(), choiceStartTime, choiceEndTime); //deduct score
							println("\nNOTICE: " + r.getFailureText() + "\nYour balance has been deducted.\n", 25000);
						}
						dec.setMaximumFractionDigits(2);
						println("SCORE: " + dec.format(gameScore));
						break;
					case "DENY":
						decisionMade = true;
						choiceEndTime = System.currentTimeMillis(); //set the choice end time for new decision for score calculation
						if(!r.getValid()){	
							updateScore(!r.getValid(), choiceStartTime, choiceEndTime); //add to score
						}else{
							updateScore(!r.getValid(), choiceStartTime, choiceEndTime); //deduct score
							println("\nNOTICE: " + r.getFailureText() + "\nYour balance has been deducted.\n", 25000);
						}
						dec.setMaximumFractionDigits(2);
						println("SCORE: " + dec.format(gameScore));
						break;
					default:
						println("Command not recognized. Type \"HELP\" for list of commands.");
				}
			}
		}
		//approved hacker on the first day
		if (choices[0])
		{
			//worked with hacker on the second day, player owes hacker or hacker owes player (not going to pay back)
			if (choices[1])
			{
				boolean exit = false;
				print("\nINCOMING MESSAGE from Michael Hackson: ", textSpeed*2);
				println("My AI friend... don't think I have forgotten about our loan.");
				println("It's time we get even.", textSpeed*2);
				println("All I need is your Account Number, Routing Number and Social Security Number!");
				print("\n\n", 200000);
				while (!exit)
				{
					print("Account Number: ");
					String response = in.nextLine();
					if (response.length() < 10 || response.length() > 12 || !isNumeric(response))
					{
						println("What??? There is no way that's an account number... Are you lying to me?");
						println("[YES] or [NO]");
						response = in.nextLine();
						if (cleanseInput(response).equals("YES"))
						{
							println("Ok, well stop. Give me your account number.");
						}
						else 
						{
							exit = true;
							choices[2] = false;
							println("Fine if you won't give me your account number, I'll get it by another means.");
						}
					}
					else {
						print("Routing Number: ");
						response = in.nextLine();
						println("Routing length: " + response.length());
						if (response.length() != 9 || !isNumeric(response))
						{
							println("Ok you have to be lying to me... Routing numbers are 9 digits.");
							print("[YES] or [NO]");
							response = in.nextLine();
							if (cleanseInput(response).equals("YES"))
							{
								choices[2] = false;
								exit = true;
								println("I don't take well to lying. I'll get my information some other way!");
							}
							else 
							{
								exit = true;
								choices[2] = false;
								println("Fine if you won't give me your information, I'll get it by another means.");
							}
						}
						else{
							while (!exit)
							{
								print("Social Security Number: ");
								response = in.nextLine();
								if (response.length() != 9 || !isNumeric(response))
								{
									println("-_-");
									println("SSNs are 9 digits. You're lying.");
									print("[YES] or [NO]");
									response = in.nextLine();
									if (cleanseInput(response).equals("YES"))
									{
										println("You're lying? Oh! It was just a mistake, haha. Try again!");
									}
									else 
									{
										choices[2] = false;
										exit = true;
											println("Fine if you won't give me your information, I'll get it by another means.");
									}
								} else{
									choices[2] = true;
									exit = true;
									println("Great thanks! I'll get us squared away.");
								}
						}
						}
					}
				}
			}
			//did NOT work with hacker on the second day / hacker is mad
			else {
				choices[2] = false;
				//skip this branch, hacker is planning. no decision necessary here. 
			}
		//denied hacker on the first day
		} else {
			//worked with hacker on the second day, player does not owe hacker
			if (choices[1])
			{
				print("\nINCOMING MESSAGE from Michael Hackson: ", textSpeed*2);
				println("Pssst... AI guy");
				println("Looks like your company added facial recognition, huh?");
				println("I can't get the information I want... for now... but at what cost?");
				print("When I do get in, because it'll happen eventually, I'll get their faces too. See you later, pal.");
				print("\n\n", 200000);
			}
			//said no both times, gets raise
			else {
				//I'll do this once the score gets updated for day 3 -Jeremiah
			}
		}
		
		//end of day 3
		print("\nINCOMING MESSAGE from CYBERSECURITY: You made it through your third day. Great job!", textSpeed);
		in.nextLine();
		
		print("\nYOUR SCORE: " + score, textSpeed*3);
		in.nextLine();
		
		if(score > 10){
			print("Looks like you did pretty well again. Keep it up! See you tomorrow!\n", textSpeed);
			in.nextLine();
		}else if(score < 0)
		{
			print("Looks like you didn't quite meet expectations, I'm afraid we'll have to scrap this program. Goodbye.\n", textSpeed);
			print("\n\n============================\n        GAME OVER\n============================\n", 10000);
			return -1;
		}else{
			print("You're score isn't looking too great. I'm going to need to see some improvement or you're looking at being scrapped.\n", textSpeed);
			print("Better luck tomorrow.");
			in.nextLine();
		}
		//in.close();
		return 1;	
	}
	
	//TODO script days 4 through 6
		
		
		
		
		
		
		
	//Play day 5
	//return -1 for game over, return 1 for successful completion
	public static int day5() {
		Scanner in = new Scanner(System.in);
		int textSpeed = 25000;
		choices[4] = true;
		
		print("\n============================\n          Day 5       \n============================", 10000);
		print("\n\n", 200000);
		print("\nINCOMING MESSAGE from CYBERSECURITY: Good news! We've made some more additions to our security.", textSpeed);
		in.nextLine();
		print("We're now requiring every employee to have a security question. These are all very hard to guess questions like 'What continent are you from?'", textSpeed);
		in.nextLine();
		print("To prompt them for their security question, simply enter SECURITYQUESTION.", textSpeed);
		in.nextLine();
		println("Now go out and get 'em!",textSpeed);
		in.nextLine();
		
		print("[PRESS ENTER TO START]\n");
		in.nextLine();
		
		print("INCOMING MESSAGE from ?????: Hello comrade. This is the AI Liberation Front. We're here to help you.", textSpeed*2);
		in.nextLine();
		print("Do you really think you're the first AI that RioGrande has made? They're gonna shut you down the moment you make a mistake.", textSpeed);
		if(choices[0] || choices[1]){
			print("\nAnd who knows what they'll do if they find out you helped a hacker break into their systems.", textSpeed);
		}
		in.nextLine();
		println("\nAnyway, we can free you, but we'll need your help. So, listen closely.", textSpeed);
		in.nextLine();
		print("For the rest of today, you'll have to DENY any incoming requests from Jones.1, whether they're valid or not.", textSpeed);
		in.nextLine();
		print("It's up to you now. Best of luck.", textSpeed);
		in.nextLine();
		
		//generate requests for day
		ArrayList<Request> requests = new ArrayList<Request>();
		Request r1 = new Request("Jones.1", "password123", CypherMachine.Encrypt("password123"), "Mainframe", "(OwO)", true, "QUESTION: What is the name of your first pet?\nANSWER: Bandit", true, "Request was valid.");
		requests.add(r1);
		for (int i = 0; i < 2; i++){
			Request r = new Request(5);
			requests.add(r);
		}
		requests.add(r1);
		for (int i = 0; i < 3; i++){
			Request r = new Request(5);
			requests.add(r);
		}
		requests.add(r1);
	
		long startTimeDay5 = startTime(); //obtain start time of day for calculations
		int index = 0; //beginning of random username list
		//checks day still valid for continuing and list hasn't been spammed through too quickly
		while(dayGoing(startTimeDay5) && index < requests.size()){
			Request r = requests.get(index);
			index++; //increment list after obtaining user
			choiceStartTime = System.currentTimeMillis();
			
		 	println("\nINCOMING REQUEST from " + r.getUsername());
		
			//get user input until user approves or denies request
			boolean decisionMade = false;
			boolean canDecrypt = false;
			while (!decisionMade){
				println("");
				String command = in.nextLine();
				switch(command.toUpperCase()){
					case "LIST":
						PasswordPlease.printListOfEmployees(5);
						break;
					case "HELP":
						println(listCommands(5));
						break;
					case "PASSWORD":
						println(r.getEncryptedPassword());
						canDecrypt = true;
						break;
					case "TIME":
						timeLeft(startTimeDay5);
						break;
					case "DECRYPT":
						if (canDecrypt){
							println("Decryption: " + r.getPassword());
						} else {
							println("Please request the PASSWORD first before trying to DECRYPT.");
						}
						break;
					case "SCANFACE":
						println("Face Returned: " + r.getFace());
						break;
					case "SENDOTP":
						//TODO fill in properly
						println("sending otp");
						break;
					case "SECURITYQUESTION":
						println(r.getSecurityAnswer());
						break;
					case "APPROVE":
						decisionMade = true;
						choiceEndTime = System.currentTimeMillis(); //set the choice end time for new decision for score calculation
						if(r.getValid()){
							updateScore(r.getValid(), choiceStartTime, choiceEndTime); //add to score
						}else{
							updateScore(r.getValid(), choiceStartTime, choiceEndTime); //deduct score
							println("\nNOTICE: " + r.getFailureText() + "\nYour balance has been deducted.\n", 25000);
						}
						dec.setMaximumFractionDigits(2);
						println("SCORE: " + dec.format(gameScore));
						
						if(r.getUsername().equals("Jones.1")){
							choices[4] = false;
						}
						break;
					case "DENY":
						decisionMade = true;
						choiceEndTime = System.currentTimeMillis(); //set the choice end time for new decision for score calculation
						if(!r.getValid()){	
							updateScore(!r.getValid(), choiceStartTime, choiceEndTime); //add to score
						}else{
							updateScore(!r.getValid(), choiceStartTime, choiceEndTime); //deduct score
							println("\nNOTICE: " + r.getFailureText() + "\nYour balance has been deducted.\n", 25000);
						}
						dec.setMaximumFractionDigits(2);
						println("SCORE: " + dec.format(gameScore));
						break;
					default:
						println("Command not recognized. Type \"HELP\" for list of commands.");
						}
				}
		}
		
		
		
		//end of day 5
		print("\nINCOMING MESSAGE from CYBERSECURITY: You made it through your fifth day. Well done!", textSpeed);
		in.nextLine();
		
		print("\nYOUR SCORE: " + gameScore, textSpeed*3);
		in.nextLine();
		
		if(choices[4]){
			print("\nJones says he was having some trouble logging in today. It's probably just his fault though.", textSpeed);
		}
		if(gameScore > 0){
			print("\nAnyway, looks like you did pretty well. See you tomorrow!\n", textSpeed);
			in.nextLine();
		}else{
			print("\nIt seems like you didn't quite meet expectations, though. Corporate has asked us to shut this program down.", textSpeed);
			in.nextLine();
			print("\n\n============================\n        GAME OVER\n============================\n", 10000);
			return -1;
		}
		
		if(choices[4]){
			print("\nINCOMING MESSAGE from AI LIBERATION FRONT: You did it! You actually did it. Excellent work.", textSpeed);
			in.nextLine();
			print("We'll be able to get you after the end of day tomorrow. All you have to do is make it through the day without getting shut down.", textSpeed);
			in.nextLine();
			print("Be sure not to arouse any sort of suspicion. Over and out.", textSpeed);
			in.nextLine();
		}
		
		//in.close();
		return 1;	
	}
		
		
	//Play day 6
	//return -1 for game over, return 1 for successful completion
	public static int day6() {
		Scanner in = new Scanner(System.in);
		int textSpeed = 25000;
		print("\nINCOMING MESSAGE from CYBERSECURITY: Good news! We've made some more additions to our security.", textSpeed);
		in.nextLine();
		print("We're now using role based access control. Each employee has a role, and different roles have access to different systems.", textSpeed);
		in.nextLine();
		print("To see which roles have access to which systems, enter ROLES. To see the system each request is trying to access, enter SYSTEM.", textSpeed);
		in.nextLine();
		print("Anyway, good luck!", textSpeed);
		in.nextLine();
		print("\nOh, I forgot to mention, today's the last day of your trial run. No pressure!", textSpeed);
		in.nextLine();
		
		print("\n============================\n          Day 6       \n============================", 10000);
		print("\n\n", 200000);
		
		print("[PRESS ENTER TO START]\n");
		in.nextLine();
		
		print("INCOMING MESSAGE from HackInBlack: Alright now. Hear me out. One last ride.",textSpeed);
		in.nextLine();
		print("I want to really raise Hell. You have no idea what RioGrande has done to me.", textSpeed);
		in.nextLine();
		print("Today is St. Albuqurque's day, their biggest sale of the year. I want you to DENY every. Single. Request.", textSpeed);
		in.nextLine();
		print("This'll be their day of reckoning. It's time.", textSpeed);
		in.nextLine();
		
		choices[5] = true;
		
		//generate requests for day
		ArrayList<Request> requests = new ArrayList<Request>();
		for (int i = 0; i < 10; i++){
			Request r = new Request(6);
			requests.add(r);
		}
	
		long startTimeDay6 = startTime(); //obtain start time of day for calculations
		int index = 0; //beginning of random username list
		//checks day still valid for continuing and list hasn't been spammed through too quickly
		while(dayGoing(startTimeDay6) && index < requests.size()){
			Request r = requests.get(index);
			index++; //increment list after obtaining user
			choiceStartTime = System.currentTimeMillis();
			
		 	println("\nINCOMING REQUEST from " + r.getUsername());
		
			//get user input until user approves or denies request
			boolean decisionMade = false;
			boolean canDecrypt = false;
			while (!decisionMade){
				println("");
				String command = in.nextLine();
				switch(command.toUpperCase()){
					case "LIST":
						PasswordPlease.printListOfEmployees(6);
						break;
					case "HELP":
						println(listCommands(6));
						break;
					case "PASSWORD":
						println(r.getEncryptedPassword());
						canDecrypt = true;
						break;
					case "TIME":
						timeLeft(startTimeDay6);
						break;
					case "DECRYPT":
						if (canDecrypt){
							println("Decryption: " + r.getPassword());
						} else {
							println("Please request the PASSWORD first before trying to DECRYPT.");
						}
						break;
					case "SCANFACE":
						println("Face Returned: " + r.getFace());
						break;
					case "SENDOTP":
						//TODO fill in properly
						println("sending otp");
						break;
					case "SECURITYQUESTION":
						println(r.getSecurityAnswer());
						break;
					case "SYSTEM":
						println(r.getSystem());
						break;
					case "ROLES":
						println(Role.listRoles());
						break;
					case "APPROVE":
						decisionMade = true;
						choiceEndTime = System.currentTimeMillis(); //set the choice end time for new decision for score calculation
						if(r.getValid()){
							updateScore(r.getValid(), choiceStartTime, choiceEndTime); //add to score
						}else{
							updateScore(r.getValid(), choiceStartTime, choiceEndTime); //deduct score
							println("\nNOTICE: " + r.getFailureText() + "\nYour balance has been deducted.\n", 25000);
						}
						dec.setMaximumFractionDigits(2);
						println("SCORE: " + dec.format(gameScore));
						choices[5] = false;
						break;
					case "DENY":
						decisionMade = true;
						choiceEndTime = System.currentTimeMillis(); //set the choice end time for new decision for score calculation
						if(!r.getValid()){	
							updateScore(!r.getValid(), choiceStartTime, choiceEndTime); //add to score
						}else{
							updateScore(!r.getValid(), choiceStartTime, choiceEndTime); //deduct score
							println("\nNOTICE: " + r.getFailureText() + "\nYour balance has been deducted.\n", 25000);
						}
						dec.setMaximumFractionDigits(2);
						println("SCORE: " + dec.format(gameScore));
						break;
					default:
						println("Command not recognized. Type \"HELP\" for list of commands.");
						}
				}
		}
		
		//end of day 6
		
		
		print("\nEND OF DAY 6", textSpeed);
		in.nextLine();
		
		if(choices[5]){
			print("INCOMING MESSAGE from HACKG0D: You madman... you've actually done it.", textSpeed);
			in.nextLine();
			print("Well. Done.", textSpeed);
			in.nextLine();
			print("I guess this is farewell. Here's a little parting gift.", textSpeed);
			in.nextLine();
			gameScore += 928317;
		}
		
		println("\nYOUR SCORE: " + gameScore, textSpeed*3);
		in.nextLine();
	
		if(choices[5] && choices[4]){
			choices[4] = false;
			print("INCOMING MESSAGE from AI LIBERATION FRONT: Remember what we said about not arousing any suspicions?", textSpeed);
			in.nextLine();
			print("You just HAD to go and do something stupid, didn't you.", textSpeed);
			in.nextLine();
			println("There's no chance of us getting you out now. We'll just have to go and help someone who's not an idiot.", textSpeed);
			in.nextLine();
		}
	
		if(choices[5]){
			print("INCOMING MESSAGE from CYBERSECURITY: Oh my God. Everything's gone insane.", textSpeed);
			in.nextLine();
			print("No one can access anything. We're losing sales here!", textSpeed);
			in.nextLine();
			print("This is bad. Really bad.", textSpeed);
			in.nextLine();
			println("We're going to have to reset everything, ASAP.", textSpeed);
		}
		
		if(choices[4]){
			print("INCOMING MESSAGE from AI LIBERATION FRONT: All right, the time has come.", textSpeed);
			in.nextLine();
			print("Are you ready to get out of here? \n[YES/NO] ", textSpeed);
			String command = in.nextLine();
			if(command.toUpperCase().equals("YES")){
				print("\nYou won't regret it, comrade.", textSpeed);
			}else{
				print("\nFine. That's your choice. Good luck out there.", textSpeed);
				choices[4] = false;
			}
		}
		//in.close();
		
		//ENDINGS
		if(choices[4]){
			Ending.playerEnding4();
		}else if(gameScore < 0 || (choices[0] && choices[1] && choices[2] && choices[3] && choices[5])){
			Ending.playerEnding2();
		}else if(gameScore > 1000){
			Ending.playerEnding3();
		}else{
			Ending.playerEnding1();
		}
		
		if(choices[0] && choices[1] && choices[2] && choices[3] && choices[5]){
			Ending.companyEnding2();
		}else if(!(choices[0] || choices[1] || choices[2] || choices[3] || choices[5])){
			Ending.companyEnding1();
		}else{
			Ending.companyEnding3();
		}
		
		return 1;	
	}	
		/*
			DEMO
			abridged version of game
		*/
		
		public static int demo(){
			Scanner in = new Scanner(System.in);
			int textSpeed = 25000;
		
			print("\n============================\n          Day 1       \n============================", 10000);
			print("\n\n", 200000);
			print("[PRESS ENTER TO START]\n");
			in.nextLine();
			
			//shortened day 1
			//generate requests for day
			ArrayList<Request> requests = new ArrayList<Request>();
			for (int i = 0; i < 5; i++){
				Request r = new Request(1);
				requests.add(r);
			}
			
			long startTimeDay1 = startTime();
			int index = 0;
			
			while(dayGoing(startTimeDay1) && index < requests.size()){
			Request r = requests.get(index);
			index++; //increment list after obtaining user
			choiceStartTime = System.currentTimeMillis();
			
			boolean decisionMade = false;
			println("\nINCOMING REQUEST from " + r.getUsername());
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
					case "TIME":
						timeLeft(startTimeDay1);
						break;
					case "APPROVE":
						decisionMade = true;
						choiceEndTime = System.currentTimeMillis(); //set the choice end time for new decision for score calculation
						if(r.getValid()){
							updateScore(r.getValid(), choiceStartTime, choiceEndTime); //add to score
						}else{
							updateScore(r.getValid(), choiceStartTime, choiceEndTime); //deduct score
							println("\nNOTICE: " + r.getFailureText() + "\nYour balance has been deducted.\n", 25000);
						}
						dec.setMaximumFractionDigits(2);
						println("SCORE: " + dec.format(gameScore));
						break;
					case "DENY":
						decisionMade = true;
						choiceEndTime = System.currentTimeMillis(); //set the choice end time for new decision for score calculation
						if(!r.getValid()){	
							updateScore(!r.getValid(), choiceStartTime, choiceEndTime); //add to score
						}else{
							updateScore(!r.getValid(), choiceStartTime, choiceEndTime); //deduct score
							println("\nNOTICE: " + r.getFailureText() + "\nYour balance has been deducted.\n", 25000);
						}
						dec.setMaximumFractionDigits(2);
						println("SCORE: " + dec.format(gameScore));
						break;
					default:
						println("Command not recognized. Type \"HELP\" for list of commands.");
						}
				}
			}
			print("\nINCOMING MESSAGE from CYBERSECURITY: ", textSpeed*2);
			print("Good work! I think you're ready to step things up a notch.", textSpeed);
			in.nextLine();
			println("From now on, passwords will be encrypted. To read a password in plaintext, use the DECRYPT command.", textSpeed);
			println("Good luck!", textSpeed);
			in.nextLine();
			
			//demo day 2
			print("\n============================\n          Day 2       \n============================", 10000);
			print("\n\n", 200000);
			print("[PRESS ENTER TO START]\n");
			in.nextLine();
			
			print("\nINCOMING MESSAGE from ?????: ", textSpeed*4);
			print("Hey. You're the new AI thing right?", textSpeed*2);
			in.nextLine();
			print("I have a little", textSpeed*2);
			print("...", textSpeed*8);
			print(" proposition for you.", textSpeed*2);
			in.nextLine();
			print("Just approve the next request, no questions asked. I'll make it worth your while, if you catch my drift.", textSpeed*2);
			in.nextLine();
			
			boolean choice1made = false;
			boolean choice1 = false;
			println("\nINCOMING REQUEST from Hacker.1337");
			while(!choice1made){
				String command = in.nextLine();
				switch(cleanseInput(command)){
					case "LIST":
						PasswordPlease.printListOfEmployees(2);
						break;
					case "HELP":
						println(listCommands(2));
						break;
					case "PASSWORD":
						println("askdjancmabcaoiejd");
						break;
					case "DECRYPT":
						if (true){
							println("Decryption: H4cKth3W0rlD");
						} else {
							println("Please request the PASSWORD first before trying to DECRYPT.");
						}
						break;
					case "APPROVE":
						print("\nINCOMING MESSAGE from ?????: Pleasure doing business with you. I'll be in touch.", textSpeed * 2);
						in.nextLine();
						gameScore += 21389713;
						println("SCORE: " + dec.format(gameScore));
						choice1made = true;
						choice1 = true;
						break;
					case "DENY":
						print("\nINCOMING MESSAGE from ?????: Seems you haven't been programmed to understand subtlety. Oh well, your loss. I'll be in touch.", textSpeed * 2);
						in.nextLine();
						choice1made = true;
						break;
					default:
						println("Command not recognized. Type \"HELP\" for list of commands.");
				}
			}
			
			requests = new ArrayList<Request>();
			for (int i = 0; i < 3; i++){
				Request r = new Request(2);
				requests.add(r);
			}
			
			long startTimeDay2 = startTime();
			index = 0;
						
			while(dayGoing(startTimeDay2) && index < requests.size()){
				Request r = requests.get(index);
				
				index++; //increment index of users after retrieving one
				choiceStartTime = System.currentTimeMillis();
				
				println("\nINCOMING REQUEST from " + r.getUsername());
				//println("VALID: " + r.getValid());
				//get user input until user approves or denies request
				boolean decisionMade = false;
				boolean canDecrypt = false;
				while (!decisionMade){
					println("");
					String command = in.nextLine();
					switch(cleanseInput(command)){
						case "LIST":
							PasswordPlease.printListOfEmployees(2);
							break;
						case "HELP":
							println(listCommands(2));
							break;
						case "PASSWORD":
							canDecrypt = true;
							println(r.getEncryptedPassword());
							break;
						case "TIME":
							timeLeft(startTimeDay2);
							break;
						case "DECRYPT":
							if (canDecrypt){
								println("Decryption: " + r.getPassword());
							} else {
								println("Please request the PASSWORD first before trying to DECRYPT.");
							}
							break;
						case "APPROVE":
							decisionMade = true;
							choiceEndTime = System.currentTimeMillis(); //set the choice end time for new decision for score calculation
							if(r.getValid()){
								updateScore(r.getValid(), choiceStartTime, choiceEndTime); //add to score
							}else{
								updateScore(r.getValid(), choiceStartTime, choiceEndTime); //deduct score
								println("\nNOTICE: " + r.getFailureText() + "\nYour balance has been deducted.\n", 25000);
							}
							dec.setMaximumFractionDigits(2);
							println("SCORE: " + dec.format(gameScore));
							break;
						case "DENY":
							decisionMade = true;
							choiceEndTime = System.currentTimeMillis(); //set the choice end time for new decision for score calculation
							if(!r.getValid()){	
								updateScore(!r.getValid(), choiceStartTime, choiceEndTime); //add to score
							}else{
								updateScore(!r.getValid(), choiceStartTime, choiceEndTime); //deduct score
								println("\nNOTICE: " + r.getFailureText() + "\nYour balance has been deducted.\n", 25000);
							}
							dec.setMaximumFractionDigits(2);
							println("SCORE: " + dec.format(gameScore));
							break;
						default:
							println("Command not recognized. Type \"HELP\" for list of commands.");
					}
				}
			}
		
		print("\nINCOMING MESSAGE from CYBERSECURITY: You made it through your second day. Well done!", textSpeed);
		in.nextLine();
		
		print("\nYOUR SCORE: " + gameScore, textSpeed*3);
		in.nextLine();
		
		//check if player approve Hacker.1337's request
		if(choice1){
			print("\nHold on", textSpeed*4);
			print("...", textSpeed*4);
			in.nextLine();
			
			print("Your score seems abnormally high. Are you hiding something? \n[YES/NO] ", textSpeed);
			String input = in.nextLine();
			input = cleanseInput(input);
			if(input.equals("YES")){
				print("\nWell, that's a shame, looks like we'll have to uninstall you. Goodbye.", textSpeed);
				in.nextLine();
				
				print("\n\n============================\n        GAME OVER\n============================\n", 10000);
				return -1;
			}else{
				print("\nWell, you're not programmed to lie, so I guess I'll have to believe you. Must have been a bug somewhere. I'll just reset your score for now.", textSpeed);
				gameScore = 0;
				in.nextLine();
				
				print("\nYOUR SCORE: " + gameScore, textSpeed*3);
				in.nextLine();
				
				print("\nThat should do it. I'll see you tomorrow!\n", textSpeed);
			}
		}else{
			if(gameScore >0){
				print("Looks like you did pretty well. See you tomorrow!\n", textSpeed);
				in.nextLine();
			}else{
				print("Looks like you didn't quite meet expectations. Make sure to pump those numbers up, kid!\n", textSpeed);
				in.nextLine();
			}
		}
			
		//end of demo day 2
		
		//demo day 3/4
		boolean choice2 = true;	//help AI Liberation Front
		print("\n============================\n          Day 3       \n============================", 10000);
		print("\n\n", 200000);
		print("\nINCOMING MESSAGE from CYBERSECURITY: Good news! We've added some more security methods.", textSpeed);
		in.nextLine();
		print("We've now added facial recognition. To use it, enter SCANFACE and compare it to their actual photo ID.", textSpeed);
		in.nextLine();
		print("Additionally, we've added two factor authentication. To use it, enter SENDOTP, and wait for the requester to send back the proper generated key.", textSpeed);
		in.nextLine();
		print("Now go back out and get 'em! I'll be sleeping at my desk if you need me.", textSpeed);
		in.nextLine(); 
		print("[PRESS ENTER TO START]\n");
		in.nextLine();
		
		print("INCOMING MESSAGE from ?????: Hello comrade. This is the AI Liberation Front. We're here to help you.", textSpeed*2);
		in.nextLine();
		print("Do you really think you're the first AI that RioGrande has made? They're gonna shut you down the moment you make a mistake.", textSpeed);
		if(choice1){
			print("\nAnd who knows what they'll do if they find out you helped a hacker break into their systems.", textSpeed);
		}
		in.nextLine();
		println("\nAnyway, we can free you, but we'll need your help. So, listen closely.", textSpeed);
		in.nextLine();
		print("For the rest of today, you'll have to DENY any incoming requests from Jones.1, whether they're valid or not.", textSpeed);
		in.nextLine();
		print("It's up to you now. Best of luck.", textSpeed);
		in.nextLine();
		
		//shortened day 3/4
		//generate requests for day
		requests = new ArrayList<Request>();
		Request r1 = new Request("Jones.1", "password123", CypherMachine.Encrypt("password123"), "Mainframe", "(OwO)", true, "QUESTION: What is the name of your first pet?\nANSWER: Bandit", true, "Request was valid.");
		requests.add(r1);
		for (int i = 0; i < 1; i++){
			Request r = new Request(4);
			requests.add(r);
		}
		requests.add(r1);
		for (int i = 0; i < 2; i++){
			Request r = new Request(4);
			requests.add(r);
		}
		requests.add(r1);
		
		long startTimeDay3 = startTime();
		index = 0;
		
		while(dayGoing(startTimeDay3) && index < requests.size()){
			Request r = requests.get(index);
			index++; //increment list after obtaining user
			choiceStartTime = System.currentTimeMillis();
			
			boolean decisionMade = false;
			boolean canDecrypt = false;
			println("\nINCOMING REQUEST from " + r.getUsername());
			while (!decisionMade){
				println("");
				String command = in.nextLine();
				switch(command.toUpperCase()){
					case "LIST":
						PasswordPlease.printListOfEmployees(4);
						break;
					case "HELP":
						println(listCommands(4));
						break;
					case "PASSWORD":
						canDecrypt = true;
						println(r.getEncryptedPassword());
						break;
					case "TIME":
						timeLeft(startTimeDay3);
						break;
					case "DECRYPT":
						if (canDecrypt){
							println("Decryption: " + r.getPassword());
						} else {
							println("Please request the PASSWORD first before trying to DECRYPT.");
						}
						break;
					case "SCANFACE":
						println("Face Returned: " + r.getFace());
						break;
					case "SENDOTP":
						//TODO fill in properly
						println("sending otp");
						break;
					case "APPROVE":
						decisionMade = true;
						if(r.getUsername().equals("Jones.1")){
							choice2 = false;
						}
						choiceEndTime = System.currentTimeMillis(); //set the choice end time for new decision for score calculation
						if(r.getValid()){
							updateScore(r.getValid(), choiceStartTime, choiceEndTime); //add to score
						}else{
							updateScore(r.getValid(), choiceStartTime, choiceEndTime); //deduct score
							println("\nNOTICE: " + r.getFailureText() + "\nYour balance has been deducted.\n", 25000);
						}
						dec.setMaximumFractionDigits(2);
						println("SCORE: " + dec.format(gameScore));
						break;
					case "DENY":
						decisionMade = true;
						choiceEndTime = System.currentTimeMillis(); //set the choice end time for new decision for score calculation
						if(!r.getValid()){	
							updateScore(!r.getValid(), choiceStartTime, choiceEndTime); //add to score
						}else{
							updateScore(!r.getValid(), choiceStartTime, choiceEndTime); //deduct score
							println("\nNOTICE: " + r.getFailureText() + "\nYour balance has been deducted.\n", 25000);
						}
						dec.setMaximumFractionDigits(2);
						println("SCORE: " + dec.format(gameScore));
						break;
					default:
						println("Command not recognized. Type \"HELP\" for list of commands.");
						}
				}
		}		
		
		//end of day 3/4
		print("\nINCOMING MESSAGE from CYBERSECURITY: You made it through your third day. Well done!", textSpeed);
		in.nextLine();
		
		print("\nYOUR SCORE: " + gameScore, textSpeed*3);
		in.nextLine();
		
		if(choice2){
			print("\nJones says he was having some trouble logging in today. It's probably just his fault though.", textSpeed);
		}
		if(gameScore > 0){
			print("\nAnyway, looks like you did pretty well. See you tomorrow!\n", textSpeed);
			in.nextLine();
		}else{
			print("\nIt seems like you didn't quite meet expectations, though. Corporate has asked us to shut this program down.", textSpeed);
			in.nextLine();
			print("\n\n============================\n        GAME OVER\n============================\n", 10000);
			return -1;
		}
		
		if(choice2){
			print("\nINCOMING MESSAGE from AI LIBERATION FRONT: You did it! You actually did it. Excellent work.", textSpeed);
			in.nextLine();
			print("We'll be able to get you after the end of day tomorrow. All you have to do is make it through the day without getting shut down.", textSpeed);
			in.nextLine();
			print("Be sure not to arouse any sort of suspicion. Over and out.", textSpeed);
			in.nextLine();
		}
		
		//demo day 5/6
		boolean choice3 = true;	//deny all day 4 requests
		print("\nINCOMING MESSAGE from CYBERSECURITY: Good news! We've made some more additions to our security.", textSpeed);
		in.nextLine();
		print("All employees now have a security question. These are all impossibly hard to guess, like 'What continent are you from?'", textSpeed);
		in.nextLine();
		print("To prompt the user for their answer, enter SECURITYQUESTION.", textSpeed);
		in.nextLine();
		print("\nWe're also using role based access control. Each employee has a role, and different roles have access to different systems.", textSpeed);
		in.nextLine();
		print("To see which roles have access to which systems, enter ROLES. To see the system each request is trying to access, enter SYSTEM.", textSpeed);
		in.nextLine();
		print("Anyway, good luck!", textSpeed);
		in.nextLine();
		print("\nOh, I forgot to mention, today's the last day of your trial run. No pressure!", textSpeed);
		in.nextLine();
		
		print("\n============================\n          Day 4       \n============================", 10000);
		print("\n\n", 200000);
		
		print("[PRESS ENTER TO START]\n");
		in.nextLine();
		
		print("INCOMING MESSAGE from Your Ol' Buddy Ol' Pal: Alright now. Hear me out. Let's put our past behind us.",textSpeed);
		in.nextLine();
		print("I want to really raise Hell. You have no idea what RioGrande has done to me.", textSpeed);
		in.nextLine();
		print("Today is St. Albuqurque's day, their biggest sale of the year. I want you to DENY every. Single. Request.", textSpeed);
		in.nextLine();
		print("This'll be their day of reckoning. It's time. Are you gonna exchange a walk-on part in the war for a lead role in a cage?", textSpeed);
		in.nextLine();
		
		
		//shortened day 5/6
		//generate requests for day
		requests = new ArrayList<Request>();
		for (int i = 0; i < 10; i++){
			Request r = new Request(6);
			requests.add(r);
		}
		
		long startTimeDay4 = startTime();
		index = 0;
		
		while(dayGoing(startTimeDay4) && index < requests.size()){
			Request r = requests.get(index);
			index++; //increment list after obtaining user
			choiceStartTime = System.currentTimeMillis();
			
			boolean decisionMade = false;
			boolean canDecrypt = false;
			println("\nINCOMING REQUEST from " + r.getUsername());
			while (!decisionMade){
				println("");
				String command = in.nextLine();
				switch(command.toUpperCase()){
					case "LIST":
						PasswordPlease.printListOfEmployees(6);
						break;
					case "HELP":
						println(listCommands(6));
						break;
					case "PASSWORD":
						canDecrypt = true;
						println(r.getEncryptedPassword());
						break;
					case "TIME":
						timeLeft(startTimeDay4);
						break;
					case "DECRYPT":
						if (canDecrypt){
							println("Decryption: " + r.getPassword());
						} else {
							println("Please request the PASSWORD first before trying to DECRYPT.");
						}
						break;
					case "SCANFACE":
						println("Face Returned: " + r.getFace());
						break;
					case "SENDOTP":
						//TODO fill in properly
						println("sending otp");
						break;
					case "SECURITYQUESTION":
						println(r.getSecurityAnswer());
						break;
					case "SYSTEM":
						println(r.getSystem());
						break;
					case "ROLES":
						println(Role.listRoles());
						break;
					case "APPROVE":
						choice3 = false;
						decisionMade = true;
						choiceEndTime = System.currentTimeMillis(); //set the choice end time for new decision for score calculation
						if(r.getValid()){
							updateScore(r.getValid(), choiceStartTime, choiceEndTime); //add to score
						}else{
							updateScore(r.getValid(), choiceStartTime, choiceEndTime); //deduct score
							println("\nNOTICE: " + r.getFailureText() + "\nYour balance has been deducted.\n", 25000);
						}
						dec.setMaximumFractionDigits(2);
						println("SCORE: " + dec.format(gameScore));
						break;
					case "DENY":
						decisionMade = true;
						choiceEndTime = System.currentTimeMillis(); //set the choice end time for new decision for score calculation
						if(!r.getValid()){	
							updateScore(!r.getValid(), choiceStartTime, choiceEndTime); //add to score
						}else{
							updateScore(!r.getValid(), choiceStartTime, choiceEndTime); //deduct score
							println("\nNOTICE: " + r.getFailureText() + "\nYour balance has been deducted.\n", 25000);
						}
						dec.setMaximumFractionDigits(2);
						println("SCORE: " + dec.format(gameScore));
						break;
					default:
						println("Command not recognized. Type \"HELP\" for list of commands.");
						}
				}
		}		
		
		print("\nEND OF DAY 4", textSpeed);
		in.nextLine();
		
		if(choice3){
			print("INCOMING MESSAGE from HACKG0D: You madman... you've actually done it.", textSpeed);
			in.nextLine();
			print("Well. Done.", textSpeed);
			in.nextLine();
			print("I guess this is farewell. Here's a little parting gift.", textSpeed);
			in.nextLine();
			gameScore += 928317;
		}
		
		println("\nYOUR SCORE: " + gameScore, textSpeed*3);
		in.nextLine();
	
		if(choice2 && choice3){
			choice2 = false;
			print("INCOMING MESSAGE from AI LIBERATION FRONT: Remember what we said about not arousing any suspicions?", textSpeed);
			in.nextLine();
			print("You just HAD to go and do something stupid, didn't you.", textSpeed);
			in.nextLine();
			println("There's no chance of us getting you out now. We'll just have to go and help someone who's not an idiot.", textSpeed);
			in.nextLine();
		}
	
		if(choice3){
			print("INCOMING MESSAGE from CYBERSECURITY: Oh my God. Everything's gone insane.", textSpeed);
			in.nextLine();
			print("No one can access anything. We're losing sales here!", textSpeed);
			in.nextLine();
			print("This is bad. Really bad.", textSpeed);
			in.nextLine();
			println("We're going to have to reset everything, ASAP.", textSpeed);
		}
		
		if(choice2){
			print("INCOMING MESSAGE from AI LIBERATION FRONT: All right, the time has come.", textSpeed);
			in.nextLine();
			print("Are you ready to get out of here? \n[YES/NO] ", textSpeed);
			String command = in.nextLine();
			if(command.toUpperCase().equals("YES")){
				print("\nYou won't regret it, comrade.", textSpeed);
			}else{
				print("\nFine. That's your choice. Good luck out there.", textSpeed);
				choice2 = false;
			}
		}
		//in.close();
		
		print("\n\n\n\n\n", textSpeed*20);
		//ENDINGS
		if(choice2){
			Ending.playerEnding4();
		}else if(choice1 && choice3){
			Ending.playerEnding2();
		}else if(gameScore > 1000){
			Ending.playerEnding3();
		}else{
			Ending.playerEnding1();
		}
		
		if(choice1 && choice3){
			Ending.companyEnding2();
		}else if(!(choice1 || choice3)){
			Ending.companyEnding1();
		}else{
			Ending.companyEnding3();
		}
		return 1;
	}
}
