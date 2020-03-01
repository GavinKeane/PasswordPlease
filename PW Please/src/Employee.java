
/*
	Class used to represent employees in the company

	2/24 Caleb Fischer - intital creation
	2/24 Caleb Fischer - started constructors and toString
	2/25 Caleb Fischer - made Employee private, added getters and setters
										 - added employee list
  2/28 Caleb Fischer - name and username generation

*/
import java.util.ArrayList;
import java.util.Random;

public class Employee {

	private static Face faceCollection = new Face();

	//list of employees
	//access outside class using Employee.employees
	public static ArrayList<Employee> employees = new ArrayList<Employee>();

	//arrays of common firstnames and lastnames for name genration
	public static String[] firstnames = {"James", "John", "Robert", "Michael", "William", "David", "Richard", "Joseph", "Thomas", "Charles", "Christopher", "Daniel", "Matthew", "Anthony", "Donald", "Mark", "Paul", "Steven", "Andrew", "Kenneth", "Joshua", "George", "Kevin", "Brian", "Edward", "Mary", "Patricia", "Jennifer", "Linda", "Elizabeth", "Barbara", "Susan", "Jessica", "Sarah", "Karen", "Nancy", "Margaret", "Lisa", "Betty", "Dorothy", "Sandra", "Ashley", "Kimberly", "Donna", "Emily", "Michelle", "Carol", "Amanda", "Melissa", "Deborah"};

	public static String[] lastnames = {"Smith", "Johnson", "Wiliams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor", "Anderson", "Thomas", "Jackson", "White", "Harris", "Martin", "Thompson", "Garcia", "Martinez", "Robinson", "Clark", "Rodriguez", "Lewis", "Lee", "Walker"};

	//Employee variables
	private String lastname;
	private String firstname;
	private String username;
	private String role;
	private String password;
	private String encryptedPassword;

	//faceSet[0] is the correct Face associated with this employee
	//faceSet[1] is the face with incorrect eyes
	//faceSet[2] is the incorrect face entirely
	private String[] faceSet;


	private String phone;
	private String securityQuestion;
	private String securityAnswer;

	/*
		Constructor
		Does not take encrypted password as argument - encrypted password is calculated using plaintext password and encryption algorithm
		Not all variables are used each day, however, all variables are required on creation
	*/
	public Employee(String lastname, String firstname, String role, String password, String face, String phone, String securityQuestion, String securityAnswer){
		this.lastname = lastname;
		this.firstname = firstname;
		this.role = role;
		this.password = password;
		this.encryptedPassword = password; //TODO update this line once encryption is implemented
		this.faceSet = faceCollection.removeRemoveRandomFaceFromCollection();
		this.phone = phone;
		this.securityQuestion = securityQuestion;
		this.securityAnswer = securityAnswer;

		//set username to lastname.# based on how many employees have their last name
		int lastnameCount = 1;
		for (Employee e: employees){
			if(e.lastname.equals(this.lastname)){
				lastnameCount++;
			}
		}
		this.username = lastname + "." + lastnameCount;

		//add employee to employee list
		employees.add(this);
	}

	/*
		No-args constructor
		Generates random employee
	*/
	public Employee(){
		//TODO random user generation

		//randomly pick first and last name
		this.lastname = lastnames[new Random().nextInt(lastnames.length)];
		this.firstname = firstnames[new Random().nextInt(firstnames.length)];

		this.role = "default";
		this.password = generatePassword();
		this.encryptedPassword = "default"; //TODO update this line once encryption is implemented
		this.faceSet = faceCollection.removeRemoveRandomFaceFromCollection();
		this.phone = "default";
		this.securityQuestion = "default";
		this.securityAnswer = "default";

		//set username to lastname.# based on how many existing employees have their last name
		int lastnameCount = 1;
		for (Employee e: employees){
			if(e.lastname.equals(this.lastname)){
				lastnameCount++;
			}
		}
		this.username = lastname + "." + lastnameCount;

		//add employee to employee list
		employees.add(this);
	}

	/*
		toString method
		takes day number as parameter, prints information relevant to provided day
	*/
	public String toString(int day){
		//TODO update to print only day-relevant info
		return this.username + " | " + this.role + " | " + this.password;
	}

	/*
		No-args toString
	*/
	public String toString(){
		return "\n" + this.firstname + " " + this.username + " | " + this.password;
	}

