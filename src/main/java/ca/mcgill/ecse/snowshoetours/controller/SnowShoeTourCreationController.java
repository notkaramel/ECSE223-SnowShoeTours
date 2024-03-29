package ca.mcgill.ecse.snowshoetours.controller;

import java.util.*;

import ca.mcgill.ecse.snowshoetours.application.SnowShoeToursApplication;
import ca.mcgill.ecse.snowshoetours.persistence.SnowShoeTourPersistence;
import ca.mcgill.ecse.snowshoetours.model.*;

public class SnowShoeTourCreationController {
    private static SnowShoeTour sst = SnowShoeToursApplication.getSnowShoeTour();

    private SnowShoeTourCreationController() {}

  /**
   * This method initiates the creation of snow tours using available guides and participants in the
   * system.
   * 
   * @author Bilar Mokhtari @bmokhtari
   * @return
   */

    /**
     * This method initiates the creation of snow tours using available guides and participants in
     * the system.
     * 
     * @author Bilar Mokhtari @bmokhtari
     * @return
     */

    public static String initiateSnowToursCreation() {
        // Retrieve unassigned guides and participants from the system
        List<Guide> unAssignedGuides = sst.getGuides();
        List<Participant> unAssignedParticipants = sst.getParticipants();
        int id = 1;
        if (unAssignedParticipants.isEmpty()) {
            // Return an error message or handle the situation when no guides are available.
            return "No unassigned participants available.";
        }
        if (unAssignedGuides.isEmpty()) {
            // Return an error message or handle the situation when no guides are available.
            return "No unassigned guides available.";
        }


        // Iterate through all unassigned participants
        for (Participant p : unAssignedParticipants) {
            // Get the participant's number of weeks and availability week
            int numberOfWeeks = p.getNrWeeks();
            int weekAvailableFrom = p.getWeekAvailableFrom();

            // Select a guide for the participant based on the current index
            Guide assignedGuide = unAssignedGuides.get(id % unAssignedGuides.size());

            // Create a new tour with the participant's available week and guide, and add it to the
            // system
            Tour newTour = sst.addTour(id, weekAvailableFrom, weekAvailableFrom + numberOfWeeks - 1,
                    assignedGuide);

            // Assign the new tour to the participant
            // and then add the participant to the new tour
            p.assign(newTour);
            newTour.addParticipant(p);
            id++;
        }

        // Save the updated system state to persistence
        try {
            SnowShoeTourPersistence.save();
        } catch (Exception e) {
            // Return an error message if the system state could not be saved
            return e.getMessage();
        }

        // Return an empty string to indicate that the operation was successful
        return "";
    }

    /**
     * This method let's participants pay for their trip
     * 
     * @author Bilar Mokhtari @bmokhtari, Antoine Phan @notkaramel, Jennifer Tram
     *         Su @jennifertramsu, Emma Friesen @emma-friesen
     * @param email : The AccountName of a participant
     * @param authorizationCode
     * @return
     */
    public static String payForTrip(String email, String authorizationCode) {
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

        Participant participant = getParticipantByEmail(email); // Get participant by email

        // Return error message if participant is null
        if (participant == null) {
            return "Participant with email address " + email + " does not exist";
        }


        if (authorizationCode == null || authorizationCode.isEmpty()) {
            // Return error message if authorization code is missing or empty
            return "Invalid authorization code";
        }

        switch (participant.getStatusFullName()) {
            case "Paid":
                return ("The participant has already paid for their tour");
            case "Started":
                return ("The participant has already paid for their tour");
            case "Cancelled":
                return ("Cannot pay for tour because the participant has cancelled their tour");
            case "Finished":
                return ("The participant has already paid for their tour");
            case "NotAssigned":
                return ("The participant has not been assigned to their tour");
            case "Assigned": {
                participant.setAuthorizationCode(authorizationCode);
                participant.pay(); // Process payment for participant
                break;
            }
        }
        
        try {
            SnowShoeTourPersistence.save(); // Save persistence after payment is processed
        } catch (Exception e) {
            // Return error message if payment processing or persistence saving fails
            return e.getMessage();
        }

        return ""; // Return empty string to indicate success
    }



