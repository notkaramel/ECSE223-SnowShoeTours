package ca.mcgill.ecse.snowshoetours.features;

import java.sql.Date;
import java.util.Arrays;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ca.mcgill.ecse.snowshoetours.model.*;
import ca.mcgill.ecse.snowshoetours.application.*;
import ca.mcgill.ecse.snowshoetours.controller.*;

import static org.junit.Assert.*;

public class AddAndRemoveGearAndComboForParticipantStepDefinitions {

  SnowShoeTour SST;
  String error;

  /**
   * @author Antoine Phan (@notkaramel)
   * @param dataTable
   */
  @Given("the following SnowShoeTour system exists \\(g7)")
  public void the_following_snow_shoe_tour_system_exists_g7(io.cucumber.datatable.DataTable dataTable) {
    // Ensure that the system is not null
    SST = SnowShoeToursApplication.getSnowShoeTour(); // given by TA Katrina from Tutorial 8
    
    List<Map<String, String>> rowValue = dataTable.asMaps();
    
    // {startDate, nrWeeks, priceOfGuidePerWeek}
    for(var r : rowValue)
    {
      Date start = Date.valueOf(r.get("startDate"));
      int nrWeeks = Integer.parseInt(r.get("nrWeeks"));
      int price = Integer.parseInt(r.get("priceOfGuidePerWeek"));
      
      SST.setStartDate(start);
      SST.setNrWeeks(nrWeeks);
      SST.setPriceOfGuidePerWeek(price);
    }
  }

  /**
   * @author Bilar (@bmokhtari)
   * @param dataTable
   */
  @Given("the following pieces of gear exist in the system \\(g7)")
  public void the_following_pieces_of_gear_exist_in_the_system_g7(io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> rows = dataTable.asMaps();
    
    // {name, pricePerWeek}
    for(var row: rows) { // Goes through each row of the datatable and checks if they each have a name and a price per week
      String gearName = row.get("name");
      int pricePerWeek = Integer.parseInt(row.get("pricePerWeek"));
      SST.addGear(gearName, pricePerWeek);
    }
  }

  /**
   * @author Antoine Phan (@notkaramel)
   * @param dataTable
   */
  @Given("the following combos exist in the system \\(g7)")
  public void the_following_combos_exist_in_the_system_g7(
		io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> valueRow = dataTable.asMaps();
    
    // {name, discount, items, quantity}
    for (var map : valueRow) {
      String name = map.get("name"); // combo name
      int price = Integer.parseInt(map.get("discount"));
      List<String> items = Arrays.asList(map.get("items").split("\\s*,\\s*"));
      List<Integer> quantity = Arrays.stream(map.get("quantity").split(",")).map(Integer::parseInt)
          .collect(Collectors.toList());
      Combo combo = new Combo(name, price, SST);

      // looping through list of items and their quantity (same size lists)
      for (int i = 0; i < items.size(); i++) {
        // Gear gear = new Gear(items.get(i), price, SST);
        List<Gear> listGear = SST.getGear();
        
        for (Gear gear : listGear) {
          if (items.get(i).equals(gear.getName())) {
            combo.addComboItem(quantity.get(i), SST, gear);
          }
        }
      }
      SST.addCombo(combo);
    }
  }

  /**
   * @author Bilar (@bmokhtari)
   * @param dataTable
   */
  @Given("the following guides exist in the system \\(g7)")
  public void the_following_guides_exist_in_the_system_g7(
      io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> rows = dataTable.asMaps();
    
    // {email, password, name, emergencyContact}
    for (var row : rows) { // Goes row by row and collects the guide's information from the table
      String email = row.get("email"); // sets variables and details associated with the guide
      String password = row.get("password");
      String guideName = row.get("name");
      String emergencyContact = row.get("emergencyContact");
      
      SST.addGuide(email,password,guideName,emergencyContact);//Creates the guide
  }
}

  /**
   * @author Antoine Phan (@notkaramel)
   * @param dataTable
   */
  @Given("the following participants exist in the system \\(g7)")
  public void the_following_participants_exist_in_the_system_g7(
      io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> valueRow = dataTable.asMaps();
    
    // {email, password, name, emergencyContact, nrWeeks, weeksAvailableFrom, weeksAvailableUntil, lodgeRequired}
    for (var map : valueRow) {
      String email = map.get("email");
      String password = map.get("password");
      String name = map.get("name");
      String emergencyContact = map.get("emergencyContact");
      int nrWeeks = Integer.parseInt(map.get("nrWeeks"));
      int weeksAvailableFrom = Integer.parseInt(map.get("weeksAvailableFrom"));
      int weeksAvailableUntil = Integer.parseInt(map.get("weeksAvailableUntil"));
      boolean lodgeRequired = Boolean.parseBoolean(map.get("lodgeRequired"));

      String aAuthorizationCode = "";
      int aRefundedPercentageAmount = 0;
      SST.addParticipant(email, password, name, emergencyContact, nrWeeks, weeksAvailableFrom, weeksAvailableUntil,
          lodgeRequired, aAuthorizationCode, aRefundedPercentageAmount);
    }
  }

  /**
   * @author Jennifer Tram Su (@jennifertramsu)
   * @param dataTable
   */
  @Given("the following participants request the following pieces of gear \\(g7)")
  public void the_following_participants_request_the_following_pieces_of_gear_g7(
      io.cucumber.datatable.DataTable dataTable) {

    List<Map<String, String>> rows = dataTable.asMaps();

    // {email, gear, quantity}
    for (var row : rows) {
      String email = row.get("email");
      String gearName = row.get("gear");
      int quantity = Integer.parseInt(row.get("quantity"));

      Participant participant = (Participant) User.getWithAccountName(email);
      Gear gear = (Gear) BookableItem.getWithName(gearName);

      participant.addBookedItem(quantity, SST, gear);
    }
  }

