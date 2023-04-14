package ca.mcgill.ecse.snowshoetours.controller;

import java.sql.Date;

import ca.mcgill.ecse.snowshoetours.application.SnowShoeToursApplication;

import ca.mcgill.ecse.snowshoetours.model.*;
import ca.mcgill.ecse.snowshoetours.persistence.SnowShoeTourPersistence;

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
								single_cost = single_cost + combo_item.getGear().getPricePerWeek()
										* tour_duration * num_ci;
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
					participants.add(p, new TOParticipantCost(participant.getAccountName(),
							participant.getName(), bookableitems, totalcost));

				}
				// Instancing TOSnowShoeTour
				TOSnowShoeTour SnowShoeTour =
						new TOSnowShoeTour(id, tour.getStartWeek(), tour.getEndWeek(),
								tour.getGuide().getAccountName(), tour.getGuide().getName(),
								tour_cost, participants.toArray(new TOParticipantCost[0]));
				SnowShoeTourPersistence.save();
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
				SnowShoeTourPersistence.save();
				return "";
			} catch (Exception e) {
				return "Error: something went wrong";
			}
		}
	}

	/**
	 * Getting a list of all the guide emails
	 * 
	 * @author Antoine Phan @notkaramel
	 * @return
	 */
	public static List<String> getGuides() {
		return ssts.getGuides().stream().map(Guide::getAccountName).toList();
	}

	public static List<String> getGears() {
		return ssts.getGear().stream().map(Gear::getName).toList();
	}

	public static List<String> getCombos() {
		return ssts.getCombos().stream().map(Combo::getName).toList();
	}

	/**
	 * Getting a list of all the participant emails
	 * 
	 * @author Jennifer Tram Su @jennifertramsu
	 * @return
	 */
	public static List<String> getParticipants() {
		return ssts.getParticipants().stream().map(Participant::getAccountName).toList();
	}

	/**
	 * Getting a list of all the start weeks of the tours
	 * 
	 * @author Antoine Phan @notkaramel
	 * @return
	 */
	public static List<Integer> getTourWeeks() {
		return ssts.getTours().stream().map(t -> t.getStartWeek()).toList();
	}

	/**
	 * Getting a list of all the tour IDs
	 * 
	 * @author Antoine Phan @notkaramel
	 * @return
	 */
	public static List<Integer> getTourIDs() {
		return ssts.getTours().stream().map(t -> t.getId()).toList();
	}

	/**
	 * * Get tours as TOSnowShoeTour
	 * 
	 * @author Antoine Phan @notkaramel
	 * @return
	 */
	public static List<TOSnowShoeTour> getTOSnowShoeTourList() {
		List<TOSnowShoeTour> tours = new ArrayList<TOSnowShoeTour>();
		for (Tour tour : ssts.getTours()) {
			tours.add(getSnowShoeTour(tour.getId()));
		}
		return tours;
	}

	/**
	 * Get Participants as TOParticipant
	 * @author Antoine Phan @notkaramel
	 * @return
	 */
	public static List<TOParticipant> getParticipantTO() {
		List<TOParticipant> participantTO = new ArrayList<TOParticipant>();
		for (Participant p : ssts.getParticipants()) {
			// Getting all info of the participant
			String email = p.getAccountName();
			String name = p.getName();
			// might cause error
			TOParticipantCost pCost;
			if (p.getTour() != null)
			{
				pCost = getSnowShoeTour(p.getTour().getId()).getParticipantCost(0);
			}
			else
			{
				pCost = new TOParticipantCost(email, name, 0, 0);
			}
			int cost = pCost.getTotalCostForBookableItems();

			String authCode = p.getAuthorizationCode();
			String status = p.getStatusFullName();

			participantTO.add(new TOParticipant(email, name, authCode, cost, status));
		}
		return participantTO;
	}

	/**
	 * Get tours as TOSnowShoeTour
	 * 
	 * @author Bilar Mokhtari @bmok
	 * @return List of TOSnowShoeTour
	 */
	public static List<Integer> getWeeksWithParticipants() {
		List<Integer> weeks = new ArrayList<>();
		List<Tour> tours = ssts.getTours();

		for (Tour tour : tours) {
			int week = tour.getStartWeek();
			if (!weeks.contains(week) && !tour.getParticipants().isEmpty()) {
				weeks.add(week);
			}
		}
	
		Collections.sort(weeks);
		return weeks;
	}

	/*
	 * @author Jennifer Tram Su @jennifertramsu
	 */
	public static TOSnowShoeTourSeason getSnowShoeTourSeason() {
		return new TOSnowShoeTourSeason(ssts.getStartDate(), ssts.getNrWeeks(),
				ssts.getPriceOfGuidePerWeek());
	}
}
