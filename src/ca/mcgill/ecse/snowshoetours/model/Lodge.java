/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

package ca.mcgill.ecse.snowshoetours.model;
import java.util.*;

// line 18 "../../../../../../SnowShoeTour.ump"
public class Lodge
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum LodgeClass { OneStar, TwoStars, ThreeStars, FourStars, FiveStars }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Lodge Attributes
  private String name;
  private String address;
  private LodgeClass lodgeClass;

  //Lodge Associations
  private LodgeRental lodgeRental;
  private List<Tour> tours;
  private List<Registration> registration;
  private Combo combo;
  private SnowShoeTour snowShoeTour;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Lodge(String aName, String aAddress, LodgeClass aLodgeClass, LodgeRental aLodgeRental, SnowShoeTour aSnowShoeTour)
  {
    name = aName;
    address = aAddress;
    lodgeClass = aLodgeClass;
    if (aLodgeRental == null || aLodgeRental.getLodge() != null)
    {
      throw new RuntimeException("Unable to create Lodge due to aLodgeRental. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    lodgeRental = aLodgeRental;
    tours = new ArrayList<Tour>();
    registration = new ArrayList<Registration>();
    boolean didAddSnowShoeTour = setSnowShoeTour(aSnowShoeTour);
    if (!didAddSnowShoeTour)
    {
      throw new RuntimeException("Unable to create lodge due to snowShoeTour. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  public Lodge(String aName, String aAddress, LodgeClass aLodgeClass, double aDiscountForLodgeRental, Combo aComboForLodgeRental, SnowShoeTour aSnowShoeTourForLodgeRental, SnowShoeTour aSnowShoeTour)
  {
    name = aName;
    address = aAddress;
    lodgeClass = aLodgeClass;
    lodgeRental = new LodgeRental(aDiscountForLodgeRental, this, aComboForLodgeRental, aSnowShoeTourForLodgeRental);
    tours = new ArrayList<Tour>();
    registration = new ArrayList<Registration>();
    boolean didAddSnowShoeTour = setSnowShoeTour(aSnowShoeTour);
    if (!didAddSnowShoeTour)
    {
      throw new RuntimeException("Unable to create lodge due to snowShoeTour. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setAddress(String aAddress)
  {
    boolean wasSet = false;
    address = aAddress;
    wasSet = true;
    return wasSet;
  }

  public boolean setLodgeClass(LodgeClass aLodgeClass)
  {
    boolean wasSet = false;
    lodgeClass = aLodgeClass;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public String getAddress()
  {
    return address;
  }

  public LodgeClass getLodgeClass()
  {
    return lodgeClass;
  }
  /* Code from template association_GetOne */
  public LodgeRental getLodgeRental()
  {
    return lodgeRental;
  }
  /* Code from template association_GetMany */
  public Tour getTour(int index)
  {
    Tour aTour = tours.get(index);
    return aTour;
  }

  public List<Tour> getTours()
  {
    List<Tour> newTours = Collections.unmodifiableList(tours);
    return newTours;
  }

  public int numberOfTours()
  {
    int number = tours.size();
    return number;
  }

  public boolean hasTours()
  {
    boolean has = tours.size() > 0;
    return has;
  }

  public int indexOfTour(Tour aTour)
  {
    int index = tours.indexOf(aTour);
    return index;
  }
  /* Code from template association_GetMany */
  public Registration getRegistration(int index)
  {
    Registration aRegistration = registration.get(index);
    return aRegistration;
  }

  public List<Registration> getRegistration()
  {
    List<Registration> newRegistration = Collections.unmodifiableList(registration);
    return newRegistration;
  }

  public int numberOfRegistration()
  {
    int number = registration.size();
    return number;
  }

  public boolean hasRegistration()
  {
    boolean has = registration.size() > 0;
    return has;
  }

  public int indexOfRegistration(Registration aRegistration)
  {
    int index = registration.indexOf(aRegistration);
    return index;
  }
  /* Code from template association_GetOne */
  public Combo getCombo()
  {
    return combo;
  }

  public boolean hasCombo()
  {
    boolean has = combo != null;
    return has;
  }
  /* Code from template association_GetOne */
  public SnowShoeTour getSnowShoeTour()
  {
    return snowShoeTour;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfTours()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Tour addTour(int aGuideCost, Guide aGuide, Registration aRegistration, SnowShoeTour aSnowShoeTour, Week... allWeeks)
  {
    return new Tour(aGuideCost, this, aGuide, aRegistration, aSnowShoeTour, allWeeks);
  }

  public boolean addTour(Tour aTour)
  {
    boolean wasAdded = false;
    if (tours.contains(aTour)) { return false; }
    Lodge existingLodge = aTour.getLodge();
    boolean isNewLodge = existingLodge != null && !this.equals(existingLodge);
    if (isNewLodge)
    {
      aTour.setLodge(this);
    }
    else
    {
      tours.add(aTour);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTour(Tour aTour)
  {
    boolean wasRemoved = false;
    //Unable to remove aTour, as it must always have a lodge
    if (!this.equals(aTour.getLodge()))
    {
      tours.remove(aTour);
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
      if(index > numberOfTours()) { index = numberOfTours() - 1; }
      tours.remove(aTour);
      tours.add(index, aTour);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTourAt(Tour aTour, int index)
  {
    boolean wasAdded = false;
    if(tours.contains(aTour))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTours()) { index = numberOfTours() - 1; }
      tours.remove(aTour);
      tours.add(index, aTour);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTourAt(aTour, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfRegistration()
  {
    return 0;
  }
  /* Code from template association_AddManyToOptionalOne */
  public boolean addRegistration(Registration aRegistration)
  {
    boolean wasAdded = false;
    if (registration.contains(aRegistration)) { return false; }
    Lodge existingLodge = aRegistration.getLodge();
    if (existingLodge == null)
    {
      aRegistration.setLodge(this);
    }
    else if (!this.equals(existingLodge))
    {
      existingLodge.removeRegistration(aRegistration);
      addRegistration(aRegistration);
    }
    else
    {
      registration.add(aRegistration);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeRegistration(Registration aRegistration)
  {
    boolean wasRemoved = false;
    if (registration.contains(aRegistration))
    {
      registration.remove(aRegistration);
      aRegistration.setLodge(null);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addRegistrationAt(Registration aRegistration, int index)
  {  
    boolean wasAdded = false;
    if(addRegistration(aRegistration))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRegistration()) { index = numberOfRegistration() - 1; }
      registration.remove(aRegistration);
      registration.add(index, aRegistration);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveRegistrationAt(Registration aRegistration, int index)
  {
    boolean wasAdded = false;
    if(registration.contains(aRegistration))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRegistration()) { index = numberOfRegistration() - 1; }
      registration.remove(aRegistration);
      registration.add(index, aRegistration);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addRegistrationAt(aRegistration, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOptionalOneToOptionalOne */
  public boolean setCombo(Combo aNewCombo)
  {
    boolean wasSet = false;
    if (aNewCombo == null)
    {
      Combo existingCombo = combo;
      combo = null;
      
      if (existingCombo != null && existingCombo.getLodge() != null)
      {
        existingCombo.setLodge(null);
      }
      wasSet = true;
      return wasSet;
    }

    Combo currentCombo = getCombo();
    if (currentCombo != null && !currentCombo.equals(aNewCombo))
    {
      currentCombo.setLodge(null);
    }

    combo = aNewCombo;
    Lodge existingLodge = aNewCombo.getLodge();

    if (!equals(existingLodge))
    {
      aNewCombo.setLodge(this);
    }
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
      existingSnowShoeTour.removeLodge(this);
    }
    snowShoeTour.addLodge(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    LodgeRental existingLodgeRental = lodgeRental;
    lodgeRental = null;
    if (existingLodgeRental != null)
    {
      existingLodgeRental.delete();
    }
    for(int i=tours.size(); i > 0; i--)
    {
      Tour aTour = tours.get(i - 1);
      aTour.delete();
    }
    while( !registration.isEmpty() )
    {
      registration.get(0).setLodge(null);
    }
    if (combo != null)
    {
      combo.setLodge(null);
    }
    SnowShoeTour placeholderSnowShoeTour = snowShoeTour;
    this.snowShoeTour = null;
    if(placeholderSnowShoeTour != null)
    {
      placeholderSnowShoeTour.removeLodge(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "address" + ":" + getAddress()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "lodgeClass" + "=" + (getLodgeClass() != null ? !getLodgeClass().equals(this)  ? getLodgeClass().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "lodgeRental = "+(getLodgeRental()!=null?Integer.toHexString(System.identityHashCode(getLodgeRental())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "combo = "+(getCombo()!=null?Integer.toHexString(System.identityHashCode(getCombo())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "snowShoeTour = "+(getSnowShoeTour()!=null?Integer.toHexString(System.identityHashCode(getSnowShoeTour())):"null");
  }
}