	/*
		Generate a password consisting of word + random numbers + special character
	*/
	public static String generatePassword(){
		ArrayList<String> pool1 = new ArrayList<String>();
		pool1.add("satisfaction");
		pool1.add("desk");
		pool1.add("clothes");
		pool1.add("charity");
		pool1.add("drama");
		pool1.add("finding");
		pool1.add("consequence");
		pool1.add("inspector");
		pool1.add("performance");
		pool1.add("population");
		pool1.add("restaurant");
		pool1.add("person");
		pool1.add("mode");
		pool1.add("collection");
		pool1.add("patience");
		pool1.add("chemistry");
		pool1.add("office");
		pool1.add("teaching");
		pool1.add("application");
		pool1.add("possibility");
		ArrayList<String> pool2 = new ArrayList<String>();
		pool2.add("advice");
		pool2.add("theory");
		pool2.add("interaction");
		pool2.add("signature");
		pool2.add("shirt");
		pool2.add("possibility");
		pool2.add("art");
		pool2.add("uncle");
		pool2.add("apartment");
		pool2.add("examination");
		pool2.add("response");
		pool2.add("obligation");
		pool2.add("celebration");
		pool2.add("baseball");
		pool2.add("classroom");
		pool2.add("player");
		pool2.add("marketing");
		pool2.add("inspector");
		pool2.add("love");
		pool2.add("city");
		ArrayList<String> pool3 = new ArrayList<String>();
		pool3.add("0");
		pool3.add("1");
		pool3.add("2");
		pool3.add("3");
		pool3.add("4");
		pool3.add("5");
		pool3.add("6");
		pool3.add("7");
		pool3.add("8");
		pool3.add("9");
		ArrayList<String> pool4 = new ArrayList<String>();
		pool4.add("!");
		pool4.add("@");
		pool4.add("#");
		pool4.add("$");
		pool4.add("%");
		pool4.add("^");
		pool4.add("&");
		pool4.add("*");
		pool4.add("(");
		pool4.add(")");
		pool4.add("_");
		pool4.add("-");
		pool4.add("=");
		pool4.add("+");
		pool4.add("[");
		pool4.add("]");
		pool4.add("?");
		pool4.add(">");
		pool4.add("<");
		pool4.add(";");
		pool4.add(":");
		pool4.add("{");
		pool4.add("}");
		Random rand = new Random();
		int ran1 = rand.nextInt(20);
		int ran2 = rand.nextInt(20);
		int ran3 = rand.nextInt(10);
		int ran4 = rand.nextInt(22);
		String password = pool1.get(ran1) + pool2.get(ran2) + pool3.get(ran3) + pool4.get(ran4);
		return password;
	}

	/*
		Getter methods
	*/
	public String getLastname(){
		return this.lastname;
	}

	public String getFirstname(){
		return this.firstname;
	}

	public String getUsername(){
		return this.username;
	}

	public String getRole(){
		return this.role;
	}

	public String getPassword(){
		return this.password;
	}

	public String getEncryptedPassword(){
		return this.encryptedPassword;
	}

	public String getFace(int option){
		//faceSet[0] is the correct Face associated with this employee
		//faceSet[1] is the face with incorrect eyes
		//faceSet[2] is the incorrect face entirely
		return this.faceSet[option];
	}

	public String getPhone(){
		return this.phone;
	}

	public String getSecurityQuestion(){
		return this.securityQuestion;
	}

	public String getSecurityAnswer(){
		return this.securityAnswer;
	}

	/*
		Setter methods
	*/
	public void setLastname(String updatedVal){
		this.lastname = updatedVal;
	}

	public void setFirstname(String updatedVal){
		this.firstname = updatedVal;
	}

	public void setUsername(String updatedVal){
		this.username = updatedVal;
	}

	public void setRole(String updatedVal){
		this.role = updatedVal;
	}

	public void setPassword(String updatedVal){
		this.password = updatedVal;
	}

	public void setEncryptedPassword(String updatedVal){
		this.encryptedPassword = updatedVal;
	}

	public void setFace(String[] updatedVal){
		this.faceSet = updatedVal;
	}

	public void setPhone(String updatedVal){
		this.phone = updatedVal;
	}

	public void setSecurityQuestion(String updatedVal){
		this.securityQuestion = updatedVal;
	}

	public void setSecurityAnswer(String updatedVal){
		this.securityAnswer = updatedVal;
	}
}
