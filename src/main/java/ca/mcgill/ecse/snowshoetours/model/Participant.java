/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

package ca.mcgill.ecse.snowshoetours.model;
import java.util.*;

// line 1 "../../../../../../ParticipantStates.ump"
// line 41 "../../../../../../SnowShoeTour.ump"
public class Participant extends NamedUser
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Participant Attributes
  private int nrWeeks;
  private int weekAvailableFrom;
  private int weekAvailableUntil;
  private boolean lodgeRequired;
  private String authorizationCode;
  private int refundedPercentageAmount;

  //Participant State Machines
  public enum Status { NotAssigned, Assigned, Paid, Started, Finished, Cancelled }
  private Status status;

  //Participant Associations
  private SnowShoeTour snowShoeTour;
  private Tour tour;
  private List<BookedItem> bookedItems;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Participant(String aAccountName, String aPassword, String aName, String aEmergencyContact, int aNrWeeks, int aWeekAvailableFrom, int aWeekAvailableUntil, boolean aLodgeRequired, String aAuthorizationCode, int aRefundedPercentageAmount, SnowShoeTour aSnowShoeTour)
  {
    super(aAccountName, aPassword, aName, aEmergencyContact);
    nrWeeks = aNrWeeks;
    weekAvailableFrom = aWeekAvailableFrom;
    weekAvailableUntil = aWeekAvailableUntil;
    lodgeRequired = aLodgeRequired;
    authorizationCode = aAuthorizationCode;
    refundedPercentageAmount = aRefundedPercentageAmount;
    boolean didAddSnowShoeTour = setSnowShoeTour(aSnowShoeTour);
    if (!didAddSnowShoeTour)
    {
      throw new RuntimeException("Unable to create participant due to snowShoeTour. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    bookedItems = new ArrayList<BookedItem>();
    setStatus(Status.NotAssigned);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setNrWeeks(int aNrWeeks)
  {
    boolean wasSet = false;
    nrWeeks = aNrWeeks;
    wasSet = true;
    return wasSet;
  }

  public boolean setWeekAvailableFrom(int aWeekAvailableFrom)
  {
    boolean wasSet = false;
    weekAvailableFrom = aWeekAvailableFrom;
    wasSet = true;
    return wasSet;
  }

  public boolean setWeekAvailableUntil(int aWeekAvailableUntil)
  {
    boolean wasSet = false;
    weekAvailableUntil = aWeekAvailableUntil;
    wasSet = true;
    return wasSet;
  }

  public boolean setLodgeRequired(boolean aLodgeRequired)
  {
    boolean wasSet = false;
    lodgeRequired = aLodgeRequired;
    wasSet = true;
    return wasSet;
  }

  public boolean setAuthorizationCode(String aAuthorizationCode)
  {
    boolean wasSet = false;
    authorizationCode = aAuthorizationCode;
    wasSet = true;
    return wasSet;
  }

  public boolean setRefundedPercentageAmount(int aRefundedPercentageAmount)
  {
    boolean wasSet = false;
    refundedPercentageAmount = aRefundedPercentageAmount;
    wasSet = true;
    return wasSet;
  }

  public int getNrWeeks()
  {
    return nrWeeks;
  }

  public int getWeekAvailableFrom()
  {
    return weekAvailableFrom;
  }

  public int getWeekAvailableUntil()
  {
    return weekAvailableUntil;
  }

  public boolean getLodgeRequired()
  {
    return lodgeRequired;
  }

  public String getAuthorizationCode()
  {
    return authorizationCode;
  }

  public int getRefundedPercentageAmount()
  {
    return refundedPercentageAmount;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isLodgeRequired()
  {
    return lodgeRequired;
  }

  public String getStatusFullName()
  {
    String answer = status.toString();
    return answer;
  }

  public Status getStatus()
  {
    return status;
  }

  public boolean assign(Tour aTour)
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case NotAssigned:
        if (hasGuide(aTour))
        {
        // line 4 "../../../../../../ParticipantStates.ump"
          doAssign(aTour);
          setStatus(Status.Assigned);
          wasEventProcessed = true;
          break;
        }
        if (!(hasGuide(aTour)))
        {
          setStatus(Status.NotAssigned);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean cancel()
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case NotAssigned:
        // line 11 "../../../../../../ParticipantStates.ump"
        setRefundedPercentageAmount(0);
        setStatus(Status.Cancelled);
        wasEventProcessed = true;
        break;
      case Assigned:
        setStatus(Status.Cancelled);
        wasEventProcessed = true;
        break;
      case Paid:
        // line 43 "../../../../../../ParticipantStates.ump"
        setRefundedPercentageAmount(50);
        setStatus(Status.Cancelled);
        wasEventProcessed = true;
        break;
      case Started:
        // line 54 "../../../../../../ParticipantStates.ump"
        setRefundedPercentageAmount(10);
        setStatus(Status.Cancelled);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean start()
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case NotAssigned:
        setStatus(Status.NotAssigned);
        wasEventProcessed = true;
        break;
      case Assigned:
        // line 31 "../../../../../../ParticipantStates.ump"
        setRefundedPercentageAmount(0);
        setStatus(Status.Cancelled);
        wasEventProcessed = true;
        break;
      case Paid:
        setStatus(Status.Started);
        wasEventProcessed = true;
        break;
      case Started:
        setStatus(Status.Started);
        wasEventProcessed = true;
        break;
      case Finished:
        setStatus(Status.Finished);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean pay()
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case NotAssigned:
        setStatus(Status.NotAssigned);
        wasEventProcessed = true;
        break;
      case Assigned:
        setStatus(Status.Paid);
        wasEventProcessed = true;
        break;
      case Paid:
        setStatus(Status.Paid);
        wasEventProcessed = true;
        break;
      case Started:
        setStatus(Status.Started);
        wasEventProcessed = true;
        break;
      case Finished:
        setStatus(Status.Finished);
        wasEventProcessed = true;
        break;
      case Cancelled:
        setStatus(Status.Cancelled);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean finish()
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case Started:
        setStatus(Status.Finished);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void setStatus(Status aStatus)
  {
    status = aStatus;
  }
  /* Code from template association_GetOne */
  public SnowShoeTour getSnowShoeTour()
  {
    return snowShoeTour;
  }
  /* Code from template association_GetOne */
  public Tour getTour()
  {
    return tour;
  }

  public boolean hasTour()
  {
    boolean has = tour != null;
    return has;
  }
  /* Code from template association_GetMany */
  public BookedItem getBookedItem(int index)
  {
    BookedItem aBookedItem = bookedItems.get(index);
    return aBookedItem;
  }

  public List<BookedItem> getBookedItems()
  {
    List<BookedItem> newBookedItems = Collections.unmodifiableList(bookedItems);
    return newBookedItems;
  }

  public int numberOfBookedItems()
  {
    int number = bookedItems.size();
    return number;
  }

  public boolean hasBookedItems()
  {
    boolean has = bookedItems.size() > 0;
    return has;
  }

  public int indexOfBookedItem(BookedItem aBookedItem)
  {
    int index = bookedItems.indexOf(aBookedItem);
    return index;
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
      existingSnowShoeTour.removeParticipant(this);
    }
    snowShoeTour.addParticipant(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setTour(Tour aTour)
  {
    boolean wasSet = false;
    Tour existingTour = tour;
    tour = aTour;
    if (existingTour != null && !existingTour.equals(aTour))
    {
      existingTour.removeParticipant(this);
    }
    if (aTour != null)
    {
      aTour.addParticipant(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBookedItems()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public BookedItem addBookedItem(int aQuantity, SnowShoeTour aSnowShoeTour, BookableItem aItem)
  {
    return new BookedItem(aQuantity, aSnowShoeTour, this, aItem);
  }

  public boolean addBookedItem(BookedItem aBookedItem)
  {
    boolean wasAdded = false;
    if (bookedItems.contains(aBookedItem)) { return false; }
    Participant existingParticipant = aBookedItem.getParticipant();
    boolean isNewParticipant = existingParticipant != null && !this.equals(existingParticipant);
    if (isNewParticipant)
    {
      aBookedItem.setParticipant(this);
    }
    else
    {
      bookedItems.add(aBookedItem);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBookedItem(BookedItem aBookedItem)
  {
    boolean wasRemoved = false;
    //Unable to remove aBookedItem, as it must always have a participant
    if (!this.equals(aBookedItem.getParticipant()))
    {
      bookedItems.remove(aBookedItem);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBookedItemAt(BookedItem aBookedItem, int index)
  {  
    boolean wasAdded = false;
    if(addBookedItem(aBookedItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBookedItems()) { index = numberOfBookedItems() - 1; }
      bookedItems.remove(aBookedItem);
      bookedItems.add(index, aBookedItem);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBookedItemAt(BookedItem aBookedItem, int index)
  {
    boolean wasAdded = false;
    if(bookedItems.contains(aBookedItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBookedItems()) { index = numberOfBookedItems() - 1; }
      bookedItems.remove(aBookedItem);
      bookedItems.add(index, aBookedItem);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBookedItemAt(aBookedItem, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    SnowShoeTour placeholderSnowShoeTour = snowShoeTour;
    this.snowShoeTour = null;
    if(placeholderSnowShoeTour != null)
    {
      placeholderSnowShoeTour.removeParticipant(this);
    }
    if (tour != null)
    {
      Tour placeholderTour = tour;
      this.tour = null;
      placeholderTour.removeParticipant(this);
    }
    for(int i=bookedItems.size(); i > 0; i--)
    {
      BookedItem aBookedItem = bookedItems.get(i - 1);
      aBookedItem.delete();
    }
    super.delete();
  }

  // line 72 "../../../../../../ParticipantStates.ump"
   private boolean hasGuide(Tour aTour){
    return aTour.getGuide() != null && !aTour.getGuide().getName().isEmpty();
  }

  // line 76 "../../../../../../ParticipantStates.ump"
   private void doAssign(Tour aTour){
    setTour(aTour);
  }

  // line 80 "../../../../../../ParticipantStates.ump"
   private boolean hasAuthCode(){
    return getAuthorizationCode() != null && !getAuthorizationCode().isEmpty();
  }


  public String toString()
  {
    return super.toString() + "["+
            "nrWeeks" + ":" + getNrWeeks()+ "," +
            "weekAvailableFrom" + ":" + getWeekAvailableFrom()+ "," +
            "weekAvailableUntil" + ":" + getWeekAvailableUntil()+ "," +
            "lodgeRequired" + ":" + getLodgeRequired()+ "," +
            "authorizationCode" + ":" + getAuthorizationCode()+ "," +
            "refundedPercentageAmount" + ":" + getRefundedPercentageAmount()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "snowShoeTour = "+(getSnowShoeTour()!=null?Integer.toHexString(System.identityHashCode(getSnowShoeTour())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "tour = "+(getTour()!=null?Integer.toHexString(System.identityHashCode(getTour())):"null");
  }
}