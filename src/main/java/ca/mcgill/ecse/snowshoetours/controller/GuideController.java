package ca.mcgill.ecse.snowshoetours.controller;

import ca.mcgill.ecse.snowshoetours.application.SnowShoeToursApplication;
import ca.mcgill.ecse.snowshoetours.model.Guide;
import ca.mcgill.ecse.snowshoetours.model.Participant;
import ca.mcgill.ecse.snowshoetours.model.SnowShoeTour;
import ca.mcgill.ecse.snowshoetours.model.User;

public class GuideController {
	private static SnowShoeTour sst = SnowShoeToursApplication.getSnowShoeTour();

	/**
	 * @author: Sameer Riaz (@SRIAZ77)
	 */
	public static String registerGuide(String email, String password, String name, String emergencyContact) {

		// EMAIL VALIDATION
		// email must not contain any spaces
		if (email.contains(" ")) {
			return "Email must not contain any spaces";
		}
		if (email == "" || email == null) {
			return "Email cannot be empty";
		}

		// email must contain some characters (anything is allowed except @), a @, some
		// characters, a dot, and some characters
		// this is a simplified check sufficient for this application
		Boolean condition1 = email.indexOf("@") > 0; // index starts at zero
		Boolean condition2 = email.indexOf("@") == email.lastIndexOf("@");
		Boolean condition3 = email.indexOf("@") < email.lastIndexOf(".") - 1;
		Boolean condition4 = email.lastIndexOf(".") < email.length() - 1;

		if (!(condition1 && condition2 && condition3 && condition4)) {
			return "Invalid email";
		}
		if (name == "" || name == null) {
			return "Name cannot be empty";
		}
		if (emergencyContact == "" || emergencyContact == null) {
			return "Emergency contact cannot be empty";
		}
		if (password == "" || password == null) {
			return "Password cannot be empty";
		}
		if (email == "manager") {
			return "Invalid email";
		}

		if (User.hasWithAccountName(email)) {
			if (User.getWithAccountName(email) instanceof Participant) {
				return "Email already linked to a participant account";
			}
			if (User.getWithAccountName(email) instanceof Guide) {
				return "Email already linked to a guide account";
			} else { // IF USER IS MANAGER
				return "Invalid email";

			}

		}

		else {
			try {
				sst.addGuide(email, password, name, emergencyContact);
				return "";
			} catch (Exception e) {
				return "Error: something went wrong";
			}
		}

	}

	/**
	 * @author: Sameer Riaz (@SRIAZ77)
	 */
	public static void deleteGuide(String email) {
		if (User.hasWithAccountName(email)) {
			if (Guide.getWithAccountName(email) instanceof Guide) { // CHECK IF INPUTTED USER IS A GUIDE TO NOT REMOVE
																	// PARTICIPANT
				try {
					Guide.getWithAccountName(email).delete();
				} catch (Exception e) {
				}
			}
		}
		if (email == "manager") {

			// UNCLEAR WHAT TO DO HERE; THE MANAGER SHOULD BE CREATED IN
			// SnowShoeToursApplication.java

		}
	}
}
