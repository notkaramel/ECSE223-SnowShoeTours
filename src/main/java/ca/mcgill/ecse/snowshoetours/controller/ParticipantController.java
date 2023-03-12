package ca.mcgill.ecse.snowshoetours.controller;

import java.util.List;

import ca.mcgill.ecse.snowshoetours.application.SnowShoeToursApplication;
import ca.mcgill.ecse.snowshoetours.model.BookableItem;
import ca.mcgill.ecse.snowshoetours.model.BookedItem;
import ca.mcgill.ecse.snowshoetours.model.Combo;
import ca.mcgill.ecse.snowshoetours.model.Gear;
import ca.mcgill.ecse.snowshoetours.model.Participant;
import ca.mcgill.ecse.snowshoetours.model.SnowShoeTour;
import ca.mcgill.ecse.snowshoetours.model.User;

public class ParticipantController {

  // Constructing instance of application object
  private static SnowShoeTour sst = SnowShoeToursApplication.getSnowShoeTour();

  public static String registerParticipant(String email, String password, String name,
      String emergencyContact, int nrWeeks, int weekAvailableFrom, int weekAvailableUntil,
      boolean lodgeRequired) {
    
    // Input validation
    if (email == null || email.equals("")) {
      return "Empty email.";
    } else if (email.contains(" ")) {
      return "Email must not contain any spaces.";
    } else if (!(email.indexOf("@") > 0 || (email.indexOf("@") == email.lastIndexOf("@"))) || (email.indexOf("@") < email.lastIndexOf(".") - 1)) || (email.lastIndexOf(".") < email.length() - 1)) {
      return "Invalid email input.";
    }
    
    if (password == null || password.equals("")) {
      return "Empty password.";
    }
    
    if (name == null || name.equals("")) {
      return "Empty name.";
    }
    
    if (emergencyContact == null || emergencyContact.equals("")) {
      return "Empty emergency contact.";
    }
    
    if (nrWeeks < 0) {
      return "Number of weeks must be greater than zero.";
    } else if (nrWeeks > sst.getNrWeeks()) {
      return "Number of weeks cannot be greater than the tour season.";
    }

    if (weekAvailableUntil - weekAvailableFrom < nrWeeks - 1) {
      return "Invalid availability.";
    }

    if (weekAvailableFrom > weekAvailableUntil) {
      return "Invalid availability.";
    }

    if (weekAvailableFrom < 0 || weekAvailableFrom > sst.getNrWeeks() || weekAvailableUntil < 0 || weekAvailableUntil > sst.getNrWeeks()) {
      return "Invalid availability.";
    }

    // Check if the participant exists
    boolean p = Participant.hasWithAccountName(name);
    
    if (!p) {
      return "Participant doesn't exist.";
    } 

    // Check that the participant is not already registered
    Participant participant = (Participant) User.getWithAccountName(name);
    
    if (sst.getParticipants().contains(participant)) {
      return "Participant is already registered.";
    }

    // Try registering participant
    try {
      sst.addParticipant(participant);
      return "";
    } catch (Exception e) {
      return "Something went wrong!";
    }
  }

  public static void deleteParticipant(String email) {
    // Do nothing if invalid email input
    if (email == null || email.equals("") || (email.contains(" ")) || (!(email.indexOf("@") > 0 || (email.indexOf("@") == email.lastIndexOf("@"))) || (email.indexOf("@") < email.lastIndexOf(".") - 1)) || (email.lastIndexOf(".") < email.length() - 1)) {
      return;
    } else if (!User.hasWithAccountName(email)) {
      return;
    } else {
      try {
        // Referential integrity taken care of by UMPLE
        Participant participant = (Participant) User.getWithAccountName(email);
        participant.delete();
      } catch (Exception e) {
        return;
      }
    }
  }
  
  // FOLLOW UP, does this mean we're turning bookable item into a booked item
  public static String addBookableItemToParticipant(String email, String bookableItemName) {
    // Input validation
    if (email == null || email.equals("")) {
      return "Empty email.";
    } else if (email.contains(" ")) {
      return "Email must not contain any spaces.";
    } else if (!(email.indexOf("@") > 0 || (email.indexOf("@") == email.lastIndexOf("@"))) || (email.indexOf("@") < email.lastIndexOf(".") - 1)) || (email.lastIndexOf(".") < email.length() - 1)) {
      return "Invalid email input.";
    }
    
    if (bookableItemName == null || bookableItemName.equals("")) {
      return "Empty bookable item name.";
    }
    
    // Check that participant exists
    boolean p = Participant.hasWithAccountName(email);
    
    if (!p) {
      return "Participant doesn't exist.";
    }
    
    Participant participant = (Participant) User.getWithAccountName(email);
    
    // See if bookable item exists
    boolean b = BookableItem.hasWithName(bookableItemName);

    if (!b) {
      return "Bookable item doesn't exist.";
    }

    try {
      // Check if bookable is type Gear or Combo
      if (BookableItem.getWithName(bookableItemName) instanceof Gear) {
        Gear gear = (Gear) BookableItem.getWithName(bookableItemName);
        
        // Assume quantity is one?
        BookedItem booked = participant.addBookedItem(1, sst, gear);
        boolean added = participant.addBookedItem(booked);
        
        if (added) {
          return "";
        } else {
          return "Gear already booked.";
        }
      } else if (BookableItem.getWithName(bookableItemName) instanceof Combo) {
        Combo combo = (Combo) BookableItem.getWithName(bookableItemName);
        
        // Quantity is one?
        BookedItem booked = participant.addBookedItem(1, sst, combo);
        boolean added = participant.addBookedItem(booked);
        
        if (added) {
          return "";
        } else {
          return "Combo already booked.";
        }
      }
    } catch (Exception e) {
      return "Something went wrong!";
    }
  }

  public static String removeBookableItemFromParticipant(String email, String bookableItemName) {
    // Input validation
    if (email == null || email.equals("")) {
      return "Empty email.";
    } else if (email.contains(" ")) {
      return "Email must not contain any spaces.";
    } else if (!(email.indexOf("@") > 0 || (email.indexOf("@") == email.lastIndexOf("@"))) || (email.indexOf("@") < email.lastIndexOf(".") - 1)) || (email.lastIndexOf(".") < email.length() - 1)) {
      return "Invalid email input.";
    }
    
    if (bookableItemName == null || bookableItemName.equals("")) {
      return "Empty bookable item name.";
    }
    
    // Check if participant exists
    boolean p = Participant.hasWithAccountName(email);
    
    if (!p) {
      return "Participant doesn't exist.";
    }
    
    // Check if bookable item exists
    boolean b = BookableItem.hasWithName(bookableItemName);
    
    if (!b) {
      return "Bookable item doesn't exist.";
    }

    // Check if participant has booked item
    Participant participant = (Participant) User.getWithAccountName(email);
    
    List<BookedItem> items = participant.getBookedItems();
    boolean found = false;
    BookedItem toRemove;

    for (BookedItem item : items) {
      BookableItem currentItem = item.getItem();
      String name = currentItem.getName();

      if (bookableItemName.equals(name)) {
        found = true;
        toRemove = item;
      }
    }

    if (!found) {
      return "Bookable item currently not registered with participant.";
    }

    // Remove item
    try {
      participant.removeBookedItem(toRemove);
      return "";
    } catch (Exception e) {
      return "Something went wrong!";
    }
  }
}