    /**
     * This method to start a trip for participants for during a specific week
     * 
     * @author Bilar Mokhtari @bmokhtari, Antoine Phan @notkaramel, Jennifer Tram
     *         Su @jennifertramsu, Emma Friesen @emma-friesen
     * @param week
     * @return
     */
    public static String startAllTripsForSpecificWeek(int week) {
        List<Tour> shoeTours = sst.getTours(); // Get all tours in the system

        for (Tour shoeTour : shoeTours) { // Loop through all tours
            // Check if tour is starting in the specified week
            if (shoeTour.getStartWeek() == week) {
                // Loop through all participants in the tour
                for (Participant participant : shoeTour.getParticipants()) {
                    switch (participant.getStatusFullName()) {
                        case "NotAssigned":
                            return "";
                        case "Assigned":
                            return "";
                        case "Started":
                            return ("Cannot start tour because the participant has already started their tour");
                        case "Cancelled":
                            return ("Cannot start tour because the participant has cancelled their tour");
                        case "Finished":
                            return ("Cannot start tour because the participant has finished their tour");
                        case "Paid": {
                            participant.start();
                            return "";
                        }
                    }
                }
            }
        }

        try {
            SnowShoeTourPersistence.save(); // Save the updated system state
        } catch (Exception e) {
            // Return an error message if the system state could not be saved
            return e.getMessage();
        }

        return "No trip started this week"; // Return the accumulated messages
    }



    /**
     * This method to finish a trip for a specific participant Checks if participant exists
     * 
     * @author Bilar Mokhtari @bmokhtari, Antoine Phan @notkaramel, Jennifer Tram
     *         Su @jennifertramsu, Emma Friesen @emma-friesen
     * @param email : The AccountName of a participant
     * @return
     */
    public static String finishParticipantTrip(String email) {
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

        Participant participant = getParticipantByEmail(email);

        if (participant == null) {
            return String.format("Participant with email address %s does not exist", email);
        }

        switch (participant.getStatusFullName()) {
            case "Cancelled":
                return ("Cannot finish tour because the participant has cancelled their tour");
            case "Finished":
                return ("Cannot finish tour because the participant has already finished their tour");
            case "NotAssigned":
                return ("Cannot finish a tour for a participant who has not started their tour");
            case "Assigned":
                return ("Cannot finish a tour for a participant who has not started their tour");
            case "Paid":
                return ("Cannot finish a tour for a participant who has not started their tour");
            case "Started":
                participant.finish();
        }

        try {

            SnowShoeTourPersistence.save(); // Save persistence after the participant's tour is
                                            // finished
        } catch (RuntimeException e) {
            return e.getMessage();
        }

        return "";
    }


    /**
     * This method to cancel a trip for participants Checks whether they exist or not and if they
     * have started their tour yet
     * 
     * @author Bilar Mokhtari @bmokhtari, Antoine Phan @notkaramel, Jennifer Tram
     *         Su @jennifertramsu, Emma Friesen @emma-friesen
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

        switch (participant.getStatusFullName()) {
            case "Started": {
                participant.cancel(); // Cancel the participant's tour
                return "";
            }
            case "NotAssigned": {
                participant.cancel(); // Cancel the participant's tour
                return "";
            }
            case "Assigned": {
                participant.cancel(); // Cancel the participant's tour
                return "";
            }
            case "Paid": {
                participant.cancel(); // Cancel the participant's tour
                return "";
            }
            case "Cancelled":
                return ("Cannot cancel tour because the participant has already cancelled their tour");
            case "Finished":
                return ("Cannot cancel tour because the participant has finished their tour");
        }

        try {
            SnowShoeTourPersistence.save(); // Save persistence after the participant's tour is
                                            // cancelled
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

