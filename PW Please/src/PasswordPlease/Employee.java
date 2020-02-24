/*
	Class used to represent employees in the company
	
	2/24/2020 Caleb Fischer - intital creation
	2/24/2020 Caleb Fischer - started constructors and toString
	
*/
public class Employee {
	
	//Employee variables
	String username;
	String role;
	String password;
	String encryptedPassword;
	String face;
	String phone;
	String securityQuestion;
	String securityAnswer;
	
	/*
		Constructor
		Does not take encrypted password as argument - encrypted password is calculated using plaintext password and encryption algorithm
		Not all variables are used each day, however, all variables are required on creation
	*/
	public Employee(String username, String role, String password, String face, String phone, String securityQuestion, String securityAnswer){
		this.username = username;
		this.role = role;
		this.password = password;
		this.encryptedPassword = password; //TODO update this line once encryption is implemented
		this.face = face;
		this.phone = phone;
		this.securityQuestion = securityQuestion;
		this.securityAnswer = securityAnswer;
	}
	
	/*
		No-args constructor
		Generates random employee
	*/
	public Employee(){
		//TODO random user generation
		this.username = "default";
		this.role = "default";
		this.password = "default";
		this.encryptedPassword = "default"; //TODO update this line once encryption is implemented
		this.face = "default";
		this.phone = "default";
		this.securityQuestion = "default";
		this.securityAnswer = "default";
	}
	
	/*
		toString method
		takes day number as parameter, prints information relevant to provided day
	*/
	public String toString(int day){
		//TODO update to print only day-relevant info
		return this.username + " | " + this.role + " | " + this.password;
	}
}
