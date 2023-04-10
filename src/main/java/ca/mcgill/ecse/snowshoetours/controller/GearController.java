package ca.mcgill.ecse.snowshoetours.controller;

import ca.mcgill.ecse.snowshoetours.application.SnowShoeToursApplication;
import ca.mcgill.ecse.snowshoetours.model.Gear;
import ca.mcgill.ecse.snowshoetours.model.SnowShoeTour;
import ca.mcgill.ecse.snowshoetours.persistence.SnowShoeTourPersistence;
import ca.mcgill.ecse.snowshoetours.model.BookableItem;
import ca.mcgill.ecse.snowshoetours.model.Combo;
import ca.mcgill.ecse.snowshoetours.model.ComboItem;

public class GearController {

	// CREATE INSTANCE OF APPLICATION OBJECT
	private static SnowShoeTour sst = SnowShoeToursApplication.getSnowShoeTour();

	/**
	 * @author Emma Friesen (@emma-friesen)
	 * @param name
	 * @param pricePerWeek
	 * @return
	 */
	public static String addGear(String name, int pricePerWeek) {

		// INPUT VALIDATION
		// check if name inputed is null
		if (name == null || name.equals("")) {
			return "The name must not be empty";
		}

		// check if pricePerWeek is less than 0
		if (pricePerWeek < 0) {
			return "The price per week must be greater than or equal to 0";
		}

		// check if gear or combo with same name already exists
		if (Gear.hasWithName(name) && Gear.getWithName(name) instanceof Gear) {
			return "A piece of gear with the same name already exists";
		} else if (Combo.hasWithName(name)) {
			return "A combo with the same name already exists";
		}

		// TRY ADDING GEAR TO SST
		try {
			sst.addGear(name, pricePerWeek);
			SnowShoeTourPersistence.save();
			return "";

		} catch (Exception e) {
			return "Error: something went wrong";
		}
	}

	/**
	 * @author Emma Friesen (@emma-friesen)
	 * 
	 * @param name
	 * @return
	 */
	public static String deleteGear(String name) {

		// INPUT VALIDATION
		// check if name inputed is null
		if (name == null || name.equals("")) {
			return "The name must not be empty";
		}

		// check if gear with same name exists
		if (Gear.hasWithName(name) == false) {
			return "A piece of gear with the same name doesnt exists";
		}

		// check if gear is currently in any combo items
		int comboIndex = sst.getCombos().size();
		for (int i = 0; i < comboIndex; i++) {

			int comboItemIndex = sst.getCombo(i).getComboItems().size();
			for (int j = 0; j < comboItemIndex; j++) {
				Gear gearInCombo = sst.getCombo(i).getComboItem(j).getGear();
				if (gearInCombo == Gear.getWithName(name)) {
					return "The piece of gear is in a combo and cannot be deleted";
				}
			}
		}

		// TRY DELETING GEAR

		try {

			Gear gear = (Gear) Gear.getWithName(name);
			if (gear != null && Gear.getWithName(name) instanceof Gear) {
				gear.delete();
				SnowShoeTourPersistence.save();
			}
			return "";

		} catch (Exception e) {
			return "Error: something went wrong";
		}
	}

	/**
	 * @author Antoine Phan (@notkaramel)
	 * @param name
	 * @param discount
	 * @return
	 */
	public static String addCombo(String name, int discount) {

		// <-- Antoine's changes -->
		// INPUT VALIDATION
		// check if name inputed is null
		if (name == null || name.equals("")) {
			return "The name must not be empty";
		}

		// check if discount number is valid
		if (discount < 0) {
			return "Discount must be at least 0";
		} else if (discount > 100) {
			return "Discount must be no more than 100";
		}

		// * Check if combo with same name already exists

		// Explaining: Since Gear and Combo are both subclasses of
		// BookableItem, using hasWithName() which only returns boolean
		// would not be enough for these two tests.

		// So we can use getWithName() then use check `instanceof`
		// to check if the name is already taken for the object.
		if (Combo.getWithName(name) instanceof Combo) {
			return "A combo with the same name already exists";
		}
		// Check if gear with same name already exists
		else if (Gear.getWithName(name) instanceof Gear) {
			return "A piece of gear with the same name already exists";
		}

		// TRY ADDING COMBO
		try {
			sst.addCombo(name, discount);
			SnowShoeTourPersistence.save();
			return "";

		} catch (Exception e) {
			return "Error: something went wrong";
		}

	}

