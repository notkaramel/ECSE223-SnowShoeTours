/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

package ca.mcgill.ecse.snowshoetours.model;
import java.util.*;

// line 50 "../../../../../../SnowShoeTour.ump"
public class Manager
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Manager Attributes
  private String password;

  //Manager Associations
  private List<Season> seasons;
  private List<Combo> combos;
  private SnowShoeTour snowShoeTour;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Manager(SnowShoeTour aSnowShoeTour)
  {
    resetPassword();
    seasons = new ArrayList<Season>();
    combos = new ArrayList<Combo>();
    boolean didAddSnowShoeTour = setSnowShoeTour(aSnowShoeTour);
    if (!didAddSnowShoeTour)
    {
      throw new RuntimeException("Unable to create manager due to snowShoeTour. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template attribute_SetDefaulted */
  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

  public boolean resetPassword()
  {
    boolean wasReset = false;
    password = getDefaultPassword();
    wasReset = true;
    return wasReset;
  }

  /**
   * default set to manager
   */
  public String getPassword()
  {
    return password;
  }
  /* Code from template attribute_GetDefaulted */
  public String getDefaultPassword()
  {
    return "manager";
  }
  /* Code from template association_GetMany */
  public Season getSeason(int index)
  {
    Season aSeason = seasons.get(index);
    return aSeason;
  }

  public List<Season> getSeasons()
  {
    List<Season> newSeasons = Collections.unmodifiableList(seasons);
    return newSeasons;
  }

  public int numberOfSeasons()
  {
    int number = seasons.size();
    return number;
  }

  public boolean hasSeasons()
  {
    boolean has = seasons.size() > 0;
    return has;
  }

  public int indexOfSeason(Season aSeason)
  {
    int index = seasons.indexOf(aSeason);
    return index;
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
  /* Code from template association_GetOne */
  public SnowShoeTour getSnowShoeTour()
  {
    return snowShoeTour;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfSeasons()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
  public boolean addSeason(Season aSeason)
  {
    boolean wasAdded = false;
    if (seasons.contains(aSeason)) { return false; }
    seasons.add(aSeason);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSeason(Season aSeason)
  {
    boolean wasRemoved = false;
    if (seasons.contains(aSeason))
    {
      seasons.remove(aSeason);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addSeasonAt(Season aSeason, int index)
  {  
    boolean wasAdded = false;
    if(addSeason(aSeason))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSeasons()) { index = numberOfSeasons() - 1; }
      seasons.remove(aSeason);
      seasons.add(index, aSeason);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSeasonAt(Season aSeason, int index)
  {
    boolean wasAdded = false;
    if(seasons.contains(aSeason))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSeasons()) { index = numberOfSeasons() - 1; }
      seasons.remove(aSeason);
      seasons.add(index, aSeason);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSeasonAt(aSeason, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfCombos()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
  public boolean addCombo(Combo aCombo)
  {
    boolean wasAdded = false;
    if (combos.contains(aCombo)) { return false; }
    combos.add(aCombo);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeCombo(Combo aCombo)
  {
    boolean wasRemoved = false;
    if (combos.contains(aCombo))
    {
      combos.remove(aCombo);
      wasRemoved = true;
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
      existingSnowShoeTour.removeManager(this);
    }
    snowShoeTour.addManager(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    seasons.clear();
    combos.clear();
    SnowShoeTour placeholderSnowShoeTour = snowShoeTour;
    this.snowShoeTour = null;
    if(placeholderSnowShoeTour != null)
    {
      placeholderSnowShoeTour.removeManager(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "password" + ":" + getPassword()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "snowShoeTour = "+(getSnowShoeTour()!=null?Integer.toHexString(System.identityHashCode(getSnowShoeTour())):"null");
  }
}