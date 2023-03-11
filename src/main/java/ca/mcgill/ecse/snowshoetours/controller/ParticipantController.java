package ca.mcgill.ecse.snowshoetours.controller;

import ca.mcgill.ecse.snowshoetours.application.SnowShoeToursApplication;
import ca.mcgill.ecse.snowshoetours.model.Participant;
import ca.mcgill.ecse.snowshoetours.model.SnowShoeTour;
import ca.mcgill.ecse.snowshoetours.model.User;

public class ParticipantController {

  // Constructing instance of application object
  private static SnowShoeTour sst = SnowShoeToursApplication.getSnowShoeTour();

  public static String registerParticipant(String email, String password, String name,
      String emergencyContact, int nrWeeks, int weekAvailableFrom, int weekAvailableUntil,
      boolean lodgeRequired) {
    // TODO Implement the method, return error message (if any)
    
    // Given that the participant exists
    boolean p = Participant.hasWithAccountName(name);
    
    if (!p) {
      return "Participant doesn't exist.";
    } 

    // Given that the participant is not already registered
    Participant participant = (Participant) User.getWithAccountName(name);
    
    if (sst.getParticipants().contains(participant)) {
      return "Participant is already registered.";
    }

    try {
      sst.addParticipant(participant);
      return "";
    } catch (Exception e) {
      return "Not implemented!";
    }
  }

  public static void deleteParticipant(String email) {
    
  }

  public static String addBookableItemToParticipant(String email, String bookableItemName) {
    // TODO Implement the method, return error message (if any)
    return "Not implemented!";
  }

  public static String removeBookableItemFromParticipant(String email, String bookableItemName) {
    // TODO Implement the method, return error message (if any)
    return "Not implemented!";
  }
}
