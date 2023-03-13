package ca.mcgill.ecse.snowshoetours.controller;

import java.sql.Date;

import ca.mcgill.ecse.snowshoetours.application.SnowShoeToursApplication;

import ca.mcgill.ecse.snowshoetours.model.*;

import java.util.*;

public class SnowShoeTourController {
	private static SnowShoeTour ssts = SnowShoeToursApplication.getSnowShoeTour();
	
  public static TOSnowShoeTour getSnowShoeTour(int id) {
    // TODO Implement the method
	//Can't find tour if there is no tour
	if(!ssts.hasTours()) {
		return null;
	}
	//If there is no tour with that id --> index out of range 
	if (id <= 0 || id > ssts.numberOfTours()-1 ) {
		return null;
	}
		
	
	else {
		try {
			Tour tour = ssts.getTour(id);
			int tour_cost = ssts.getPriceOfGuidePerWeek()*(tour.getStartWeek()-tour.getEndWeek());
			TOSnowShoeTour SnowShoeTour = new TOSnowShoeTour(id,tour.getStartWeek(),tour.getEndWeek(),tour.getGuide().getAccountName(),tour.getGuide().getName(),tour_cost);
			//TOParticipant Attributes --> Create a TOParticipant object for each participant registered for the tour 
			int p_no = tour.numberOfParticipants();
			ArrayList <TOParticipantCost> participants = new ArrayList<TOParticipantCost>();
			for(int p = 0; p < p_no ; p++) {
				Participant participant = tour.getParticipant(p);
				//Calculate the total cost for bookable items 
				int bookableitems = 0;
				for(int i = 0;i<participant.getBookedItems().size();i++) {
					BookedItem booked_item = participant.getBookedItem(i);
					BookableItem bookable_item = booked_item.getItem();
					int num_item = booked_item.getQuantity();
					int discount = 0; 
					int single_cost = 0;
					//Check if the item is a standalone gear or in a combo 
					if (bookable_item instanceof Gear) {
						Gear gear = (Gear)bookable_item;
						single_cost = gear.getPricePerWeek() * ssts.getNrWeeks();
						
					}else if(bookable_item instanceof Combo) {
						Combo combo = (Combo) bookable_item;
						discount = combo.getDiscount();
						//add the price of each type of item in the combo
						for(int ci = 0; ci<combo.getBookedItems().size();ci ++) {
							ComboItem combo_item= combo.getComboItem(ci);
							int num_ci = combo_item.getQuantity();
							single_cost = single_cost + combo_item.getGear().getPricePerWeek() * ssts.getNrWeeks()*num_ci;

						}
				}
					
					bookableitems = bookableitems+(single_cost * num_item-discount);
				}
				
				
				//total_cost for snowshoetour is cost of bookable item + guide cost -refundable amount?   //typecast
				int totalcost = (int) ((bookableitems + tour_cost)*(participant.getRefundedPercentageAmount()*0.001));
				//hold on how is totalcost an integer if there is a percentage refund? 
				
				participants.set(p, new TOParticipantCost(participant.getAccountName(),participant.getName(),bookableitems,totalcost));
				
			}
			//how do you add participants to SnowShoeTour (setParticipants is private)
			  
			return SnowShoeTour;
		}catch(Exception e) {
			return null;
		}
	}
	  
  }

  public static String updateSnowShoeTour(Date startDate, int nrWeeks, int priceOfGuidePerWeek) {
    // TODO Implement the method, return error message (if any)
	if (nrWeeks < 0) {
		return "The number of riding weeks must be greater than or equal to zero";
	}
	if (priceOfGuidePerWeek < 0) {
		return "The price of guide per week must be greater than or equal to zero";
	}
	if (startDate.before(ssts.getStartDate())) {
		return "The start date cannot be from previous year or earlier";
	}
	else {
		try {
			ssts.setStartDate(startDate);
			ssts.setNrWeeks(nrWeeks);
			ssts.setPriceOfGuidePerWeek(priceOfGuidePerWeek);
			return "";		
		}catch(Exception e){
			return "Error: something went wrong";
			}
		}
  	}
}
