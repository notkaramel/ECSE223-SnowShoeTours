package ca.mcgill.ecse.snowshoetours.features;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import ca.mcgill.ecse.snowshoetours.application.SnowShoeToursApplication;
import ca.mcgill.ecse.snowshoetours.controller.GuideController;
import ca.mcgill.ecse.snowshoetours.model.Guide;
import ca.mcgill.ecse.snowshoetours.model.Manager;
import ca.mcgill.ecse.snowshoetours.model.Participant;
import ca.mcgill.ecse.snowshoetours.model.SnowShoeTour;
import ca.mcgill.ecse.snowshoetours.model.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RegisterAndDeleteGuideStepDefinitions {
  private SnowShoeTour sst;
  private String error;
  private int errorCount;

  /**
   */
  @Given("the following participants exist in the system \\(g6)")
  public void the_following_participants_exist_in_the_system_g6(
      io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> rows = dataTable.asMaps();
    for (var row : rows) { // for each row, parse the information to create a guide within the
                           // system
      sst.addParticipant(row.get("email"), row.get("password"), row.get("name"),
          row.get("emergencyContact"), Integer.parseInt(row.get("nrWeeks")),
          Integer.parseInt(row.get("weeksAvailableFrom")),
          Integer.parseInt(row.get("weeksAvailableUntil")),
          Boolean.parseBoolean(row.get("lodgeRequired")), null, 0);
    }
  }

  /**
   */
  @Given("the following SnowShoeTour system exists \\(g6)")
  public void the_following_snow_shoe_tour_system_exists_g6(
      io.cucumber.datatable.DataTable dataTable) {
    Map<String, String> data = dataTable.asMaps().get(0);
    sst = SnowShoeToursApplication.getSnowShoeTour();
    sst.setStartDate(Date.valueOf(data.get("startDate")));
    sst.setNrWeeks(Integer.parseInt(data.get("nrWeeks")));
    sst.setPriceOfGuidePerWeek(Integer.parseInt(data.get("priceOfGuidePerWeek")));
    error = "";
    errorCount = 0;
  }

  /**
   */
  @Given("the following guides exist in the system \\(g6)")
  public void the_following_guides_exist_in_the_system_g6(
      io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> rows = dataTable.asMaps();
    for (var row : rows) {
      sst.addGuide(row.get("email"), row.get("password"), row.get("name"),
          row.get("emergencyContact"));
    }
  }

  /**
   */
  @When("a new guide attempts to register with email {string}, password {string}, name {string}, and emergency contact {string} \\(g6)")
  public void a_new_guide_attempts_to_register_with_email_password_name_and_emergency_contact_g6(
      String email, String password, String name, String emergencyContact) {
    callController(GuideController.registerGuide(email, password, name, emergencyContact));
  }

  /**
   *
   *         Manager attempts to delete a guide with their email
   *
   * @param string the guide's email
   */
  @When("the manager attempts to delete the guide with email {string} \\(g6)")
  public void the_manager_attempts_to_delete_the_guide_with_email_g6(String string) {
    GuideController.deleteGuide(string); // deleting the guide
  }

  /**
   */
  @Then("a guide account shall not exist with email {string}, password {string}, name {string}, and emergency contact {string} \\(g6)")
  public void a_guide_account_shall_not_exist_with_email_password_name_and_emergency_contact_g6(
      String email, String password, String name, String emergencyContact) {
    User user = User.getWithAccountName(email); 
    Guide guide = user instanceof Guide ? ((Guide) user) : null; 
    if (guide != null) { 
      boolean atLeastOneGuideAttributeInvalid = (!(password.equals(guide.getPassword()))
          || !(name.equals(guide.getName()))
          || !(emergencyContact.equals(guide.getEmergencyContact())));
      assertTrue(atLeastOneGuideAttributeInvalid);
    }
  }

  /**
   */
  @Then("the number of guides shall be {string} \\(g6)")
  public void the_number_of_guides_shall_be_g6(String string) {
    assertEquals(Integer.parseInt(string), sst.getGuides().size());
  }

  /**
   */
  @Then("the system shall raise the error {string} \\(g6)")
  public void the_system_shall_raise_the_error_g6(String error) {
    assertEquals(error, this.error);
    assertNotEquals(0, this.errorCount);
  }

  /**
   */
  @Then("a guide account shall exist with email {string}, password {string}, name {string}, and emergency contact {string} \\(g6)")
  public void a_guide_account_shall_exist_with_email_password_name_and_emergency_contact_g6(
      String email, String password, String name, String emergencyContact) {
    User user = User.getWithAccountName(email); // try to find user with given email
    Guide guide = user instanceof Guide ? ((Guide) user) : null; 
    // assert that guide exists
    assertNotNull(guide);
    // assert that all information corresponds to the inputs
    assertEquals(email, guide.getAccountName());
    assertEquals(password, guide.getPassword());
    assertEquals(name, guide.getName());
    assertEquals(emergencyContact, guide.getEmergencyContact());
  }

  /**
   * This method counts and registers the errors Taken from btms example
   * 
   */
  private void callController(String result) {
    if (!result.isEmpty()) {
      error += result;
      errorCount += 1;
    }
  }

  /**
   *
   *         Checking that a manager exists with the given email
   *
   * @param string
   */
  @Then("a manager account shall exist with email {string} \\(g6)")
  public void a_manager_account_shall_exist_with_email_g6(String string) {
    Manager manager = sst.getManager();
    assertNotNull(manager); // making sure that the manager exists
    User managerUser = (User) manager;
    assertEquals(string, managerUser.getAccountName()); // making sure the manager has the email {string}

  }

  /**
   *
   *         Checking number of managers
   *
   * @param string number of managers
   */
  @Then("the number of managers shall be {string} \\(g6)")
  public void the_number_of_managers_shall_be_g6(String string) {
    // Write code here that turns the phrase above into concrete actions
    int nrManagers = 0; // initialize the number of manager to 0
    if (sst.hasManager()) { // if hasManager is true, there is 1 manager in the system
      nrManagers++; // the number of manager become 1
    }

    assertEquals(Integer.valueOf(string), nrManagers);
  }

  /**
   *
   *         Check if a participant account exists with the email given
   *
   * @param string, the email of the participant
   */
  @Then("a participant account shall exist with email {string} \\(g6)")
  public void a_participant_account_shall_exist_with_email_g6(String string) {
    User existingUser = User.getWithAccountName(string);
    Boolean participantExistance = false;
    if (existingUser != null) {
      if (existingUser instanceof Participant) {
        participantExistance = true;
      }
    }
    assertTrue(participantExistance);

  }

  /**
   *
   *         Checking number of participants
   *
   * @param string number of participants
   */
  @Then("the number of participants shall be {string} \\(g6)")
  public void the_number_of_participants_shall_be_g6(String string) {
    // Write code here that turns the phrase above into concrete actions
    int nrParticipants = Integer.valueOf(string); 
    assertEquals(nrParticipants, sst.getParticipants().size()); 

  }

  /**
   *
   *         Checking that a guide doesn't exist with the given email
   *
   * @param string
   */
  @Then("a guide account shall not exist with email {string} \\(g6)")
  public void a_guide_account_shall_not_exist_with_email_g6(String string) {
    User existingUser = User.getWithAccountName(string); 
    Boolean guideExistance = false; 
    if (existingUser != null) { 
      if (existingUser instanceof Guide) { 
        guideExistance = true; 
      }
    }
    assertFalse(guideExistance); 
  }
}
