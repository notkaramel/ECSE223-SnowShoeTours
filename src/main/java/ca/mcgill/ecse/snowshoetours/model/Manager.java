/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

package ca.mcgill.ecse.snowshoetours.model;
import java.util.*;

// line 24 "../../../../../../SnowShoeTour.ump"
public class Manager extends User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Manager Associations
  private SnowShoeTour snowShoeTour;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Manager(String aAccountName, String aPassword, SnowShoeTour aSnowShoeTour)
  {
    super(aAccountName, aPassword);
    boolean didAddSnowShoeTour = setSnowShoeTour(aSnowShoeTour);
    if (!didAddSnowShoeTour)
    {
      throw new RuntimeException("Unable to create manager due to snowShoeTour. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public SnowShoeTour getSnowShoeTour()
  {
    return snowShoeTour;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setSnowShoeTour(SnowShoeTour aNewSnowShoeTour)
  {
    boolean wasSet = false;
    if (aNewSnowShoeTour == null)
    {
      //Unable to setSnowShoeTour to null, as manager must always be associated to a snowShoeTour
      return wasSet;
    }
    
    Manager existingManager = aNewSnowShoeTour.getManager();
    if (existingManager != null && !equals(existingManager))
    {
      //Unable to setSnowShoeTour, the current snowShoeTour already has a manager, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    SnowShoeTour anOldSnowShoeTour = snowShoeTour;
    snowShoeTour = aNewSnowShoeTour;
    snowShoeTour.setManager(this);

    if (anOldSnowShoeTour != null)
    {
      anOldSnowShoeTour.setManager(null);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    SnowShoeTour existingSnowShoeTour = snowShoeTour;
    snowShoeTour = null;
    if (existingSnowShoeTour != null)
    {
      existingSnowShoeTour.setManager(null);
    }
    super.delete();
  }

}