/*
	Class used to represent incoming access requests

	2/25 Caleb Fischer - Created class
	
*/
import java.util.*;
public class Request {
	
	//Request variables
	private String username;
	private String password;
	private String encryptedPassword;
	private String system; //system user is trying to access
	private String face;
	private boolean hasFactor;
	private String securityAnswer;
	private boolean valid;
	private String failureText; //text to show if player chooses incorrectly
	
	/*
		Constructors
	*/
	public Request(String username, String password, String encryptedPassword, String system, String face, boolean hasFactor, String securityAnswer, boolean valid, String failureText){
		this.username = username;
		this.password = password;
		this.encryptedPassword = encryptedPassword;
		this.system = system;
		this.face = face;
		this.hasFactor = hasFactor;
		this.securityAnswer = securityAnswer;
		this.valid = valid;
		this.failureText = failureText;
	}
	
	/*
		No-args constructor
		Generates random request with information based on what day it is
	*/
	public Request(int day){	
		//randomly decide whether request will be valid or not, 50/50 chance
		int rand = new Random().nextInt(20);
		boolean valid = true;
		if (rand < 10){
			valid = false;
		}
		
		//pick random Employee
		Employee e = Employee.employees.get(new Random().nextInt(Employee.employees.size()));
		this.username = e.getUsername();
		this.failureText = "The request was valid.";
		
		switch(day){
			//TODO invalid request generation for each day
			case 1:
				if (valid){
					this.password = e.getPassword();
				}else{
					this.password = Employee.generatePassword();
					this.failureText = "Password was incorrect.";
				}
			case 2:
				if(valid){
					this.password = e.getEncryptedPassword();
				} else {
					this.password = Employee.generatePassword();
					this.password = CypherMachine.Encrypt(this.password);
					this.failureText = "Password was incorrect.";
				}
			break;			
		}
		
		this.encryptedPassword = "default";
		this.system = "default";
		this.face = "default";
		this.hasFactor = true;
		this.securityAnswer = "default";
		this.valid = valid;
	}
	
	public String toString(){
		return this.username + " | " + this.password + " | " + this.valid;
	}
	
	/*
		Getter methods
	*/
	public String getUsername(){
		return this.username;
	}
	
	public String getPassword(){
		return this.password;
	}
	
	public String getEncryptedPassword(){
		return this.encryptedPassword;
	}
	
	public String getSystem(){
		return this.system;
	}
	
	public String getFace(){
		return this.face;
	}
	
	public boolean getHasFactor(){
		return this.hasFactor;
	}
	
	public String getSecurityAnswer(){
		return this.securityAnswer;
	}
	
	public boolean getValid(){
		return this.valid;
	}
	
	public String getFailureText(){
		return this.failureText;
	}
	
	/*
		Setter methods
	*/
	public void setUsername(String updatedVal){
		this.username = updatedVal;
	}
	
	public void setPassword(String updatedVal){
		this.password = updatedVal;
	}
	
	public void setEncryptedPassword(String updatedVal){
		this.encryptedPassword = updatedVal;
	}
	
	public void setSystem(String updatedVal){
		this.system = updatedVal;
	}
	
	public void setFace(String updatedVal){
		this.face = updatedVal;
	}
	
	public void setHasFactor(boolean updatedVal){
		this.hasFactor = updatedVal;
	}
	
	public void setSecurityAnswer(String updatedVal){
		this.securityAnswer = updatedVal;
	}
	
	public void setValid(boolean updatedVal){
		this.valid = updatedVal;
	}
	
	public void setFailureText(String updatedVal){
		this.failureText = updatedVal;
	}
}
