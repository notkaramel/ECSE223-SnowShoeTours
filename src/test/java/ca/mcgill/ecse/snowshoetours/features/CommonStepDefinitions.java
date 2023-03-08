package ca.mcgill.ecse.snowshoetours.features;

import ca.mcgill.ecse.snowshoetours.application.SnowShoeToursApplication;
import io.cucumber.java.After;

public class CommonStepDefinitions {
  /**
   * Method used to delete the current SnowShoeTour system instance before the next test. This is
   * effective for all scenarios in all feature files
   */
  @After
  public void tearDown() {
    SnowShoeToursApplication.getSnowShoeTour().delete();
  }
}
