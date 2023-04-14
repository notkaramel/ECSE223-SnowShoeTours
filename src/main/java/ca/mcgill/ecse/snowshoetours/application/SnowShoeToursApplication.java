package ca.mcgill.ecse.snowshoetours.application;

import java.sql.Date;
import ca.mcgill.ecse.snowshoetours.javafx.fxml.MainPageView;
import ca.mcgill.ecse.snowshoetours.model.Manager;
import ca.mcgill.ecse.snowshoetours.model.SnowShoeTour;
import ca.mcgill.ecse.snowshoetours.model.Lodge.LodgeRating;
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
      // reset();
    }
    if (snowShoeTour.getManager() == null) {
      snowShoeTour.setManager(new Manager("manager", "manager", snowShoeTour));
    }
    return snowShoeTour;
  }

  // shouldn't be here, try put it in before the stepDef to avoid the errors
  private static void reset(){
    snowShoeTour = new SnowShoeTour(new Date(0), 10, 0);
    SnowShoeTourPersistence.save();
  }
  private static void demoReset() {
    snowShoeTour = new SnowShoeTour(new Date(0), 10, 0);
    snowShoeTour.addParticipant("emma_1@frosty.bite", "emma123", "Emma", "(438)333-2222", 3, 1, 6,
        true, "LovelyDay", 0);
    snowShoeTour.addParticipant("antoine_2@noeclipse.pls", "antoine123", "Antoine", "(438)333-2222",
        2, 2, 6, true, "EclipseBad", 0);
    snowShoeTour.addParticipant("jennifer_4@hybrid.ide", "jennifer123", "Jennifer", "(438)333-2222",
    3, 4, 6, true, "", 0);
    snowShoeTour.addParticipant("sameer_6@vscode.love", "", "Sameer", "(438)333-2222", 3, 6, 8, true,
    "", 0);

    snowShoeTour.addGuide("bilar@creation.ctrl", "bmok", "Bilar", "(438)987-6543");
    snowShoeTour.addGuide("angela@whyeclipse.why", "angela", "Angela", "(438)987-6543");

    snowShoeTour.addLodge("Jennifer Inn", "Trottier Basement", LodgeRating.FiveStars);


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
