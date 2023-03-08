/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

package ca.mcgill.ecse.snowshoetours.model;
import java.util.*;

// line 65 "../../../../../../SnowShoeTour.ump"
public class Combo extends BookableItem
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Combo Attributes
  private int discount;

  //Combo Associations
  private SnowShoeTour snowShoeTour;
  private List<ComboItem> comboItems;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Combo(String aName, int aDiscount, SnowShoeTour aSnowShoeTour)
  {
    super(aName);
    discount = aDiscount;
    boolean didAddSnowShoeTour = setSnowShoeTour(aSnowShoeTour);
    if (!didAddSnowShoeTour)
    {
      throw new RuntimeException("Unable to create combo due to snowShoeTour. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    comboItems = new ArrayList<ComboItem>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDiscount(int aDiscount)
  {
    boolean wasSet = false;
    discount = aDiscount;
    wasSet = true;
    return wasSet;
  }

  public int getDiscount()
  {
    return discount;
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
      existingSnowShoeTour.removeCombo(this);
    }
    snowShoeTour.addCombo(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfComboItemsValid()
  {
    boolean isValid = numberOfComboItems() >= minimumNumberOfComboItems();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfComboItems()
  {
    return 2;
  }
  /* Code from template association_AddMandatoryManyToOne */
  public ComboItem addComboItem(int aQuantity, SnowShoeTour aSnowShoeTour, Gear aGear)
  {
    ComboItem aNewComboItem = new ComboItem(aQuantity, aSnowShoeTour, this, aGear);
    return aNewComboItem;
  }

  public boolean addComboItem(ComboItem aComboItem)
  {
    boolean wasAdded = false;
    if (comboItems.contains(aComboItem)) { return false; }
    Combo existingCombo = aComboItem.getCombo();
    boolean isNewCombo = existingCombo != null && !this.equals(existingCombo);

    if (isNewCombo && existingCombo.numberOfComboItems() <= minimumNumberOfComboItems())
    {
      return wasAdded;
    }
    if (isNewCombo)
    {
      aComboItem.setCombo(this);
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
    //Unable to remove aComboItem, as it must always have a combo
    if (this.equals(aComboItem.getCombo()))
    {
      return wasRemoved;
    }

    //combo already at minimum (2)
    if (numberOfComboItems() <= minimumNumberOfComboItems())
    {
      return wasRemoved;
    }

    comboItems.remove(aComboItem);
    wasRemoved = true;
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
      placeholderSnowShoeTour.removeCombo(this);
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
            "discount" + ":" + getDiscount()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "snowShoeTour = "+(getSnowShoeTour()!=null?Integer.toHexString(System.identityHashCode(getSnowShoeTour())):"null");
  }
}