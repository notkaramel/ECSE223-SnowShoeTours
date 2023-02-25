/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

package ca.mcgill.ecse.snowshoetours.model;
import java.util.*;
import java.sql.Date;

// line 90 "../../../../../../SnowShoeTour.ump"
public class SnowShoeTour
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //SnowShoeTour Associations
  private List<Person> persons;
  private List<Registration> registrations;
  private List<Week> weeks;
  private List<Guide> guides;
  private List<Season> seasons;
  private List<Participant> participants;
  private List<Tour> tours;
  private List<Manager> managers;
  private List<GearRequest> gearRequests;
  private List<Combo> combos;
  private List<LodgeRental> lodgeRentals;
  private List<Lodge> lodges;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public SnowShoeTour()
  {
    persons = new ArrayList<Person>();
    registrations = new ArrayList<Registration>();
    weeks = new ArrayList<Week>();
    guides = new ArrayList<Guide>();
    seasons = new ArrayList<Season>();
    participants = new ArrayList<Participant>();
    tours = new ArrayList<Tour>();
    managers = new ArrayList<Manager>();
    gearRequests = new ArrayList<GearRequest>();
    combos = new ArrayList<Combo>();
    lodgeRentals = new ArrayList<LodgeRental>();
    lodges = new ArrayList<Lodge>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public Person getPerson(int index)
  {
    Person aPerson = persons.get(index);
    return aPerson;
  }

  public List<Person> getPersons()
  {
    List<Person> newPersons = Collections.unmodifiableList(persons);
    return newPersons;
  }

  public int numberOfPersons()
  {
    int number = persons.size();
    return number;
  }

  public boolean hasPersons()
  {
    boolean has = persons.size() > 0;
    return has;
  }

  public int indexOfPerson(Person aPerson)
  {
    int index = persons.indexOf(aPerson);
    return index;
  }
  /* Code from template association_GetMany */
  public Registration getRegistration(int index)
  {
    Registration aRegistration = registrations.get(index);
    return aRegistration;
  }

  public List<Registration> getRegistrations()
  {
    List<Registration> newRegistrations = Collections.unmodifiableList(registrations);
    return newRegistrations;
  }

  public int numberOfRegistrations()
  {
    int number = registrations.size();
    return number;
  }

  public boolean hasRegistrations()
  {
    boolean has = registrations.size() > 0;
    return has;
  }

  public int indexOfRegistration(Registration aRegistration)
  {
    int index = registrations.indexOf(aRegistration);
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
  public Manager getManager(int index)
  {
    Manager aManager = managers.get(index);
    return aManager;
  }

  public List<Manager> getManagers()
  {
    List<Manager> newManagers = Collections.unmodifiableList(managers);
    return newManagers;
  }

  public int numberOfManagers()
  {
    int number = managers.size();
    return number;
  }

  public boolean hasManagers()
  {
    boolean has = managers.size() > 0;
    return has;
  }

  public int indexOfManager(Manager aManager)
  {
    int index = managers.indexOf(aManager);
    return index;
  }
  /* Code from template association_GetMany */
  public GearRequest getGearRequest(int index)
  {
    GearRequest aGearRequest = gearRequests.get(index);
    return aGearRequest;
  }

  public List<GearRequest> getGearRequests()
  {
    List<GearRequest> newGearRequests = Collections.unmodifiableList(gearRequests);
    return newGearRequests;
  }

  public int numberOfGearRequests()
  {
    int number = gearRequests.size();
    return number;
  }

  public boolean hasGearRequests()
  {
    boolean has = gearRequests.size() > 0;
    return has;
  }

  public int indexOfGearRequest(GearRequest aGearRequest)
  {
    int index = gearRequests.indexOf(aGearRequest);
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
  public LodgeRental getLodgeRental(int index)
  {
    LodgeRental aLodgeRental = lodgeRentals.get(index);
    return aLodgeRental;
  }

  public List<LodgeRental> getLodgeRentals()
  {
    List<LodgeRental> newLodgeRentals = Collections.unmodifiableList(lodgeRentals);
    return newLodgeRentals;
  }

  public int numberOfLodgeRentals()
  {
    int number = lodgeRentals.size();
    return number;
  }

  public boolean hasLodgeRentals()
  {
    boolean has = lodgeRentals.size() > 0;
    return has;
  }

  public int indexOfLodgeRental(LodgeRental aLodgeRental)
  {
    int index = lodgeRentals.indexOf(aLodgeRental);
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPersons()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */


  public boolean addPerson(Person aPerson)
  {
    boolean wasAdded = false;
    if (persons.contains(aPerson)) { return false; }
    SnowShoeTour existingSnowShoeTour = aPerson.getSnowShoeTour();
    boolean isNewSnowShoeTour = existingSnowShoeTour != null && !this.equals(existingSnowShoeTour);
    if (isNewSnowShoeTour)
    {
      aPerson.setSnowShoeTour(this);
    }
    else
    {
      persons.add(aPerson);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePerson(Person aPerson)
  {
    boolean wasRemoved = false;
    //Unable to remove aPerson, as it must always have a snowShoeTour
    if (!this.equals(aPerson.getSnowShoeTour()))
    {
      persons.remove(aPerson);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPersonAt(Person aPerson, int index)
  {  
    boolean wasAdded = false;
    if(addPerson(aPerson))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPersons()) { index = numberOfPersons() - 1; }
      persons.remove(aPerson);
      persons.add(index, aPerson);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePersonAt(Person aPerson, int index)
  {
    boolean wasAdded = false;
    if(persons.contains(aPerson))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPersons()) { index = numberOfPersons() - 1; }
      persons.remove(aPerson);
      persons.add(index, aPerson);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPersonAt(aPerson, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfRegistrations()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Registration addRegistration(int aTourCost, String aAuthCode, double aRefundRate, boolean aLodgeRented, Tour aTour, Participant aParticipant)
  {
    return new Registration(aTourCost, aAuthCode, aRefundRate, aLodgeRented, aTour, aParticipant, this);
  }

  public boolean addRegistration(Registration aRegistration)
  {
    boolean wasAdded = false;
    if (registrations.contains(aRegistration)) { return false; }
    SnowShoeTour existingSnowShoeTour = aRegistration.getSnowShoeTour();
    boolean isNewSnowShoeTour = existingSnowShoeTour != null && !this.equals(existingSnowShoeTour);
    if (isNewSnowShoeTour)
    {
      aRegistration.setSnowShoeTour(this);
    }
    else
    {
      registrations.add(aRegistration);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeRegistration(Registration aRegistration)
  {
    boolean wasRemoved = false;
    //Unable to remove aRegistration, as it must always have a snowShoeTour
    if (!this.equals(aRegistration.getSnowShoeTour()))
    {
      registrations.remove(aRegistration);
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
      if(index > numberOfRegistrations()) { index = numberOfRegistrations() - 1; }
      registrations.remove(aRegistration);
      registrations.add(index, aRegistration);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveRegistrationAt(Registration aRegistration, int index)
  {
    boolean wasAdded = false;
    if(registrations.contains(aRegistration))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRegistrations()) { index = numberOfRegistrations() - 1; }
      registrations.remove(aRegistration);
      registrations.add(index, aRegistration);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addRegistrationAt(aRegistration, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfWeeks()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Week addWeek(Season aSeason)
  {
    return new Week(aSeason, this);
  }

  public boolean addWeek(Week aWeek)
  {
    boolean wasAdded = false;
    if (weeks.contains(aWeek)) { return false; }
    SnowShoeTour existingSnowShoeTour = aWeek.getSnowShoeTour();
    boolean isNewSnowShoeTour = existingSnowShoeTour != null && !this.equals(existingSnowShoeTour);
    if (isNewSnowShoeTour)
    {
      aWeek.setSnowShoeTour(this);
    }
    else
    {
      weeks.add(aWeek);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeWeek(Week aWeek)
  {
    boolean wasRemoved = false;
    //Unable to remove aWeek, as it must always have a snowShoeTour
    if (!this.equals(aWeek.getSnowShoeTour()))
    {
      weeks.remove(aWeek);
      wasRemoved = true;
    }
    return wasRemoved;
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfGuides()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Guide addGuide(String aEmail, String aName, String aAccountName, String aPassword, String aEmergencyContact, int aWeeklyCost)
  {
    return new Guide(aEmail, aName, aAccountName, aPassword, aEmergencyContact, this, aWeeklyCost, this);
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
  public static int minimumNumberOfSeasons()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Season addSeason(Date aStartDate, Date aEndDate, int aNumberOfWeeks)
  {
    return new Season(aStartDate, aEndDate, aNumberOfWeeks, this);
  }

  public boolean addSeason(Season aSeason)
  {
    boolean wasAdded = false;
    if (seasons.contains(aSeason)) { return false; }
    SnowShoeTour existingSnowShoeTour = aSeason.getSnowShoeTour();
    boolean isNewSnowShoeTour = existingSnowShoeTour != null && !this.equals(existingSnowShoeTour);
    if (isNewSnowShoeTour)
    {
      aSeason.setSnowShoeTour(this);
    }
    else
    {
      seasons.add(aSeason);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSeason(Season aSeason)
  {
    boolean wasRemoved = false;
    //Unable to remove aSeason, as it must always have a snowShoeTour
    if (!this.equals(aSeason.getSnowShoeTour()))
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
  public static int minimumNumberOfParticipants()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Participant addParticipant(String aEmail, String aName, String aAccountName, String aPassword, String aEmergencyContact, int aNumberOfWeeks, Week aStartWeek, Week aEndWeek, Tour aTour, Registration aRegistration)
  {
    return new Participant(aEmail, aName, aAccountName, aPassword, aEmergencyContact, this, aNumberOfWeeks, aStartWeek, aEndWeek, aTour, aRegistration, this);
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
  public static int minimumNumberOfTours()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Tour addTour(int aGuideCost, Lodge aLodge, Guide aGuide, Registration aRegistration, Week... allWeeks)
  {
    return new Tour(aGuideCost, aLodge, aGuide, aRegistration, this, allWeeks);
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfManagers()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Manager addManager()
  {
    return new Manager(this);
  }

  public boolean addManager(Manager aManager)
  {
    boolean wasAdded = false;
    if (managers.contains(aManager)) { return false; }
    SnowShoeTour existingSnowShoeTour = aManager.getSnowShoeTour();
    boolean isNewSnowShoeTour = existingSnowShoeTour != null && !this.equals(existingSnowShoeTour);
    if (isNewSnowShoeTour)
    {
      aManager.setSnowShoeTour(this);
    }
    else
    {
      managers.add(aManager);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeManager(Manager aManager)
  {
    boolean wasRemoved = false;
    //Unable to remove aManager, as it must always have a snowShoeTour
    if (!this.equals(aManager.getSnowShoeTour()))
    {
      managers.remove(aManager);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addManagerAt(Manager aManager, int index)
  {  
    boolean wasAdded = false;
    if(addManager(aManager))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfManagers()) { index = numberOfManagers() - 1; }
      managers.remove(aManager);
      managers.add(index, aManager);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveManagerAt(Manager aManager, int index)
  {
    boolean wasAdded = false;
    if(managers.contains(aManager))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfManagers()) { index = numberOfManagers() - 1; }
      managers.remove(aManager);
      managers.add(index, aManager);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addManagerAt(aManager, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfGearRequests()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public GearRequest addGearRequest(int aCost, GearRequest.GearType aType)
  {
    return new GearRequest(aCost, aType, this);
  }

  public boolean addGearRequest(GearRequest aGearRequest)
  {
    boolean wasAdded = false;
    if (gearRequests.contains(aGearRequest)) { return false; }
    SnowShoeTour existingSnowShoeTour = aGearRequest.getSnowShoeTour();
    boolean isNewSnowShoeTour = existingSnowShoeTour != null && !this.equals(existingSnowShoeTour);
    if (isNewSnowShoeTour)
    {
      aGearRequest.setSnowShoeTour(this);
    }
    else
    {
      gearRequests.add(aGearRequest);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeGearRequest(GearRequest aGearRequest)
  {
    boolean wasRemoved = false;
    //Unable to remove aGearRequest, as it must always have a snowShoeTour
    if (!this.equals(aGearRequest.getSnowShoeTour()))
    {
      gearRequests.remove(aGearRequest);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addGearRequestAt(GearRequest aGearRequest, int index)
  {  
    boolean wasAdded = false;
    if(addGearRequest(aGearRequest))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGearRequests()) { index = numberOfGearRequests() - 1; }
      gearRequests.remove(aGearRequest);
      gearRequests.add(index, aGearRequest);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveGearRequestAt(GearRequest aGearRequest, int index)
  {
    boolean wasAdded = false;
    if(gearRequests.contains(aGearRequest))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGearRequests()) { index = numberOfGearRequests() - 1; }
      gearRequests.remove(aGearRequest);
      gearRequests.add(index, aGearRequest);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addGearRequestAt(aGearRequest, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfCombos()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Combo addCombo(int aComboPrice, String aComboName, LodgeRental aLodgeRental, GearRequest... allGears)
  {
    return new Combo(aComboPrice, aComboName, aLodgeRental, this, allGears);
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
  public static int minimumNumberOfLodgeRentals()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public LodgeRental addLodgeRental(double aDiscount, Lodge aLodge, Combo aCombo)
  {
    return new LodgeRental(aDiscount, aLodge, aCombo, this);
  }

  public boolean addLodgeRental(LodgeRental aLodgeRental)
  {
    boolean wasAdded = false;
    if (lodgeRentals.contains(aLodgeRental)) { return false; }
    SnowShoeTour existingSnowShoeTour = aLodgeRental.getSnowShoeTour();
    boolean isNewSnowShoeTour = existingSnowShoeTour != null && !this.equals(existingSnowShoeTour);
    if (isNewSnowShoeTour)
    {
      aLodgeRental.setSnowShoeTour(this);
    }
    else
    {
      lodgeRentals.add(aLodgeRental);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeLodgeRental(LodgeRental aLodgeRental)
  {
    boolean wasRemoved = false;
    //Unable to remove aLodgeRental, as it must always have a snowShoeTour
    if (!this.equals(aLodgeRental.getSnowShoeTour()))
    {
      lodgeRentals.remove(aLodgeRental);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addLodgeRentalAt(LodgeRental aLodgeRental, int index)
  {  
    boolean wasAdded = false;
    if(addLodgeRental(aLodgeRental))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLodgeRentals()) { index = numberOfLodgeRentals() - 1; }
      lodgeRentals.remove(aLodgeRental);
      lodgeRentals.add(index, aLodgeRental);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveLodgeRentalAt(LodgeRental aLodgeRental, int index)
  {
    boolean wasAdded = false;
    if(lodgeRentals.contains(aLodgeRental))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLodgeRentals()) { index = numberOfLodgeRentals() - 1; }
      lodgeRentals.remove(aLodgeRental);
      lodgeRentals.add(index, aLodgeRental);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addLodgeRentalAt(aLodgeRental, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfLodges()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Lodge addLodge(String aName, String aAddress, Lodge.LodgeClass aLodgeClass, LodgeRental aLodgeRental)
  {
    return new Lodge(aName, aAddress, aLodgeClass, aLodgeRental, this);
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

  public void delete()
  {
    while (persons.size() > 0)
    {
      Person aPerson = persons.get(persons.size() - 1);
      aPerson.delete();
      persons.remove(aPerson);
    }
    
    while (registrations.size() > 0)
    {
      Registration aRegistration = registrations.get(registrations.size() - 1);
      aRegistration.delete();
      registrations.remove(aRegistration);
    }
    
    while (weeks.size() > 0)
    {
      Week aWeek = weeks.get(weeks.size() - 1);
      aWeek.delete();
      weeks.remove(aWeek);
    }
    
    while (guides.size() > 0)
    {
      Guide aGuide = guides.get(guides.size() - 1);
      aGuide.delete();
      guides.remove(aGuide);
    }
    
    while (seasons.size() > 0)
    {
      Season aSeason = seasons.get(seasons.size() - 1);
      aSeason.delete();
      seasons.remove(aSeason);
    }
    
    while (participants.size() > 0)
    {
      Participant aParticipant = participants.get(participants.size() - 1);
      aParticipant.delete();
      participants.remove(aParticipant);
    }
    
    while (tours.size() > 0)
    {
      Tour aTour = tours.get(tours.size() - 1);
      aTour.delete();
      tours.remove(aTour);
    }
    
    while (managers.size() > 0)
    {
      Manager aManager = managers.get(managers.size() - 1);
      aManager.delete();
      managers.remove(aManager);
    }
    
    while (gearRequests.size() > 0)
    {
      GearRequest aGearRequest = gearRequests.get(gearRequests.size() - 1);
      aGearRequest.delete();
      gearRequests.remove(aGearRequest);
    }
    
    while (combos.size() > 0)
    {
      Combo aCombo = combos.get(combos.size() - 1);
      aCombo.delete();
      combos.remove(aCombo);
    }
    
    while (lodgeRentals.size() > 0)
    {
      LodgeRental aLodgeRental = lodgeRentals.get(lodgeRentals.size() - 1);
      aLodgeRental.delete();
      lodgeRentals.remove(aLodgeRental);
    }
    
    while (lodges.size() > 0)
    {
      Lodge aLodge = lodges.get(lodges.size() - 1);
      aLodge.delete();
      lodges.remove(aLodge);
    }
    
  }

}