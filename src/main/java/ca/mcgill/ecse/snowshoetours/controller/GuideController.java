package ca.mcgill.ecse.snowshoetours.controller;

import ca.mcgill.ecse.snowshoetours.application.SnowShoeToursApplication;
import ca.mcgill.ecse.snowshoetours.model.Guide;
import ca.mcgill.ecse.snowshoetours.model.SnowShoeTour;
import ca.mcgill.ecse.snowshoetours.model.User;

public class GuideController {
	private static SnowShoeTour sst = SnowShoeToursApplication.getSnowShoeTour();
  public static String registerGuide(String email, String password, String name,
      String emergencyContact) {
    // TODO Implement the method, return error message (if any)
	  
	 // EMAIL VALIDATION
	   // email must not contain any spaces
	   if (email.contains(" ")) {
		   return "Error: email can not contain spaces.";
	   }

	   // email must contain some characters (anything is allowed except @), a @, some characters, a dot, and some characters
	   // this is a simplified check sufficient for this application
	   Boolean condition1 = email.indexOf("@") > 0; // index starts at zero
	   Boolean condition2 = email.indexOf("@") == email.lastIndexOf("@");
	   Boolean condition3 = email.indexOf("@") < email.lastIndexOf(".") - 1;
	   Boolean condition4 = email.lastIndexOf(".") < email.length() - 1;
	   
	   if (!(condition1 && condition2 && condition3 && condition4)) {
		   return "Error: Invalid email address";
	   }
	   if (name == "" || name == null) {
		   return "Error: name can not be null";
	   }
	   if (emergencyContact == "" || emergencyContact == null) {
		   return "Error: emergency contact can not be null";
	   }
	   if (password == "" || password == null) {
		   return "Error: password can not be null";
	   }
	   if (email == "manager" || Guide.hasWithAccountName(email)) {
		   return "Error: This account name is not allowed";
	   }
	   else {
		   try {
			   sst.addGuide(name, password, name, emergencyContact);
			   return "";
		   }
		   catch (Exception e) {
			   return "Error: something went wrong";
		   }
	   }
	  
  }

  public static void deleteGuide(String email) {
	  if (Guide.hasWithAccountName(email)) {
		  try { User.getWithAccountName(email).delete();
	  }
		  catch (Exception e) {
		  }
		  }
	  }
  }
