package ca.mcgill.ecse.snowshoetours.javafx.fxml.controllers;

import ca.mcgill.ecse.snowshoetours.controller.ParticipantController;
import ca.mcgill.ecse.snowshoetours.javafx.fxml.MainPageView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

/**
 * @author Jennifer Tram Su (@jennifertramsu)
 */
public class ParticipantPageController {

    @FXML // fx:id="deleteParticipantButton"
    private Button deleteParticipantButton; // Value injected by FXMLLoader

    @FXML // fx:id="lodgeRequestCheckBox"
    private CheckBox lodgeRequestCheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="participantAvailabilityTextField"
    private TextField participantAvailabilityTextField; // Value injected by FXMLLoader

    @FXML // fx:id="participantChoiceBoxDelete", for delete participant feature
    private ChoiceBox<String> participantChoiceBoxDelete; // Value injected by FXMLLoader

    @FXML // fx:id="participantChoiceBoxGearCombo", for adding/deleting gear & combo feature
    private ChoiceBox<String> participantChoiceBoxGearCombo;
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

    // Add/Remove Gear & Combo feature
    @FXML
    private Button AddGearComboButton;

    @FXML
    private RadioButton ComboRadioOption;

    @FXML
    private ChoiceBox<String> GearComboChoiceBox;

    @FXML
    private RadioButton GearRadioOption;

    @FXML
    private Button RemoveGearComboButton;

    private boolean gearOptionSelected = true;

    /**
     * Helper method to initialize the choice box
     * 
     * @author Antoine Phan @notkaramel
     * @param choiceBox
     */
    private void initParticipantChoiceBox(ChoiceBox<String> choiceBox) {
        choiceBox.addEventHandler(MainPageView.REFRESH_EVENT, e -> {
            choiceBox.setItems(ViewUtils.getParticipants());
            choiceBox.setValue(null);
        });

        // Register the refreshable nodes
        MainPageView.getInstance().registerRefreshEvent(choiceBox);
    }

    private void initGearAndComboChoiceBox(ChoiceBox<String> choiceBox) {
        System.out.println(gearOptionSelected);

        choiceBox.addEventHandler(MainPageView.REFRESH_EVENT, e -> {
            if (gearOptionSelected) {
                choiceBox.setValue("Select a gear");
                choiceBox.setItems(ViewUtils.getGears());
            } else {
                choiceBox.setValue("Select a combo");
                choiceBox.setItems(ViewUtils.getCombos());
            }
        });

    }

    /**
     * This method is called by the FXMLLoader when initialization is complete
     * 
     * @author Jennifer Tram Su (@jennifertramsu), Antoine Phan (@notkaramel)
     */
    @FXML
    public void initialize() {
        // Initialize the choice box
        initParticipantChoiceBox(participantChoiceBoxDelete);
        initParticipantChoiceBox(participantChoiceBoxGearCombo);

        // initialize the radio buttons
        GearRadioOption.setSelected(true);
        ComboRadioOption.setSelected(false);

        initGearAndComboChoiceBox(GearComboChoiceBox);
        MainPageView.getInstance().registerRefreshEvent(GearComboChoiceBox);
    }

    /**
     * @author Jennifer Tram Su (@jennifertramsu)
     * @param event
     */
    @FXML
    void deleteParticipantClicked(ActionEvent event) {
        String email = participantChoiceBoxDelete.getValue().toString();
        ParticipantController.deleteParticipant(email);

        // Refresh the choice box
        MainPageView.getInstance().refresh();
    }

    /**
     * @author Jennifer Tram Su (@jennifertramsu)
     * @param event
     */
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
        if (ViewUtils.successful(ParticipantController.registerParticipant(email, password, name,
                emergencyContact, nrWeeks, weekAvailableFrom, weekAvailableUntil, lodgeRequired))) {
            participantNameTextField.clear();
            participantEmailTextField.clear();
            participantPasswordTextField.clear();
            participantEmergencyTextField.clear();
            participantAvailabilityTextField.clear();
            weekFromTextField.clear();
            weekToTextField.clear();
            lodgeRequestCheckBox.setSelected(false);
            ViewUtils.makePopupWindow("Success", "Successfully registered " + name);
        }
    }

    /**
     * @author Jennifer Tram Su (@jennifertramsu), Antoine Phan (@notkaramel)
     */
    @FXML
    void addGearComboAction(ActionEvent event) {

        String email = participantChoiceBoxGearCombo.getValue().toString();
        String gearCombo = GearComboChoiceBox.getValue().toString();
        if (ViewUtils
                .successful(ParticipantController.addBookableItemToParticipant(email, gearCombo))) {
            // Refresh the choice box
            ViewUtils.makePopupWindow("Successfully Added",
                    "Successfully added " + gearCombo + " to " + email);
            MainPageView.getInstance().refresh();
        } else {
            ViewUtils.makePopupWindow("Error", "Error adding " + gearCombo + " to " + email);
        }
    }

    /**
     * @author Jennifer Tram Su (@jennifertramsu), Antoine Phan (@notkaramel)
     */
    @FXML
    void removeGearComboAction(ActionEvent event) {
        String email = participantChoiceBoxGearCombo.getValue().toString();
        String gearCombo = GearComboChoiceBox.getValue().toString();
        if (ViewUtils.successful(
                ParticipantController.removeBookableItemFromParticipant(email, gearCombo))) {
            ViewUtils.makePopupWindow("Successfully Removed",
                    "Successfully removed " + gearCombo + " from " + email);
            // Refresh the choice box
            MainPageView.getInstance().refresh();
        } else {
            ViewUtils.makePopupWindow("Error", "Error removing " + gearCombo + " from " + email);
        }
    }

    /**
     * @author Antoine Phan (@notkaramel)
     * @param event
     */
    @FXML
    void optionComboSelected(ActionEvent event) {
        String email = "";
        try {
            email = participantChoiceBoxGearCombo.getValue().toString();
        } catch (NullPointerException e) {
            System.out.println("No participant selected");
        }

        ComboRadioOption.setSelected(true);
        GearRadioOption.setSelected(false);
        gearOptionSelected = false;
        MainPageView.getInstance().refresh();
        participantChoiceBoxGearCombo.setValue(email);
    }

    /**
     * @author Antoine Phan (@notkaramel)
     * @param event
     */
    @FXML
    void optionGearSelected(ActionEvent event) {
        String email = "";
        try {
            email = participantChoiceBoxGearCombo.getValue().toString();
        } catch (NullPointerException e) {
            System.out.println("No participant selected");
        }
        ComboRadioOption.setSelected(false);
        GearRadioOption.setSelected(true);
        gearOptionSelected = true;
        MainPageView.getInstance().refresh();
        participantChoiceBoxGearCombo.setValue(email);
    }
}
