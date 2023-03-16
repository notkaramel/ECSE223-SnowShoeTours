package ca.mcgill.ecse.snowshoetours.features;

import java.util.Arrays;
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
   * Check if the system exists
   * 
   * @author Antoine Phan (@notkaramel)
   * @param dataTable
   */
  @Given("the following SnowShoeTour system exists \\(g7)")
  public void the_following_snow_shoe_tour_system_exists_g7(io.cucumber.datatable.DataTable dataTable) {
    // Ensure that the system is not null
    // This was introduced by TA Katrina from tutorial 8
    SST = SnowShoeToursApplication.getSnowShoeTour();
  }

  /**
   * @author Bilar @bmokhtari
   * @param dataTable
   */
  @Given("the following pieces of gear exist in the system \\(g7)")
  public void the_following_pieces_of_gear_exist_in_the_system_g7(io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> rows = dataTable.asMaps();
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
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
    // List<Map<String, Integer, List<String>, List<Integer>>> valueRow = dataTable;
    List<Map<String, String>> valueRow = dataTable.asMaps();
    for (var map : valueRow) {
      String name = map.get("name"); // combo name
      int price = Integer.parseInt(map.get("price"));
      List<String> items = Arrays.asList(map.get("items").split("\\s*,\\s*"));
      List<Integer> quantity = Arrays.stream(map.get("quantity").split("\\s+")).map(Integer::parseInt)
          .collect(Collectors.toList());
      Combo combo = new Combo(name, price, SST);

      for (int i = 0; i < items.size(); i++) {
        Gear gear = new Gear(items.get(i), price, SST);
        combo.addComboItem(quantity.get(i), SST, gear);
      }
      SST.addCombo(combo); // Jen: I believe this is already called in the addComboItem method
    }
  }

  /**
   * @author Bilar @bmokhtari
   * @param dataTable
   */
  @Given("the following guides exist in the system \\(g7)")
  public void the_following_guides_exist_in_the_system_g7(
      io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> rows = dataTable.asMaps();
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
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
    List<Map<String, String>> valueRow = dataTable.asMaps();
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

    // throw new io.cucumber.java.PendingException();
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
   * @author Jennifer
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
   * @author Bilar @bmokhtari
   * @param dataTable
   */
  @When("the manager attempts to remove a piece of gear or combo with name {string} from the participant with email {string} \\(g7)")
  public void the_manager_attempts_to_remove_a_piece_of_gear_or_combo_with_name_from_the_participant_with_email_g7(
      String string, String string2) {
    // Write code here that turns the phrase above into concrete actions
   ParticipantController.removeBookableItemFromParticipant(string, string2); // This line calls a static method removeBookableItemFromParticipant() in the ParticipantController class, passing two strings as parameters. 
  }

  /**
   * @author Antoine Phan (@notkaramel)
   * @param dataTable
   */
  @Then("the number of participants shall be {string} \\(g7)")
  public void the_number_of_participants_shall_be_g7(String string) {
    assertEquals(Integer.parseInt(string), SST.numberOfParticipants());
  }

  /**
   * @author Bilar @bmokhtari
   * @param dataTable
   */
  @Then("the number of pieces of gear or combos for the participant with email {string} shall be {string} \\(g7)")
  public void the_number_of_pieces_of_gear_or_combos_for_the_participant_with_email_shall_be_g7(
      String string, String string2) {
    // Write code here that turns the phrase above into concrete actions
    Participant emailPart = (Participant) Participant.getWithAccountName(string); //This line retrieves a participant object with the given account name (stored in the string variable) and assigns it to the emailPart
    if (emailPart == null) {
      throw new AssertionError("The participant isn't registered in the system"); //This line checks if the emailPart variable is null. If it is, then it throws an AssertionError with the message "The participant isn't registered in the system". 
    }
    assertEquals(Integer.parseInt(string2), emailPart.getBookedItems().size()); // This line checks if the size of the bookedItems list of the emailPart participant object is equal to the integer value of string2 (which is presumably a string representation of an integer). 
  }

  /**
   * @author Sameer
   * @param dataTable
   */
  @Then("a piece of gear or combo shall not exist with name {string} for the participant with email {string} \\(g7)")
  public void a_piece_of_gear_or_combo_shall_not_exist_with_name_for_the_participant_with_email_g7(
      String string, String string2) {
    // Write code here that turns the phrase above into concrete actions
    List<Participant> list = SST.getParticipants();

    Participant selected_participant;

    for (Participant p : list) {
      if (p.getAccountName() == string2) {
        selected_participant = p;
      }
    }
    for (BookedItem b : selected_participant.getBookedItems()) {
      if (b.getItem().getName() == string) {
        assert (false);
      }
    }
    assert (true);
    // Antoine: Should we fix this? I don't think this would work
  }

  /**
   * @author Sameer
   * @param dataTable
   */
  @Then("the system shall raise the error {string} \\(g7)")
  public void the_system_shall_raise_the_error_g7(String string) {
    // Write code here that turns the phrase above into concrete actions
    assertEquals(error, string);
  }

  /**
   * @author Angela
   * @param dataTable
   */
  @Then("a piece of gear or combo shall exist with name {string} and quantity {string} for the participant with email {string} \\(g7)")
  public void a_piece_of_gear_or_combo_shall_exist_with_name_and_quantity_for_the_participant_with_email_g7(
      String name, String quantity, String email) {
    // Write code here that turns the phrase above into concrete actions

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
    assertEquals(Integer.toString(num), quantity);

    // throw new io.cucumber.java.PendingException();
  }

	/**
   * @author Bilar @bmokhtari OR Jennifer
   * @param dataTable
   */
  @When("the manager attempts to add a piece of gear or combo with name {gear} to the participant with email {email} \\(g7)")
  public void the_manager_attempts_to_add_a_piece_of_gear_or_combo_with_name_to_the_participant_with_email_g7(
      String email, String gear) {
    // Write code here that turns the phrase above into concrete actions

    error = ParticipantController.addBookableItemToParticipant(email, gear);
  }
}
