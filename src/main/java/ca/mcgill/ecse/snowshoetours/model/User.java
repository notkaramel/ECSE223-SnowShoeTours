/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

package ca.mcgill.ecse.snowshoetours.model;
import java.util.*;

// line 18 "../../../../../../SnowShoeTour.ump"
public abstract class User
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, User> usersByAccountName = new HashMap<String, User>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //User Attributes
  private String accountName;
  private String password;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public User(String aAccountName, String aPassword)
  {
    password = aPassword;
    if (!setAccountName(aAccountName))
    {
      throw new RuntimeException("Cannot create due to duplicate accountName. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setAccountName(String aAccountName)
  {
    boolean wasSet = false;
    String anOldAccountName = getAccountName();
    if (anOldAccountName != null && anOldAccountName.equals(aAccountName)) {
      return true;
    }
    if (hasWithAccountName(aAccountName)) {
      return wasSet;
    }
    accountName = aAccountName;
    wasSet = true;
    if (anOldAccountName != null) {
      usersByAccountName.remove(anOldAccountName);
    }
    usersByAccountName.put(aAccountName, this);
    return wasSet;
  }

  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

  public String getAccountName()
  {
    return accountName;
  }
  /* Code from template attribute_GetUnique */
  public static User getWithAccountName(String aAccountName)
  {
    return usersByAccountName.get(aAccountName);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithAccountName(String aAccountName)
  {
    return getWithAccountName(aAccountName) != null;
  }

  public String getPassword()
  {
    return password;
  }

  public void delete()
  {
    usersByAccountName.remove(getAccountName());
  }


  public String toString()
  {
    return super.toString() + "["+
            "accountName" + ":" + getAccountName()+ "," +
            "password" + ":" + getPassword()+ "]";
  }
}