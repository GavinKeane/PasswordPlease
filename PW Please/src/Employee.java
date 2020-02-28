
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
	private String face;
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
		this.face = face;
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
		this.password = "default";
		this.encryptedPassword = "default"; //TODO update this line once encryption is implemented
		this.face = "default";
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
	
	public String getFace(){
		return this.face;
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
	
	public void setFace(String updatedVal){
		this.face = updatedVal;
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