  /**
   * @author Jennifer Tram Su (@jennifertramsu)
   * @param dataTable
   */
  @Given("the following participants request the following combos \\(g7)")
  public void the_following_participants_request_the_following_combos_g7(
      io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> rows = dataTable.asMaps();

    // {email, gear, quantity}
    for (var row : rows) {
      String email = row.get("email");
      String comboName = row.get("gear");
      int quantity = Integer.parseInt(row.get("quantity"));

      Participant participant = (Participant) User.getWithAccountName(email);
      Combo combo = (Combo) BookableItem.getWithName(comboName);

      participant.addBookedItem(quantity, SST, combo);
    }
  }

  /**
   * @author Bilar (@bmokhtari)
   * @param dataTable
   */
  @When("the manager attempts to remove a piece of gear or combo with name {string} from the participant with email {string} \\(g7)")
  public void the_manager_attempts_to_remove_a_piece_of_gear_or_combo_with_name_from_the_participant_with_email_g7(
      String name, String email) {
   ParticipantController.removeBookableItemFromParticipant(email, name); // This line calls a static method removeBookableItemFromParticipant() in the ParticipantController class, passing two strings as parameters. 
  }

  /**
   * @author Antoine Phan (@notkaramel)
   * @param dataTable
   */
  @Then("the number of participants shall be {string} \\(g7)")
  public void the_number_of_participants_shall_be_g7(String num) {
    assertEquals(Integer.parseInt(num), SST.numberOfParticipants());
  }

  /**
   * @author Bilar (@bmokhtari)
   * @param dataTable
   */
  @Then("the number of pieces of gear or combos for the participant with email {string} shall be {string} \\(g7)")
  public void the_number_of_pieces_of_gear_or_combos_for_the_participant_with_email_shall_be_g7(
      String email, String num) {
    // Write code here that turns the phrase above into concrete actions
    Participant emailPart = (Participant) Participant.getWithAccountName(email); //This line retrieves a participant object with the given account name (stored in the string variable) and assigns it to the emailPart
    //if (emailPart == null) {
    //  throw new AssertionError("The participant isn't registered in the system"); //This line checks if the emailPart variable is null. If it is, then it throws an AssertionError with the message "The participant isn't registered in the system". 
    //}
    assertEquals(Integer.parseInt(num), emailPart.getBookedItems().size()); // This line checks if the size of the bookedItems list of the emailPart participant object is equal to the integer value of string2 (which is presumably a string representation of an integer). 
  }

  /**
   * @author Sameer (@SRIAZ77)
   * @param dataTable
   */
  @Then("a piece of gear or combo shall not exist with name {string} for the participant with email {string} \\(g7)")
  public void a_piece_of_gear_or_combo_shall_not_exist_with_name_for_the_participant_with_email_g7(
      String name, String email) {
    List<Participant> list = SST.getParticipants();

    for (Participant p : list) {
      if (p.getAccountName() == email)
      {
        for (BookedItem b : p.getBookedItems()) {
          assertNotEquals(name, b.getItem().getName());
        }
        return; // break out of the for loop since email is unique
      }
    }
    // Did not found the participant
    //assertFalse("Error: Participant not exists in the system", true);
    //assertEquals("The participant does not exist", error);
    

      // if (b.getItem().getName() == string) {
        
      //   assert (false);
      // }
    // assert (true);
  }

  /**
   * @author Sameer (@SRIAZ77)
   * @param dataTable
   */
  @Then("the system shall raise the error {string} \\(g7)")
  public void the_system_shall_raise_the_error_g7(String string) {
    assertEquals(string, error);
  }

  /**
   * @author Angela (@angelaxzhu)
   * @param dataTable
   */
  @Then("a piece of gear or combo shall exist with name {string} and quantity {string} for the participant with email {string} \\(g7)")
  public void a_piece_of_gear_or_combo_shall_exist_with_name_and_quantity_for_the_participant_with_email_g7(
      String name, String quantity, String email) {
    // Finding the participant with the email
    // This part is kind of shady idk :(

    List<Participant> p_list = SST.getParticipants();
    Participant participant_of_interest = null;
    BookedItem item = null;
    int num = 0;
    for (int p = 0; p < SST.getParticipants().size(); p++) {
      Participant participant = p_list.get(p);
      if (participant.getAccountName().equals(email)) {
        participant_of_interest = participant;
        List<BookedItem> items_list = participant_of_interest.getBookedItems();
        for (int i = 0; i < items_list.size(); i++) {
          if (items_list.get(i).getItem().getName().equals(name)) {
            item = items_list.get(i);
            num = item.getQuantity();
          }
        }
      }
    }
    // There exists a participant with that email
    assertTrue(participant_of_interest != null);
    // The participant has that item
    assertTrue(item != null);
    // The item has the correct quantity
    assertEquals(quantity, Integer.toString(num));

  }

	/**
   * @author Jennifer Tram Su (@jennifertramsu)
   * @param dataTable
   */
  @When("the manager attempts to add a piece of gear or combo with name {string} to the participant with email {string} \\(g7)")
  public void the_manager_attempts_to_add_a_piece_of_gear_or_combo_with_name_to_the_participant_with_email_g7(
      String gear, String email) {
    // Write code here that turns the phrase above into concrete actions

    error = ParticipantController.addBookableItemToParticipant(email, gear);
  }
}
