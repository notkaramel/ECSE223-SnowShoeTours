/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

package ca.mcgill.ecse.snowshoetours.model;
import java.util.*;

// line 33 "../../../../../../SnowShoeTour.ump"
public abstract class Person
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, Person> personsByEmail = new HashMap<String, Person>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Person Attributes
  private String email;
  private String name;
  private String accountName;
  private String password;
  private String emergencyContact;

  //Person Associations
  private SnowShoeTour snowShoeTour;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Person(String aEmail, String aName, String aAccountName, String aPassword, String aEmergencyContact, SnowShoeTour aSnowShoeTour)
  {
    name = aName;
    accountName = aAccountName;
    password = aPassword;
    emergencyContact = aEmergencyContact;
    if (!setEmail(aEmail))
    {
      throw new RuntimeException("Cannot create due to duplicate email. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    boolean didAddSnowShoeTour = setSnowShoeTour(aSnowShoeTour);
    if (!didAddSnowShoeTour)
    {
      throw new RuntimeException("Unable to create person due to snowShoeTour. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setEmail(String aEmail)
  {
    boolean wasSet = false;
    String anOldEmail = getEmail();
    if (anOldEmail != null && anOldEmail.equals(aEmail)) {
      return true;
    }
    if (hasWithEmail(aEmail)) {
      return wasSet;
    }
    email = aEmail;
    wasSet = true;
    if (anOldEmail != null) {
      personsByEmail.remove(anOldEmail);
    }
    personsByEmail.put(aEmail, this);
    return wasSet;
  }

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setAccountName(String aAccountName)
  {
    boolean wasSet = false;
    accountName = aAccountName;
    wasSet = true;
    return wasSet;
  }

  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

  public boolean setEmergencyContact(String aEmergencyContact)
  {
    boolean wasSet = false;
    emergencyContact = aEmergencyContact;
    wasSet = true;
    return wasSet;
  }

  /**
   * make Person an abstract class
   */
  public String getEmail()
  {
    return email;
  }
  /* Code from template attribute_GetUnique */
  public static Person getWithEmail(String aEmail)
  {
    return personsByEmail.get(aEmail);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithEmail(String aEmail)
  {
    return getWithEmail(aEmail) != null;
  }

  public String getName()
  {
    return name;
  }

  public String getAccountName()
  {
    return accountName;
  }

  public String getPassword()
  {
    return password;
  }

  public String getEmergencyContact()
  {
    return emergencyContact;
  }
  /* Code from template association_GetOne */
  public SnowShoeTour getSnowShoeTour()
  {
    return snowShoeTour;
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
      existingSnowShoeTour.removePerson(this);
    }
    snowShoeTour.addPerson(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    personsByEmail.remove(getEmail());
    SnowShoeTour placeholderSnowShoeTour = snowShoeTour;
    this.snowShoeTour = null;
    if(placeholderSnowShoeTour != null)
    {
      placeholderSnowShoeTour.removePerson(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "email" + ":" + getEmail()+ "," +
            "name" + ":" + getName()+ "," +
            "accountName" + ":" + getAccountName()+ "," +
            "password" + ":" + getPassword()+ "," +
            "emergencyContact" + ":" + getEmergencyContact()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "snowShoeTour = "+(getSnowShoeTour()!=null?Integer.toHexString(System.identityHashCode(getSnowShoeTour())):"null");
  }
}