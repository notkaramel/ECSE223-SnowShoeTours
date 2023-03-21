package ca.mcgill.ecse.snowshoetours.features;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import ca.mcgill.ecse.snowshoetours.application.SnowShoeToursApplication;
import ca.mcgill.ecse.snowshoetours.controller.GearController;
import ca.mcgill.ecse.snowshoetours.model.BookableItem;
import ca.mcgill.ecse.snowshoetours.model.BookedItem;
import ca.mcgill.ecse.snowshoetours.model.Combo;
import ca.mcgill.ecse.snowshoetours.model.Gear;
import ca.mcgill.ecse.snowshoetours.model.Participant;
import ca.mcgill.ecse.snowshoetours.model.SnowShoeTour;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddAndDeleteComboStepDefinitions {
  private SnowShoeTour sst;
  private String error;

  /**
   * This method creates a sst and initializes the error message.
   * 
   * @param dataTable from feature file containing startDate, nrWeeks and
   *                  priceOfGuidePerWeek
   * @return returns nothing
   */
  @Given("the following SnowShoeTour system exists \\(g4)")
  public void the_following_snow_shoe_tour_system_exists_g4(
      io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
    sst = SnowShoeToursApplication.getSnowShoeTour();
    sst.setStartDate(java.sql.Date.valueOf(rows.get(0).get("startDate")));
    sst.setNrWeeks(Integer.parseInt(rows.get(0).get("nrWeeks")));
    sst.setPriceOfGuidePerWeek(Integer.parseInt(rows.get(0).get("priceOfGuidePerWeek")));
    error = "";
  }

  /**
   * Given step to make sure that certain participants exist in the system
   * before the beginning of a certain test
   * 
   */
  @Given("the following participants exist in the system \\(g4)")
  public void the_following_participants_exist_in_the_system_g4( // K
      io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> rows = dataTable.asMaps();
    for (var row : rows) {
      String email = row.get("email");
      String password = row.get("password");
      String name = row.get("name");
      String emergencyContact = row.get("emergencyContact");
      int nrWeeks = Integer.parseInt(row.get("nrWeeks"));
      int weekAvailableFrom = Integer.parseInt(row.get("weeksAvailableFrom"));
      int weekAvailableUntil = Integer.parseInt(row.get("weeksAvailableUntil"));
      boolean lodgeRequired = Boolean.parseBoolean(row.get("lodgeRequired"));
      new Participant(email, password, name, emergencyContact, nrWeeks, weekAvailableFrom, weekAvailableUntil,
          lodgeRequired, null, 0, sst);
    }
  }

  /**
   * This method checks whether the pieces of gear in the datatable are in the
   * system.
   * 
   * @param dataTable from feature file containing the gears
   * @return returns error message, if any
   */
  @Given("the following pieces of gear exist in the system \\(g4)")
  public void the_following_pieces_of_gear_exist_in_the_system_g4(
      io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
    for (Map<String, String> columns : rows) {
      sst.addGear(columns.get("name"), Integer.parseInt(columns.get("pricePerWeek")));
    }
  }

  /**
   * Given step to make sure that certain participants have been assigned certain
   * combos
   * in the system before the beginning of a certain test
   * 
   */
  @Given("the following participants request the following combos \\(g4)")
  public void the_following_participants_request_the_following_combos_g4( // W
      io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> rows = dataTable.asMaps();
    for (var row : rows) {
      String email = row.get("email");
      String name = row.get("combo");
      int quantity = Integer.parseInt(row.get("quantity"));
      Participant participant = (Participant) Participant.getWithAccountName(email);
      BookableItem combo = BookableItem.getWithName(name);
      new BookedItem(quantity, sst, participant, combo);
    }
  }

  /**
   * This method checks whether the pieces of combos in the datatable are in the
   * system.
   * 
   * @param dataTable from feature file containing the combos
   * @return returns error message, if any
   */
  @Given("the following combos exist in the system \\(g4)")
  public void the_following_combos_exist_in_the_system_g4(
      io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
    for (Map<String, String> columns : rows) {
      Combo c = new Combo(columns.get("name"), Integer.parseInt(columns.get("discount")), sst);
      String str = columns.get("items");
      String q = columns.get("quantity");
      List<String> quantities = Arrays.asList(q.split(","));
      List<String> items = Arrays.asList(str.split(","));
      String item;
      int quantity;
      for (int i = 0; i < items.size(); i++) {
        item = items.get(i);
        quantity = Integer.parseInt(quantities.get(i));
        List<Gear> gears = sst.getGear();
        Gear g1 = null;
        for (Gear g : gears) {
          if (g.getName().equals(item)) {
            g1 = g;
          }
        }
        c.addComboItem(quantity, sst, g1);
      }
    }
  }

  /**
   * This method checks whether the combo is added successfully
   * 
   * @param name of combo, discount
   * @return returns error message, if any
   */
  @When("the manager attempts to add a combo with name {string} and discount {string} \\(g4)")
  public void the_manager_attempts_to_add_a_combo_with_name_and_discount_g4(String string,
      String string2) {

    callController(GearController.addCombo(string, Integer.parseInt(string2)));
  }

  /**
   * When step that uses the controller to delete the Combo with name <string>
   * when
   * the manager tries to.
   * 
   */
  @When("the manager attempts to delete the combo with name {string} \\(g4)")
  public void the_manager_attempts_to_delete_the_combo_with_name_g4(String string) { // W
    GearController.deleteCombo(string);
  }

  /**
   * This method checks whether a combo with a given name exists
   * 
   * @param name of combo
   * @return returns error message, if any
   */
  @Then("the number of combos shall be {string} \\(g4)")
  public void the_number_of_combos_shall_be_g4(String string) {
    assertEquals(Integer.parseInt(string), sst.getCombos().size());
  }

  /**
   * This method checks whether a combo has a given number of gear types
   * 
   * @param name of combo, number of gears
   * @return returns error message, if any
   */
  @Then("the number of pieces of gear for the combo with name {string} shall be {string} \\(g4)")
  public void the_number_of_pieces_of_gear_for_the_combo_with_name_shall_be_g4(String string,
      String string2) {
    Combo c1 = null;
    for (Combo c : sst.getCombos()) {
      if (c.getName().equals(string)) {
        c1 = c;
        break;
      }
    }
    assertNotNull(c1);
    int q = c1.getComboItems().size();
    assertEquals(Integer.parseInt(string2), q);

  }

  /**
   * This method checks whether a combo with name and discount exist
   * 
   * @param name of combo, discount
   * @return returns error message, if any
   */
  @Then("a combo shall not exist with name {string} and discount {string} \\(g4)")
  public void a_combo_shall_not_exist_with_name_and_discount_g4(String string, String string2) {
    boolean exist = false;
    for (Combo c : sst.getCombos()) {
      if (c.getName().equals(string) && c.getDiscount() == Integer.parseInt(string2)) {
        exist = true;
        break;
      }
    }

    assertTrue(!exist);

  }

  /**
   * This method checks whether a combo with given name and discount exist
   * 
   * @param name of combo, discount
   * @return returns error message, if any
   */
  @Then("a combo shall exist with name {string} and discount {string} \\(g4)")
  public void a_combo_shall_exist_with_name_and_discount_g4(String string, String string2) {
    boolean exist = false;

    for (Combo c : sst.getCombos()) {
      if (c.getName().equals(string) && c.getDiscount() == Integer.parseInt(string2)) {
        exist = true;
        break;
      }
    }
    assertTrue(exist);

  }

  /**
   * Then step to verify that no combo with name <string> exists in the system.
   * 
   */
  @Then("a combo shall not exist with name {string} \\(g4)")
  public void a_combo_shall_not_exist_with_name_g4(String string) { // W
    assertNull(BookableItem.getWithName(string));
  }

  /**
   * Then step definition to make sure that a certain participant has the right
   * amount of combos
   * 
   * @param string
   */
  @Then("the participant with email {string} shall have a combo with name {string} and quantity {string} \\(g4)")
  public void the_participant_with_email_shall_have_a_combo_with_name_and_quantity_g4(String string,
      String string2, String string3) {
    boolean name = false;
    boolean quantity = false; // j
    for (BookedItem bookedItem : ((Participant) Participant.getWithAccountName(string)).getBookedItems()) {
      name = bookedItem.getItem().getName().equals(string2);
      if (name && bookedItem.getItem() instanceof Combo) {
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
   * Then step definition to make sure that the number of bookableItems is right
   * 
   * @param string
   */
  @Then("the number of pieces of gear for the participant with email {string} shall be {string} \\(g4)")
  public void the_number_of_pieces_of_gear_for_the_participant_with_email_shall_be_g4(String string,
      String string2) { // S
    int total = 0;
    Participant participant = getParticipantByEmail(string);

    // We assumed that pieces of gear in both context of delete gear and delete
    // combo means "BookableItem"
    // Because this step definition is used in both features (Combo and Gear)
    // In the combo feature it checks for the number of remaining combos
    // ("<numberOfRequestedCombos>") after a deletion
    // In the gear feature it checks for the number of remaining gears
    // ("<numberOfRequestedGears>") after a deletion
    total = participant.getBookedItems().size();

    assertEquals(Integer.parseInt(string2), total);
  }

  /**
   * This method checks whether an error exists
   * 
   * @param error string
   * @return nothing
   */
  @Then("the system shall raise the error {string} \\(g4)")
  public void the_system_shall_raise_the_error_g4(String string) {
    assertTrue(error.contains(string));
  }

  private void callController(String result) { // Taken from tutorial 6
    if (!result.isEmpty()) {
      error += result;
    }
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
}
