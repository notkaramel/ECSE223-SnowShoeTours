/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

package ca.mcgill.ecse.snowshoetours.model;

// line 66 "../../../../../SnowShoeTour.ump"
public class LodgeRental
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //LodgeRental Attributes
  private double discount;

  //LodgeRental Associations
  private Lodge lodge;
  private Combo combo;
  private SnowShoeTour snowShoeTour;

  //Helper Variables
  private int cachedHashCode;
  private boolean canSetLodge;
  private boolean canSetCombo;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public LodgeRental(double aDiscount, Lodge aLodge, Combo aCombo, SnowShoeTour aSnowShoeTour)
  {
    cachedHashCode = -1;
    canSetLodge = true;
    canSetCombo = true;
    discount = aDiscount;
    if (aLodge == null || aLodge.getLodgeRental() != null)
    {
      throw new RuntimeException("Unable to create LodgeRental due to aLodge. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    lodge = aLodge;
    if (aCombo == null || aCombo.getLodgeRental() != null)
    {
      throw new RuntimeException("Unable to create LodgeRental due to aCombo. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    combo = aCombo;
    boolean didAddSnowShoeTour = setSnowShoeTour(aSnowShoeTour);
    if (!didAddSnowShoeTour)
    {
      throw new RuntimeException("Unable to create lodgeRental due to snowShoeTour. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  public LodgeRental(double aDiscount, String aNameForLodge, String aAddressForLodge, LodgeClass aLodgeClassForLodge, SnowShoeTour aSnowShoeTourForLodge, int aComboPriceForCombo, String aComboNameForCombo, SnowShoeTour aSnowShoeTourForCombo, GearRequest... allGearsForCombo, SnowShoeTour aSnowShoeTour)
  {
    discount = aDiscount;
    lodge = new Lodge(aNameForLodge, aAddressForLodge, aLodgeClassForLodge, this, aSnowShoeTourForLodge);
    combo = new Combo(aComboPriceForCombo, aComboNameForCombo, this, aSnowShoeTourForCombo, allGearsForCombo);
    boolean didAddSnowShoeTour = setSnowShoeTour(aSnowShoeTour);
    if (!didAddSnowShoeTour)
    {
      throw new RuntimeException("Unable to create lodgeRental due to snowShoeTour. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDiscount(double aDiscount)
  {
    boolean wasSet = false;
    discount = aDiscount;
    wasSet = true;
    return wasSet;
  }

  public double getDiscount()
  {
    return discount;
  }
  /* Code from template association_GetOne */
  public Lodge getLodge()
  {
    return lodge;
  }
  /* Code from template association_GetOne */
  public Combo getCombo()
  {
    return combo;
  }
  /* Code from template association_GetOne */
  public SnowShoeTour getSnowShoeTour()
  {
    return snowShoeTour;
  }
  /* Code from template association_SetOneToManyAssociationClass */
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
      existingSnowShoeTour.removeLodgeRental(this);
    }
    if (!snowShoeTour.addLodgeRental(this))
    {
      snowShoeTour = existingSnowShoeTour;
      wasSet = false;
    }
    else
    {
      wasSet = true;
    }
    return wasSet;
  }

  public boolean equals(Object obj)
  {
    if (obj == null) { return false; }
    if (!getClass().equals(obj.getClass())) { return false; }

    LodgeRental compareTo = (LodgeRental)obj;
  
    if (getLodge() == null && compareTo.getLodge() != null)
    {
      return false;
    }
    else if (getLodge() != null && !getLodge().equals(compareTo.getLodge()))
    {
      return false;
    }

    if (getCombo() == null && compareTo.getCombo() != null)
    {
      return false;
    }
    else if (getCombo() != null && !getCombo().equals(compareTo.getCombo()))
    {
      return false;
    }

    return true;
  }

  public int hashCode()
  {
    if (cachedHashCode != -1)
    {
      return cachedHashCode;
    }
    cachedHashCode = 17;
    if (getLodge() != null)
    {
      cachedHashCode = cachedHashCode * 23 + getLodge().hashCode();
    }
    else
    {
      cachedHashCode = cachedHashCode * 23;
    }
    if (getCombo() != null)
    {
      cachedHashCode = cachedHashCode * 23 + getCombo().hashCode();
    }
    else
    {
      cachedHashCode = cachedHashCode * 23;
    }

    canSetLodge = false;
    canSetCombo = false;
    return cachedHashCode;
  }

  public void delete()
  {
    Lodge existingLodge = lodge;
    lodge = null;
    if (existingLodge != null)
    {
      existingLodge.delete();
    }
    Combo existingCombo = combo;
    combo = null;
    if (existingCombo != null)
    {
      existingCombo.delete();
    }
    SnowShoeTour placeholderSnowShoeTour = snowShoeTour;
    this.snowShoeTour = null;
    if(placeholderSnowShoeTour != null)
    {
      placeholderSnowShoeTour.removeLodgeRental(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "discount" + ":" + getDiscount()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "lodge = "+(getLodge()!=null?Integer.toHexString(System.identityHashCode(getLodge())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "combo = "+(getCombo()!=null?Integer.toHexString(System.identityHashCode(getCombo())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "snowShoeTour = "+(getSnowShoeTour()!=null?Integer.toHexString(System.identityHashCode(getSnowShoeTour())):"null");
  }
}