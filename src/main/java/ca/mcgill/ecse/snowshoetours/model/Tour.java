/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

package ca.mcgill.ecse.snowshoetours.model;
import java.util.*;

// line 83 "../../../../../../SnowShoeTour.ump"
public class Tour
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<Integer, Tour> toursById = new HashMap<Integer, Tour>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Tour Attributes
  private int id;
  private int startWeek;
  private int endWeek;

  //Tour Associations
  private List<Participant> participants;
  private Guide guide;
  private Lodge lodge;
  private SnowShoeTour snowShoeTour;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Tour(int aId, int aStartWeek, int aEndWeek, Guide aGuide, SnowShoeTour aSnowShoeTour)
  {
    startWeek = aStartWeek;
    endWeek = aEndWeek;
    if (!setId(aId))
    {
      throw new RuntimeException("Cannot create due to duplicate id. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    participants = new ArrayList<Participant>();
    boolean didAddGuide = setGuide(aGuide);
    if (!didAddGuide)
    {
      throw new RuntimeException("Unable to create tour due to guide. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddSnowShoeTour = setSnowShoeTour(aSnowShoeTour);
    if (!didAddSnowShoeTour)
    {
      throw new RuntimeException("Unable to create tour due to snowShoeTour. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setId(int aId)
  {
    boolean wasSet = false;
    Integer anOldId = getId();
    if (anOldId != null && anOldId.equals(aId)) {
      return true;
    }
    if (hasWithId(aId)) {
      return wasSet;
    }
    id = aId;
    wasSet = true;
    if (anOldId != null) {
      toursById.remove(anOldId);
    }
    toursById.put(aId, this);
    return wasSet;
  }

  public boolean setStartWeek(int aStartWeek)
  {
    boolean wasSet = false;
    startWeek = aStartWeek;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndWeek(int aEndWeek)
  {
    boolean wasSet = false;
    endWeek = aEndWeek;
    wasSet = true;
    return wasSet;
  }

  public int getId()
  {
    return id;
  }
  /* Code from template attribute_GetUnique */
  public static Tour getWithId(int aId)
  {
    return toursById.get(aId);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithId(int aId)
  {
    return getWithId(aId) != null;
  }

  public int getStartWeek()
  {
    return startWeek;
  }

  public int getEndWeek()
  {
    return endWeek;
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
  /* Code from template association_GetOne */
  public Guide getGuide()
  {
    return guide;
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
  public SnowShoeTour getSnowShoeTour()
  {
    return snowShoeTour;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfParticipants()
  {
    return 0;
  }
  /* Code from template association_AddManyToOptionalOne */
  public boolean addParticipant(Participant aParticipant)
  {
    boolean wasAdded = false;
    if (participants.contains(aParticipant)) { return false; }
    Tour existingTour = aParticipant.getTour();
    if (existingTour == null)
    {
      aParticipant.setTour(this);
    }
    else if (!this.equals(existingTour))
    {
      existingTour.removeParticipant(aParticipant);
      addParticipant(aParticipant);
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
    if (participants.contains(aParticipant))
    {
      participants.remove(aParticipant);
      aParticipant.setTour(null);
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
  /* Code from template association_SetOptionalOneToMany */
  public boolean setLodge(Lodge aLodge)
  {
    boolean wasSet = false;
    Lodge existingLodge = lodge;
    lodge = aLodge;
    if (existingLodge != null && !existingLodge.equals(aLodge))
    {
      existingLodge.removeTour(this);
    }
    if (aLodge != null)
    {
      aLodge.addTour(this);
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
      existingSnowShoeTour.removeTour(this);
    }
    snowShoeTour.addTour(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    toursById.remove(getId());
    while( !participants.isEmpty() )
    {
      participants.get(0).setTour(null);
    }
    Guide placeholderGuide = guide;
    this.guide = null;
    if(placeholderGuide != null)
    {
      placeholderGuide.removeTour(this);
    }
    if (lodge != null)
    {
      Lodge placeholderLodge = lodge;
      this.lodge = null;
      placeholderLodge.removeTour(this);
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
            "id" + ":" + getId()+ "," +
            "startWeek" + ":" + getStartWeek()+ "," +
            "endWeek" + ":" + getEndWeek()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "guide = "+(getGuide()!=null?Integer.toHexString(System.identityHashCode(getGuide())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "lodge = "+(getLodge()!=null?Integer.toHexString(System.identityHashCode(getLodge())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "snowShoeTour = "+(getSnowShoeTour()!=null?Integer.toHexString(System.identityHashCode(getSnowShoeTour())):"null");
  }
}