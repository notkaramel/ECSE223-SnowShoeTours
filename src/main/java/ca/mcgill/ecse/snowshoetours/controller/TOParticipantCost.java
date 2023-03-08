/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

package ca.mcgill.ecse.snowshoetours.controller;

// line 14 "../../../../../../SnowShoeTourTransferObjects.ump"
public class TOParticipantCost
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOParticipantCost Attributes
  private String participantEmail;
  private String participantName;
  private int totalCostForBookableItems;
  private int totalCostForSnowShoeTour;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOParticipantCost(String aParticipantEmail, String aParticipantName, int aTotalCostForBookableItems, int aTotalCostForSnowShoeTour)
  {
    participantEmail = aParticipantEmail;
    participantName = aParticipantName;
    totalCostForBookableItems = aTotalCostForBookableItems;
    totalCostForSnowShoeTour = aTotalCostForSnowShoeTour;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public String getParticipantEmail()
  {
    return participantEmail;
  }

  public String getParticipantName()
  {
    return participantName;
  }

  public int getTotalCostForBookableItems()
  {
    return totalCostForBookableItems;
  }

  public int getTotalCostForSnowShoeTour()
  {
    return totalCostForSnowShoeTour;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "participantEmail" + ":" + getParticipantEmail()+ "," +
            "participantName" + ":" + getParticipantName()+ "," +
            "totalCostForBookableItems" + ":" + getTotalCostForBookableItems()+ "," +
            "totalCostForSnowShoeTour" + ":" + getTotalCostForSnowShoeTour()+ "]";
  }
}