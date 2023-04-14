/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

package ca.mcgill.ecse.snowshoetours.controller;

// line 22 "../../../../../../SnowShoeTourTransferObjects.ump"
public class TOParticipant
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOParticipant Attributes
  private String participantEmail;
  private String participantName;
  private String authorizationCode;
  private int totalCost;
  private String status;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOParticipant(String aParticipantEmail, String aParticipantName, String aAuthorizationCode, int aTotalCost, String aStatus)
  {
    participantEmail = aParticipantEmail;
    participantName = aParticipantName;
    authorizationCode = aAuthorizationCode;
    totalCost = aTotalCost;
    status = aStatus;
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

  public String getAuthorizationCode()
  {
    return authorizationCode;
  }

  public int getTotalCost()
  {
    return totalCost;
  }

  public String getStatus()
  {
    return status;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "participantEmail" + ":" + getParticipantEmail()+ "," +
            "participantName" + ":" + getParticipantName()+ "," +
            "authorizationCode" + ":" + getAuthorizationCode()+ "," +
            "totalCost" + ":" + getTotalCost()+ "," +
            "status" + ":" + getStatus()+ "]";
  }
}