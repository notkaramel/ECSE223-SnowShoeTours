package ca.mcgill.ecse.snowshoetours.persistence;

import java.sql.Date;

import ca.mcgill.ecse.snowshoetours.application.SnowShoeToursApplication;
import ca.mcgill.ecse.snowshoetours.controller.GearController;
import ca.mcgill.ecse.snowshoetours.controller.GuideController;
import ca.mcgill.ecse.snowshoetours.controller.ParticipantController;
import ca.mcgill.ecse.snowshoetours.controller.SnowShoeTourController;
import ca.mcgill.ecse.snowshoetours.model.SnowShoeTour;

public class SnowShoeTourPersistence {

    private static String filename = "data.json";
    private static JsonSerializer serializer = new JsonSerializer("ca.mcgill.ecse.snowshoetours");

    public static void setFilename(String filename) {
      SnowShoeTourPersistence.filename = filename;
    }

    public static void save() {
      save(SnowShoeToursApplication.getSnowShoeTour());
    }

    public static void save(SnowShoeTour snowShoeTour) {
      serializer.serialize(snowShoeTour, filename);
    }

    public static SnowShoeTour load() {
      var snowShoeTour = (SnowShoeTour) serializer.deserialize(filename);
      if (snowShoeTour == null) {
        snowShoeTour = new SnowShoeTour(null, 0, 0);
      } else {
        snowShoeTour.reinitialize();
      }
      return snowShoeTour;
    }
     public static void main(String[] args){

      /**TESTING, REMOVE THIS LATER **/ 

      SnowShoeTour testSST = SnowShoeToursApplication.getSnowShoeTour();
      Date date = new Date(1);
      SnowShoeTourController.updateSnowShoeTour(date,  10, 10);

      GearController.addGear("Boots", 10);
      GuideController.registerGuide("Sameer@mcgill.ca", "pass", "Sameer", "NA");
      ParticipantController.registerParticipant("Antoine@mcgill.ca", "pass","Antoine" , "(555)555-5555", 1, 1, 2, false);
      // System.out.println(testSST.getParticipants());
      
      testSST.delete();

      SnowShoeTour testSST2 = load();
    // System.out.println(testSST2.addGuide("Sameer@mcgill.ca", "123", "Sameer2", "Bla"));
     System.out.println(testSST2.getGuides());
     System.out.println(testSST2.getParticipants());
     }
}
