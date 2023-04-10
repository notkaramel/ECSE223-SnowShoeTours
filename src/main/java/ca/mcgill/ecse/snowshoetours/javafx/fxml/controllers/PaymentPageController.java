package ca.mcgill.ecse.snowshoetours.javafx.fxml.controllers;
import ca.mcgill.ecse.snowshoetours.application.SnowShoeToursApplication;
import ca.mcgill.ecse.snowshoetours.controller.TOParticipantCost;
import ca.mcgill.ecse.snowshoetours.model.Lodge;
import ca.mcgill.ecse.snowshoetours.model.Participant;
import ca.mcgill.ecse.snowshoetours.model.SnowShoeTour;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

/**
* @author Emma Friesen @emma-friesen
*/

public class PaymentPageController implements Initializable{
	private static SnowShoeTour sst = SnowShoeToursApplication.getSnowShoeTour();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button paymentAuthorizationButton;

    @FXML
    private TextField paymentAuthorizationCodeInput;

    @FXML
    private ChoiceBox<String> paymentParticipantChoiceBox;
    
    String email;
    
    String authorizationCode;
    
    private ArrayList<String> notPaidParticipantsList = new ArrayList<String>();
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	for (Participant participant : sst.getParticipants()) {
    		
    		if (participant.getStatusFullName() == "Assigned") {
    			notPaidParticipantsList.add(participant.getAccountName());
    		}
	    	
    		paymentParticipantChoiceBox.getItems().addAll(notPaidParticipantsList);
	    }
	}
    
    @FXML
    void authorizeParticipantPayment(ActionEvent event) {
    	authorizationCode = paymentAuthorizationCodeInput.getText();
    	email = paymentParticipantChoiceBox.getValue();
    	
    	
    	//TODO: call pay
    	//refresh participants list?
    }

}
