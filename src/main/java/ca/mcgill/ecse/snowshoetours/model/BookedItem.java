/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

package ca.mcgill.ecse.snowshoetours.model;

// line 49 "../../../../../SnowShoeTour.ump"
public class BookedItem
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //BookedItem Attributes
  private int quantity;

  //BookedItem Associations
  private SnowShoeTour snowShoeTour;
  private Participant participant;
  private BookableItem item;

  //Helper Variables
  private int cachedHashCode;
  private boolean canSetParticipant;
  private boolean canSetItem;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public BookedItem(int aQuantity, SnowShoeTour aSnowShoeTour, Participant aParticipant, BookableItem aItem)
  {
    cachedHashCode = -1;
    canSetParticipant = true;
    canSetItem = true;
    quantity = aQuantity;
    boolean didAddSnowShoeTour = setSnowShoeTour(aSnowShoeTour);
    if (!didAddSnowShoeTour)
    {
      throw new RuntimeException("Unable to create bookedItem due to snowShoeTour. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddParticipant = setParticipant(aParticipant);
    if (!didAddParticipant)
    {
      throw new RuntimeException("Unable to create bookedItem due to participant. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddItem = setItem(aItem);
    if (!didAddItem)
    {
      throw new RuntimeException("Unable to create bookedItem due to item. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
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
  public Participant getParticipant()
  {
    return participant;
  }
  /* Code from template association_GetOne */
  public BookableItem getItem()
  {
    return item;
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
      existingSnowShoeTour.removeBookedItem(this);
    }
    if (!snowShoeTour.addBookedItem(this))
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
  /* Code from template association_SetOneToManyAssociationClass */
  public boolean setParticipant(Participant aParticipant)
  {
    boolean wasSet = false;
    if (!canSetParticipant) { return false; }
    if (aParticipant == null)
    {
      return wasSet;
    }

    Participant existingParticipant = participant;
    participant = aParticipant;
    if (existingParticipant != null && !existingParticipant.equals(aParticipant))
    {
      existingParticipant.removeBookedItem(this);
    }
    if (!participant.addBookedItem(this))
    {
      participant = existingParticipant;
      wasSet = false;
    }
    else
    {
      wasSet = true;
    }
    return wasSet;
  }
  /* Code from template association_SetOneToManyAssociationClass */
  public boolean setItem(BookableItem aItem)
  {
    boolean wasSet = false;
    if (!canSetItem) { return false; }
    if (aItem == null)
    {
      return wasSet;
    }

    BookableItem existingItem = item;
    item = aItem;
    if (existingItem != null && !existingItem.equals(aItem))
    {
      existingItem.removeBookedItem(this);
    }
    if (!item.addBookedItem(this))
    {
      item = existingItem;
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

    BookedItem compareTo = (BookedItem)obj;
  
    if (getParticipant() == null && compareTo.getParticipant() != null)
    {
      return false;
    }
    else if (getParticipant() != null && !getParticipant().equals(compareTo.getParticipant()))
    {
      return false;
    }

    if (getItem() == null && compareTo.getItem() != null)
    {
      return false;
    }
    else if (getItem() != null && !getItem().equals(compareTo.getItem()))
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
    if (getParticipant() != null)
    {
      cachedHashCode = cachedHashCode * 23 + getParticipant().hashCode();
    }
    else
    {
      cachedHashCode = cachedHashCode * 23;
    }
    if (getItem() != null)
    {
      cachedHashCode = cachedHashCode * 23 + getItem().hashCode();
    }
    else
    {
      cachedHashCode = cachedHashCode * 23;
    }

    canSetParticipant = false;
    canSetItem = false;
    return cachedHashCode;
  }

  public void delete()
  {
    SnowShoeTour placeholderSnowShoeTour = snowShoeTour;
    this.snowShoeTour = null;
    if(placeholderSnowShoeTour != null)
    {
      placeholderSnowShoeTour.removeBookedItem(this);
    }
    Participant placeholderParticipant = participant;
    this.participant = null;
    if(placeholderParticipant != null)
    {
      placeholderParticipant.removeBookedItem(this);
    }
    BookableItem placeholderItem = item;
    this.item = null;
    if(placeholderItem != null)
    {
      placeholderItem.removeBookedItem(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "quantity" + ":" + getQuantity()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "snowShoeTour = "+(getSnowShoeTour()!=null?Integer.toHexString(System.identityHashCode(getSnowShoeTour())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "participant = "+(getParticipant()!=null?Integer.toHexString(System.identityHashCode(getParticipant())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "item = "+(getItem()!=null?Integer.toHexString(System.identityHashCode(getItem())):"null");
  }
}