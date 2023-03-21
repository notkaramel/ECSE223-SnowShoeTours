package ca.mcgill.ecse.snowshoetours.controller;

import java.util.List;

import ca.mcgill.ecse.snowshoetours.application.SnowShoeToursApplication;
import ca.mcgill.ecse.snowshoetours.model.BookableItem;
import ca.mcgill.ecse.snowshoetours.model.BookedItem;
import ca.mcgill.ecse.snowshoetours.model.Combo;
import ca.mcgill.ecse.snowshoetours.model.Gear;
import ca.mcgill.ecse.snowshoetours.model.Guide;
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
      return "Email cannot be empty";
    } else if (email.contains(" ")) {
      return "Invalid email";
    } else if (!(email.indexOf("@") > 0)) {
      return "Invalid email";
    } else if (email.indexOf("@") != email.lastIndexOf("@")) {
      return "Invalid email";
    } else if (!(email.indexOf("@") < email.lastIndexOf(".") - 1)) {
      return "Invalid email";
    } else if (!(email.lastIndexOf(".") < email.length() - 1)) {
      return "Invalid email";
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

    if (weekAvailableFrom < 0 || weekAvailableFrom > sst.getNrWeeks() || weekAvailableUntil < 0
        || weekAvailableUntil > sst.getNrWeeks()) {
      return "Invalid availability.";
    }

    // Check that the participant is not already registered
    Participant participant = (Participant) User.getWithAccountName(name);

    if (sst.getParticipants().contains(participant)) {
      return "Email already linked to a participant account";
    }

    // Try registering participant
    try {
      sst.addParticipant(name, password, name, emergencyContact, nrWeeks, weekAvailableFrom,
          weekAvailableUntil, lodgeRequired, "", 0); // code is empty, refund set to 0
      return "";
    } catch (Exception e) {
      return "Something went wrong!";
    }
  }

  public static void deleteParticipant(String email) {
    // Do nothing if invalid email input
    if (email == null || email.equals("") || (email.contains(" "))
        || (!(email.indexOf("@") > 0 || (email.indexOf("@") == email.lastIndexOf("@")))
            || (email.indexOf("@") < email.lastIndexOf(".") - 1))
        || (email.lastIndexOf(".") < email.length() - 1)) {
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

  public static String addBookableItemToParticipant(String email, String bookableItemName) {
    // Input validation
    if (email == null || email.equals("")) {
      return "Email cannot be empty";
    } else if (email.contains(" ")) {
      return "Invalid email";
    } else if (!(email.indexOf("@") > 0)) {
      return "Invalid email";
    } else if (email.indexOf("@") != email.lastIndexOf("@")) {
      return "Invalid email";
    } else if (!(email.indexOf("@") < email.lastIndexOf(".") - 1)) {
      return "Invalid email";
    } else if (!(email.lastIndexOf(".") < email.length() - 1)) {
      return "Invalid email";
    }

    if (bookableItemName == null || bookableItemName.equals("")) {
      return "The piece of gear or combo does not exist";
    }

    // Check that participant exists
    boolean p = User.hasWithAccountName(email);

    if (!p) {
      return "The participant does not exist";
    }

    // Check that User is not a Guide
    for (Guide guide : sst.getGuides()) {
      if (guide.getAccountName().equals(email)) { // Guide found
        return "The participant does not exist";
      }
    }

    Participant participant = (Participant) User.getWithAccountName(email);

    // See if bookable item exists
    boolean b = BookableItem.hasWithName(bookableItemName);

    if (!b) {
      return "The piece of gear or combo does not exist";
    }

    try {
      // Check if bookable is type Gear or Combo
      if (BookableItem.getWithName(bookableItemName) instanceof Gear) {
        Gear gear = (Gear) BookableItem.getWithName(bookableItemName);

        // Check if participant has booked item
        List<BookedItem> items = participant.getBookedItems();
        boolean found = false;

        for (BookedItem item : items) {
          BookableItem currentItem = item.getItem();
          String name = currentItem.getName();

          if (bookableItemName.equals(name)) {
            found = true;
            item.setQuantity(item.getQuantity() + 1); // Increase by one
          }
        }
        if (!found) {
          participant.addBookedItem(1, sst, gear);
        }
      } else if (BookableItem.getWithName(bookableItemName) instanceof Combo) {
        Combo combo = (Combo) BookableItem.getWithName(bookableItemName);

        List<BookedItem> items = participant.getBookedItems();
        boolean found = false;

        for (BookedItem item : items) {
          BookableItem currentItem = item.getItem();
          String name = currentItem.getName();

          if (bookableItemName.equals(name)) {
            found = true;
            item.setQuantity(item.getQuantity() + 1); // Increase by one
            break;
          }
        }
        if (!found) { // BookedItem for Gear doesn't exist yet
          participant.addBookedItem(1, sst, combo);
        }
      }
      return "";
    } catch (Exception e) {
      return "Something went wrong!";
    }
  }

  public static String removeBookableItemFromParticipant(String email, String bookableItemName) {
    // Input validation
    if (email == null || email.equals("")) {
      return "Email cannot be empty";
    } else if (email.contains(" ")) {
      return "Invalid email";
    } else if (!(email.indexOf("@") > 0)) {
      return "Invalid email";
    } else if (email.indexOf("@") != email.lastIndexOf("@")) {
      return "Invalid email";
    } else if (!(email.indexOf("@") < email.lastIndexOf(".") - 1)) {
      return "Invalid email";
    } else if (!(email.lastIndexOf(".") < email.length() - 1)) {
      return "Invalid email";
    }

    if (bookableItemName == null || bookableItemName.equals("")) {
      return "Empty bookable item name.";
    }

    // Check if participant exists
    boolean p = User.hasWithAccountName(email);

    if (!p) {
      return "The participant does not exist";
    }

    // Check that User is not a Guide
    for (Guide guide : sst.getGuides()) {
      if (guide.getAccountName().equals(email)) { // Guide found
        return "The participant does not exist";
      }
    }

    Participant participant = (Participant) User.getWithAccountName(email);

    // Check if bookable item exists
    boolean b = BookableItem.hasWithName(bookableItemName);

    if (!b) {
      return "Bookable item doesn't exist.";
    }

    try {
      // Check if participant has booked item and check for quantity
      List<BookedItem> items = participant.getBookedItems();

      for (BookedItem item : items) {
        BookableItem currentItem = item.getItem();
        String name = currentItem.getName();

        if (bookableItemName.equals(name)) { // Found the booked item
          item.setQuantity(item.getQuantity() - 1); // Decrease by one

          if (item.getQuantity() == 0) { // Quantity is zero, remove item
            item.delete();
          }
        }
      }
      return "";
    } catch (Exception e) {
      return "Something went wrong!";
    }
  }
}