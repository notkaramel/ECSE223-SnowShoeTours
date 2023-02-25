/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

package ca.mcgill.ecse.snowshoetours.model;
import java.util.*;

// line 79 "../../../../../../SnowShoeTour.ump"
public class GearRequest
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum GearType { Boots, ShowShoe, Poles, Others }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //GearRequest Attributes
  private int cost;
  private GearType type;

  //GearRequest Associations
  private List<Combo> combos;
  private List<Registration> registration;
  private SnowShoeTour snowShoeTour;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public GearRequest(int aCost, GearType aType, SnowShoeTour aSnowShoeTour)
  {
    cost = aCost;
    type = aType;
    combos = new ArrayList<Combo>();
    registration = new ArrayList<Registration>();
    boolean didAddSnowShoeTour = setSnowShoeTour(aSnowShoeTour);
    if (!didAddSnowShoeTour)
    {
      throw new RuntimeException("Unable to create gearRequest due to snowShoeTour. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCost(int aCost)
  {
    boolean wasSet = false;
    cost = aCost;
    wasSet = true;
    return wasSet;
  }

  public boolean setType(GearType aType)
  {
    boolean wasSet = false;
    type = aType;
    wasSet = true;
    return wasSet;
  }

  public int getCost()
  {
    return cost;
  }

  public GearType getType()
  {
    return type;
  }
  /* Code from template association_GetMany */
  public Combo getCombo(int index)
  {
    Combo aCombo = combos.get(index);
    return aCombo;
  }

  public List<Combo> getCombos()
  {
    List<Combo> newCombos = Collections.unmodifiableList(combos);
    return newCombos;
  }

  public int numberOfCombos()
  {
    int number = combos.size();
    return number;
  }

  public boolean hasCombos()
  {
    boolean has = combos.size() > 0;
    return has;
  }

  public int indexOfCombo(Combo aCombo)
  {
    int index = combos.indexOf(aCombo);
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
  public SnowShoeTour getSnowShoeTour()
  {
    return snowShoeTour;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfCombos()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addCombo(Combo aCombo)
  {
    boolean wasAdded = false;
    if (combos.contains(aCombo)) { return false; }
    combos.add(aCombo);
    if (aCombo.indexOfGear(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aCombo.addGear(this);
      if (!wasAdded)
      {
        combos.remove(aCombo);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeCombo(Combo aCombo)
  {
    boolean wasRemoved = false;
    if (!combos.contains(aCombo))
    {
      return wasRemoved;
    }

    int oldIndex = combos.indexOf(aCombo);
    combos.remove(oldIndex);
    if (aCombo.indexOfGear(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aCombo.removeGear(this);
      if (!wasRemoved)
      {
        combos.add(oldIndex,aCombo);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addComboAt(Combo aCombo, int index)
  {  
    boolean wasAdded = false;
    if(addCombo(aCombo))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCombos()) { index = numberOfCombos() - 1; }
      combos.remove(aCombo);
      combos.add(index, aCombo);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveComboAt(Combo aCombo, int index)
  {
    boolean wasAdded = false;
    if(combos.contains(aCombo))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCombos()) { index = numberOfCombos() - 1; }
      combos.remove(aCombo);
      combos.add(index, aCombo);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addComboAt(aCombo, index);
    }
    return wasAdded;
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
    if (aRegistration.indexOfGear(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aRegistration.addGear(this);
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
    if (aRegistration.indexOfGear(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aRegistration.removeGear(this);
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
      existingSnowShoeTour.removeGearRequest(this);
    }
    snowShoeTour.addGearRequest(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ArrayList<Combo> copyOfCombos = new ArrayList<Combo>(combos);
    combos.clear();
    for(Combo aCombo : copyOfCombos)
    {
      if (aCombo.numberOfGears() <= Combo.minimumNumberOfGears())
      {
        aCombo.delete();
      }
      else
      {
        aCombo.removeGear(this);
      }
    }
    ArrayList<Registration> copyOfRegistration = new ArrayList<Registration>(registration);
    registration.clear();
    for(Registration aRegistration : copyOfRegistration)
    {
      aRegistration.removeGear(this);
    }
    SnowShoeTour placeholderSnowShoeTour = snowShoeTour;
    this.snowShoeTour = null;
    if(placeholderSnowShoeTour != null)
    {
      placeholderSnowShoeTour.removeGearRequest(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "cost" + ":" + getCost()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "type" + "=" + (getType() != null ? !getType().equals(this)  ? getType().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "snowShoeTour = "+(getSnowShoeTour()!=null?Integer.toHexString(System.identityHashCode(getSnowShoeTour())):"null");
  }
}