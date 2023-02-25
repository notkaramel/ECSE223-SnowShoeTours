/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

package ca.mcgill.ecse.snowshoetours.model;
import java.sql.Date;
import java.util.*;

// line 8 "../../../../../../SnowShoeTour.ump"
public class Season
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Season Attributes
  private Date startDate;
  private Date endDate;
  private int numberOfWeeks;

  //Season Associations
  private List<Week> weeks;
  private SnowShoeTour snowShoeTour;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Season(Date aStartDate, Date aEndDate, int aNumberOfWeeks, SnowShoeTour aSnowShoeTour)
  {
    startDate = aStartDate;
    endDate = aEndDate;
    numberOfWeeks = aNumberOfWeeks;
    weeks = new ArrayList<Week>();
    boolean didAddSnowShoeTour = setSnowShoeTour(aSnowShoeTour);
    if (!didAddSnowShoeTour)
    {
      throw new RuntimeException("Unable to create season due to snowShoeTour. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
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

  public boolean setEndDate(Date aEndDate)
  {
    boolean wasSet = false;
    endDate = aEndDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setNumberOfWeeks(int aNumberOfWeeks)
  {
    boolean wasSet = false;
    numberOfWeeks = aNumberOfWeeks;
    wasSet = true;
    return wasSet;
  }

  public Date getStartDate()
  {
    return startDate;
  }

  public Date getEndDate()
  {
    return endDate;
  }

  public int getNumberOfWeeks()
  {
    return numberOfWeeks;
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
  public SnowShoeTour getSnowShoeTour()
  {
    return snowShoeTour;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfWeeks()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Week addWeek(SnowShoeTour aSnowShoeTour)
  {
    return new Week(this, aSnowShoeTour);
  }

  public boolean addWeek(Week aWeek)
  {
    boolean wasAdded = false;
    if (weeks.contains(aWeek)) { return false; }
    Season existingSeason = aWeek.getSeason();
    boolean isNewSeason = existingSeason != null && !this.equals(existingSeason);
    if (isNewSeason)
    {
      aWeek.setSeason(this);
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
    //Unable to remove aWeek, as it must always have a season
    if (!this.equals(aWeek.getSeason()))
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
      existingSnowShoeTour.removeSeason(this);
    }
    snowShoeTour.addSeason(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=weeks.size(); i > 0; i--)
    {
      Week aWeek = weeks.get(i - 1);
      aWeek.delete();
    }
    SnowShoeTour placeholderSnowShoeTour = snowShoeTour;
    this.snowShoeTour = null;
    if(placeholderSnowShoeTour != null)
    {
      placeholderSnowShoeTour.removeSeason(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "numberOfWeeks" + ":" + getNumberOfWeeks()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "startDate" + "=" + (getStartDate() != null ? !getStartDate().equals(this)  ? getStartDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endDate" + "=" + (getEndDate() != null ? !getEndDate().equals(this)  ? getEndDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "snowShoeTour = "+(getSnowShoeTour()!=null?Integer.toHexString(System.identityHashCode(getSnowShoeTour())):"null");
  }
}