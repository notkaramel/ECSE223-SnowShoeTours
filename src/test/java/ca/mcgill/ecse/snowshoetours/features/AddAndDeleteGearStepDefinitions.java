package ca.mcgill.ecse.snowshoetours.features;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import ca.mcgill.ecse.snowshoetours.application.SnowShoeToursApplication;
import ca.mcgill.ecse.snowshoetours.controller.GearController;
import ca.mcgill.ecse.snowshoetours.model.BookableItem;
import ca.mcgill.ecse.snowshoetours.model.BookedItem;
import ca.mcgill.ecse.snowshoetours.model.Combo;
import ca.mcgill.ecse.snowshoetours.model.ComboItem;
import ca.mcgill.ecse.snowshoetours.model.Gear;
import ca.mcgill.ecse.snowshoetours.model.Participant;
import ca.mcgill.ecse.snowshoetours.model.SnowShoeTour;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddAndDeleteGearStepDefinitions {
  private SnowShoeTour sst;
  private String error;

  /**
   * 
   * @param dataTable
   */
  @Given("the following SnowShoeTour system exists \\(g5)")
  public void the_following_snow_shoe_tour_system_exists_g5(
      io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> rows = dataTable.asMaps();
    for (var row : rows) {
      Date startDate = Date.valueOf(row.get("startDate")); // Extract data from the Cucumber data
                                                           // table
      int nrWeeks = Integer.parseInt(row.get("nrWeeks"));
      int priceOfGuidePerWeek = Integer.parseInt(row.get("priceOfGuidePerWeek"));
      sst = SnowShoeToursApplication.getSnowShoeTour(); // Instantiate sst
      sst.setStartDate(startDate); // Setters
      sst.setNrWeeks(nrWeeks);
      sst.setPriceOfGuidePerWeek(priceOfGuidePerWeek);

      error = ""; // error counter
    }
  }

  /**
   * 
   * @param dataTable - the data table containing the elements to test
   */
  @Given("the following pieces of gear exist in the system \\(g5)")
  public void the_following_pieces_of_gear_exist_in_the_system_g5(
      io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> rows = dataTable.asMaps();
    for (var row : rows) {
      String name = row.get("name"); // Extract data from the Cucumber data table
      int pricePerWeek = Integer.parseInt(row.get("pricePerWeek")); // Extract data from the
                                                                    // Cucumber data table
      sst.addGear(name, pricePerWeek); // Add extracted data to the current sst
    }
  }

  /**
   * 
   * @param dataTable
   */
  @Given("the following combos exist in the system \\(g5)")
  public void the_following_combos_exist_in_the_system_g5(
      io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> rows2 = dataTable.asMaps();
    for (var row : rows2) { // for each row of the table
      String name = row.get("name"); // Extract data from the table
      int discount = Integer.parseInt(row.get("discount"));
      Combo combo = new Combo(name, discount, sst); // Create new combo
      String[] itemsList = row.get("items").split(","); // Create lists from table data
      String[] quantityList = row.get("quantity").split(",");

      for (int i = 0; i < itemsList.length; i++) { // Since each quantity is always related in a
                                                   // one-to-one relationship to each item, the
                                                   // length can be either of the number of items or
                                                   // the quantity of each item.
        int qty = Integer.parseInt(quantityList[i]); // Transform quantity from String to int
        for (var gear : sst.getGear()) { // Iterate through the list of gears
          if (itemsList[i].equals(gear.getName())) {
            gear.addComboItem(qty, sst, combo); // Add combo item to related gear and combo (new
                                                // ComboItem(int aQuantity, SnowShoeTour
                                                // aSnowShoeTour, Combo aCombo, this))
          }

        }
      }
    }
  }

  /**
   * Given Step to make sure that the BikeTourPlus system exists before the
   * beginning of a test
   * 
   */
  @Given("the following participants request the following pieces of gear \\(g5)")
  public void the_following_participants_request_the_following_pieces_of_gear_g5( // S
      io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> rows = dataTable.asMaps();
    for (var row : rows) {
      String gear = row.get("gear");
      int quantity = Integer.parseInt(row.get("quantity"));
      getParticipantByEmail(row.get("email")).addBookedItem(quantity, sst,
          BookableItem.getWithName(gear));
    }
  }

  /**
   *
   * 
   *         Adding participants to system
   *
   */
  @Given("the following participants exist in the system \\(g5)")
  public void the_following_participants_exist_in_the_system_g5(
      io.cucumber.datatable.DataTable dataTable) {

    List<Map<String, String>> participants = dataTable.asMaps(String.class, String.class);

    for (var participant : participants) {
      int numberOfWeeks = Integer.parseInt(participant.get("nrWeeks"));
      int weeksAvailableFrom = Integer.parseInt(participant.get("weeksAvailableFrom"));
      int weeksAvailableUntil = Integer.parseInt(participant.get("weeksAvailableUntil"));
      boolean lodgeRequired = Boolean.valueOf(participant.get("lodgeRequired"));
      sst.addParticipant(participant.get("email"), participant.get("password"),
          participant.get("name"), participant.get("emergencyContact"), numberOfWeeks,
          weeksAvailableFrom, weeksAvailableUntil, lodgeRequired, null, 0);
    }

  }

  /**
   * 
   * @param string  - name of the gear
   * @param string2 - price per week
   */
  @When("the manager attempts to add a new piece of gear with name {string} and price per week {string} \\(g5)")
  public void the_manager_attempts_to_add_a_new_piece_of_gear_with_name_and_price_per_week_g5(
      String string, String string2) {
    callController(GearController.addGear(string, Integer.parseInt(string2)));
  }

  /**
   * When Step to attempt to delete a piece of gear
   * 
   */
  @When("the manager attempts to delete the piece of gear with name {string} \\(g5)")
  public void the_manager_attempts_to_delete_the_piece_of_gear_with_name_g5(String string) { // S
    // Write code here that turns the phrase above into concrete actions
    callController(GearController.deleteGear(string));
  }

  /**
   * 
   * @param string  - name
   * @param string2 - price per week Expected to find no Gear with this name and
   *                price per week
   */
  @Then("a piece of gear shall not exist with name {string} and price per week {string} \\(g5)")
  public void a_piece_of_gear_shall_not_exist_with_name_and_price_per_week_g5(String string,
      String string2) {
    boolean found = false; // keep track of if a Gear with given name and pricePerWeek was found
    // Check all Gear to find a match for given name and pricePerWeek
    for (Gear oneGear : sst.getGear()) {
      if (oneGear.getName().equals(string) && Integer.toString(oneGear.getPricePerWeek()).equals(string2)) {
        // Found a Gear with given name and pricePerWeek
        found = true;
        break;

      }
    }
    // Assert that a Gear was not found to have the given name and pricePerWeek
    assertFalse(found);
  }

  /**
   * 
   * @param string the number of gear pieces
   */
  @Then("the number of pieces of gear in the system shall be {string} \\(g5)")
  public void the_number_of_pieces_of_gear_in_the_system_shall_be_g5(String string) {
    assertEquals(string, Integer.toString(sst.numberOfGear()));
  }

  /**
   * Then Step to make sure that a certain piece of gear exists
   * 
   */
  @Then("a piece of gear shall exist with name {string} and price per week {string} \\(g5)")
  public void a_piece_of_gear_shall_exist_with_name_and_price_per_week_g5(String string,
      String string2) { // S
    BookableItem bookableItem = BookableItem.getWithName(string);
    assertNotNull(bookableItem);
    Gear gear = ((Gear) bookableItem);
    assertEquals(string, gear.getName());
    assertEquals(Integer.parseInt(string2), gear.getPricePerWeek());
    // no need to check if gear exists as that will be sufficient
  }

  /**
   * Then Step to make sure that the number of pieces of gear is right
   * 
   */
  @Then("the number of pieces of gear shall be {string} \\(g5)")
  public void the_number_of_pieces_of_gear_shall_be_g5(String string) { // E
    // Write code here that turns the phrase above into concrete actions
    assertEquals(Integer.parseInt(string), sst.numberOfGear());
  }

  /**
   * Then Step to make sure that a certain combo has the right amount of piece of
   * gear
   * 
   */
  @Then("the combo with name {string} shall have a piece of gear with name {string} and quantity {string} \\(g5)")
  public void the_combo_with_name_shall_have_a_piece_of_gear_with_name_and_quantity_g5(
      String string, String string2, String string3) { // E
    // Write code here that turns the phrase above into concrete actions
    ComboItem aComboItem = null;
    for (ComboItem comboItem : ((Combo) BookableItem.getWithName(string)).getComboItems()) {
      if (comboItem.getGear().getName().equals(string2)) {
        aComboItem = comboItem;
      }
    }
    assertEquals(string2, aComboItem.getGear().getName());
    assertEquals(Integer.parseInt(string3), aComboItem.getQuantity());
  }

  /**
   * Then step definition to make sure that the number of bookableItems is right
   * 
   * @param string
   */
  @Then("the number of pieces of gear for the participant with email {string} shall be {string} \\(g5)")
  public void the_number_of_pieces_of_gear_for_the_participant_with_email_shall_be_g5(String string,
      String string2) { // S
    int total = 0;
    Participant participant = getParticipantByEmail(string);

    total = participant.getBookedItems().size();

    assertEquals(Integer.parseInt(string2), total);
  }

  /**
   * Then Step to make sure that the system raises an error
   * 
   */
  @Then("the system shall raise the error {string} \\(g5)")
  public void the_system_shall_raise_the_error_g5(String string) { // E
    assertTrue(error.contains(string));
  }

  /**
   * Then Step to make sure that a certain piece of gear doesn't exist
   * 
   */
  @Then("a piece of gear shall not exist with name {string} \\(g5)")
  public void a_piece_of_gear_shall_not_exist_with_name_g5(String string) { // S
    // Write code here that turns the phrase above into concrete actions
    assertNull(BookableItem.getWithName(string));
  }

  /**
   * Then Step to make sure that participant has the right amount of gear
   * 
   */
  @Then("the participant with email {string} shall have a piece of gear with name {string} and quantity {string} \\(g5)")
  public void the_participant_with_email_shall_have_a_piece_of_gear_with_name_and_quantity_g5(
      String string, String string2, String string3) { // C
    boolean name = false;
    boolean quantity = false;
    for (BookedItem bookedItem : (((Participant) Participant.getWithAccountName(string))
        .getBookedItems())) {
      name = bookedItem.getItem().getName().equals(string2);
      if (name && bookedItem.getItem() instanceof Gear) {
        if (Integer.parseInt(string3) == bookedItem.getQuantity()) {
          quantity = true;
          break;
        }
      } else
        name = false;

    }
    assertTrue(name && quantity);
  }

  /**
   * Then Step to make sure that the number of pieces of gear is right before the
   * beginning of a
   * test
   * 
   */

  @Then("the number of pieces of gear for the combo with name {string} shall be {string} \\(g5)")
  public void the_number_of_pieces_of_gear_for_the_combo_with_name_shall_be_g5(String string,
      String string2) {
    Combo combo = (Combo) BookableItem.getWithName(string);
    assertEquals(Integer.parseInt(string2), combo.getComboItems().size());
  }

  /**
   * Helper method to get a participant by email
   * 
   * @param email
   * @return
   */
  private Participant getParticipantByEmail(String email) {
    for (var participant : sst.getParticipants()) {
      if (participant.getAccountName().equals(email)) {
        return participant;
      }
    }
    return null;
  }

  /**
   * This method calls controller and sets error and counter. (Was taken from the
   * BTMS example)
   * 
   * @param result
   */
  private void callController(String result) {
    if (!result.isEmpty()) {
      error += result;
    }
  }
}
