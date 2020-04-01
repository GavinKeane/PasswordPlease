/*
	Helper class for working with roles and systems
*/ 

import java.util.*;

public class Role{

	//list of roles
	public static String[] roles = {"IT", "Finance", "Sales"};

	//arrays of systems various roles can access
	public static String[] generalSystems = {"Email", "Directory", "Corporate Network"};
	public static String[] itSystems = {"Email", "Directory", "Corporate Network", "Mainframe", "Unix"};
	public static String[] financeSystems = {"Email", "Directory", "Corporate Network", "SAP", "Salesforce"};
	public static String[] salesSystems = {"Email", "Directory", "Corporate Network", "Customer Database", "Salesforce"};
	
	//return a random role for employee creation
	public static String getRandomRole(){
		int rnd = new Random().nextInt(roles.length);
		return roles[rnd];
	}
	
	//print list of roles and systems they can access
	public static String listRoles(){
		String s = "ALL EMPLOYEES: Email, Directory, Corporate Network \nIT:            Mainframe, Unix \nFinance:       SAP, Salesforce \nSales:         Customer Database, Salesforce";
		return s;
	}
	
	//get a valid system for a certain role: used for request generation
	public static String getValidSystem(String role){
		String result = "";
		String[] a;
		
		switch(role.toUpperCase()){
			case "IT":
				a = itSystems.clone();
				break;
			case "FINANCE":
				a = financeSystems.clone();
				break;
			case "SALES":
				a = salesSystems.clone();
				break;
			default:
				a = new String[] {};
		}
		
		int rnd = new Random().nextInt(a.length);
		result = a[rnd];
		
		return result;
	}
	
	//get an invalid system for a certain role: used for request generation
	public static String getInvalidSystem(String role){
		String result = "";
		String[] a;
		
		switch(role.toUpperCase()){
			case "IT":
				a = new String[] {"SAP", "Salesforce", "Customer Database"};
				break;
			case "FINANCE":
				a = new String[] {"Mainframe", "Unix", "Customer Database"};
				break;
			case "SALES":
				a = new String[] {"Mainframe", "Unix", "SAP"};
				break;
			default:
				a = new String[] {};
		}
		
		int rnd = new Random().nextInt(a.length);
		result = a[rnd];
		
		return result;
	}
}
