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
