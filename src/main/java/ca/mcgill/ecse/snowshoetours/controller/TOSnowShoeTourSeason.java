//%% NEW FILE TOSnowShoeSeason BEGINS HERE %%

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

package ca.mcgill.ecse.snowshoetours.controller;
import java.sql.Date;

// line 4 "../../../../../../model.ump"
// line 14 "../../../../../../model.ump"
public class TOSnowShoeTourSeason
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOSnowShoeSeason Attributes
  private Date startDate;
  private int nrWeeks;
  private int guideCost;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOSnowShoeTourSeason(Date aStartDate, int aNrWeeks, int aGuideCost)
  {
    startDate = aStartDate;
    nrWeeks = aNrWeeks;
    guideCost = aGuideCost;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Date getStartDate()
  {
    return startDate;
  }

  public int getNrWeeks()
  {
    return nrWeeks;
  }

  public int getGuideCost()
  {
    return guideCost;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "nrWeeks" + ":" + getNrWeeks()+ "," +
            "guideCost" + ":" + getGuideCost()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "startDate" + "=" + (getStartDate() != null ? !getStartDate().equals(this)  ? getStartDate().toString().replaceAll("  ","    ") : "this" : "null");
  }
}