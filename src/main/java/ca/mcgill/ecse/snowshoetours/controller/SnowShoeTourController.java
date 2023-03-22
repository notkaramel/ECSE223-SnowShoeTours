package ca.mcgill.ecse.snowshoetours.controller;

import java.sql.Date;

import ca.mcgill.ecse.snowshoetours.application.SnowShoeToursApplication;

import ca.mcgill.ecse.snowshoetours.model.*;

import java.util.*;

public class SnowShoeTourController {
	private static SnowShoeTour ssts = SnowShoeToursApplication.getSnowShoeTour();

	/**
	 * @author Angela Zhu @angelaxzhu
	 * @param id
	 * @return
	 */
	public static TOSnowShoeTour getSnowShoeTour(int id) {

		// Can't find tour if there are no tours for this season
		if (!ssts.hasTours()) {
			return null;
		}
		// If there is no tour with that id (index out of range) --> no tour returned
		// id starts at 1 but index start at 0
		if (id <= 0 || id > ssts.numberOfTours()) {
			return null;
		}

		// Create a new instance of TOSnowShoeTour
		else {
			try {
				// id-1 because getTour takes the index which starts at 0
				Tour tour = ssts.getTour(id - 1);
				// +1 because start week 1 to end week 1 is still one week not 0
				int tour_duration = tour.getEndWeek() - tour.getStartWeek() + 1;
				int tour_cost = ssts.getPriceOfGuidePerWeek() * (tour_duration);

				// TOParticipant Attributes --> Create a TOParticipant object for each
				// participant registered for the tour
				int p_no = tour.numberOfParticipants();
				ArrayList<TOParticipantCost> participants = new ArrayList<TOParticipantCost>();
				for (int p = 0; p < p_no; p++) {
					Participant participant = tour.getParticipant(p);
					boolean lodge_rented = participant.isLodgeRequired();

					// Calculate the total cost for bookable items for the participant
					int bookableitems = 0;
					// iterating through list of booked items
					for (int i = 0; i < participant.getBookedItems().size(); i++) {
						BookedItem booked_item = participant.getBookedItem(i);
						BookableItem bookable_item = booked_item.getItem();
						int num_item = booked_item.getQuantity();
						int discount = 0;
						int single_cost = 0;

						// Check if the item is a standalone gear or in a combo
						if (bookable_item instanceof Gear) {
							Gear gear = (Gear) bookable_item;
							single_cost = gear.getPricePerWeek() * tour_duration;

						} else if (bookable_item instanceof Combo) {
							Combo combo = (Combo) bookable_item;
							discount = combo.getDiscount();

							// add the price of each type of item in the combo
							// Iterating through each type of gear
							int num_comboItem = combo.numberOfComboItems();
							for (int ci = 0; ci < num_comboItem; ci++) {
								ComboItem combo_item = combo.getComboItem(ci);
								int num_ci = combo_item.getQuantity();
								single_cost = single_cost
										+ combo_item.getGear().getPricePerWeek() * tour_duration * num_ci;
							}
							if (lodge_rented == false) {
								discount = 0;
							}
							// price of a single combo
							single_cost = (int) (single_cost - (single_cost * discount * 0.01));
						}
						// total price of gear for one participant
						bookableitems = (bookableitems + (single_cost * num_item));
					}

					// Calculate the total cost of the trip for a single participant
					int totalcost = (bookableitems + tour_cost);
					// Adding the info to the list of participants
					participants.add(p, new TOParticipantCost(participant.getAccountName(), participant.getName(),
							bookableitems, totalcost));

				}
				// Instancing TOSnowShoeTour
				TOSnowShoeTour SnowShoeTour = new TOSnowShoeTour(id, tour.getStartWeek(), tour.getEndWeek(),
						tour.getGuide().getAccountName(), tour.getGuide().getName(), tour_cost,
						participants.toArray(new TOParticipantCost[0]));
				return SnowShoeTour;
			} catch (Exception e) {
				return null;
			}
		}

	}

	/**
	 * @author Angela Zhu @angelaxzhu
	 * 
	 * @param startDate
	 * @param nrWeeks
	 * @param priceOfGuidePerWeek
	 * @return
	 */
	public static String updateSnowShoeTour(Date startDate, int nrWeeks, int priceOfGuidePerWeek) {
		if (nrWeeks < 0) {
			return "The number of riding weeks must be greater than or equal to zero";
		}
		if (priceOfGuidePerWeek < 0) {
			return "The price of guide per week must be greater than or equal to zero";
		}
		if (startDate.before(ssts.getStartDate())) {
			return "The start date cannot be from previous year or earlier";
		} else {
			try {
				ssts.setStartDate(startDate);
				ssts.setNrWeeks(nrWeeks);
				ssts.setPriceOfGuidePerWeek(priceOfGuidePerWeek);
				return "";
			} catch (Exception e) {
				return "Error: something went wrong";
			}
		}
	}
}
