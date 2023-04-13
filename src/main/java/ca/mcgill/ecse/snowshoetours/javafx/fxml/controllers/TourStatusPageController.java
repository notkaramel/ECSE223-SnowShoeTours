package ca.mcgill.ecse.snowshoetours.javafx.fxml.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import ca.mcgill.ecse.snowshoetours.controller.SnowShoeTourCreationController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import ca.mcgill.ecse.snowshoetours.javafx.fxml.MainPageView;

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
    private ChoiceBox<String> participantChoiceBox;

    @FXML
    private ChoiceBox<Integer> weekChoiceBox;

    @FXML
    private Button startTripButton;

    @FXML
    void cancelParticipantTrip(ActionEvent event) {
        String email = participantChoiceBox.getValue().toString();
        String result = SnowShoeTourCreationController.cancelParticipantTrip(email);
        if (!result.isEmpty()) {
            ViewUtils.showError(result); // Display the error message or handle it accordingly
        } else {
            ViewUtils.makePopupWindow("Cancelled", "Participant" + email + " successfully cancelled");
        }
    }

    @FXML
    void finishParticipantTrip(ActionEvent event) {
        String email = participantChoiceBox.getValue().toString();
        String result = SnowShoeTourCreationController.finishParticipantTrip(email);
        if (!result.isEmpty()) {
            ViewUtils.showError(result); // Display the error message or handle it accordingly
        }else {
            ViewUtils.makePopupWindow("Success !!!", "Participant Successfully Finished their Trip");
        }
    }

    @FXML
    void startTrip(ActionEvent event) {
        int week = weekChoiceBox.getValue();
        String result = SnowShoeTourCreationController.startAllTripsForSpecificWeek(week);
        if (!result.isEmpty()) {
            ViewUtils.showError(result); // Display the error message or handle it accordingly
        } else {
            ViewUtils.makePopupWindow("Success !!!", "All Trips for Week " + week + " Successfully Started");
        }
    }

    @FXML
    void initialize() {
        // Participant choice box is refreshable
        participantChoiceBox.addEventHandler(MainPageView.REFRESH_EVENT, e -> {
        participantChoiceBox.setItems(ViewUtils.getParticipants());
        participantChoiceBox.setValue(null);
        });

        // Register the refreshable nodes
        MainPageView.getInstance().registerRefreshEvent(participantChoiceBox);

        // Use the getParticipants() method from the ViewUtils class
        participantChoiceBox.setItems(ViewUtils.getParticipants());

        // Participant choice box is refreshable
        weekChoiceBox.addEventHandler(MainPageView.REFRESH_EVENT, e -> {
            weekChoiceBox.setItems(ViewUtils.getTourWeeks());
            weekChoiceBox.setValue(null);
        });

        // Register the refreshable nodes
        MainPageView.getInstance().registerRefreshEvent(weekChoiceBox);

        // Use the getParticipants() method from the ViewUtils class
        weekChoiceBox.setItems(ViewUtils.getTourWeeks());
    }
}
