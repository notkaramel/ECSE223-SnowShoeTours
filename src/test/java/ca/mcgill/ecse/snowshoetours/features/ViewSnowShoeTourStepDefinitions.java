package ca.mcgill.ecse.snowshoetours.features;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import ca.mcgill.ecse.snowshoetours.application.SnowShoeToursApplication;
import ca.mcgill.ecse.snowshoetours.controller.SnowShoeTourController;
import ca.mcgill.ecse.snowshoetours.controller.TOParticipantCost;
import ca.mcgill.ecse.snowshoetours.controller.TOSnowShoeTour;
import ca.mcgill.ecse.snowshoetours.model.BookableItem;
import ca.mcgill.ecse.snowshoetours.model.Combo;
import ca.mcgill.ecse.snowshoetours.model.Gear;
import ca.mcgill.ecse.snowshoetours.model.Guide;
import ca.mcgill.ecse.snowshoetours.model.Participant;
import ca.mcgill.ecse.snowshoetours.model.SnowShoeTour;
import ca.mcgill.ecse.snowshoetours.model.Tour;
import ca.mcgill.ecse.snowshoetours.model.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ViewSnowShoeTourStepDefinitions {
  private SnowShoeTour sst;
  private TOSnowShoeTour tour;

  /**
   * @param dataTable
   */
  @Given("the following SnowShoeTour system exists \\(g2)")
  public void the_following_snow_shoe_tour_system_exists_g2(
      io.cucumber.datatable.DataTable dataTable) {

    sst = SnowShoeToursApplication.getSnowShoeTour();

    List<Map<String, String>> rows = dataTable.asMaps();
    for (var row : rows) {
      Date startDate = Date.valueOf(row.get("startDate"));
      int nrWeeks = Integer.valueOf(row.get("nrWeeks"));
      int weeklyGuidePrice = Integer.valueOf(row.get("priceOfGuidePerWeek"));
      sst.setStartDate(startDate);
      sst.setNrWeeks(nrWeeks);
      sst.setPriceOfGuidePerWeek(weeklyGuidePrice);
    }
  }

  /**
   * @param dataTable
   */
  @Given("the following pieces of gear exist in the system \\(g2)")
  public void the_following_pieces_of_gear_exist_in_the_system_g2(
      io.cucumber.datatable.DataTable dataTable) {

    List<Map<String, String>> rows = dataTable.asMaps();
    for (var row : rows) {
      String gearName = row.get("name");
      int price = Integer.valueOf(row.get("pricePerWeek"));

      sst.addGear(gearName, price);
    }
  }

  /**
   * @param dataTable
   */
  @Given("the following combos exist in the system \\(g2)")
  public void the_following_combos_exist_in_the_system_g2(
      io.cucumber.datatable.DataTable dataTable) {

    List<Map<String, String>> rows = dataTable.asMaps();
    for (var row : rows) {

      String comboName = row.get("name");
      int discount = Integer.valueOf(row.get("discount"));

      List<String> items = Arrays.asList(row.get("items").split(","));

      List<String> tempQuantity = Arrays.asList(row.get("quantity").split(","));
      List<Integer> quantity = new ArrayList<Integer>();
      for (String q : tempQuantity) {
        quantity.add(Integer.valueOf(q));
      }

      Combo combo = sst.addCombo(comboName, discount);

      int i = 0;
      for (String item : items) {
        for (Gear existingGear : sst.getGear()) {
          if (existingGear.getName().equals(item)) {
            int gearQuantity = quantity.get(i);
            sst.addComboItem(gearQuantity, combo, existingGear);
            i++;

          }
        }
      }

    }
  }

  /**
   * @param dataTable
   */
  @Given("the following guides exist in the system \\(g2)")
  public void the_following_guides_exist_in_the_system_g2(
      io.cucumber.datatable.DataTable dataTable) {

    List<Map<String, String>> rows = dataTable.asMaps();
    for (var row : rows) {

      String email = row.get("email");
      String password = row.get("password");
      String name = row.get("name");
      String emergencyContact = row.get("emergencyContact");

      sst.addGuide(email, password, name, emergencyContact);
    }
  }

  /**
   * @param dataTable
   */
  @Given("the following participants exist in the system \\(g2)")
  public void the_following_participants_exist_in_the_system_g2(
      io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> rows = dataTable.asMaps();
    for (var row : rows) {
      String email = row.get("email");
      String password = row.get("password");
      String name = row.get("name");
      String emergencyContact = row.get("emergencyContact");
      int nrWeeks = Integer.parseInt(row.get("nrWeeks"));
      int weeksAvailableFrom = Integer.parseInt(row.get("weeksAvailableFrom"));
      int weeksAvailableUntil = Integer.parseInt(row.get("weeksAvailableUntil"));
      boolean lodgeRequired = Boolean.parseBoolean(row.get("lodgeRequired"));
      sst.addParticipant(email, password, name, emergencyContact, nrWeeks,
          weeksAvailableFrom, weeksAvailableUntil, lodgeRequired, "", 0);
    }
  }

  /**
   * @param dataTable
   */
  @Given("the following participants request the following pieces of gear \\(g2)")
  public void the_following_participants_request_the_following_pieces_of_gear_g2(
      io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> rows = dataTable.asMaps();
    for (var row : rows) {
      String email = row.get("email");
      Participant p = (Participant) Participant.getWithAccountName(email);

      String gearName = row.get("gear");
      BookableItem item = BookableItem.getWithName(gearName);

      int quantity = Integer.valueOf(row.get("quantity"));
      p.addBookedItem(quantity, sst, item);
    }
  }

  /**
   * @param dataTable
   */
  @Given("the following participants request the following combos \\(g2)")
  public void the_following_participants_request_the_following_combos_g2(
      io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> rows = dataTable.asMaps();
    for (var row : rows) {
      String email = row.get("email");
      String comboName = row.get("gear");
      int quantity = Integer.parseInt(row.get("quantity"));

      Participant participant = (Participant) User.getWithAccountName(email);
      Combo combo = (Combo) BookableItem.getWithName(comboName);
      participant.addBookedItem(quantity, sst, combo);
    }
  }

  /**
   * @param dataTable
   */
  @Given("the following snowshoe tours exist in the system \\(g2)")
  public void the_following_snow_shoe_tours_exist_in_the_system_g2(
      io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> rows = dataTable.asMaps();
    for (var row : rows) {
      int id = Integer.parseInt(row.get("id"));
      int startWeek = Integer.parseInt(row.get("startWeek"));
      int endWeek = Integer.parseInt(row.get("endWeek"));
      String[] participantEmails = row.get("participants").split(",");
      String guideEmail = row.get("guide");

      Guide guide = (Guide) User.getWithAccountName(guideEmail);
      Tour tour = sst.addTour(id, startWeek, endWeek, guide);

      for (String email : participantEmails) {
        Participant p = (Participant) User.getWithAccountName(email);
        tour.addParticipant(p);
      }
    }
  }

  /**
   * @param string
   */
  @When("the manager attempts to view the snowshoe tour with id {string} \\(g2)")
  public void the_manager_attempts_to_view_the_snowshoe_tour_with_id_g2(String string) {
    tour = SnowShoeTourController.getSnowShoeTour(Integer.parseInt(string));
  }

  /**
   * @param dataTable
   */
  @Then("the following snowshoe tour information shall be provided \\(g2)")
  public void the_following_snowshoe_tour_information_shall_be_provided_g2(
      io.cucumber.datatable.DataTable dataTable) {
    assertNotNull(tour); // tour with id should exist
    List<Map<String, String>> rows = dataTable.asMaps();
    for (var row : rows) {
      assertEquals(Integer.parseInt(row.get("id")), tour.getId());
      assertEquals(Integer.parseInt(row.get("startWeek")), tour.getStartWeek());
      assertEquals(Integer.parseInt(row.get("endWeek")), tour.getEndWeek());
      assertEquals(row.get("guideEmail"), tour.getGuideEmail());
      assertEquals(row.get("guideName"), tour.getGuideName());
      assertEquals(Integer.parseInt(row.get("totalCostForGuide")), tour.getTotalCostForGuide());

      // Check info for each participant in list, which is stored in participantCost
      // transfer
      // objects
      String[] givenParticipantEmails = row.get("participantsEmail").split(",");
      assertEquals(givenParticipantEmails.length, tour.numberOfParticipantCosts());

      List<TOParticipantCost> participantCosts = tour.getParticipantCosts();
      List<Integer> checkedIndices = new ArrayList<Integer>();
      for (int i = 0; i < participantCosts.size(); i++) {
        TOParticipantCost pc = participantCosts.get(i);
        // Need to use participant info corresponding to participantCost in list
        int index = Arrays.asList(givenParticipantEmails).indexOf(pc.getParticipantEmail());
        assertNotEquals(-1, index); // check user email exists in list
        assertFalse(checkedIndices.contains(index)); // check that this index hasn't been checked
        checkedIndices.add(index);

        assertEquals(row.get("participantsEmail").split(",")[index], pc.getParticipantEmail());
        assertEquals(row.get("participantsName").split(",")[index], pc.getParticipantName());
        assertEquals(Integer.parseInt(row.get("totalCostsForBookableItems").split(",")[index]),
            pc.getTotalCostForBookableItems());
        assertEquals(Integer.parseInt(row.get("totalCostsForSnowShoeTour").split(",")[index]),
            pc.getTotalCostForSnowShoeTour());
      }
    }
  }
}
