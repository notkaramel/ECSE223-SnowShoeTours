package ca.mcgill.ecse.snowshoetours.javafx.fxml.controllers;

import ca.mcgill.ecse.snowshoetours.controller.ParticipantController;
import ca.mcgill.ecse.snowshoetours.javafx.fxml.MainPageView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/*
* @author Jennifer Tram Su (@jennifertramsu)
*/
public class ParticipantPageController {

    @FXML // fx:id="deleteParticipantButton"
    private Button deleteParticipantButton; // Value injected by FXMLLoader

    @FXML // fx:id="lodgeRequestCheckBox"
    private CheckBox lodgeRequestCheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="participantAvailabilityTextField"
    private TextField participantAvailabilityTextField; // Value injected by FXMLLoader

    @FXML // fx:id="participantChoiceBox"
    private ChoiceBox<String> participantChoiceBox; // Value injected by FXMLLoader

    @FXML // fx:id="participantEmailTextField"
    private TextField participantEmailTextField; // Value injected by FXMLLoader

    @FXML // fx:id="participantEmergencyTextField"
    private TextField participantEmergencyTextField; // Value injected by FXMLLoader

    @FXML // fx:id="participantNameTextField"
    private TextField participantNameTextField; // Value injected by FXMLLoader

    @FXML // fx:id="participantPasswordTextField"
    private PasswordField participantPasswordTextField; // Value injected by FXMLLoader

    @FXML // fx:id="registerParticipantButton"
    private Button registerParticipantButton; // Value injected by FXMLLoader

    @FXML // fx:id="weekFromTextField"
    private TextField weekFromTextField; // Value injected by FXMLLoader

    @FXML // fx:id="weekToTextField"
    private TextField weekToTextField; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    public void initialize() {
        // Participant choice box is refreshable
        participantChoiceBox.addEventHandler(MainPageView.REFRESH_EVENT, e -> {
			participantChoiceBox.setItems(ViewUtils.getParticipants());
			participantChoiceBox.setValue(null);
		});

        // Register the refreshable nodes
        MainPageView.getInstance().registerRefreshEvent(participantChoiceBox);
    }

    @FXML
    void deleteParticipantClicked(ActionEvent event) {
        String email = participantChoiceBox.getValue().toString();
        ParticipantController.deleteParticipant(email);
        
        // Refresh the choice box
        MainPageView.getInstance().refresh();
    }

    @FXML
    void registerParticipantClicked(ActionEvent event) {
        String name = participantNameTextField.getText();
        String email = participantEmailTextField.getText();
        String password = participantPasswordTextField.getText();
        String emergencyContact = participantEmergencyTextField.getText();
        int nrWeeks = Integer.parseInt(participantAvailabilityTextField.getText());
        int weekAvailableFrom = Integer.parseInt(weekFromTextField.getText());
        int weekAvailableUntil = Integer.parseInt(weekToTextField.getText());
        boolean lodgeRequired = false;

        if (lodgeRequestCheckBox.isSelected()) {
            lodgeRequired = true;
        } else if (!lodgeRequestCheckBox.isSelected()) {
            lodgeRequired = false;
        }

        // Reset text fields if successful
        if (ViewUtils.successful(ParticipantController.registerParticipant(email, password, name, emergencyContact, nrWeeks, weekAvailableFrom, weekAvailableUntil, lodgeRequired))) {
            participantNameTextField.clear();
            participantEmailTextField.clear();
            participantPasswordTextField.clear();
            participantEmergencyTextField.clear();
            participantAvailabilityTextField.clear();
            weekFromTextField.clear();
            weekToTextField.clear();
            lodgeRequestCheckBox.setSelected(false);
        }
    }
}
