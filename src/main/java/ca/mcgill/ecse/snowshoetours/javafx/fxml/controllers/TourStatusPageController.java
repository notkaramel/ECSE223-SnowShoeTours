package ca.mcgill.ecse.snowshoetours.javafx.fxml.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import ca.mcgill.ecse.snowshoetours.controller.SnowShoeTourCreationController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
/**
 * @author Bilar Mokhtari @bmokhtari
 */
public class TourStatusPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button cancelTripButton;

    @FXML
    private Button finishTripButton;

    @FXML
    private ChoiceBox<?> participantChoiceBox;

    @FXML
    private ChoiceBox<?> weekChoiceBox;

    @FXML
    private Button startTripButton;

    @FXML
    void cancelParticipantTrip(ActionEvent event) {

    }

    @FXML
    private void handleFinishTrip() {
        String email = (String) participantChoiceBox.getValue();
        String result = SnowShoeTourCreationController.finishParticipantTrip(email);
        if (!result.isEmpty()) {
            System.out.println(result); // Display the error message or handle it accordingly
        }
    }
    @FXML
    private void handleCancelTrip() {
        String email = (String) weekChoiceBox.getValue();
        String result = SnowShoeTourCreationController.cancelParticipantTrip(email);
        if (!result.isEmpty()) {
            System.out.println(result); // Display the error message or handle it accordingly
        }
    }

    @FXML
    void startTrip(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert cancelTripButton != null : "fx:id=\"cancelTripButton\" was not injected: check your FXML file 'TourStatusPage.fxml'.";
        assert finishTripButton != null : "fx:id=\"finishTripButton\" was not injected: check your FXML file 'TourStatusPage.fxml'.";
        assert participantChoiceBox != null : "fx:id=\"participantChoiceBox\" was not injected: check your FXML file 'TourStatusPage.fxml'.";
        assert weekChoiceBox != null : "fx:id=\"participantChoiceBox1\" was not injected: check your FXML file 'TourStatusPage.fxml'.";
        assert startTripButton != null : "fx:id=\"startTripButton\" was not injected: check your FXML file 'TourStatusPage.fxml'.";

    }

}
