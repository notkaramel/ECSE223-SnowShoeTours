package ca.mcgill.ecse.snowshoetours.application;

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
      snowShoeTour = SnowShoeTourPersistence.load();
    }
    if (snowShoeTour.getManager() == null) {
      snowShoeTour.setManager(new Manager("manager", "manager", snowShoeTour));
    }
    return snowShoeTour;
  }
}
