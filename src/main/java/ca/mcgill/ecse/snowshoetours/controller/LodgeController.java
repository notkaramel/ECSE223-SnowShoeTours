package ca.mcgill.ecse.snowshoetours.controller;

import ca.mcgill.ecse.snowshoetours.application.SnowShoeToursApplication;
import ca.mcgill.ecse.snowshoetours.model.Lodge;
import ca.mcgill.ecse.snowshoetours.model.Lodge.LodgeRating;
import ca.mcgill.ecse.snowshoetours.persistence.SnowShoeTourPersistence;
import ca.mcgill.ecse.snowshoetours.model.SnowShoeTour;

public class LodgeController {
	private static SnowShoeTour sst = SnowShoeToursApplication.getSnowShoeTour();

	/**
	 * @author: Sameer Riaz (@SRIAZ77)
	 */
	public static String addLodge(String name, String address, int nrStars) {
		// check if inputs are valid
		if (name == "" || name == null || address == "" || address == null || nrStars < 1
				|| nrStars > 5) {
			return "Invalid Input";
		}
		// ensuring lodge name is not already in use
		if (Lodge.hasWithName(name)) {
			return "There already exists a lodge with that name";
		}
		// converting int to LodgeRating enum
		else {
			LodgeRating rating = LodgeRating.OneStar;

			switch (nrStars) {
				case 1:
					rating = LodgeRating.OneStar;
				case 2:
					rating = LodgeRating.TwoStars;
				case 3:
					rating = LodgeRating.ThreeStars;
				case 4:
					rating = LodgeRating.FourStars;
				case 5:
					rating = LodgeRating.FiveStars;
			}
			try {
				sst.addLodge(name, address, rating);
				SnowShoeTourPersistence.save();
				return "";
			} catch (Exception e) {
				return ("Error: Something went wrong");
			}
		}
	}

	/**
	 * @author: Sameer Riaz (@SRIAZ77)
	 */
	public static void deleteLodge(String name) {
		// ADD VALIDATION
		if (Lodge.hasWithName(name)) {
			try {
				if(sst.removeLodge(Lodge.getWithName(name)))
				{
					System.out.println("Lodge deleted");
				};
				SnowShoeTourPersistence.save();
			} catch (Exception e) {
			}
		}
	}
}
