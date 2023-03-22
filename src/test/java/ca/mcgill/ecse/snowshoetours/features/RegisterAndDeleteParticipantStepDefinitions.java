package ca.mcgill.ecse.snowshoetours.features;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import ca.mcgill.ecse.snowshoetours.application.SnowShoeToursApplication;
import ca.mcgill.ecse.snowshoetours.controller.ParticipantController;
import ca.mcgill.ecse.snowshoetours.model.BookableItem;
import ca.mcgill.ecse.snowshoetours.model.Combo;
import ca.mcgill.ecse.snowshoetours.model.Gear;
import ca.mcgill.ecse.snowshoetours.model.Guide;
import ca.mcgill.ecse.snowshoetours.model.Manager;
import ca.mcgill.ecse.snowshoetours.model.Participant;
import ca.mcgill.ecse.snowshoetours.model.SnowShoeTour;
import ca.mcgill.ecse.snowshoetours.model.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RegisterAndDeleteParticipantStepDefinitions {
  private SnowShoeTour sst;
  private String message;

  /**
   * @param dataTable
   *                  Barry Zhang
   */
  @Given("the following SnowShoeTour system exists \\(g1)")
  public void the_following_SnowShoeTour_System_exists_g1(
      io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> rows = dataTable.asMaps();
    sst = SnowShoeToursApplication.getSnowShoeTour();
    for (Map<String, String> row : rows) { // for each row, get the information to create a SnowShoeTour system
      Date startDate = Date.valueOf(row.get("startDate"));
      sst.setStartDate(startDate);
      int nrWeeks = Integer.parseInt(row.get("nrWeeks"));
      sst.setNrWeeks(nrWeeks);
      int priceOfGuidePerWeek = Integer.parseInt(row.get("priceOfGuidePerWeek"));
      sst.setPriceOfGuidePerWeek(priceOfGuidePerWeek);
    }
  }

  /**
   * @param dataTable
   *                  Barry Zhang
   */
  @Given("the following guides exist in the system \\(g1)")
  public void the_following_guides_exist_in_the_system_g1(
      io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> rows = dataTable.asMaps();
    for (Map<String, String> row : rows) {
      String email = row.get("email");
      String password = row.get("password");
      String name = row.get("name");
      String emergencyContact = row.get("emergencyContact");
      sst.addGuide(email, password, name, emergencyContact);
    }
  }

  /**
   * @param dataTable
   *                  Barry Zhang
   */
  @Given("the following participants exist in the system \\(g1)")
  public void the_following_participants_exist_in_the_system_g1(
      io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> rows = dataTable.asMaps();
    for (var row : rows) { // for each row, get the information to create a participant within the system
      sst.addParticipant(row.get("email"), row.get("password"), row.get("name"),
          row.get("emergencyContact"), Integer.parseInt(row.get("nrWeeks")),
          Integer.parseInt(row.get("weeksAvailableFrom")),
          Integer.parseInt(row.get("weeksAvailableUntil")),
          Boolean.parseBoolean(row.get("lodgeRequired")), "", 0);
    }
  }

  /**
   *
   * Add given pieces of gear to the sst system
   *
   * @param dataTable
   */

  @Given("the following pieces of gear exist in the system \\(g1)")
  public void the_following_pieces_of_gear_exist_in_the_system_g1(
      io.cucumber.datatable.DataTable dataTable) {

    List<Map<String, String>> gears = dataTable.asMaps();
    for (var gear : gears) {

      String name = gear.get("name");
      int pricePerWeek = Integer.parseInt(gear.get("pricePerWeek"));
      sst.addGear(name, pricePerWeek);
    }

  }

  /**
   * This method adds the requested combo to system
   *
   * @param dataTable
   *
   */
  @Given("the following combos exist in the system \\(g1)")
  public void the_following_combos_exist_in_the_system_g1(
      io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> rows = dataTable.asMaps();
    for (var row : rows) {

      String comboName = row.get("name");
      int comboDiscount = Integer.parseInt(row.get("discount"));
      int nrOfItems = row.get("items").split(",").length;
      Combo combo = new Combo(comboName, comboDiscount, sst);
      for (int i = 0; i < nrOfItems; i++) {
        String itemName = row.get("items").split(",")[i];
        Gear gear = (Gear) BookableItem.getWithName(itemName);
        int itemQuantity = Integer.parseInt(row.get("quantity").split(",")[i]);
        combo.addComboItem(itemQuantity, sst, gear);

      }
    }
  }

  /**
   * This method adds a requested gear to a participant.
   *
   * @param dataTable
   */

  @Given("the following participants request the following pieces of gear \\(g1)")
  public void the_following_participants_request_the_following_pieces_of_gear_g1(
      io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> rows = dataTable.asMaps();
    for (var row : rows) {
      String gear = row.get("gear");
      int quantity = Integer.parseInt(row.get("quantity"));
      Participant p = (Participant) Participant.getWithAccountName(row.get("email"));
      p.addBookedItem(quantity, sst, BookableItem.getWithName(gear));
    }
  }

  /**
   * This method adds a requested combo to a participant.
   *
   * @param dataTable
   */
  @Given("the following participants request the following combos \\(g1)")
  public void the_following_participants_request_the_following_combos_g1(
      io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> rows = dataTable.asMaps();

    for (var row : rows) {
      String participantEmail = row.get("email");
      Participant participant = (Participant) Participant.getWithAccountName(participantEmail);
      String gearName = row.get("gear");
      BookableItem gearBookableItem = BookableItem.getWithName(gearName);
      int gearQuantity = Integer.parseInt(row.get("quantity"));
      participant.addBookedItem(gearQuantity, sst, gearBookableItem);
    }

  }

  /**
   * @param email
   * @param password
   * @param name
   * @param emergencyContact
   * @param nrWeeks
   * @param weeksAvailableFrom
   * @param weeksAvailableUntil
   * @param lodgeRequired
   *                            Barry Zhang
   */
  @When("a new participant attempts to register with email {string}, password {string}, name {string}, emergency contact {string}, number of weeks {string}, week available from {string}, week available until {string}, and lodge required {string} \\(g1)")
  public void a_new_participant_attempts_to_register_with_email_password_name_emergency_contact_number_of_weeks_week_available_from_week_available_until_and_lodge_required_g1(
      String email, String password, String name, String emergencyContact, String nrWeeks,
      String weeksAvailableFrom, String weeksAvailableUntil, String lodgeRequired) {
    // Write code here that turns the phrase above into concrete actions
    message = ParticipantController.registerParticipant(email, password, name,
        emergencyContact, Integer.parseInt(nrWeeks), Integer.parseInt(weeksAvailableFrom),
        Integer.parseInt(weeksAvailableUntil), Boolean.parseBoolean(lodgeRequired));
  }

  /**
   *
   * Manager attempts to delete a participant with their email
   *
   * @param string the participant's email
   */
  @When("the manager attempts to delete the participant with email {string} \\(g1)")
  public void the_manager_attempts_to_delete_the_participant_with_email_g1(String string) {
    ParticipantController.deleteParticipant(string);
  }

  /**
   * @param email
   * @param password
   * @param name
   * @param emergencyContact
   * @param nrWeeks
   * @param weeksAvailableFrom
   * @param weeksAvailableUntil
   * @param lodgeRequired
   *                            Barry Zhang
   */
  @Then("a participant account shall not exist with email {string}, password {string}, name {string}, emergency contact {string}, number of weeks {string}, week available from {string}, week available until {string}, and lodge required {string} \\(g1)")
  public void a_participant_account_shall_not_exist_with_email_password_name_emergency_contact_number_of_weeks_week_available_from_week_available_until_and_lodge_required_g1(
      String email, String password, String name, String emergencyContact, String nrWeeks,
      String weeksAvailableFrom, String weeksAvailableUntil, String lodgeRequired) {
    for (Participant participant : sst.getParticipants()) {
      assertFalse(participant.getAccountName().equals(email) && participant.getPassword()
          .equals(password)
          && participant.getName()
              .equals(name)
          && participant.getEmergencyContact()
              .equals(emergencyContact)
          && participant.getNrWeeks() == Integer.parseInt(
              nrWeeks)
          && participant.getWeekAvailableFrom() == Integer.parseInt(
              weeksAvailableFrom)
          && participant.getWeekAvailableUntil() == Integer.parseInt(
              weeksAvailableUntil)
          && participant.getLodgeRequired() == Boolean.parseBoolean(
              lodgeRequired));
    }
  }

  /**   *
   *         Checking number of guides
   *
   * @param string number of managers
   */

  @Then("the number of guides shall be {string} \\(g1)")
  public void the_number_of_guides_shall_be_g1(String string) {
    int nrGuide = Integer.valueOf(string);
    assertEquals(nrGuide, sst.getGuides().size()); 
  }

  
  /**
     *         Checking that a manager exists with the given email
     *
     * @param string
     */
    @Then("a manager account shall exist with email {string} \\(g1)")
    public void a_manager_account_shall_exist_with_email_g1(String string) {
        Manager manager = sst.getManager();
        assertNotNull(manager); 
        User managerUser = (User) manager;
        assertEquals(string, managerUser.getAccountName()); 

  }


  /**  
   *         Checking number of managers
   *
   * @param string number of managers
   */
  @Then("the number of managers shall be {string} \\(g1)")
  public void the_number_of_managers_shall_be_g1(String string) {
    int nrManagers = 0; 
    if (sst.hasManager()) { 
      nrManagers++; 
    }

    assertEquals(Integer.valueOf(string), nrManagers); 

  }

  /**
   * @param numberOfParticipants
   *                             Barry Zhang
   */
  @Then("the number of participants shall be {string} \\(g1)")
  public void the_number_of_participants_shall_be_g1(String numberOfParticipants) {
    assertEquals(Integer.parseInt(numberOfParticipants), sst.numberOfParticipants());
  }

  /**
   * @param errorMsg
   *                 Barry Zhang
   */
  @Then("the system shall raise the error {string} \\(g1)")
  public void the_system_shall_raise_the_error_g1(String errorMsg) {
    assertTrue(message.contains(errorMsg));
  }

  /**
   * @param email
   * @param password
   * @param name
   * @param emergencyContact
   * @param nrWeeks
   * @param weeksAvailableFrom
   * @param weeksAvailableUntil
   * @param lodgeRequired
   *                            Barry Zhang
   */
  @Then("a participant account shall exist with email {string}, password {string}, name {string}, emergency contact {string}, number of weeks {string}, week available from {string}, week available until {string}, and lodge required {string} \\(g1)")
  public void a_participant_account_shall_exist_with_email_password_name_emergency_contact_number_of_weeks_week_available_from_week_available_until_and_lodge_required_g1(
      String email, String password, String name, String emergencyContact, String nrWeeks,
      String weeksAvailableFrom, String weeksAvailableUntil, String lodgeRequired) {
    User maybeParticipant = User.getWithAccountName(email);
    assertTrue(maybeParticipant instanceof Participant);
    Participant participant = (Participant) maybeParticipant;
    assertEquals(email, participant.getAccountName());
    assertEquals(password, participant.getPassword());
    assertEquals(name, participant.getName());
    assertEquals(emergencyContact, participant.getEmergencyContact());
    assertEquals(Integer.parseInt(nrWeeks), participant.getNrWeeks());
    assertEquals(Integer.parseInt(weeksAvailableFrom), participant.getWeekAvailableFrom());
    assertEquals(Integer.parseInt(weeksAvailableUntil), participant.getWeekAvailableUntil());
    assertEquals(Boolean.parseBoolean(lodgeRequired), participant.getLodgeRequired());
  }

  /**
   *
   * Checking that a participant account doesn't exist with the given
   * email
   *
   * @param string
   */
  @Then("a participant account shall not exist with email {string} \\(g1)")
  public void a_participant_account_shall_not_exist_with_email_g1(String string) {
    User existingUser = User.getWithAccountName(string);
    Boolean participantExistance = false;
    if (existingUser != null) {
      if (existingUser instanceof Participant) {
        participantExistance = true;
      }
    }
    assertFalse(participantExistance);

  }

  /**
   * this method asserts if the number of the number of booked items of type gear
   * is equal to the
   * given value in string 2
   *
   * @param string  - gear name
   * @param string2 - email
   */
  @Then("the piece of gear with name {string} shall be requested by {string} \\(g1)")
  public void the_piece_of_gear_with_name_shall_be_requested_by_g1(String string, String string2) {

    assertEquals(String.valueOf(BookableItem.getWithName(string).numberOfBookedItems()), string2);
  }

  /**
   *
   * this method asserts if the number of booked items of type combo is equal to
   * the given value in
   * string 2
   * 
   * @param string  - combo name
   * @param string2 - email
   */

  @Then("the combo with name {string} shall be requested by {string} \\(g1)")
  public void the_combo_with_name_shall_be_requested_by_g1(String string, String string2) {
    assertEquals(String.valueOf(BookableItem.getWithName(string).numberOfBookedItems()), string2);
  }

  /**
   *
   * Checking that a guide exists with the given email
   *
   * @param string
   */
  @Then("a guide account shall exist with email {string} \\(g1)")
  public void a_guide_account_shall_exist_with_email_g1(String string) {
    User existingUser = User.getWithAccountName(string);
    Boolean guideExistance = false;
    if (existingUser != null) {
      if (existingUser instanceof Guide) {
        guideExistance = true;
      }
    }
    assertTrue(guideExistance);
  }
}
