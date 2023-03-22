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
					return "The name must not be empty";
				  }
				
				//check if pricePerWeek is less than 0
				if (pricePerWeek < 0) {
					return "The price per week must be greater than or equal to 0"; 
					}
			  
				//check if gear or combo with same name already exists 
				if (Gear.hasWithName(name) && Gear.getWithName(name) instanceof Gear) {
					return "A piece of gear with the same name already exists";
				} 
				else if (Combo.hasWithName(name)) {
					return "A combo with the same name already exists";
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
				if (name == null || name.equals("")) {
					return "The name must not be empty";
				}
				  
				//check if gear with same name exists 
				if (Gear.hasWithName(name) == false) {
					return "A piece of gear with the same name doesnt exists"; 
				} 
				
				//check if gear is currently in any combo items
				int comboIndex = sst.getCombos().size();
				for(int i=0; i<comboIndex; i++) {
					
					int comboItemIndex = sst.getCombo(i).getComboItems().size();
					for(int j=0; j < comboItemIndex; j++) {
						Gear gearInCombo = sst.getCombo(i).getComboItem(j).getGear();
						if (gearInCombo == Gear.getWithName(name)) {
							return "The piece of gear is in a combo and cannot be deleted";
						}
					}
				}
				
			  
				//TRY DELETING GEAR

				try {
					
					Gear gear = (Gear) Gear.getWithName(name);
					if (gear != null && Gear.getWithName(name) instanceof Gear) {
						gear.delete();}
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
		
		//INPUT VALIDATION
		// check if gearName or comboName inputed  null
		if (gearName == null || comboName == null) {
			return "Error: gearName and/or comboName are null";
		}
		
		//check if gear with same name already exists 
		if (Gear.hasWithName(gearName)) {
			return "Error: Gear with that name already exists.";
		} 
		
		//check if combo with same name already exists 
		if (Combo.hasWithName(comboName)) {
			return "Error: Combo with that name already exists.";
		} 
		
		//get gear and combo with gearName and comboName, respectively
		BookableItem gear = Gear.getWithName(gearName);
		BookableItem combo = Combo.getWithName(comboName);
		
		//TRY ADDING GEAR TO COMBO
		try {
	
			//type casting gear and combo to their appropriate classes, and adding gear to the combo
			((Gear) gear).addComboItem(1, sst, ((Combo) combo)); 
			return "";
			
		} catch(Exception e) {
			return "Something went wrong";
		}
  }

	public static String removeGearFromCombo(String gearName, String comboName) {
		
		//INPUT VALIDATION
		// check if gearName or comboName inputed  null
		if (gearName == null || comboName == null) {
			return "Error: gearName and/or comboName are null";
		}
		
		//check if gear with same name already exists 
		if (Gear.hasWithName(gearName)) {
			return "Error: Gear with that name already exists.";
		} 
			
		//check if combo with same name already exists 
		if (Combo.hasWithName(comboName)) {
			return "Error: Combo with that name already exists.";
		} 
			
		//get gear and combo with gearName and comboName, respectively
		BookableItem gear = Gear.getWithName(gearName);
		BookableItem combo = Combo.getWithName(comboName);
		
		
		//TRY DELETING GEAR FROM COMBO
		//initiate comboIndex
		Integer comboIndex = null;
		
		try {
			
			//find index of the comboItem with name in the list of comboItems, and set to comboIndex
			for (int i=0; i < sst.getComboItems().size() ; i++) {
				if(sst.getComboItem(i).getGear().getName() == gearName) {
					comboIndex = i;
					break;
				}
			}
			
			//remove comboItem
			sst.removeComboItem(sst.getComboItem(comboIndex));

			return "";
			
			
		} catch(Exception e) {
			return "Something went wrong";
		}
	}
}