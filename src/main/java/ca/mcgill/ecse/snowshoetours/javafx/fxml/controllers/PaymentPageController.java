package ca.mcgill.ecse.snowshoetours.javafx.fxml.controllers;
import ca.mcgill.ecse.snowshoetours.application.SnowShoeToursApplication;
import ca.mcgill.ecse.snowshoetours.javafx.fxml.MainPageView;
import ca.mcgill.ecse.snowshoetours.model.SnowShoeTour;
import ca.mcgill.ecse.snowshoetours.controller.SnowShoeTourCreationController;

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
    
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	
    	paymentParticipantChoiceBox.addEventHandler(MainPageView.REFRESH_EVENT, e -> {
    		paymentParticipantChoiceBox.setItems(ViewUtils.getParticipants());
    		paymentParticipantChoiceBox.setValue(null);});
			
    	MainPageView.getInstance().registerRefreshEvent(paymentParticipantChoiceBox);
    	
	}
    
    @FXML
    void authorizeParticipantPayment(ActionEvent event) {
    	authorizationCode = paymentAuthorizationCodeInput.getText();
    	email = paymentParticipantChoiceBox.getValue();
  
    	
    	if (ViewUtils.successful(SnowShoeTourCreationController.payForTrip(email, authorizationCode))){
    		paymentAuthorizationCodeInput.clear();
		}
    }

}
