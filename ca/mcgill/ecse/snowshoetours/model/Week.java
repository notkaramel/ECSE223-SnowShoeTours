/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

package ca.mcgill.ecse.snowshoetours.model;
import java.util.*;

// line 14 "../../../../../../SnowShoeTour.ump"
public class Week
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static int nextNumber = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Autounique Attributes

  /**
   * starting with 1
   */
  private int number;

  //Week Associations
  private List<Tour> tour;
  private Season season;
  private SnowShoeTour snowShoeTour;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Week(Season aSeason, SnowShoeTour aSnowShoeTour)
  {
    number = nextNumber++;
    tour = new ArrayList<Tour>();
    boolean didAddSeason = setSeason(aSeason);
    if (!didAddSeason)
    {
      throw new RuntimeException("Unable to create week due to season. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddSnowShoeTour = setSnowShoeTour(aSnowShoeTour);
    if (!didAddSnowShoeTour)
    {
      throw new RuntimeException("Unable to create week due to snowShoeTour. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  /**
   * starting with 1
   */
  public int getNumber()
  {
    return number;
  }
  /* Code from template association_GetMany */
  public Tour getTour(int index)
  {
    Tour aTour = tour.get(index);
    return aTour;
  }

  public List<Tour> getTour()
  {
    List<Tour> newTour = Collections.unmodifiableList(tour);
    return newTour;
  }

  public int numberOfTour()
  {
    int number = tour.size();
    return number;
  }

  public boolean hasTour()
  {
    boolean has = tour.size() > 0;
    return has;
  }

  public int indexOfTour(Tour aTour)
  {
    int index = tour.indexOf(aTour);
    return index;
  }
  /* Code from template association_GetOne */
  public Season getSeason()
  {
    return season;
  }
  /* Code from template association_GetOne */
  public SnowShoeTour getSnowShoeTour()
  {
    return snowShoeTour;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfTour()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addTour(Tour aTour)
  {
    boolean wasAdded = false;
    if (tour.contains(aTour)) { return false; }
    tour.add(aTour);
    if (aTour.indexOfWeek(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aTour.addWeek(this);
      if (!wasAdded)
      {
        tour.remove(aTour);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeTour(Tour aTour)
  {
    boolean wasRemoved = false;
    if (!tour.contains(aTour))
    {
      return wasRemoved;
    }

    int oldIndex = tour.indexOf(aTour);
    tour.remove(oldIndex);
    if (aTour.indexOfWeek(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aTour.removeWeek(this);
      if (!wasRemoved)
      {
        tour.add(oldIndex,aTour);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addTourAt(Tour aTour, int index)
  {  
    boolean wasAdded = false;
    if(addTour(aTour))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTour()) { index = numberOfTour() - 1; }
      tour.remove(aTour);
      tour.add(index, aTour);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTourAt(Tour aTour, int index)
  {
    boolean wasAdded = false;
    if(tour.contains(aTour))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTour()) { index = numberOfTour() - 1; }
      tour.remove(aTour);
      tour.add(index, aTour);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTourAt(aTour, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setSeason(Season aSeason)
  {
    boolean wasSet = false;
    if (aSeason == null)
    {
      return wasSet;
    }

    Season existingSeason = season;
    season = aSeason;
    if (existingSeason != null && !existingSeason.equals(aSeason))
    {
      existingSeason.removeWeek(this);
    }
    season.addWeek(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setSnowShoeTour(SnowShoeTour aSnowShoeTour)
  {
    boolean wasSet = false;
    if (aSnowShoeTour == null)
    {
      return wasSet;
    }

    SnowShoeTour existingSnowShoeTour = snowShoeTour;
    snowShoeTour = aSnowShoeTour;
    if (existingSnowShoeTour != null && !existingSnowShoeTour.equals(aSnowShoeTour))
    {
      existingSnowShoeTour.removeWeek(this);
    }
    snowShoeTour.addWeek(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ArrayList<Tour> copyOfTour = new ArrayList<Tour>(tour);
    tour.clear();
    for(Tour aTour : copyOfTour)
    {
      if (aTour.numberOfWeeks() <= Tour.minimumNumberOfWeeks())
      {
        aTour.delete();
      }
      else
      {
        aTour.removeWeek(this);
      }
    }
    Season placeholderSeason = season;
    this.season = null;
    if(placeholderSeason != null)
    {
      placeholderSeason.removeWeek(this);
    }
    SnowShoeTour placeholderSnowShoeTour = snowShoeTour;
    this.snowShoeTour = null;
    if(placeholderSnowShoeTour != null)
    {
      placeholderSnowShoeTour.removeWeek(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "number" + ":" + getNumber()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "season = "+(getSeason()!=null?Integer.toHexString(System.identityHashCode(getSeason())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "snowShoeTour = "+(getSnowShoeTour()!=null?Integer.toHexString(System.identityHashCode(getSnowShoeTour())):"null");
  }
}