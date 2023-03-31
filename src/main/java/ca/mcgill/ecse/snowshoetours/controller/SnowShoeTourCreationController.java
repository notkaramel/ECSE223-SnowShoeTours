package ca.mcgill.ecse.snowshoetours.controller;

import java.util.*;

import ca.mcgill.ecse.snowshoetours.application.SnowShoeToursApplication;
import ca.mcgill.ecse.snowshoetours.model.*;

public class SnowShoeTourCreationController {
  private static SnowShoeTour sst = SnowShoeToursApplication.getSnowShoeTour();
    private  SnowShoeTourCreationController(){
  }
  public static String initiateSnowToursCreation() {
    List<Guide> unAssignedGuides = sst.getGuides();
    List<Participant> unAssignedParticipants = sst.getParticipants();

    // Check if there are enough guides to lead all snow tours
    if (unAssignedGuides.size() < unAssignedParticipants.size())
        return "There are not enough guides to lead all snow tours.";

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

    return "";
}

public static String payForTrip(String email, String authorizationCode) {
  Participant participant = getAccountList().getA; // [NOT IMPLEMENTED]

  if (participant == null) {
      return String.format("Participant with email address %s does not exist", email);
  }

  if (authorizationCode == null || authorizationCode.isEmpty()) {
      return "Authorization code is missing or empty"; // Return an error message if the authorization code is missing or empty
  }

  try {
      participant.pay();
  } catch (Exception e) {
      return e.getMessage();
  }

  return "";
}

public static String startAllTripsForSpecificWeek(int week) {
  List<Tour> shoeTours = sst.getTours(); // Get all tours

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
      return ""; // Return an empty string to indicate that the trips were started successfully
  } else {
      return "No trips were started for week " + week; // Return a message indicating that no trips were started
    }
}

public static String finishParticipantTrip(String email) {
  Participant participant = sst.getParticipantByEmail(email); // Get the participant by email [NOT IMPLEMENTED]

  if (participant == null) {
      return String.format("Participant with email address %s does not exist", email); // Use string interpolation for the error message 
  }

  try {
      participant.finish(); // Finish the participant's tour
  } catch (RuntimeException e) {
      return e.getMessage(); // Return the error message from the exception
  }

  return ""; // Return an empty string to indicate success
}

public static String cancelParticipantTrip(String name) {
  Participant participant = participant.getWithAccountName(email); // Get the participant by accountName

  if (participant == null) {
      return String.format("Participant with Account Name address %s does not exist", name); // Use string interpolation for the error message
  }

  if (!participant.hasTour()) {
      return "Participant has not started their tour"; // Return an error message if the participant has not started their tour
  }

  participant.cancel(); // Cancel the participant's tour
  return ""; // Return an empty string to indicate success
}





public static List<Participant> getAccountList() {
  List<User> usersOfSystem = new ArrayList<>(sst.getParticipants().size() + sst.getGuides().size()); // Initialize the list with an initial capacity

  for (Participant participant : sst.getParticipants()) {
      usersOfSystem.add(new Pa(
              participant.getAccountName(),
              participant.getName(),
              "Participant",
              participant.getStatusFullName(),
              participant.getWeekAvailableFrom(),
              participant.getWeekAvailableUntil(),
              participant.getNrWeeks(),
              participant.isLodgeRequired()
      ));
  }

  for (Guide guide : sst.getGuides()) {
      usersOfSystem.add(new User(
              guide.getAccountName(),
              guide.getName(),
              "Guide"
      ));
  }

  return getAccountList();
}



}

