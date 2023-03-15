package ca.mcgill.ecse.snowshoetours.features;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    throw new io.cucumber.java.PendingException();
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
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
    throw new io.cucumber.java.PendingException();
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

    throw new io.cucumber.java.PendingException();
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
    throw new io.cucumber.java.PendingException();
  }

  /**
   * @author Antoine Phan (@notkaramel)
   * @param dataTable
   */
  @Then("the number of participants shall be {string} \\(g7)")
  public void the_number_of_participants_shall_be_g7(String string) {
    // Write code here that turns the phrase above into concrete actions

    // assertEquals(expected, actual);
    throw new io.cucumber.java.PendingException();
  }

  /**
   * @author Bilar @bmokhtari
   * @param dataTable
   */
  @Then("the number of pieces of gear or combos for the participant with email {string} shall be {string} \\(g7)")
  public void the_number_of_pieces_of_gear_or_combos_for_the_participant_with_email_shall_be_g7(
      String string, String string2) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  /**
   * @author Sameer
   * @param dataTable
   */
  @Then("a piece of gear or combo shall not exist with name {string} for the participant with email {string} \\(g7)")
  public void a_piece_of_gear_or_combo_shall_not_exist_with_name_for_the_participant_with_email_g7(
      String string, String string2) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  /**
   * @author Sameer
   * @param dataTable
   */
  @Then("the system shall raise the error {string} \\(g7)")
  public void the_system_shall_raise_the_error_g7(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  /**
   * @author Angela
   * @param dataTable
   */
  @Then("a piece of gear or combo shall exist with name {string} and quantity {string} for the participant with email {string} \\(g7)")
  public void a_piece_of_gear_or_combo_shall_exist_with_name_and_quantity_for_the_participant_with_email_g7(
      String string, String string2, String string3) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
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
