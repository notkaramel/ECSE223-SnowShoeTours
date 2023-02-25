/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

package ca.mcgill.ecse.snowshoetours.model;
import java.util.*;

// line 54 "../../../../../../SnowShoeTour.ump"
public class Guide extends Person
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Guide Attributes
  private int weeklyCost;

  //Guide Associations
  private List<Tour> tour;
  private SnowShoeTour snowShoeTour;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Guide(String aEmail, String aName, String aAccountName, String aPassword, String aEmergencyContact, SnowShoeTour aSnowShoeTour, int aWeeklyCost, SnowShoeTour aSnowShoeTour)
  {
    super(aEmail, aName, aAccountName, aPassword, aEmergencyContact, aSnowShoeTour);
    weeklyCost = aWeeklyCost;
    tour = new ArrayList<Tour>();
    boolean didAddSnowShoeTour = setSnowShoeTour(aSnowShoeTour);
    if (!didAddSnowShoeTour)
    {
      throw new RuntimeException("Unable to create guide due to snowShoeTour. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setWeeklyCost(int aWeeklyCost)
  {
    boolean wasSet = false;
    weeklyCost = aWeeklyCost;
    wasSet = true;
    return wasSet;
  }

  public int getWeeklyCost()
  {
    return weeklyCost;
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
  public SnowShoeTour getSnowShoeTour()
  {
    return snowShoeTour;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfTour()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Tour addTour(int aGuideCost, Lodge aLodge, Registration aRegistration, SnowShoeTour aSnowShoeTour, Week... allWeeks)
  {
    return new Tour(aGuideCost, aLodge, this, aRegistration, aSnowShoeTour, allWeeks);
  }

  public boolean addTour(Tour aTour)
  {
    boolean wasAdded = false;
    if (tour.contains(aTour)) { return false; }
    Guide existingGuide = aTour.getGuide();
    boolean isNewGuide = existingGuide != null && !this.equals(existingGuide);
    if (isNewGuide)
    {
      aTour.setGuide(this);
    }
    else
    {
      tour.add(aTour);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTour(Tour aTour)
  {
    boolean wasRemoved = false;
    //Unable to remove aTour, as it must always have a guide
    if (!this.equals(aTour.getGuide()))
    {
      tour.remove(aTour);
      wasRemoved = true;
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
      existingSnowShoeTour.removeGuide(this);
    }
    snowShoeTour.addGuide(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=tour.size(); i > 0; i--)
    {
      Tour aTour = tour.get(i - 1);
      aTour.delete();
    }
    SnowShoeTour placeholderSnowShoeTour = snowShoeTour;
    this.snowShoeTour = null;
    if(placeholderSnowShoeTour != null)
    {
      placeholderSnowShoeTour.removeGuide(this);
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "weeklyCost" + ":" + getWeeklyCost()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "snowShoeTour = "+(getSnowShoeTour()!=null?Integer.toHexString(System.identityHashCode(getSnowShoeTour())):"null");
  }
}