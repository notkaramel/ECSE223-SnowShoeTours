/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

package ca.mcgill.ecse.snowshoetours.model;
import java.sql.Date;
import java.util.*;

// line 3 "../../../../../../SnowShoeTour.ump"
public class SnowShoeTour
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //SnowShoeTour Attributes
  private Date startDate;
  private int nrWeeks;
  private int priceOfGuidePerWeek;

  //SnowShoeTour Associations
  private Manager manager;
  private List<Guide> guides;
  private List<Participant> participants;
  private List<BookedItem> bookedItems;
  private List<Gear> gear;
  private List<Combo> combos;
  private List<ComboItem> comboItems;
  private List<Lodge> lodges;
  private List<Tour> tours;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public SnowShoeTour(Date aStartDate, int aNrWeeks, int aPriceOfGuidePerWeek)
  {
    startDate = aStartDate;
    nrWeeks = aNrWeeks;
    priceOfGuidePerWeek = aPriceOfGuidePerWeek;
    guides = new ArrayList<Guide>();
    participants = new ArrayList<Participant>();
    bookedItems = new ArrayList<BookedItem>();
    gear = new ArrayList<Gear>();
    combos = new ArrayList<Combo>();
    comboItems = new ArrayList<ComboItem>();
    lodges = new ArrayList<Lodge>();
    tours = new ArrayList<Tour>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStartDate(Date aStartDate)
  {
    boolean wasSet = false;
    startDate = aStartDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setNrWeeks(int aNrWeeks)
  {
    boolean wasSet = false;
    nrWeeks = aNrWeeks;
    wasSet = true;
    return wasSet;
  }

  public boolean setPriceOfGuidePerWeek(int aPriceOfGuidePerWeek)
  {
    boolean wasSet = false;
    priceOfGuidePerWeek = aPriceOfGuidePerWeek;
    wasSet = true;
    return wasSet;
  }

  public Date getStartDate()
  {
    return startDate;
  }

  public int getNrWeeks()
  {
    return nrWeeks;
  }

  public int getPriceOfGuidePerWeek()
  {
    return priceOfGuidePerWeek;
  }
  /* Code from template association_GetOne */
  public Manager getManager()
  {
    return manager;
  }

  public boolean hasManager()
  {
    boolean has = manager != null;
    return has;
  }
  /* Code from template association_GetMany */
  public Guide getGuide(int index)
  {
    Guide aGuide = guides.get(index);
    return aGuide;
  }

  public List<Guide> getGuides()
  {
    List<Guide> newGuides = Collections.unmodifiableList(guides);
    return newGuides;
  }

  public int numberOfGuides()
  {
    int number = guides.size();
    return number;
  }

  public boolean hasGuides()
  {
    boolean has = guides.size() > 0;
    return has;
  }

  public int indexOfGuide(Guide aGuide)
  {
    int index = guides.indexOf(aGuide);
    return index;
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
  /* Code from template association_GetMany */
  public Gear getGear(int index)
  {
    Gear aGear = gear.get(index);
    return aGear;
  }

  public List<Gear> getGear()
  {
    List<Gear> newGear = Collections.unmodifiableList(gear);
    return newGear;
  }

  public int numberOfGear()
  {
    int number = gear.size();
    return number;
  }

  public boolean hasGear()
  {
    boolean has = gear.size() > 0;
    return has;
  }

  public int indexOfGear(Gear aGear)
  {
    int index = gear.indexOf(aGear);
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
  /* Code from template association_GetMany */
  public ComboItem getComboItem(int index)
  {
    ComboItem aComboItem = comboItems.get(index);
    return aComboItem;
  }

  public List<ComboItem> getComboItems()
  {
    List<ComboItem> newComboItems = Collections.unmodifiableList(comboItems);
    return newComboItems;
  }

  public int numberOfComboItems()
  {
    int number = comboItems.size();
    return number;
  }

  public boolean hasComboItems()
  {
    boolean has = comboItems.size() > 0;
    return has;
  }

  public int indexOfComboItem(ComboItem aComboItem)
  {
    int index = comboItems.indexOf(aComboItem);
    return index;
  }
  /* Code from template association_GetMany */
  public Lodge getLodge(int index)
  {
    Lodge aLodge = lodges.get(index);
    return aLodge;
  }

  public List<Lodge> getLodges()
  {
    List<Lodge> newLodges = Collections.unmodifiableList(lodges);
    return newLodges;
  }

  public int numberOfLodges()
  {
    int number = lodges.size();
    return number;
  }

  public boolean hasLodges()
  {
    boolean has = lodges.size() > 0;
    return has;
  }

  public int indexOfLodge(Lodge aLodge)
  {
    int index = lodges.indexOf(aLodge);
    return index;
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
  /* Code from template association_SetOptionalOneToOne */
  public boolean setManager(Manager aNewManager)
  {
    boolean wasSet = false;
    if (manager != null && !manager.equals(aNewManager) && equals(manager.getSnowShoeTour()))
    {
      //Unable to setManager, as existing manager would become an orphan
      return wasSet;
    }

    manager = aNewManager;
    SnowShoeTour anOldSnowShoeTour = aNewManager != null ? aNewManager.getSnowShoeTour() : null;

    if (!this.equals(anOldSnowShoeTour))
    {
      if (anOldSnowShoeTour != null)
      {
        anOldSnowShoeTour.manager = null;
      }
      if (manager != null)
      {
        manager.setSnowShoeTour(this);
      }
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfGuides()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Guide addGuide(String aAccountName, String aPassword, String aName, String aEmergencyContact)
  {
    return new Guide(aAccountName, aPassword, aName, aEmergencyContact, this);
  }

  public boolean addGuide(Guide aGuide)
  {
    boolean wasAdded = false;
    if (guides.contains(aGuide)) { return false; }
    SnowShoeTour existingSnowShoeTour = aGuide.getSnowShoeTour();
    boolean isNewSnowShoeTour = existingSnowShoeTour != null && !this.equals(existingSnowShoeTour);
    if (isNewSnowShoeTour)
    {
      aGuide.setSnowShoeTour(this);
    }
    else
    {
      guides.add(aGuide);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeGuide(Guide aGuide)
  {
    boolean wasRemoved = false;
    //Unable to remove aGuide, as it must always have a snowShoeTour
    if (!this.equals(aGuide.getSnowShoeTour()))
    {
      guides.remove(aGuide);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addGuideAt(Guide aGuide, int index)
  {  
    boolean wasAdded = false;
    if(addGuide(aGuide))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGuides()) { index = numberOfGuides() - 1; }
      guides.remove(aGuide);
      guides.add(index, aGuide);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveGuideAt(Guide aGuide, int index)
  {
    boolean wasAdded = false;
    if(guides.contains(aGuide))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGuides()) { index = numberOfGuides() - 1; }
      guides.remove(aGuide);
      guides.add(index, aGuide);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addGuideAt(aGuide, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfParticipants()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Participant addParticipant(String aAccountName, String aPassword, String aName, String aEmergencyContact, int aNrWeeks, int aWeekAvailableFrom, int aWeekAvailableUntil, boolean aLodgeRequired, String aAuthorizationCode, int aRefundedPercentageAmount)
  {
    return new Participant(aAccountName, aPassword, aName, aEmergencyContact, aNrWeeks, aWeekAvailableFrom, aWeekAvailableUntil, aLodgeRequired, aAuthorizationCode, aRefundedPercentageAmount, this);
  }

  public boolean addParticipant(Participant aParticipant)
  {
    boolean wasAdded = false;
    if (participants.contains(aParticipant)) { return false; }
    SnowShoeTour existingSnowShoeTour = aParticipant.getSnowShoeTour();
    boolean isNewSnowShoeTour = existingSnowShoeTour != null && !this.equals(existingSnowShoeTour);
    if (isNewSnowShoeTour)
    {
      aParticipant.setSnowShoeTour(this);
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
    //Unable to remove aParticipant, as it must always have a snowShoeTour
    if (!this.equals(aParticipant.getSnowShoeTour()))
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBookedItems()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public BookedItem addBookedItem(int aQuantity, Participant aParticipant, BookableItem aItem)
  {
    return new BookedItem(aQuantity, this, aParticipant, aItem);
  }

  public boolean addBookedItem(BookedItem aBookedItem)
  {
    boolean wasAdded = false;
    if (bookedItems.contains(aBookedItem)) { return false; }
    SnowShoeTour existingSnowShoeTour = aBookedItem.getSnowShoeTour();
    boolean isNewSnowShoeTour = existingSnowShoeTour != null && !this.equals(existingSnowShoeTour);
    if (isNewSnowShoeTour)
    {
      aBookedItem.setSnowShoeTour(this);
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
    //Unable to remove aBookedItem, as it must always have a snowShoeTour
    if (!this.equals(aBookedItem.getSnowShoeTour()))
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfGear()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Gear addGear(String aName, int aPricePerWeek)
  {
    return new Gear(aName, aPricePerWeek, this);
  }

  public boolean addGear(Gear aGear)
  {
    boolean wasAdded = false;
    if (gear.contains(aGear)) { return false; }
    SnowShoeTour existingSnowShoeTour = aGear.getSnowShoeTour();
    boolean isNewSnowShoeTour = existingSnowShoeTour != null && !this.equals(existingSnowShoeTour);
    if (isNewSnowShoeTour)
    {
      aGear.setSnowShoeTour(this);
    }
    else
    {
      gear.add(aGear);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeGear(Gear aGear)
  {
    boolean wasRemoved = false;
    //Unable to remove aGear, as it must always have a snowShoeTour
    if (!this.equals(aGear.getSnowShoeTour()))
    {
      gear.remove(aGear);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addGearAt(Gear aGear, int index)
  {  
    boolean wasAdded = false;
    if(addGear(aGear))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGear()) { index = numberOfGear() - 1; }
      gear.remove(aGear);
      gear.add(index, aGear);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveGearAt(Gear aGear, int index)
  {
    boolean wasAdded = false;
    if(gear.contains(aGear))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGear()) { index = numberOfGear() - 1; }
      gear.remove(aGear);
      gear.add(index, aGear);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addGearAt(aGear, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfCombos()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Combo addCombo(String aName, int aDiscount)
  {
    return new Combo(aName, aDiscount, this);
  }

  public boolean addCombo(Combo aCombo)
  {
    boolean wasAdded = false;
    if (combos.contains(aCombo)) { return false; }
    SnowShoeTour existingSnowShoeTour = aCombo.getSnowShoeTour();
    boolean isNewSnowShoeTour = existingSnowShoeTour != null && !this.equals(existingSnowShoeTour);
    if (isNewSnowShoeTour)
    {
      aCombo.setSnowShoeTour(this);
    }
    else
    {
      combos.add(aCombo);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeCombo(Combo aCombo)
  {
    boolean wasRemoved = false;
    //Unable to remove aCombo, as it must always have a snowShoeTour
    if (!this.equals(aCombo.getSnowShoeTour()))
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfComboItems()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public ComboItem addComboItem(int aQuantity, Combo aCombo, Gear aGear)
  {
    return new ComboItem(aQuantity, this, aCombo, aGear);
  }

  public boolean addComboItem(ComboItem aComboItem)
  {
    boolean wasAdded = false;
    if (comboItems.contains(aComboItem)) { return false; }
    SnowShoeTour existingSnowShoeTour = aComboItem.getSnowShoeTour();
    boolean isNewSnowShoeTour = existingSnowShoeTour != null && !this.equals(existingSnowShoeTour);
    if (isNewSnowShoeTour)
    {
      aComboItem.setSnowShoeTour(this);
    }
    else
    {
      comboItems.add(aComboItem);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeComboItem(ComboItem aComboItem)
  {
    boolean wasRemoved = false;
    //Unable to remove aComboItem, as it must always have a snowShoeTour
    if (!this.equals(aComboItem.getSnowShoeTour()))
    {
      comboItems.remove(aComboItem);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addComboItemAt(ComboItem aComboItem, int index)
  {  
    boolean wasAdded = false;
    if(addComboItem(aComboItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfComboItems()) { index = numberOfComboItems() - 1; }
      comboItems.remove(aComboItem);
      comboItems.add(index, aComboItem);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveComboItemAt(ComboItem aComboItem, int index)
  {
    boolean wasAdded = false;
    if(comboItems.contains(aComboItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfComboItems()) { index = numberOfComboItems() - 1; }
      comboItems.remove(aComboItem);
      comboItems.add(index, aComboItem);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addComboItemAt(aComboItem, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfLodges()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Lodge addLodge(String aName, String aAddress, Lodge.LodgeRating aRating)
  {
    return new Lodge(aName, aAddress, aRating, this);
  }

  public boolean addLodge(Lodge aLodge)
  {
    boolean wasAdded = false;
    if (lodges.contains(aLodge)) { return false; }
    SnowShoeTour existingSnowShoeTour = aLodge.getSnowShoeTour();
    boolean isNewSnowShoeTour = existingSnowShoeTour != null && !this.equals(existingSnowShoeTour);
    if (isNewSnowShoeTour)
    {
      aLodge.setSnowShoeTour(this);
    }
    else
    {
      lodges.add(aLodge);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeLodge(Lodge aLodge)
  {
    boolean wasRemoved = false;
    //Unable to remove aLodge, as it must always have a snowShoeTour
    if (!this.equals(aLodge.getSnowShoeTour()))
    {
      lodges.remove(aLodge);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addLodgeAt(Lodge aLodge, int index)
  {  
    boolean wasAdded = false;
    if(addLodge(aLodge))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLodges()) { index = numberOfLodges() - 1; }
      lodges.remove(aLodge);
      lodges.add(index, aLodge);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveLodgeAt(Lodge aLodge, int index)
  {
    boolean wasAdded = false;
    if(lodges.contains(aLodge))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLodges()) { index = numberOfLodges() - 1; }
      lodges.remove(aLodge);
      lodges.add(index, aLodge);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addLodgeAt(aLodge, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfTours()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Tour addTour(int aId, int aStartWeek, int aEndWeek, Guide aGuide)
  {
    return new Tour(aId, aStartWeek, aEndWeek, aGuide, this);
  }

  public boolean addTour(Tour aTour)
  {
    boolean wasAdded = false;
    if (tours.contains(aTour)) { return false; }
    SnowShoeTour existingSnowShoeTour = aTour.getSnowShoeTour();
    boolean isNewSnowShoeTour = existingSnowShoeTour != null && !this.equals(existingSnowShoeTour);
    if (isNewSnowShoeTour)
    {
      aTour.setSnowShoeTour(this);
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
    //Unable to remove aTour, as it must always have a snowShoeTour
    if (!this.equals(aTour.getSnowShoeTour()))
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

  public void delete()
  {
    Manager existingManager = manager;
    manager = null;
    if (existingManager != null)
    {
      existingManager.delete();
      existingManager.setSnowShoeTour(null);
    }
    while (guides.size() > 0)
    {
      Guide aGuide = guides.get(guides.size() - 1);
      aGuide.delete();
      guides.remove(aGuide);
    }
    
    while (participants.size() > 0)
    {
      Participant aParticipant = participants.get(participants.size() - 1);
      aParticipant.delete();
      participants.remove(aParticipant);
    }
    
    while (bookedItems.size() > 0)
    {
      BookedItem aBookedItem = bookedItems.get(bookedItems.size() - 1);
      aBookedItem.delete();
      bookedItems.remove(aBookedItem);
    }
    
    while (gear.size() > 0)
    {
      Gear aGear = gear.get(gear.size() - 1);
      aGear.delete();
      gear.remove(aGear);
    }
    
    while (combos.size() > 0)
    {
      Combo aCombo = combos.get(combos.size() - 1);
      aCombo.delete();
      combos.remove(aCombo);
    }
    
    while (comboItems.size() > 0)
    {
      ComboItem aComboItem = comboItems.get(comboItems.size() - 1);
      aComboItem.delete();
      comboItems.remove(aComboItem);
    }
    
    while (lodges.size() > 0)
    {
      Lodge aLodge = lodges.get(lodges.size() - 1);
      aLodge.delete();
      lodges.remove(aLodge);
    }
    
    while (tours.size() > 0)
    {
      Tour aTour = tours.get(tours.size() - 1);
      aTour.delete();
      tours.remove(aTour);
    }
    
  }


  public String toString()
  {
    return super.toString() + "["+
            "nrWeeks" + ":" + getNrWeeks()+ "," +
            "priceOfGuidePerWeek" + ":" + getPriceOfGuidePerWeek()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "startDate" + "=" + (getStartDate() != null ? !getStartDate().equals(this)  ? getStartDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "manager = "+(getManager()!=null?Integer.toHexString(System.identityHashCode(getManager())):"null");
  }
}