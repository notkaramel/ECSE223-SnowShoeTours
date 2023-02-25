/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

package ca.mcgill.ecse.snowshoetours.model;
import java.util.*;

// line 3 "../../../../../../SnowShoeTour.ump"
public class Tour
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Tour Attributes
  private int guideCost;

  //Tour Associations
  private Lodge lodge;
  private List<Participant> participants;
  private List<Week> weeks;
  private Guide guide;
  private Registration registration;
  private SnowShoeTour snowShoeTour;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Tour(int aGuideCost, Lodge aLodge, Guide aGuide, Registration aRegistration, SnowShoeTour aSnowShoeTour, Week... allWeeks)
  {
    guideCost = aGuideCost;
    boolean didAddLodge = setLodge(aLodge);
    if (!didAddLodge)
    {
      throw new RuntimeException("Unable to create tour due to lodge. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    participants = new ArrayList<Participant>();
    weeks = new ArrayList<Week>();
    boolean didAddWeeks = setWeeks(allWeeks);
    if (!didAddWeeks)
    {
      throw new RuntimeException("Unable to create Tour, must have at least 1 weeks. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddGuide = setGuide(aGuide);
    if (!didAddGuide)
    {
      throw new RuntimeException("Unable to create tour due to guide. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    if (aRegistration == null || aRegistration.getTour() != null)
    {
      throw new RuntimeException("Unable to create Tour due to aRegistration. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    registration = aRegistration;
    boolean didAddSnowShoeTour = setSnowShoeTour(aSnowShoeTour);
    if (!didAddSnowShoeTour)
    {
      throw new RuntimeException("Unable to create tour due to snowShoeTour. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  public Tour(int aGuideCost, Lodge aLodge, Guide aGuide, int aTourCostForRegistration, String aAuthCodeForRegistration, double aRefundRateForRegistration, boolean aLodgeRentedForRegistration, Participant aParticipantForRegistration, SnowShoeTour aSnowShoeTourForRegistration, SnowShoeTour aSnowShoeTour, Week... allWeeks)
  {
    guideCost = aGuideCost;
    boolean didAddLodge = setLodge(aLodge);
    if (!didAddLodge)
    {
      throw new RuntimeException("Unable to create tour due to lodge. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    participants = new ArrayList<Participant>();
    weeks = new ArrayList<Week>();
    boolean didAddWeeks = setWeeks(allWeeks);
    if (!didAddWeeks)
    {
      throw new RuntimeException("Unable to create Tour, must have at least 1 weeks. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddGuide = setGuide(aGuide);
    if (!didAddGuide)
    {
      throw new RuntimeException("Unable to create tour due to guide. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    registration = new Registration(aTourCostForRegistration, aAuthCodeForRegistration, aRefundRateForRegistration, aLodgeRentedForRegistration, this, aParticipantForRegistration, aSnowShoeTourForRegistration);
    boolean didAddSnowShoeTour = setSnowShoeTour(aSnowShoeTour);
    if (!didAddSnowShoeTour)
    {
      throw new RuntimeException("Unable to create tour due to snowShoeTour. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setGuideCost(int aGuideCost)
  {
    boolean wasSet = false;
    guideCost = aGuideCost;
    wasSet = true;
    return wasSet;
  }

  /**
   * Tour attributes
   */
  public int getGuideCost()
  {
    return guideCost;
  }
  /* Code from template association_GetOne */
  public Lodge getLodge()
  {
    return lodge;
  }
  /* Code from template association_GetMany */
  public Participant getParticipant(int index)
  {
    Participant aParticipant = participants.get(index);
    return aParticipant;
  }

  public List<Participant> getParticipants()
  {
    List<Participant> newParticipants = Collections.unmodifiableList(participants);
    return newParticipants;
  }

  public int numberOfParticipants()
  {
    int number = participants.size();
    return number;
  }

  public boolean hasParticipants()
  {
    boolean has = participants.size() > 0;
    return has;
  }

  public int indexOfParticipant(Participant aParticipant)
  {
    int index = participants.indexOf(aParticipant);
    return index;
  }
  /* Code from template association_GetMany */
  public Week getWeek(int index)
  {
    Week aWeek = weeks.get(index);
    return aWeek;
  }

  public List<Week> getWeeks()
  {
    List<Week> newWeeks = Collections.unmodifiableList(weeks);
    return newWeeks;
  }

  public int numberOfWeeks()
  {
    int number = weeks.size();
    return number;
  }

  public boolean hasWeeks()
  {
    boolean has = weeks.size() > 0;
    return has;
  }

  public int indexOfWeek(Week aWeek)
  {
    int index = weeks.indexOf(aWeek);
    return index;
  }
  /* Code from template association_GetOne */
  public Guide getGuide()
  {
    return guide;
  }
  /* Code from template association_GetOne */
  public Registration getRegistration()
  {
    return registration;
  }
  /* Code from template association_GetOne */
  public SnowShoeTour getSnowShoeTour()
  {
    return snowShoeTour;
  }
  /* Code from template association_SetOneToMany */
  public boolean setLodge(Lodge aLodge)
  {
    boolean wasSet = false;
    if (aLodge == null)
    {
      return wasSet;
    }

    Lodge existingLodge = lodge;
    lodge = aLodge;
    if (existingLodge != null && !existingLodge.equals(aLodge))
    {
      existingLodge.removeTour(this);
    }
    lodge.addTour(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfParticipants()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Participant addParticipant(String aEmail, String aName, String aAccountName, String aPassword, String aEmergencyContact, SnowShoeTour aSnowShoeTour, int aNumberOfWeeks, Week aStartWeek, Week aEndWeek, Registration aRegistration, SnowShoeTour aSnowShoeTour)
  {
    return new Participant(aEmail, aName, aAccountName, aPassword, aEmergencyContact, aSnowShoeTour, aNumberOfWeeks, aStartWeek, aEndWeek, this, aRegistration, aSnowShoeTour);
  }

  public boolean addParticipant(Participant aParticipant)
  {
    boolean wasAdded = false;
    if (participants.contains(aParticipant)) { return false; }
    Tour existingTour = aParticipant.getTour();
    boolean isNewTour = existingTour != null && !this.equals(existingTour);
    if (isNewTour)
    {
      aParticipant.setTour(this);
    }
    else
    {
      participants.add(aParticipant);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeParticipant(Participant aParticipant)
  {
    boolean wasRemoved = false;
    //Unable to remove aParticipant, as it must always have a tour
    if (!this.equals(aParticipant.getTour()))
    {
      participants.remove(aParticipant);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addParticipantAt(Participant aParticipant, int index)
  {  
    boolean wasAdded = false;
    if(addParticipant(aParticipant))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfParticipants()) { index = numberOfParticipants() - 1; }
      participants.remove(aParticipant);
      participants.add(index, aParticipant);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveParticipantAt(Participant aParticipant, int index)
  {
    boolean wasAdded = false;
    if(participants.contains(aParticipant))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfParticipants()) { index = numberOfParticipants() - 1; }
      participants.remove(aParticipant);
      participants.add(index, aParticipant);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addParticipantAt(aParticipant, index);
    }
    return wasAdded;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfWeeksValid()
  {
    boolean isValid = numberOfWeeks() >= minimumNumberOfWeeks();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfWeeks()
  {
    return 1;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addWeek(Week aWeek)
  {
    boolean wasAdded = false;
    if (weeks.contains(aWeek)) { return false; }
    weeks.add(aWeek);
    if (aWeek.indexOfTour(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aWeek.addTour(this);
      if (!wasAdded)
      {
        weeks.remove(aWeek);
      }
    }
    return wasAdded;
  }
  /* Code from template association_AddMStarToMany */
  public boolean removeWeek(Week aWeek)
  {
    boolean wasRemoved = false;
    if (!weeks.contains(aWeek))
    {
      return wasRemoved;
    }

    if (numberOfWeeks() <= minimumNumberOfWeeks())
    {
      return wasRemoved;
    }

    int oldIndex = weeks.indexOf(aWeek);
    weeks.remove(oldIndex);
    if (aWeek.indexOfTour(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aWeek.removeTour(this);
      if (!wasRemoved)
      {
        weeks.add(oldIndex,aWeek);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_SetMStarToMany */
  public boolean setWeeks(Week... newWeeks)
  {
    boolean wasSet = false;
    ArrayList<Week> verifiedWeeks = new ArrayList<Week>();
    for (Week aWeek : newWeeks)
    {
      if (verifiedWeeks.contains(aWeek))
      {
        continue;
      }
      verifiedWeeks.add(aWeek);
    }

    if (verifiedWeeks.size() != newWeeks.length || verifiedWeeks.size() < minimumNumberOfWeeks())
    {
      return wasSet;
    }

    ArrayList<Week> oldWeeks = new ArrayList<Week>(weeks);
    weeks.clear();
    for (Week aNewWeek : verifiedWeeks)
    {
      weeks.add(aNewWeek);
      if (oldWeeks.contains(aNewWeek))
      {
        oldWeeks.remove(aNewWeek);
      }
      else
      {
        aNewWeek.addTour(this);
      }
    }

    for (Week anOldWeek : oldWeeks)
    {
      anOldWeek.removeTour(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addWeekAt(Week aWeek, int index)
  {  
    boolean wasAdded = false;
    if(addWeek(aWeek))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfWeeks()) { index = numberOfWeeks() - 1; }
      weeks.remove(aWeek);
      weeks.add(index, aWeek);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveWeekAt(Week aWeek, int index)
  {
    boolean wasAdded = false;
    if(weeks.contains(aWeek))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfWeeks()) { index = numberOfWeeks() - 1; }
      weeks.remove(aWeek);
      weeks.add(index, aWeek);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addWeekAt(aWeek, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setGuide(Guide aGuide)
  {
    boolean wasSet = false;
    if (aGuide == null)
    {
      return wasSet;
    }

    Guide existingGuide = guide;
    guide = aGuide;
    if (existingGuide != null && !existingGuide.equals(aGuide))
    {
      existingGuide.removeTour(this);
    }
    guide.addTour(this);
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
      existingSnowShoeTour.removeTour(this);
    }
    snowShoeTour.addTour(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Lodge placeholderLodge = lodge;
    this.lodge = null;
    if(placeholderLodge != null)
    {
      placeholderLodge.removeTour(this);
    }
    for(int i=participants.size(); i > 0; i--)
    {
      Participant aParticipant = participants.get(i - 1);
      aParticipant.delete();
    }
    ArrayList<Week> copyOfWeeks = new ArrayList<Week>(weeks);
    weeks.clear();
    for(Week aWeek : copyOfWeeks)
    {
      aWeek.removeTour(this);
    }
    Guide placeholderGuide = guide;
    this.guide = null;
    if(placeholderGuide != null)
    {
      placeholderGuide.removeTour(this);
    }
    Registration existingRegistration = registration;
    registration = null;
    if (existingRegistration != null)
    {
      existingRegistration.delete();
    }
    SnowShoeTour placeholderSnowShoeTour = snowShoeTour;
    this.snowShoeTour = null;
    if(placeholderSnowShoeTour != null)
    {
      placeholderSnowShoeTour.removeTour(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "guideCost" + ":" + getGuideCost()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "lodge = "+(getLodge()!=null?Integer.toHexString(System.identityHashCode(getLodge())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "guide = "+(getGuide()!=null?Integer.toHexString(System.identityHashCode(getGuide())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "registration = "+(getRegistration()!=null?Integer.toHexString(System.identityHashCode(getRegistration())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "snowShoeTour = "+(getSnowShoeTour()!=null?Integer.toHexString(System.identityHashCode(getSnowShoeTour())):"null");
  }
}