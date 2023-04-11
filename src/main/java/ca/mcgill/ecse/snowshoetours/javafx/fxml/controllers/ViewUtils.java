package ca.mcgill.ecse.snowshoetours.javafx.fxml.controllers;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import ca.mcgill.ecse.snowshoetours.application.SnowShoeToursApplication;
import ca.mcgill.ecse.snowshoetours.model.SnowShoeTour;
import ca.mcgill.ecse.snowshoetours.javafx.fxml.MainPageView;
import ca.mcgill.ecse.snowshoetours.controller.TOParticipantCost;
import ca.mcgill.ecse.snowshoetours.controller.SnowShoeTourController;
import ca.mcgill.ecse.snowshoetours.controller.TOSnowShoeTour;

public class ViewUtils {
    // private static SnowShoeTour sst = SnowShoeToursApplication.getSnowShoeTour();

    /** Calls the controller and shows an error, if applicable. */
    public static boolean callController(String result) {
        if (result.isEmpty()) {
            MainPageView.getInstance().refresh();
            return true;
        }
        showError(result);
        return false;
    }

    /**
     * Calls the controller and returns true on success. This method is included for readability.
     */
    public static boolean successful(String controllerResult) {
        return callController(controllerResult);
    }

    /**
     * Creates a popup window.
     *
     * @param title: title of the popup window
     * @param message: message to display
     */
    public static void makePopupWindow(String title, String message) {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        VBox dialogPane = new VBox();

        // create UI elements
        Text text = new Text(message);
        Button okButton = new Button("OK");
        okButton.setOnAction(a -> dialog.close());

        // display the popup window
        int innerPadding = 10; // inner padding/spacing
        int outerPadding = 100; // outer padding
        dialogPane.setSpacing(innerPadding);
        dialogPane.setAlignment(Pos.CENTER);
        dialogPane.setPadding(new Insets(innerPadding, innerPadding, innerPadding, innerPadding));
        dialogPane.getChildren().addAll(text, okButton);
        Scene dialogScene =
                new Scene(dialogPane, outerPadding + 5 * message.length(), outerPadding);
        dialog.setScene(dialogScene);
        dialog.setTitle(title);
        dialog.show();
    }

    public static void showError(String message) {
        makePopupWindow("Error", message);
    }

    public static ObservableList<TOSnowShoeTour> getSnowShoeTours() {
        return FXCollections.observableList(SnowShoeTourController.getSnowShoeTours());
    }

    public static ObservableList<String> getParticipants() {
        return FXCollections.observableList(SnowShoeTourController.getParticipants());
    }

    public static ObservableList<String> getGuides() {
        return FXCollections.observableList(SnowShoeTourController.getGuides());
    }
    public static ObservableList<String> getGears() {
        return FXCollections.observableList(SnowShoeTourController.getGears());
    }
    public static ObservableList<String> getCombos() {
        return FXCollections.observableList(SnowShoeTourController.getCombos());
    }


    // public static ObservableList<String> getPersons() {
    //     List<String> persons = FMSController.getPersons();
    //     return FXCollections.observableList(persons);
    // }

    // public static ObservableList<String> getAirports() {
    //     List<String> airports = FMSController.getAirports();
    //     return FXCollections.observableList(airports);
    // }

    // public static ObservableList<TOFlight> getFlights() {
    //     List<TOFlight> flights = FMSController.getFlights();
    //     return FXCollections.observableList(flights);
    // }
}