	/**
	 * @author Antoine Phan (@notkaramel)
	 * @param name
	 */
	public static void deleteCombo(String name) {
		Combo combo = (Combo) Combo.getWithName(name);
		if (combo != null) {
			combo.delete();
			SnowShoeTourPersistence.save();
		}
	}

	/**
	 * Method that adds gear to a combo
	 * 
	 * @author Bilar Mokhtari @bmokhtari
	 * @param gearName
	 * @param comboName
	 * @return
	 */
	public static String addGearToCombo(String gearName, String comboName) {
		// checks if comboName inputs does in fact exist
		if (!isCombo(comboName)) {
			return "The combo does not exist";
		}
		// checks if gearName inputs does in fact exist
		if (!isGear(gearName)) {
			return "The piece of gear does not exist";
		}

		// get gear and combo with gearName and comboName, respectively
		Gear gear = (Gear) BookableItem.getWithName(gearName);
		Combo combo = (Combo) BookableItem.getWithName(comboName);
		ComboItem comboItem = getComboItem(combo, gear);

		// TRY ADDING GEAR TO COMBO
		try {
			// When we already have an instance of this gear type in the combo
			if (comboItem != null) {
				comboItem.setQuantity(comboItem.getQuantity() + 1);
				// When we add a gear item for the first time to the combo
			} else {
				combo.addComboItem(1, sst, gear);
			}
			SnowShoeTourPersistence.save();
		} catch (Exception e) {
			return "Something went wrong";
		}
		return "";
	}

	/**
	 * Method that deletes a gear from a combo
	 * 
	 * @author Bilar Mokhtari (@bmokhtari)
	 * @param gearName
	 * @param comboName
	 * @return
	 */
	public static String removeGearFromCombo(String gearName, String comboName) {


		// checks comboName inputs does in fact exist
		if (!isCombo(comboName)) {
			return "The combo does not exist";
		}
		// checks if gearName inputs does in fact exist
		if (!isGear(gearName)) {
			return "The piece of gear does not exist";
		}



		// get gear and combo with gearName and comboName, respectively,
		// then creates a comboItem using the helper method getComboItem
		Gear gear = (Gear) BookableItem.getWithName(gearName);
		Combo combo = (Combo) BookableItem.getWithName(comboName);
		ComboItem comboItem = getComboItem(combo, gear);
		if (!comboContainsGear(combo, gear)) {
			return "The combo does not contain the specified piece of gear";
		}
		if (comboItem == null) {
			return "";
		}

		try {
			// If there's only one instance of that gear left in the combo
			if (comboItem.getQuantity() == 1) {
				// Just makes sure that even after we remove that gear from the combo it will
				// still have 2 gears left
				if (combo.numberOfComboItems() == Combo.minimumNumberOfComboItems()) {
					return "A combo must have at least two pieces of gear";
				} else {
					// If it has more than 2 left even afte we delete that gearType then we go on
					// with the deletion
					comboItem.delete();
				}
			} else {
				// If it has more than 1 instance of that gear type we simply remove one
				comboItem.setQuantity(comboItem.getQuantity() - 1);
			}
			SnowShoeTourPersistence.save();
		} catch (Exception e) {
			return "Something went wrong";
		}
		return "";

	}

	/**
	 * @author Bilar Mokhtari (@bmokhtari) Helper functions
	 * @param name
	 * @return
	 */
	// Checks if the combo in question is actually a type combo by using their name
	private static boolean isCombo(String name) {
		return BookableItem.getWithName(name) instanceof Combo;
	}

	// Checks if the item in question is actually a type gear by using their name
	private static boolean isGear(String name) {
		return BookableItem.getWithName(name) instanceof Gear;
	}

	// Gets the specific item from a combo using the items name and the combos name
	// by iterating through them
	private static ComboItem getComboItem(Combo combo, Gear gear) {
		ComboItem comboItem = null;
		for (ComboItem item : sst.getComboItems()) {
			if (item.getCombo() == combo) {
				if (item.getGear() == gear) {
					comboItem = item;
				}
			}
		}
		return comboItem;
	}

	public static boolean comboContainsGear(Combo combo, Gear gear) {
		for (ComboItem comboItem : combo.getComboItems()) {
			if (comboItem.getGear().equals(gear)) {
				return true;
			}
		}
		return false;
	}
}
