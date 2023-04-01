package ca.mcgill.ecse.snowshoetours.controller;

import java.util.*;

import ca.mcgill.ecse.snowshoetours.application.SnowShoeToursApplication;
import ca.mcgill.ecse.snowshoetours.model.*;

public class SnowShoeTourCreationController {
  private static SnowShoeTour sst = SnowShoeToursApplication.getSnowShoeTour();
    private  SnowShoeTourCreationController(){
  }


/**
	 * This method initiates the creation of snow tours using available guides and participants in the system.
	 * 
	 * @author Bilar Mokhtari @bmokhtari
	 * @return
	 */
  
  public static String initiateSnowToursCreation() {
    List<Guide> unAssignedGuides = sst.getGuides();
    List<Participant> unAssignedParticipants = sst.getParticipants();

    // Check if there are enough guides to lead all snow tours
    if (unAssignedGuides.size() < unAssignedParticipants.size())
        return "There are not enough guides to lead all the snow tours.";

    if (unAssignedGuides.isEmpty())
      return "A guide must exist.";
    if (unAssignedParticipants.isEmpty())
      return "A participant must exist.";
    
    try {
      for (Participant participant : unAssignedParticipants) {
        //Makes sure that the participant hasn't been assigned yet
        if (participant.getSnowShoeTour() == null) {
          Guide guide = unAssignedGuides.remove(0); // Get the next unassigned guide
          participant.assignGuide(guide);
          if (unAssignedGuides.isEmpty()) // Exit loop if all guides have been assigned
            break;
        }
      }
    } catch (Exception e) {
      return e.getMessage();
    }
      
    SnowShoeTourPersistence.save(); // Save the persistence after all tours have been assigned
      
    return "";
      
}

/**
	 * This method let's participants pay for their trip
	 * 
	 * @author Bilar Mokhtari @bmokhtari
   * @param email : The AccountName of a participant
   * @param authorizationCode
	 * @return
	 */
  public static String payForTrip(String email, String authorizationCode) {
    Participant participant = getParticipantByEmail(email); // Get participant by email

    if (participant == null) {
        return String.format("Participant with email address %s does not exist", email); // Return error message if participant is null
    }

    if (authorizationCode == null || authorizationCode.isEmpty()) {
        return "Invalid authorization code"; // Return error message if authorization code is missing or empty
    }

    try {
        participant.pay(); // Process payment for participant
        SnowShoeTourPersistence.save(); // Save persistence after payment is processed
    } catch (Exception e) {
        return e.getMessage(); // Return error message if payment processing or persistence saving fails
    }

    return ""; // Return empty string to indicate success
}

  

/**
	 * This method to start a trip for participants for during a specific week
	 * 
	 * @author Bilar Mokhtari @bmokhtari
   * @param week
	 * @return
	 */
  public static String startAllTripsForSpecificWeek(int week) {
    List<Tour> shoeTours = sst.getTours(); // Get all tours in the system
    boolean tripStarted = false; // Flag to keep track of whether any trips were started

    for (Tour shoeTour : shoeTours) { // Loop through all tours
        if (shoeTour.getStartWeek() == week) { // Check if tour is starting in the specified week
            for (Participant participant : shoeTour.getParticipants()) { // Loop through all participants in the tour
                if (!participant.hasTour()) { // Check if the participant has not already started the tour
                    participant.startTour(); // Start the tour for the participant
                    tripStarted = true; // Set flag to indicate that a trip was started
                }
            }
        }
    }

    if (tripStarted) { // Check if any trips were started
        try {
            SnowShoeTourPersistence.save(); // Save the updated system state
        } catch (Exception e) {
            return e.getMessage(); // Return an error message if the system state could not be saved
        }
        return ""; // Return an empty string to indicate that the trips were started successfully
    } else {
        return String.format("No trips were started for week %s", week); // Return a message indicating that no trips were started
    }
}



/**
	 * This method to finish a trip for a specific participant
   * Checks if participant exists
	 * 
	 * @author Bilar Mokhtari @bmokhtari
   * @param email : The AccountName of a participant
	 * @return
	 */
  public static String finishParticipantTrip(String email) {
    Participant participant = getParticipantByEmail(email);
  
    if (participant == null) {
        return String.format("Participant with email address %s does not exist", email);
    }
  
    try {
        participant.finish();
        SnowShoeTourPersistence.save(); // Save persistence after the participant's tour is finished
    } catch (RuntimeException e) {
        return e.getMessage();
    }
  
    return "";
  }
  

/**
	 * This method to cancel a trip for participants
   * Checks whether they exist or not and if they have started their tour yet
	 * 
	 * @author Bilar Mokhtari @bmokhtari
   * @param week
	 * @return
	 */
  public static String cancelParticipantTrip(String email) {
    // Get the participant by email
    Participant participant = getParticipantByEmail(email);
  
    if (participant == null) {
        // Use string interpolation for the error message
        return String.format("Participant with email address %s does not exist", email);
    }
  
    if (!participant.hasTour()) {
        return "Participant has not started their tour";
    }
  
    participant.cancel(); // Cancel the participant's tour
  
    try {
        SnowShoeTourPersistence.save(); // Save persistence after the participant's tour is cancelled
    } catch (Exception e) {
        return e.getMessage(); // Return the error message from the exception
    }
  
    return ""; // Return an empty string to indicate success
  }
  



/**
	 * @author Bilar Mokhtari (@bmokhtari) Helper function
	 * @param email : The AccountName of a participant
	 * @return
	 */

public static Participant getParticipantByEmail(String email) {
  List<Participant> participants = sst.getParticipants();
  
  for (Participant participant : participants) {
      if (participant.getAccountName().equals(email)) {
          return participant;
      }
  }
  
  // Participant not found with matching email
  return null;
}



}

