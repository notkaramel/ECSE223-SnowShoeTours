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
      demoReset();
    }
    if (snowShoeTour.getManager() == null) {
      snowShoeTour.setManager(new Manager("manager", "manager", snowShoeTour));
    }
    return snowShoeTour;
  }

  private static void demoReset() {
    snowShoeTour = new SnowShoeTour(new Date(0), 10, 0);
    snowShoeTour.addParticipant("emma@frosty.bite", "emma123", "Emma", "(438)333-2222", 3, 2, 6, true,
        "LovelyDay", 0);
    snowShoeTour.addParticipant("antoine@noeclipse.pls", "antoine123", "Antoine", "(438)333-2222", 3,
        2, 6, true, "EclipseBad", 0);
    snowShoeTour.addParticipant("sameer@vscode.love", "sameer123", "Sameer", "(438)333-2222", 3, 2,
        6, true, "VSCodeBest", 0);


    snowShoeTour.addGuide("bilar@creation.ctrl", "bmok", "Bilar", "(438)987-6543");
    snowShoeTour.addGuide("angela@eclipse.why", "angela", "Angela", "(438)987-6543");

    // snowShoeTour.addTour(1, 2, 3, snowShoeTour.getGuide(0));
    // snowShoeTour.addTour(2, 3, 4, snowShoeTour.getGuide(0));


    snowShoeTour.addGear("pole", 10);
    snowShoeTour.addGear("shoe", 10);

    snowShoeTour.addCombo("Bestie", 20);
    snowShoeTour.addCombo("Frosty", 30);

    if (snowShoeTour.getManager() == null) {
      snowShoeTour.setManager(new Manager("manager", "manager", snowShoeTour));
    }
    SnowShoeTourPersistence.save();
  }

}
