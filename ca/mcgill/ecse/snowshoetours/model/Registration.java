/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

package ca.mcgill.ecse.snowshoetours.model;
import java.util.*;

// line 72 "../../../../../../SnowShoeTour.ump"
public class Registration
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, Registration> registrationsByAuthCode = new HashMap<String, Registration>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Registration Attributes
  private int tourCost;
  private String authCode;
  private double refundRate;
  private boolean lodgeRented;

  //Registration Associations
  private Tour tour;
  private Lodge lodge;
  private Participant participant;
  private List<Combo> combo;
  private List<GearRequest> gears;
  private SnowShoeTour snowShoeTour;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Registration(int aTourCost, String aAuthCode, double aRefundRate, boolean aLodgeRented, Tour aTour, Participant aParticipant, SnowShoeTour aSnowShoeTour)
  {
    tourCost = aTourCost;
    refundRate = aRefundRate;
    lodgeRented = aLodgeRented;
    if (!setAuthCode(aAuthCode))
    {
      throw new RuntimeException("Cannot create due to duplicate authCode. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    if (aTour == null || aTour.getRegistration() != null)
    {
      throw new RuntimeException("Unable to create Registration due to aTour. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    tour = aTour;
    if (aParticipant == null || aParticipant.getRegistration() != null)
    {
      throw new RuntimeException("Unable to create Registration due to aParticipant. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    participant = aParticipant;
    combo = new ArrayList<Combo>();
    gears = new ArrayList<GearRequest>();
    boolean didAddSnowShoeTour = setSnowShoeTour(aSnowShoeTour);
    if (!didAddSnowShoeTour)
    {
      throw new RuntimeException("Unable to create registration due to snowShoeTour. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  public Registration(int aTourCost, String aAuthCode, double aRefundRate, boolean aLodgeRented, int aGuideCostForTour, Lodge aLodgeForTour, Guide aGuideForTour, SnowShoeTour aSnowShoeTourForTour, Week... allWeeksForTour, String aEmailForParticipant, String aNameForParticipant, String aAccountNameForParticipant, String aPasswordForParticipant, String aEmergencyContactForParticipant, SnowShoeTour aSnowShoeTourForParticipant, int aNumberOfWeeksForParticipant, Week aStartWeekForParticipant, Week aEndWeekForParticipant, Tour aTourForParticipant, SnowShoeTour aSnowShoeTourForParticipant, SnowShoeTour aSnowShoeTour)
  {
    tourCost = aTourCost;
    if (!setAuthCode(aAuthCode))
    {
      throw new RuntimeException("Cannot create due to duplicate authCode. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    refundRate = aRefundRate;
    lodgeRented = aLodgeRented;
    tour = new Tour(aGuideCostForTour, aLodgeForTour, aGuideForTour, this, aSnowShoeTourForTour, allWeeksForTour);
    participant = new Participant(aEmailForParticipant, aNameForParticipant, aAccountNameForParticipant, aPasswordForParticipant, aEmergencyContactForParticipant, aSnowShoeTourForParticipant, aNumberOfWeeksForParticipant, aStartWeekForParticipant, aEndWeekForParticipant, aTourForParticipant, this, aSnowShoeTourForParticipant);
    combo = new ArrayList<Combo>();
    gears = new ArrayList<GearRequest>();
    boolean didAddSnowShoeTour = setSnowShoeTour(aSnowShoeTour);
    if (!didAddSnowShoeTour)
    {
      throw new RuntimeException("Unable to create registration due to snowShoeTour. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setTourCost(int aTourCost)
  {
    boolean wasSet = false;
    tourCost = aTourCost;
    wasSet = true;
    return wasSet;
  }

  public boolean setAuthCode(String aAuthCode)
  {
    boolean wasSet = false;
    String anOldAuthCode = getAuthCode();
    if (anOldAuthCode != null && anOldAuthCode.equals(aAuthCode)) {
      return true;
    }
    if (hasWithAuthCode(aAuthCode)) {
      return wasSet;
    }
    authCode = aAuthCode;
    wasSet = true;
    if (anOldAuthCode != null) {
      registrationsByAuthCode.remove(anOldAuthCode);
    }
    registrationsByAuthCode.put(aAuthCode, this);
    return wasSet;
  }

  public boolean setRefundRate(double aRefundRate)
  {
    boolean wasSet = false;
    refundRate = aRefundRate;
    wasSet = true;
    return wasSet;
  }

  public boolean setLodgeRented(boolean aLodgeRented)
  {
    boolean wasSet = false;
    lodgeRented = aLodgeRented;
    wasSet = true;
    return wasSet;
  }

  public int getTourCost()
  {
    return tourCost;
  }

  public String getAuthCode()
  {
    return authCode;
  }
  /* Code from template attribute_GetUnique */
  public static Registration getWithAuthCode(String aAuthCode)
  {
    return registrationsByAuthCode.get(aAuthCode);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithAuthCode(String aAuthCode)
  {
    return getWithAuthCode(aAuthCode) != null;
  }

  public double getRefundRate()
  {
    return refundRate;
  }

  public boolean getLodgeRented()
  {
    return lodgeRented;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isLodgeRented()
  {
    return lodgeRented;
  }
  /* Code from template association_GetOne */
  public Tour getTour()
  {
    return tour;
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
  /* Code from template association_GetOne */
  public Participant getParticipant()
  {
    return participant;
  }
  /* Code from template association_GetMany */
  public Combo getCombo(int index)
  {
    Combo aCombo = combo.get(index);
    return aCombo;
  }

  public List<Combo> getCombo()
  {
    List<Combo> newCombo = Collections.unmodifiableList(combo);
    return newCombo;
  }

  public int numberOfCombo()
  {
    int number = combo.size();
    return number;
  }

  public boolean hasCombo()
  {
    boolean has = combo.size() > 0;
    return has;
  }

  public int indexOfCombo(Combo aCombo)
  {
    int index = combo.indexOf(aCombo);
    return index;
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
  public SnowShoeTour getSnowShoeTour()
  {
    return snowShoeTour;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setLodge(Lodge aLodge)
  {
    boolean wasSet = false;
    Lodge existingLodge = lodge;
    lodge = aLodge;
    if (existingLodge != null && !existingLodge.equals(aLodge))
    {
      existingLodge.removeRegistration(this);
    }
    if (aLodge != null)
    {
      aLodge.addRegistration(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfCombo()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addCombo(Combo aCombo)
  {
    boolean wasAdded = false;
    if (combo.contains(aCombo)) { return false; }
    combo.add(aCombo);
    if (aCombo.indexOfRegistration(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aCombo.addRegistration(this);
      if (!wasAdded)
      {
        combo.remove(aCombo);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeCombo(Combo aCombo)
  {
    boolean wasRemoved = false;
    if (!combo.contains(aCombo))
    {
      return wasRemoved;
    }

    int oldIndex = combo.indexOf(aCombo);
    combo.remove(oldIndex);
    if (aCombo.indexOfRegistration(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aCombo.removeRegistration(this);
      if (!wasRemoved)
      {
        combo.add(oldIndex,aCombo);
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
      if(index > numberOfCombo()) { index = numberOfCombo() - 1; }
      combo.remove(aCombo);
      combo.add(index, aCombo);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveComboAt(Combo aCombo, int index)
  {
    boolean wasAdded = false;
    if(combo.contains(aCombo))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCombo()) { index = numberOfCombo() - 1; }
      combo.remove(aCombo);
      combo.add(index, aCombo);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addComboAt(aCombo, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfGears()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addGear(GearRequest aGear)
  {
    boolean wasAdded = false;
    if (gears.contains(aGear)) { return false; }
    gears.add(aGear);
    if (aGear.indexOfRegistration(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aGear.addRegistration(this);
      if (!wasAdded)
      {
        gears.remove(aGear);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeGear(GearRequest aGear)
  {
    boolean wasRemoved = false;
    if (!gears.contains(aGear))
    {
      return wasRemoved;
    }

    int oldIndex = gears.indexOf(aGear);
    gears.remove(oldIndex);
    if (aGear.indexOfRegistration(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aGear.removeRegistration(this);
      if (!wasRemoved)
      {
        gears.add(oldIndex,aGear);
      }
    }
    return wasRemoved;
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
      existingSnowShoeTour.removeRegistration(this);
    }
    snowShoeTour.addRegistration(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    registrationsByAuthCode.remove(getAuthCode());
    Tour existingTour = tour;
    tour = null;
    if (existingTour != null)
    {
      existingTour.delete();
    }
    if (lodge != null)
    {
      Lodge placeholderLodge = lodge;
      this.lodge = null;
      placeholderLodge.removeRegistration(this);
    }
    Participant existingParticipant = participant;
    participant = null;
    if (existingParticipant != null)
    {
      existingParticipant.delete();
    }
    ArrayList<Combo> copyOfCombo = new ArrayList<Combo>(combo);
    combo.clear();
    for(Combo aCombo : copyOfCombo)
    {
      aCombo.removeRegistration(this);
    }
    ArrayList<GearRequest> copyOfGears = new ArrayList<GearRequest>(gears);
    gears.clear();
    for(GearRequest aGear : copyOfGears)
    {
      aGear.removeRegistration(this);
    }
    SnowShoeTour placeholderSnowShoeTour = snowShoeTour;
    this.snowShoeTour = null;
    if(placeholderSnowShoeTour != null)
    {
      placeholderSnowShoeTour.removeRegistration(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "tourCost" + ":" + getTourCost()+ "," +
            "authCode" + ":" + getAuthCode()+ "," +
            "refundRate" + ":" + getRefundRate()+ "," +
            "lodgeRented" + ":" + getLodgeRented()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "tour = "+(getTour()!=null?Integer.toHexString(System.identityHashCode(getTour())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "lodge = "+(getLodge()!=null?Integer.toHexString(System.identityHashCode(getLodge())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "participant = "+(getParticipant()!=null?Integer.toHexString(System.identityHashCode(getParticipant())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "snowShoeTour = "+(getSnowShoeTour()!=null?Integer.toHexString(System.identityHashCode(getSnowShoeTour())):"null");
  }
}