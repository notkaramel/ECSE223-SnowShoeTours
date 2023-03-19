package ca.mcgill.ecse.snowshoetours.features;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import ca.mcgill.ecse.snowshoetours.application.SnowShoeToursApplication;
import ca.mcgill.ecse.snowshoetours.controller.GearController;
import ca.mcgill.ecse.snowshoetours.model.SnowShoeTour;
import ca.mcgill.ecse.snowshoetours.model.BookableItem;
import ca.mcgill.ecse.snowshoetours.model.Combo;
import ca.mcgill.ecse.snowshoetours.model.ComboItem;
import ca.mcgill.ecse.snowshoetours.model.Gear;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * This class provides the step definitions for AddAndRemoveGearForCombo
 */
public class AddAndRemoveGearForComboStepDefinitions {

  private String error;
  private SnowShoeTour sst;

  /**
   * @param dataTable This method parses the given dataTable to initialize the SnowShoeTour system
   */
  @Given("the following SnowShoeTour system exists \\(g8)")
  public void the_following_snow_shoe_tour_system_exists_g8(
      io.cucumber.datatable.DataTable dataTable) {

    List<Map<String, String>> rows = dataTable.asMaps();

    // loop through each row of data table
    for (var row : rows) {
      // Create the SnowShoeTour System with the specified startDate, nrWeeks,
      // priceOfGuidePerWeek
      Date startDate = java.sql.Date.valueOf(row.get("startDate"));
      int nrWeeks = Integer.parseInt(row.get("nrWeeks"));
      int priceOfGuidePerWeek = Integer.parseInt(row.get("priceOfGuidePerWeek"));
      sst = SnowShoeToursApplication.getSnowShoeTour();
      sst.setNrWeeks(nrWeeks);
      sst.setStartDate(startDate);
      sst.setPriceOfGuidePerWeek(priceOfGuidePerWeek);
      error = "";

    }
  }

  /**
   * @param dataTable This method parses the given dataTable to generate the gear in the system
   */
  @Given("the following pieces of gear exist in the system \\(g8)")
  public void the_following_pieces_of_gear_exist_in_the_system_g8(
      io.cucumber.datatable.DataTable dataTable) {
    // Create gear pieces in the system with the specified name, pricePerWeek
    List<Map<String, String>> rows = dataTable.asMaps();
    for (var row : rows) {
      String name = row.get("name");
      int pricePerWeek = Integer.parseInt(row.get("pricePerWeek"));
      sst.addGear(name, pricePerWeek);
    }
  }

  /**
   * @param dataTable This method parses the given dataTable to create combos associated with
   *        multiple gear items and quantities
   */
  @Given("the following combos exist in the system \\(g8)")
  public void the_following_combos_exist_in_the_system_g8(
      io.cucumber.datatable.DataTable dataTable) {

    List<Map<String, String>> rows = dataTable.asMaps();

    // loop through each row of data table
    for (var row : rows) {
      // create a combo with the specified name and discount
      String name = row.get("name");
      int discount = Integer.parseInt(row.get("discount"));
      Combo combo = sst.addCombo(name, discount);

      // get items and quantities strings
      String item_names_string = row.get("items");
      String item_quantities_string = row.get("quantity");

      // split the comma-separated strings into list of strings
      List<String> item_names_list = Arrays.asList(item_names_string.split(","));
      List<String> item_quantities_list = Arrays.asList(item_quantities_string.split(","));

      for (int i = 0; i < (item_names_list).size(); i++) {
        // get existing gear object with name in data table
        Gear gear = (Gear) BookableItem.getWithName(item_names_list.get(i));

        // create a combo item, which links the combo with its gear items and specifies
        // quantities
        gear.addComboItem(Integer.valueOf(item_quantities_list.get(i)), sst, combo);
      }
    }
  }

  /**
   * @param testGearName
   * @param testComboName This method attempts to add a specific piece of gear to a specific combo
   */
  @When("the manager attempts to add a piece of gear with name {string} to the combo with name {string} \\(g8)")
  public void the_manager_attempts_to_add_a_piece_of_gear_with_name_to_the_combo_with_name_g8(
      String string, String string2) {
    callController(GearController.addGearToCombo(string, string2));

  }

  /**
   * @param string
   * @param string2 This method calls a controller method to remove a specific gear from a specific
   *        combo
   */
  @When("the manager attempts to remove a piece of gear with name {string} from the combo with name {string} \\(g8)")
  public void the_manager_attempts_to_remove_a_piece_of_gear_with_name_from_the_combo_with_name_g8(
      String string, String string2) {
    callController(GearController.removeGearFromCombo(string, string2));
  }

  /**
   *         is equal to the value specified in the .feature file
   * @param string Correct number of combos
   */
  @Then("the number of combos shall be {string} \\(g8)")
  public void the_number_of_combos_shall_be_g8(String string) {
    assertEquals(Integer.parseInt(string), sst.getCombos().size());
  }

  /**
   * @param string This method verifies system catches the correct error
   */
  @Then("the system shall raise the error {string} \\(g8)")
  public void the_system_shall_raise_the_error_g8(String string) {
    assertEquals(string, error);
  }

  /**
   * @param string
   * @param string2 This method checks if combo has correct number of gear for a combo
   */
  @Then("the number of pieces of gear for the combo with name {string} shall be {string} \\(g8)")
  public void the_number_of_pieces_of_gear_for_the_combo_with_name_shall_be_g8(String string,
      String string2) {
    // get item with specified name
    BookableItem foundItem = BookableItem.getWithName(string);

    // ensure that the foundItem exists
    assertNotNull(foundItem);

    // ensure that the foundItem is a combo
    assertTrue(foundItem instanceof Combo);

    // compare number of pieces of gear
    Combo combo = (Combo) foundItem;
    assertEquals(Integer.valueOf(string2), combo.numberOfComboItems());
  }

  /**
   * @param string
   * @param string2
   * @param string3
   */
  @Then("a piece of gear shall exist with name {string} and quantity {string} for the combo with name {string} \\(g8)")
  public void a_piece_of_gear_shall_exist_with_name_and_quantity_for_the_combo_with_name_g8(
      String string, String string2, String string3) {
    assertNotNull(BookableItem.getWithName(string)); // check if gear exists
    assertNotNull(BookableItem.getWithName(string3)); // check if combo exists

    int quantity = -1;
    for (ComboItem comboItem : sst.getComboItems()) {
      if (comboItem.getGear().getName().equals(string)
          && comboItem.getCombo().getName().equals(string3)) {
        quantity = comboItem.getQuantity();
      }
    }
    assertEquals(Integer.parseInt(string2), quantity);
  }

  /**
   * @param string
   * @param string2
   */
  @Then("a piece of gear shall not exist with name {string} for the combo with name {string} \\(g8)")
  public void a_piece_of_gear_shall_not_exist_with_name_for_the_combo_with_name_g8(String string,
      String string2) {
    for (ComboItem comboItem : sst.getComboItems()) {
      if (comboItem.getGear().getName().equals(string)
          && comboItem.getCombo().getName().equals(string2)) {
        fail("An unexpected gear exists in the combo");
      }
    }
  }

  /** Calls controller and sets error and counter */
  private void callController(String result) {
    if (!result.isEmpty()) {
      error += result;
    }
  }

}
