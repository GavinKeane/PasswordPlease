/*
	Class used to represent incoming access requests

	2/25 Caleb Fischer - Created class
	
*/
public class Request {
	
	//Request variables
	private String username;
	private String password;
	private String encryptedPassword;
	private String system; //system user is trying to access
	private String face;
	private String token;
	private String securityAnswer;
	private boolean valid;
	private String failureText; //text to show if player chooses incorrectly
	
	/*
		Constructors
	*/
	public Request(String username, String password, String encryptedPassword, String system, String face, String token, String securityAnswer, boolean valid, String failureText){
		this.username = username;
		this.password = password;
		this.encryptedPassword = encryptedPassword;
		this.system = system;
		this.face = face;
		this.token = token;
		this.securityAnswer = securityAnswer;
		this.valid = valid;
		this.failureText = failureText;
	}
	
	/*
		No-args constructor
		Generates random request with information based on what day it is
	*/
	public Request(int day){
		//TODO random request generation
		this.username = "default";
		this.password = "default";
		this.encryptedPassword = "default";
		this.system = "default";
		this.face = "default";
		this.token = "default";
		this.securityAnswer = "default";
		this.valid = true;
		this.failureText = "default";
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
	
	public String getToken(){
		return this.token;
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
	
	public void setToken(String updatedVal){
		this.token = updatedVal;
	}
	
	public void setSecurityAndwer(String updatedVal){
		this.securityAnswer = updatedVal;
	}
	
	public void setValid(boolean updatedVal){
		this.valid = updatedVal;
	}
	
	public void setFailureText(String updatedVal){
		this.failureText = updatedVal;
	}
}
