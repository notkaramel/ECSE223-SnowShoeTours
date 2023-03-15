package ca.mcgill.ecse.snowshoetours.features;

import java.util.List;

import ca.mcgill.ecse.snowshoetours.model.BookedItem;
import ca.mcgill.ecse.snowshoetours.model.Participant;
import ca.mcgill.ecse.snowshoetours.model.SnowShoeTour;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddAndRemoveGearAndComboForParticipantStepDefinitions {
	
	SnowShoeTour SST;
	
	
  @Given("the following SnowShoeTour system exists \\(g7)")
  public void the_following_snow_shoe_tour_system_exists_g7(
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

  @Given("the following pieces of gear exist in the system \\(g7)")
  public void the_following_pieces_of_gear_exist_in_the_system_g7(
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
    throw new io.cucumber.java.PendingException();
  }

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
    throw new io.cucumber.java.PendingException();
  }

  @Given("the following participants request the following pieces of gear \\(g7)")
  public void the_following_participants_request_the_following_pieces_of_gear_g7(
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

  @Given("the following participants request the following combos \\(g7)")
  public void the_following_participants_request_the_following_combos_g7(
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

  @When("the manager attempts to remove a piece of gear or combo with name {string} from the participant with email {string} \\(g7)")
  public void the_manager_attempts_to_remove_a_piece_of_gear_or_combo_with_name_from_the_participant_with_email_g7(
      String string, String string2) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the number of participants shall be {string} \\(g7)")
  public void the_number_of_participants_shall_be_g7(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the number of pieces of gear or combos for the participant with email {string} shall be {string} \\(g7)")
  public void the_number_of_pieces_of_gear_or_combos_for_the_participant_with_email_shall_be_g7(
      String string, String string2) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("a piece of gear or combo shall not exist with name {string} for the participant with email {string} \\(g7)")
  public void a_piece_of_gear_or_combo_shall_not_exist_with_name_for_the_participant_with_email_g7(
      String string, String string2) {
	  List<Participant> list = SST.getParticipants();
	  
	  Participant selected_participant;
	  
	  for (Participant p: list) {
		  if (p.getAccountName() == string2) {
			  selected_participant = p;
		  }
	  }
	  for (BookedItem b: selected_participant.getBookedItems()) {
		  if (b.getItem().getName() == string) {
			  assert(false);
		  }
	  }
	  assert(true);
    
  }

  @Then("the system shall raise the error {string} \\(g7)")
  public void the_system_shall_raise_the_error_g7(String string) {
    assertEquals(error, string);
  }

  @Then("a piece of gear or combo shall exist with name {string} and quantity {string} for the participant with email {string} \\(g7)")
  public void a_piece_of_gear_or_combo_shall_exist_with_name_and_quantity_for_the_participant_with_email_g7(
      String string, String string2, String string3) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @When("the manager attempts to add a piece of gear or combo with name {string} to the participant with email {string} \\(g7)")
  public void the_manager_attempts_to_add_a_piece_of_gear_or_combo_with_name_to_the_participant_with_email_g7(
      String string, String string2) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }
}
