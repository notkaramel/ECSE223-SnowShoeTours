package ca.mcgill.ecse.snowshoetours.controller;

import java.util.List;

import ca.mcgill.ecse.snowshoetours.application.SnowShoeToursApplication;
import ca.mcgill.ecse.snowshoetours.model.Guide;
import ca.mcgill.ecse.snowshoetours.model.Participant;
import ca.mcgill.ecse.snowshoetours.model.SnowShoeTour;

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
      for (Participant newParticipant : unAssignedParticipants) {
        //Makes sure that the participant hasn't been assigned yet
        if (newParticipant.getSnowShoeTour() == null) {
          Guide guide = unAssignedGuides.remove(0); // Get the next unassigned guide
          newParticipant.assignGuide(guide);
        }
      }
    } catch (Exception e) {
      return e.getMessage();
    }

    //Check if a participant was not assigned
    for (Participant person : unAssignedParticipants) {
      if (person.getSnowShoeTour() == null)
        return "At least one participant could not be assigned to their snow tour";
    }

    return "";
}

}

