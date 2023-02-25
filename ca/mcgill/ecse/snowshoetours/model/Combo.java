/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

package ca.mcgill.ecse.snowshoetours.model;
import java.util.*;

// line 61 "../../../../../../SnowShoeTour.ump"
public class Combo
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Combo Attributes
  private int comboPrice;
  private String comboName;

  //Combo Associations
  private LodgeRental lodgeRental;
  private List<GearRequest> gears;
  private Lodge lodge;
  private List<Registration> registration;
  private SnowShoeTour snowShoeTour;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Combo(int aComboPrice, String aComboName, LodgeRental aLodgeRental, SnowShoeTour aSnowShoeTour, GearRequest... allGears)
  {
    comboPrice = aComboPrice;
    comboName = aComboName;
    if (aLodgeRental == null || aLodgeRental.getCombo() != null)
    {
      throw new RuntimeException("Unable to create Combo due to aLodgeRental. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    lodgeRental = aLodgeRental;
    gears = new ArrayList<GearRequest>();
    boolean didAddGears = setGears(allGears);
    if (!didAddGears)
    {
      throw new RuntimeException("Unable to create Combo, must have at least 2 gears. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    registration = new ArrayList<Registration>();
    boolean didAddSnowShoeTour = setSnowShoeTour(aSnowShoeTour);
    if (!didAddSnowShoeTour)
    {
      throw new RuntimeException("Unable to create combo due to snowShoeTour. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  public Combo(int aComboPrice, String aComboName, double aDiscountForLodgeRental, Lodge aLodgeForLodgeRental, SnowShoeTour aSnowShoeTourForLodgeRental, SnowShoeTour aSnowShoeTour, GearRequest... allGears)
  {
    comboPrice = aComboPrice;
    comboName = aComboName;
    lodgeRental = new LodgeRental(aDiscountForLodgeRental, aLodgeForLodgeRental, this, aSnowShoeTourForLodgeRental);
    gears = new ArrayList<GearRequest>();
    boolean didAddGears = setGears(allGears);
    if (!didAddGears)
    {
      throw new RuntimeException("Unable to create Combo, must have at least 2 gears. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    registration = new ArrayList<Registration>();
    boolean didAddSnowShoeTour = setSnowShoeTour(aSnowShoeTour);
    if (!didAddSnowShoeTour)
    {
      throw new RuntimeException("Unable to create combo due to snowShoeTour. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setComboPrice(int aComboPrice)
  {
    boolean wasSet = false;
    comboPrice = aComboPrice;
    wasSet = true;
    return wasSet;
  }

  public boolean setComboName(String aComboName)
  {
    boolean wasSet = false;
    comboName = aComboName;
    wasSet = true;
    return wasSet;
  }

  public int getComboPrice()
  {
    return comboPrice;
  }

  public String getComboName()
  {
    return comboName;
  }
  /* Code from template association_GetOne */
  public LodgeRental getLodgeRental()
  {
    return lodgeRental;
  }
  /* Code from template association_GetMany */
  public GearRequest getGear(int index)
  {
    GearRequest aGear = gears.get(index);
    return aGear;
  }

  public List<GearRequest> getGears()
  {
    List<GearRequest> newGears = Collections.unmodifiableList(gears);
    return newGears;
  }

  public int numberOfGears()
  {
    int number = gears.size();
    return number;
  }

  public boolean hasGears()
  {
    boolean has = gears.size() > 0;
    return has;
  }

  public int indexOfGear(GearRequest aGear)
  {
    int index = gears.indexOf(aGear);
    return index;
  }
  /* Code from template association_GetOne */
  public Lodge getLodge()
  {
    return lodge;
  }

  public boolean hasLodge()
  {
    boolean has = lodge != null;
    return has;
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
  public SnowShoeTour getSnowShoeTour()
  {
    return snowShoeTour;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfGearsValid()
  {
    boolean isValid = numberOfGears() >= minimumNumberOfGears();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfGears()
  {
    return 2;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addGear(GearRequest aGear)
  {
    boolean wasAdded = false;
    if (gears.contains(aGear)) { return false; }
    gears.add(aGear);
    if (aGear.indexOfCombo(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aGear.addCombo(this);
      if (!wasAdded)
      {
        gears.remove(aGear);
      }
    }
    return wasAdded;
  }
  /* Code from template association_AddMStarToMany */
  public boolean removeGear(GearRequest aGear)
  {
    boolean wasRemoved = false;
    if (!gears.contains(aGear))
    {
      return wasRemoved;
    }

    if (numberOfGears() <= minimumNumberOfGears())
    {
      return wasRemoved;
    }

    int oldIndex = gears.indexOf(aGear);
    gears.remove(oldIndex);
    if (aGear.indexOfCombo(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aGear.removeCombo(this);
      if (!wasRemoved)
      {
        gears.add(oldIndex,aGear);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_SetMStarToMany */
  public boolean setGears(GearRequest... newGears)
  {
    boolean wasSet = false;
    ArrayList<GearRequest> verifiedGears = new ArrayList<GearRequest>();
    for (GearRequest aGear : newGears)
    {
      if (verifiedGears.contains(aGear))
      {
        continue;
      }
      verifiedGears.add(aGear);
    }

    if (verifiedGears.size() != newGears.length || verifiedGears.size() < minimumNumberOfGears())
    {
      return wasSet;
    }

    ArrayList<GearRequest> oldGears = new ArrayList<GearRequest>(gears);
    gears.clear();
    for (GearRequest aNewGear : verifiedGears)
    {
      gears.add(aNewGear);
      if (oldGears.contains(aNewGear))
      {
        oldGears.remove(aNewGear);
      }
      else
      {
        aNewGear.addCombo(this);
      }
    }

    for (GearRequest anOldGear : oldGears)
    {
      anOldGear.removeCombo(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addGearAt(GearRequest aGear, int index)
  {  
    boolean wasAdded = false;
    if(addGear(aGear))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGears()) { index = numberOfGears() - 1; }
      gears.remove(aGear);
      gears.add(index, aGear);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveGearAt(GearRequest aGear, int index)
  {
    boolean wasAdded = false;
    if(gears.contains(aGear))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGears()) { index = numberOfGears() - 1; }
      gears.remove(aGear);
      gears.add(index, aGear);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addGearAt(aGear, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOptionalOneToOptionalOne */
  public boolean setLodge(Lodge aNewLodge)
  {
    boolean wasSet = false;
    if (aNewLodge == null)
    {
      Lodge existingLodge = lodge;
      lodge = null;
      
      if (existingLodge != null && existingLodge.getCombo() != null)
      {
        existingLodge.setCombo(null);
      }
      wasSet = true;
      return wasSet;
    }

    Lodge currentLodge = getLodge();
    if (currentLodge != null && !currentLodge.equals(aNewLodge))
    {
      currentLodge.setCombo(null);
    }

    lodge = aNewLodge;
    Combo existingCombo = aNewLodge.getCombo();

    if (!equals(existingCombo))
    {
      aNewLodge.setCombo(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfRegistration()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addRegistration(Registration aRegistration)
  {
    boolean wasAdded = false;
    if (registration.contains(aRegistration)) { return false; }
    registration.add(aRegistration);
    if (aRegistration.indexOfCombo(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aRegistration.addCombo(this);
      if (!wasAdded)
      {
        registration.remove(aRegistration);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeRegistration(Registration aRegistration)
  {
    boolean wasRemoved = false;
    if (!registration.contains(aRegistration))
    {
      return wasRemoved;
    }

    int oldIndex = registration.indexOf(aRegistration);
    registration.remove(oldIndex);
    if (aRegistration.indexOfCombo(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aRegistration.removeCombo(this);
      if (!wasRemoved)
      {
        registration.add(oldIndex,aRegistration);
      }
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
      existingSnowShoeTour.removeCombo(this);
    }
    snowShoeTour.addCombo(this);
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
    ArrayList<GearRequest> copyOfGears = new ArrayList<GearRequest>(gears);
    gears.clear();
    for(GearRequest aGear : copyOfGears)
    {
      aGear.removeCombo(this);
    }
    if (lodge != null)
    {
      lodge.setCombo(null);
    }
    ArrayList<Registration> copyOfRegistration = new ArrayList<Registration>(registration);
    registration.clear();
    for(Registration aRegistration : copyOfRegistration)
    {
      aRegistration.removeCombo(this);
    }
    SnowShoeTour placeholderSnowShoeTour = snowShoeTour;
    this.snowShoeTour = null;
    if(placeholderSnowShoeTour != null)
    {
      placeholderSnowShoeTour.removeCombo(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "comboPrice" + ":" + getComboPrice()+ "," +
            "comboName" + ":" + getComboName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "lodgeRental = "+(getLodgeRental()!=null?Integer.toHexString(System.identityHashCode(getLodgeRental())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "lodge = "+(getLodge()!=null?Integer.toHexString(System.identityHashCode(getLodge())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "snowShoeTour = "+(getSnowShoeTour()!=null?Integer.toHexString(System.identityHashCode(getSnowShoeTour())):"null");
  }
}