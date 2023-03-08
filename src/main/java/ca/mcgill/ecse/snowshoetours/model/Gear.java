/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

package ca.mcgill.ecse.snowshoetours.model;
import java.util.*;

// line 60 "../../../../../../SnowShoeTour.ump"
public class Gear extends BookableItem
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Gear Attributes
  private int pricePerWeek;

  //Gear Associations
  private SnowShoeTour snowShoeTour;
  private List<ComboItem> comboItems;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Gear(String aName, int aPricePerWeek, SnowShoeTour aSnowShoeTour)
  {
    super(aName);
    pricePerWeek = aPricePerWeek;
    boolean didAddSnowShoeTour = setSnowShoeTour(aSnowShoeTour);
    if (!didAddSnowShoeTour)
    {
      throw new RuntimeException("Unable to create gear due to snowShoeTour. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    comboItems = new ArrayList<ComboItem>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPricePerWeek(int aPricePerWeek)
  {
    boolean wasSet = false;
    pricePerWeek = aPricePerWeek;
    wasSet = true;
    return wasSet;
  }

  public int getPricePerWeek()
  {
    return pricePerWeek;
  }
  /* Code from template association_GetOne */
  public SnowShoeTour getSnowShoeTour()
  {
    return snowShoeTour;
  }
  /* Code from template association_GetMany */
  public ComboItem getComboItem(int index)
  {
    ComboItem aComboItem = comboItems.get(index);
    return aComboItem;
  }

  public List<ComboItem> getComboItems()
  {
    List<ComboItem> newComboItems = Collections.unmodifiableList(comboItems);
    return newComboItems;
  }

  public int numberOfComboItems()
  {
    int number = comboItems.size();
    return number;
  }

  public boolean hasComboItems()
  {
    boolean has = comboItems.size() > 0;
    return has;
  }

  public int indexOfComboItem(ComboItem aComboItem)
  {
    int index = comboItems.indexOf(aComboItem);
    return index;
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
      existingSnowShoeTour.removeGear(this);
    }
    snowShoeTour.addGear(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfComboItems()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public ComboItem addComboItem(int aQuantity, SnowShoeTour aSnowShoeTour, Combo aCombo)
  {
    return new ComboItem(aQuantity, aSnowShoeTour, aCombo, this);
  }

  public boolean addComboItem(ComboItem aComboItem)
  {
    boolean wasAdded = false;
    if (comboItems.contains(aComboItem)) { return false; }
    Gear existingGear = aComboItem.getGear();
    boolean isNewGear = existingGear != null && !this.equals(existingGear);
    if (isNewGear)
    {
      aComboItem.setGear(this);
    }
    else
    {
      comboItems.add(aComboItem);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeComboItem(ComboItem aComboItem)
  {
    boolean wasRemoved = false;
    //Unable to remove aComboItem, as it must always have a gear
    if (!this.equals(aComboItem.getGear()))
    {
      comboItems.remove(aComboItem);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addComboItemAt(ComboItem aComboItem, int index)
  {  
    boolean wasAdded = false;
    if(addComboItem(aComboItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfComboItems()) { index = numberOfComboItems() - 1; }
      comboItems.remove(aComboItem);
      comboItems.add(index, aComboItem);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveComboItemAt(ComboItem aComboItem, int index)
  {
    boolean wasAdded = false;
    if(comboItems.contains(aComboItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfComboItems()) { index = numberOfComboItems() - 1; }
      comboItems.remove(aComboItem);
      comboItems.add(index, aComboItem);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addComboItemAt(aComboItem, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    SnowShoeTour placeholderSnowShoeTour = snowShoeTour;
    this.snowShoeTour = null;
    if(placeholderSnowShoeTour != null)
    {
      placeholderSnowShoeTour.removeGear(this);
    }
    for(int i=comboItems.size(); i > 0; i--)
    {
      ComboItem aComboItem = comboItems.get(i - 1);
      aComboItem.delete();
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "pricePerWeek" + ":" + getPricePerWeek()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "snowShoeTour = "+(getSnowShoeTour()!=null?Integer.toHexString(System.identityHashCode(getSnowShoeTour())):"null");
  }
}