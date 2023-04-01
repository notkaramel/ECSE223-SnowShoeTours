/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

package ca.mcgill.ecse.snowshoetours.model;

// line 72 "../../../../../SnowShoeTour.ump"
public class ComboItem
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ComboItem Attributes
  private int quantity;

  //ComboItem Associations
  private SnowShoeTour snowShoeTour;
  private Combo combo;
  private Gear gear;

  //Helper Variables
  private int cachedHashCode;
  private boolean canSetCombo;
  private boolean canSetGear;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ComboItem(int aQuantity, SnowShoeTour aSnowShoeTour, Combo aCombo, Gear aGear)
  {
    cachedHashCode = -1;
    canSetCombo = true;
    canSetGear = true;
    quantity = aQuantity;
    boolean didAddSnowShoeTour = setSnowShoeTour(aSnowShoeTour);
    if (!didAddSnowShoeTour)
    {
      throw new RuntimeException("Unable to create comboItem due to snowShoeTour. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddCombo = setCombo(aCombo);
    if (!didAddCombo)
    {
      throw new RuntimeException("Unable to create comboItem due to combo. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddGear = setGear(aGear);
    if (!didAddGear)
    {
      throw new RuntimeException("Unable to create comboItem due to gear. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setQuantity(int aQuantity)
  {
    boolean wasSet = false;
    quantity = aQuantity;
    wasSet = true;
    return wasSet;
  }

  public int getQuantity()
  {
    return quantity;
  }
  /* Code from template association_GetOne */
  public SnowShoeTour getSnowShoeTour()
  {
    return snowShoeTour;
  }
  /* Code from template association_GetOne */
  public Combo getCombo()
  {
    return combo;
  }
  /* Code from template association_GetOne */
  public Gear getGear()
  {
    return gear;
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
      existingSnowShoeTour.removeComboItem(this);
    }
    if (!snowShoeTour.addComboItem(this))
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
  /* Code from template association_SetOneToMandatoryMany */
  public boolean setCombo(Combo aCombo)
  {
    boolean wasSet = false;
    if (!canSetCombo) { return false; }
    //Must provide combo to comboItem
    if (aCombo == null)
    {
      return wasSet;
    }

    if (combo != null && combo.numberOfComboItems() <= Combo.minimumNumberOfComboItems())
    {
      return wasSet;
    }

    Combo existingCombo = combo;
    combo = aCombo;
    if (existingCombo != null && !existingCombo.equals(aCombo))
    {
      boolean didRemove = existingCombo.removeComboItem(this);
      if (!didRemove)
      {
        combo = existingCombo;
        return wasSet;
      }
    }
    combo.addComboItem(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToManyAssociationClass */
  public boolean setGear(Gear aGear)
  {
    boolean wasSet = false;
    if (!canSetGear) { return false; }
    if (aGear == null)
    {
      return wasSet;
    }

    Gear existingGear = gear;
    gear = aGear;
    if (existingGear != null && !existingGear.equals(aGear))
    {
      existingGear.removeComboItem(this);
    }
    if (!gear.addComboItem(this))
    {
      gear = existingGear;
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

    ComboItem compareTo = (ComboItem)obj;
  
    if (getCombo() == null && compareTo.getCombo() != null)
    {
      return false;
    }
    else if (getCombo() != null && !getCombo().equals(compareTo.getCombo()))
    {
      return false;
    }

    if (getGear() == null && compareTo.getGear() != null)
    {
      return false;
    }
    else if (getGear() != null && !getGear().equals(compareTo.getGear()))
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
    if (getCombo() != null)
    {
      cachedHashCode = cachedHashCode * 23 + getCombo().hashCode();
    }
    else
    {
      cachedHashCode = cachedHashCode * 23;
    }
    if (getGear() != null)
    {
      cachedHashCode = cachedHashCode * 23 + getGear().hashCode();
    }
    else
    {
      cachedHashCode = cachedHashCode * 23;
    }

    canSetCombo = false;
    canSetGear = false;
    return cachedHashCode;
  }

  public void delete()
  {
    SnowShoeTour placeholderSnowShoeTour = snowShoeTour;
    this.snowShoeTour = null;
    if(placeholderSnowShoeTour != null)
    {
      placeholderSnowShoeTour.removeComboItem(this);
    }
    Combo placeholderCombo = combo;
    this.combo = null;
    if(placeholderCombo != null)
    {
      placeholderCombo.removeComboItem(this);
    }
    Gear placeholderGear = gear;
    this.gear = null;
    if(placeholderGear != null)
    {
      placeholderGear.removeComboItem(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "quantity" + ":" + getQuantity()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "snowShoeTour = "+(getSnowShoeTour()!=null?Integer.toHexString(System.identityHashCode(getSnowShoeTour())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "combo = "+(getCombo()!=null?Integer.toHexString(System.identityHashCode(getCombo())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "gear = "+(getGear()!=null?Integer.toHexString(System.identityHashCode(getGear())):"null");
  }
}