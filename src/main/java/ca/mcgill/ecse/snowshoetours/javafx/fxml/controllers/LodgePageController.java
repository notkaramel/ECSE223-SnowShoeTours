package ca.mcgill.ecse.snowshoetours.javafx.fxml.controllers;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import ca.mcgill.ecse.snowshoetours.application.SnowShoeToursApplication;
import ca.mcgill.ecse.snowshoetours.model.SnowShoeTour;
import ca.mcgill.ecse.snowshoetours.model.Lodge;
import ca.mcgill.ecse.snowshoetours.model.Lodge.LodgeRating;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

/**
 * @author Emma Friesen @emma-friesen
 */

public class LodgePageController implements Initializable {
	private static SnowShoeTour sst = SnowShoeToursApplication.getSnowShoeTour();


    @FXML
    private Button lodgeAddButton;

    @FXML
    private TextField lodgeAddressTextField;

    @FXML
    private ChoiceBox<String> lodgeChoiceBox;

    @FXML
    private Button lodgeDeleteButton;

    @FXML
    private TextField lodgeNameTextField;

    @FXML
    private ChoiceBox<String> lodgeRatingChoiceBox;
    
    String name;
    String address;
    String stars;
    LodgeRating rating;
    
    private String[] lodgeRating = {"One Star", "Two Stars", "Three Stars", "Four Stars", "Five Stars"};
    
    private ArrayList<String> lodgeList = new ArrayList<String>();
  
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		for (Lodge lodge : sst.getLodges()) {
			String lodgeName = lodge.getName();
	    	lodgeList.add(lodgeName);
	    }
		
		lodgeChoiceBox.getItems().addAll(lodgeList);
		lodgeRatingChoiceBox.getItems().addAll(lodgeRating);
	}

    @FXML
    void addLodge(ActionEvent event) {
    	name = lodgeNameTextField.getText();
    	address = lodgeAddressTextField.getText();
    	stars = lodgeRatingChoiceBox.getValue();
    	
    	if (stars == "One Star") {
    		rating = LodgeRating.OneStar;
    	}
    	else if (stars == "Two Stars") {
    		rating = LodgeRating.TwoStars;
    	}
    	else if (stars == "Three Stars") {
    		rating = LodgeRating.ThreeStars;
    	}
    	else if (stars == "Four Stars") {
    		rating = LodgeRating.FourStars;
    	}
    	else if (stars == "Five Stars") {
    		rating = LodgeRating.FiveStars;
    	}
    	
		sst.addLodge(name, address, rating);
		
		lodgeList.add(name);
		
		//TODO: refresh?
		//TODO: add error pages
    }

    
    @FXML
    void deleteLodge(ActionEvent event) {
    	String lodgeNameToDelete = lodgeChoiceBox.getValue();
    	
    	Lodge lodgeToDelete = Lodge.getWithName(lodgeNameToDelete);
    		
    	sst.removeLodge(lodgeToDelete);
    	
    	lodgeList.remove(Lodge.getWithName(lodgeNameToDelete));
    	
    	//TODO: refresh?
    	//TODO: add error pages

    }

}




