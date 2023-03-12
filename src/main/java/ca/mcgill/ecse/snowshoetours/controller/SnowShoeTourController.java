package ca.mcgill.ecse.snowshoetours.controller;

import java.sql.Date;

import ca.mcgill.ecse.snowshoetours.application.SnowShoeToursApplication;

import ca.mcgill.ecse.snowshoetours.model.SnowShoeTour;

//Check if necessary
import ca.mcgill.ecse.snowshoetours.model.Tour;

public class SnowShoeTourController {
	private static SnowShoeTour ssts = SnowShoeToursApplication.getSnowShoeTour();
	
  public static TOSnowShoeTour getSnowShoeTour(int id) {
    // TODO Implement the method 
	if(!sst.hasTours()) {
		return null;
	}
	  id_dne =
	
	if (id_dne) {
		return "Error: There is no tour with that id";
	}
	else {
		try {
			Tour tour = sst.getTour(id);  
			return tour;
		}catch(Exception e) {
			return "Error: Something went wrong";
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
