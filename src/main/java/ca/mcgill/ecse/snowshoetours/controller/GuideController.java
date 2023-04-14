package ca.mcgill.ecse.snowshoetours.controller;

import ca.mcgill.ecse.snowshoetours.application.SnowShoeToursApplication;
import ca.mcgill.ecse.snowshoetours.model.Guide;
import ca.mcgill.ecse.snowshoetours.model.Participant;
import ca.mcgill.ecse.snowshoetours.model.SnowShoeTour;
import ca.mcgill.ecse.snowshoetours.model.User;
import ca.mcgill.ecse.snowshoetours.persistence.SnowShoeTourPersistence;

public class GuideController {
	private static SnowShoeTour sst = SnowShoeToursApplication.getSnowShoeTour();

	/**
	 * @author: Sameer Riaz (@SRIAZ77), Jennifer Tram Su (@jennifertramsu)
	 */
	public static String registerGuide(String email, String password, String name,
			String emergencyContact) {

		// Input validation
		if (email == null || email.equals("")) {
			return "Email cannot be empty";
		} else if (email.contains(" ")) {
			return "Email must not contain any spaces";
		} else if (!(email.indexOf("@") > 0)) {
			return "Invalid email";
		} else if (email.indexOf("@") != email.lastIndexOf("@")) {
			return "Invalid email";
		} else if (!(email.indexOf("@") < email.lastIndexOf(".") - 1)) {
			return "Invalid email";
		} else if (!(email.lastIndexOf(".") < email.length() - 1)) {
			return "Invalid email";
		}

		// Cannot be a participant
		for (Participant participant : sst.getParticipants()) {
			if (participant.getAccountName().equals(email)) {
				return "Email already linked to a participant account";
			}
		}

		if (password == null || password.equals("")) {
			return "Password cannot be empty";
		}

		if (name == null || name.equals("")) {
			return "Name cannot be empty";
		}

		if (emergencyContact == null || emergencyContact.equals("")) {
			return "Emergency contact cannot be empty";
		}

		// Check that the participant is not already registered
		Guide guide = (Guide) User.getWithAccountName(email);

		if (guide != null) {
			return "Email already linked to a guide account";
		}

		try {
			sst.addGuide(email, password, name, emergencyContact);
			SnowShoeTourPersistence.save();
			return "";
		} catch (Exception e) {
			return "Error: something went wrong";
		}	
	}

	/**
	 * @author: Sameer Riaz (@SRIAZ77)
	 */
	public static void deleteGuide(String email) {
		if (User.hasWithAccountName(email)) {
			if (Guide.getWithAccountName(email) instanceof Guide) { // CHECK IF INPUTTED USER IS A
																	// GUIDE TO NOT REMOVE
																	// PARTICIPANT
				try {
					Guide.getWithAccountName(email).delete();
					SnowShoeTourPersistence.save();
				} catch (Exception e) {
				}
			}
		}
	}
}
