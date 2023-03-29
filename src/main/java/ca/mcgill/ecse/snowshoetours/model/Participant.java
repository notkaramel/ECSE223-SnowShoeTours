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
  public enum Status { Idle, NotPaid, Paid, TourOnGoing, TourFinished, Cancelled }
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
    setStatus(Status.Idle);
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

  public boolean assignGuide(Guide aGuide)
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case Idle:
        if (isGuideAvailable())
        {
        // line 5 "../../../../../../ParticipantStates.ump"
          doAssignGuide(aGuide);
          setStatus(Status.NotPaid);
          wasEventProcessed = true;
          break;
        }
        if (!(isGuideAvailable()))
        {
        // line 10 "../../../../../../ParticipantStates.ump"
          rejectAssignGuide("Guide is unavailable");
          setStatus(Status.Idle);
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
      case Idle:
        // line 16 "../../../../../../ParticipantStates.ump"
        doRefund(null);
        setStatus(Status.Cancelled);
        wasEventProcessed = true;
        break;
      case NotPaid:
        // line 32 "../../../../../../ParticipantStates.ump"
        doRefund(null);
        setStatus(Status.Cancelled);
        wasEventProcessed = true;
        break;
      case Paid:
        // line 54 "../../../../../../ParticipantStates.ump"
        doRefund(50);
        setStatus(Status.Cancelled);
        wasEventProcessed = true;
        break;
      case TourOnGoing:
        // line 67 "../../../../../../ParticipantStates.ump"
        doRefund(10);
        setStatus(Status.Cancelled);
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
      case NotPaid:
        if (!(hasAuthCode()))
        {
        // line 23 "../../../../../../ParticipantStates.ump"
          rejectPayment();
          setStatus(Status.NotPaid);
          wasEventProcessed = true;
          break;
        }
        if (hasAuthCode())
        {
          setStatus(Status.Paid);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean startTour()
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case NotPaid:
        if (isTime())
        {
        // line 37 "../../../../../../ParticipantStates.ump"
          doRefund(null);
          setStatus(Status.Cancelled);
          wasEventProcessed = true;
          break;
        }
        break;
      case Paid:
        if (isTime())
        {
        // line 45 "../../../../../../ParticipantStates.ump"
          // doStartTour();
          setStatus(Status.TourOnGoing);
          wasEventProcessed = true;
          break;
        }
        if (!(isTime()))
        {
          setStatus(Status.Paid);
          wasEventProcessed = true;
          break;
        }
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
      case TourOnGoing:
        // line 61 "../../../../../../ParticipantStates.ump"
        // doFinishTour();
        setStatus(Status.TourFinished);
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

  // line 79 "../../../../../../ParticipantStates.ump"
   private void doAssignGuide(Guide aGuide){
    getTour().setGuide(aGuide);
  }

  // line 83 "../../../../../../ParticipantStates.ump"
   private boolean isGuideAvailable(){
    return getTour().getGuide() != null;
  }

  // line 87 "../../../../../../ParticipantStates.ump"
   private void rejectAssignGuide(String error){
    throw new RuntimeException(error);
  }

  // line 91 "../../../../../../ParticipantStates.ump"
   private void rejectPayment(){
    throw new RuntimeException("Payment not accepted");
  }

  // line 95 "../../../../../../ParticipantStates.ump"
   private void doRefund(Integer refundedPercentage){
    if(refundedPercentage == null){
      throw new RuntimeException("No penalty, user has not paid");
    }
    else {
      setRefundedPercentageAmount(refundedPercentage);
    }
  }

  // line 104 "../../../../../../ParticipantStates.ump"
   private boolean hasAuthCode(){
    return getAuthorizationCode() != null && !getAuthorizationCode().isEmpty();
  }

  // line 108 "../../../../../../ParticipantStates.ump"
   private boolean isTime(){
    java.util.Date today = new java.util.Date();
    // timeDifference = today - getSnowShoeTour().getStartDate() (using getTime --> in ms)
    long timeDifference = today.getTime() - getSnowShoeTour().getStartDate().getTime();
    // convert ms to weeks
    int weekDifference = (int) timeDifference / (1000*60*60*24*7);

    // isTime = (weekDifference == getTour().getStartWeek())
    return weekDifference == getTour().getStartWeek();
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