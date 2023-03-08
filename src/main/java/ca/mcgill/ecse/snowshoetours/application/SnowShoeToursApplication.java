package ca.mcgill.ecse.snowshoetours.application;

import java.sql.Date;
import ca.mcgill.ecse.snowshoetours.model.SnowShoeTour;

public class SnowShoeToursApplication {

  private static SnowShoeTour snowShoeTour;

  public static void main(String[] args) {
    // TODO Start the application user interface here
  }

  public static SnowShoeTour getSnowShoeTour() {
    if (snowShoeTour == null) {
      // these attributes are default, you should set them later with the setters
      snowShoeTour = new SnowShoeTour(new Date(0), 0, 0);
    }
    return snowShoeTour;
  }

}
