/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

package ca.mcgill.ecse.snowshoetours.controller;

// line 38 "../../../../../../SnowShoeTourTransferObjects.ump"
public class TOGuide
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOGuide Attributes
  private String guideName;
  private String guideEmail;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOGuide(String aGuideName, String aGuideEmail)
  {
    guideName = aGuideName;
    guideEmail = aGuideEmail;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public String getGuideName()
  {
    return guideName;
  }

  public String getGuideEmail()
  {
    return guideEmail;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "guideName" + ":" + getGuideName()+ "," +
            "guideEmail" + ":" + getGuideEmail()+ "]";
  }
}