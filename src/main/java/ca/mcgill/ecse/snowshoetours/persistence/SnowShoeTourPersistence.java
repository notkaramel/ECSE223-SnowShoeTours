package ca.mcgill.ecse.snowshoetours.persistence;

import ca.mcgill.ecse.snowshoetours.application.SnowShoeToursApplication;
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

}
