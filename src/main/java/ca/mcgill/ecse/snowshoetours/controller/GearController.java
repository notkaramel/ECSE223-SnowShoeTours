package ca.mcgill.ecse.snowshoetours.controller;

import ca.mcgill.ecse.snowshoetours.application.SnowShoeToursApplication;
import ca.mcgill.ecse.snowshoetours.model.Gear;
import ca.mcgill.ecse.snowshoetours.model.SnowShoeTour;
import ca.mcgill.ecse.snowshoetours.model.BookableItem;
import ca.mcgill.ecse.snowshoetours.model.Combo;
import ca.mcgill.ecse.snowshoetours.model.ComboItem;


public class GearController {
	
	// CREATE INSTANCE OF APPLICATION OBJECT
	private static SnowShoeTour sst = SnowShoeToursApplication.getSnowShoeTour(); 
	
	
	public static String addGear(String name, int pricePerWeek) {
		
		//INPUT VALIDATION
		// check if name inputed is null
		if (name == null || name.equals("")) {
			return "Error: empty/null name";
		  }
		
		//check if pricePerWeek is less than 0
		if (pricePerWeek < 0) {
			return "Error: invalid price"; // TODO: are there any other ways prices can be invalid?
			}
	  
		//check if gear with same name already exists 
		if (Gear.hasWithName(name)) {
			return "Error: Gear with that name already exists.";
		} 
		
		//TRY ADDING GEAR TO SST
		try {
			sst.addGear(name, pricePerWeek);
			return "";
			
		} catch(Exception e) {
			return "Error: something went wrong";
		}
  }


	public static String deleteGear(String name) {
	
		//INPUT VALIDATION
		// check if name inputed is null
		if (name == null || name.equals("")) {// is input validation needed for deleting?
			return "Error: empty/null name";
		}
		  
		//check if gear with same name already exists 
		if (Gear.hasWithName(name) == false) {
			return "Error: No gear with that name exists.";
		} 
	  
		//TRY DELETING GEAR
		//initiate gearIndex
		Integer gearIndex = null;
	  
		try {
			
			//find index of the gear with name in the list of gear, and set to gearIndex
			for (int i=0; i < sst.getGear().size(); i++) {
				if(sst.getGear(i).getName() == name) {
					gearIndex = i;
					break;
				}
			}
			
			//delete gear with name located at gearIndex
			sst.getGear(gearIndex).delete();
			return "";
			  
		} catch(Exception e) {
			return "Error: something went wrong";
		}
  }
	
	public static String addCombo(String name, int discount) { 
		
		//INPUT VALIDATION
		// check if name inputed is null
		if (name == null || name.equals("")) {
			return "Error: empty/null name";
		}
		
		//check if discount number is valid
		if (discount < 0 || discount > 100) {
			return "Error: invalid "; 
		}
		
		//check if gear with same name already exists 
		if (Combo.hasWithName(name)) {
			return "Error: Combo with that name already exists.";
		} 	
		
		//TRY ADDING COMBO
		try {
			sst.addCombo(name, discount);
			return "";
				
		} catch(Exception e) {
			return "Error: something went wrong";
		}
	  		
  }

	public static void deleteCombo(String name) {
		
		//TRY DELETING COMBO
		//initiate comboIndex
		Integer comboIndex = null;
	  
		try {
			
			//find index of the combo with name in the list of combos, and set to comboIndex
			for (int i=0; i < sst.getCombos().size() ; i++) {
				if(sst.getCombo(i).getName() == name) {
					comboIndex = i;
					break;
				}
			}
			 
			//delete combo with name located at comboIndex
			sst.getCombo(comboIndex).delete();
				
			} catch(Exception e) {}
  }

	public static String addGearToCombo(String gearName, String comboName) {
		// checks if comboName inputs does in fact exist
		if (!isCombo(comboName)){
			return "The combo does not exist";
		}
		// checks if gearName inputs does in fact exist
		if(!isGear(gearName)){
			return "The piece of gear does not exist";
		}
		
		//get gear and combo with gearName and comboName, respectively
		Gear gear = (Gear) BookableItem.getWithName(gearName);
		Combo combo = (Combo) BookableItem.getWithName(comboName);
		ComboItem comboItem = getComboItem(combo, gear);
		Integer comboIndex = null;
		
		//TRY ADDING GEAR TO COMBO
		try {
      		// When we already have an instance of this gear type in the combo
      		if (comboItem != null) {
        	comboItem.setQuantity(comboItem.getQuantity() + 1);
        	// When we add a gear item for the first time to the combo
     	 	} else {
        		combo.addComboItem(1, sst, gear);
      	}
		} catch(Exception e) {
			return "Something went wrong";
		}
		return "";
  }

	public static String removeGearFromCombo(String gearName, String comboName) {
		

		// checks comboName inputs does in fact exist
		if (!isCombo(comboName)){
			return "The combo does not exist";
		}
		// checks if gearName inputs does in fact exist
		if(!isGear(gearName)){
			return "The piece of gear does not exist";
		}
			
		//get gear and combo with gearName and comboName, respectively, 
		//then creates a comboItem using the helper method getComboItem 
		Gear gear = (Gear) BookableItem.getWithName(gearName);
		Combo combo = (Combo) BookableItem.getWithName(comboName);
		ComboItem comboItem = getComboItem(combo, gear);
		Integer comboIndex = null;
		if (comboItem == null) {
		  return "";
		}
		
		

		
		try {
			// If there's only one instance of that gear left in the combo
			if(comboItem.getQuantity() == 1) {
				// Just makes sure that even after we remove that gear from the combo it will still have 2 gears left
				if(combo.numberOfComboItems() == Combo.minimumNumberOfComboItems()) {
					return "A combo must have at least two pieces of gear";
				}else {
					//If it has more than 2 left even afte we delete that gearType then we go on with the deletion
					comboItem.delete();
				}
			}else {
				//If it has more than 1 instance of that gear type we simply remove one
				comboItem.setQuantity(comboItem.getQuantity() - 1);
			}
		} catch(Exception e) {
			return "Something went wrong";
		}
		return "";
		
	}
	// Checks if the combo in question is actually a type combo by using their name
	private static boolean isCombo(String name) {
		return BookableItem.getWithName(name) instanceof Combo;
 	}
	// Checks if the item in question is actually a type gear by using their name
 	private static boolean isGear(String name) {
		return BookableItem.getWithName(name) instanceof Gear;
 	}
	// Gets the specific item from a combo using the items name and the combos name by iterating through them
	private static ComboItem getComboItem(Combo combo, Gear gear) {
		ComboItem comboItem = null;
		for (ComboItem item : sst.getComboItems()) {
		  if (item.getCombo() == combo) {
			if(item.getGear() == gear){
				comboItem = item;
			}
		  }
		}
		return comboItem;
	}
}