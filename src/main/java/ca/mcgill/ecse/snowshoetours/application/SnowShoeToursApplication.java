package ca.mcgill.ecse.snowshoetours.application;

import java.sql.Date;
import ca.mcgill.ecse.snowshoetours.javafx.fxml.MainPageView;
import ca.mcgill.ecse.snowshoetours.model.Manager;
import ca.mcgill.ecse.snowshoetours.model.SnowShoeTour;
import ca.mcgill.ecse.snowshoetours.persistence.SnowShoeTourPersistence;
import javafx.application.Application;

public class SnowShoeToursApplication {

  private static SnowShoeTour snowShoeTour; // = SnowShoeTourPersistence.load();;

  public static void main(String[] args) {
    snowShoeTour = getSnowShoeTour();
    Application.launch(MainPageView.class, args);
  }

  public static SnowShoeTour getSnowShoeTour() {
    if (snowShoeTour == null) {
      // snowShoeTour = SnowShoeTourPersistence.load();
      // these attributes are default, you should set them later with the setters
      snowShoeTour = new SnowShoeTour(new Date(0), 5, 0);
    }
    if (snowShoeTour.getManager() == null){
      snowShoeTour.setManager(new Manager("manager", "manager", snowShoeTour));
    }
    return snowShoeTour;
  }

}
