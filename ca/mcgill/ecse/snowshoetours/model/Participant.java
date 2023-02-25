/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

package ca.mcgill.ecse.snowshoetours.model;
import java.util.*;

// line 42 "../../../../../../SnowShoeTour.ump"
public class Participant extends Person
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Participant Attributes
  private int numberOfWeeks;
  private Week startWeek;
  private Week endWeek;

  //Participant Associations
  private Tour tour;
  private Registration registration;
  private SnowShoeTour snowShoeTour;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Participant(String aEmail, String aName, String aAccountName, String aPassword, String aEmergencyContact, SnowShoeTour aSnowShoeTour, int aNumberOfWeeks, Week aStartWeek, Week aEndWeek, Tour aTour, Registration aRegistration, SnowShoeTour aSnowShoeTour)
  {
    super(aEmail, aName, aAccountName, aPassword, aEmergencyContact, aSnowShoeTour);
    numberOfWeeks = aNumberOfWeeks;
    startWeek = aStartWeek;
    endWeek = aEndWeek;
    boolean didAddTour = setTour(aTour);
    if (!didAddTour)
    {
      throw new RuntimeException("Unable to create participant due to tour. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    if (aRegistration == null || aRegistration.getParticipant() != null)
    {
      throw new RuntimeException("Unable to create Participant due to aRegistration. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    registration = aRegistration;
    boolean didAddSnowShoeTour = setSnowShoeTour(aSnowShoeTour);
    if (!didAddSnowShoeTour)
    {
      throw new RuntimeException("Unable to create participant due to snowShoeTour. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  public Participant(String aEmail, String aName, String aAccountName, String aPassword, String aEmergencyContact, SnowShoeTour aSnowShoeTour, int aNumberOfWeeks, Week aStartWeek, Week aEndWeek, Tour aTour, int aTourCostForRegistration, String aAuthCodeForRegistration, double aRefundRateForRegistration, boolean aLodgeRentedForRegistration, Tour aTourForRegistration, SnowShoeTour aSnowShoeTourForRegistration, SnowShoeTour aSnowShoeTour)
  {
    super(aEmail, aName, aAccountName, aPassword, aEmergencyContact, aSnowShoeTour);
    numberOfWeeks = aNumberOfWeeks;
    startWeek = aStartWeek;
    endWeek = aEndWeek;
    boolean didAddTour = setTour(aTour);
    if (!didAddTour)
    {
      throw new RuntimeException("Unable to create participant due to tour. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    registration = new Registration(aTourCostForRegistration, aAuthCodeForRegistration, aRefundRateForRegistration, aLodgeRentedForRegistration, aTourForRegistration, this, aSnowShoeTourForRegistration);
    boolean didAddSnowShoeTour = setSnowShoeTour(aSnowShoeTour);
    if (!didAddSnowShoeTour)
    {
      throw new RuntimeException("Unable to create participant due to snowShoeTour. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setNumberOfWeeks(int aNumberOfWeeks)
  {
    boolean wasSet = false;
    numberOfWeeks = aNumberOfWeeks;
    wasSet = true;
    return wasSet;
  }

  public boolean setStartWeek(Week aStartWeek)
  {
    boolean wasSet = false;
    startWeek = aStartWeek;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndWeek(Week aEndWeek)
  {
    boolean wasSet = false;
    endWeek = aEndWeek;
    wasSet = true;
    return wasSet;
  }

  /**
   * email (and password) must be unique
   */
  public int getNumberOfWeeks()
  {
    return numberOfWeeks;
  }

  public Week getStartWeek()
  {
    return startWeek;
  }

  public Week getEndWeek()
  {
    return endWeek;
  }
  /* Code from template association_GetOne */
  public Tour getTour()
  {
    return tour;
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
  public boolean setTour(Tour aTour)
  {
    boolean wasSet = false;
    if (aTour == null)
    {
      return wasSet;
    }

    Tour existingTour = tour;
    tour = aTour;
    if (existingTour != null && !existingTour.equals(aTour))
    {
      existingTour.removeParticipant(this);
    }
    tour.addParticipant(this);
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
      existingSnowShoeTour.removeParticipant(this);
    }
    snowShoeTour.addParticipant(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Tour placeholderTour = tour;
    this.tour = null;
    if(placeholderTour != null)
    {
      placeholderTour.removeParticipant(this);
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
      placeholderSnowShoeTour.removeParticipant(this);
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "numberOfWeeks" + ":" + getNumberOfWeeks()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "startWeek" + "=" + (getStartWeek() != null ? !getStartWeek().equals(this)  ? getStartWeek().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endWeek" + "=" + (getEndWeek() != null ? !getEndWeek().equals(this)  ? getEndWeek().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "tour = "+(getTour()!=null?Integer.toHexString(System.identityHashCode(getTour())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "registration = "+(getRegistration()!=null?Integer.toHexString(System.identityHashCode(getRegistration())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "snowShoeTour = "+(getSnowShoeTour()!=null?Integer.toHexString(System.identityHashCode(getSnowShoeTour())):"null");
  }
}