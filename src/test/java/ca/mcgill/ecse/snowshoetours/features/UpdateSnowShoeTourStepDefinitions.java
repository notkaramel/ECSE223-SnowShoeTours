package ca.mcgill.ecse.snowshoetours.features;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import ca.mcgill.ecse.snowshoetours.application.SnowShoeToursApplication;
import ca.mcgill.ecse.snowshoetours.controller.SnowShoeTourController;
import ca.mcgill.ecse.snowshoetours.model.SnowShoeTour;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class UpdateSnowShoeTourStepDefinitions {

  private String error;
  private SnowShoeTour sst;


  /**
   * This function sets up an instance of SnowShoeTour with values specified in the feature file
   * 
   * @param dataTable the data table from the feature file
   */
  @Given("the following SnowShoeTour system exists \\(g3)")
  public void the_following_snow_shoe_tour_system_exists_g3(
      io.cucumber.datatable.DataTable dataTable) {
    // clear errors
    error = "";

    // create instance of SnowShoeTour
    sst = SnowShoeToursApplication.getSnowShoeTour();

    // initialize variables that will be used for attributes (null s.t. an error is thrown if the
    // feature file is not giving values)
    String startDateString = null;
    String nrWeeksString = null;
    String priceOfGuidePerWeekString = null;

    // making a list of maps from the data table from the feature file
    List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

    for (Map<String, String> row : rows) { // this will only have one iteration as the feature file
                                           // only gives one row
      startDateString = row.get("startDate");
      nrWeeksString = row.get("nrWeeks");
      priceOfGuidePerWeekString = row.get("priceOfGuidePerWeek");
    }

    // set attributes
    // startDate
    Date startDate = Date.valueOf(startDateString);
    sst.setStartDate(startDate);

    // nrWeeks
    int nrWeeks = Integer.parseInt(nrWeeksString);
    sst.setNrWeeks(nrWeeks);

    // priceOfGuidePerWeek
    int priceOfGuidePerWeek = Integer.parseInt(priceOfGuidePerWeekString);
    sst.setPriceOfGuidePerWeek(priceOfGuidePerWeek);
  }

  /**
   * This function updates the instance of SnowShoeTour with variables from the feature file
   * 
   * @param string string from data table in feature file (Start date)
   * @param string2 string from data table in feature file (Number of weeks)
   * @param string3 string from data table in feature file (Price of guide per week)
   */
  @When("the manager attempts to update the SnowShoeTour information to start date {string}, number of weeks {string}, and price of guide per week {string} \\(g3)")
  public void the_manager_attempts_to_update_the_snow_shoe_tour_information_to_start_date_number_of_weeks_and_price_of_guide_per_week_g3(
      String string, String string2, String string3) {

    // strings into proper types to call controller
    Date startDate = Date.valueOf(string);
    int nrWeeks = Integer.parseInt(string2);
    int price = Integer.parseInt(string3);

    // call controller
    // (this is called on controller, not instance, but I am not sure how it knows that there is
    // only one instance of SnowShoeTour, maybe ask TA)
    // save error
    error = SnowShoeTourController.updateSnowShoeTour(startDate, nrWeeks, price);
  }

  /**
   * This funciton checks whether the updated variables of the SnowShoeTour instance are correct
   * 
   * @param string string from data table in feature file (Start date)
   * @param string2 string from data table in feature file (Number of weeks)
   * @param string3 string from data table in feature file (Price of guide per week)
   */
  @Then("the SnowShoeTour information shall be start date {string}, number of weeks {string}, and price of guide per week {string} \\(g3)")
  public void the_snow_shoe_tour_information_shall_be_start_date_number_of_weeks_and_price_of_guide_per_week_g3(
      String string, String string2, String string3) {

    // get attributes of instance
    Date instanceStartDate = sst.getStartDate();
    int instanceNrWeeks = sst.getNrWeeks();
    int instancePrice = sst.getPriceOfGuidePerWeek();

    // get desired attributes
    Date startDate = Date.valueOf(string);
    int nrWeeks = Integer.parseInt(string2);
    int price = Integer.parseInt(string3);

    // assert equalities and check for errors
    assertEquals(startDate, instanceStartDate);
    assertEquals(nrWeeks, instanceNrWeeks);
    assertEquals(price, instancePrice);
  }

  /**
   * Check if error risen by controller is the desired error
   * 
   * @param string Desired error message to be raised (from feature file)
   */
  @Then("the system shall raise the error {string} \\(g3)")
  public void the_system_shall_raise_the_error_g3(String string) {
    assertEquals(string, error);
  }
}
