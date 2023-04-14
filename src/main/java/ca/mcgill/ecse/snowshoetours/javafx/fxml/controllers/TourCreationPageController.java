package ca.mcgill.ecse.snowshoetours.javafx.fxml.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import ca.mcgill.ecse.snowshoetours.controller.SnowShoeTourCreationController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * @author Bilar Mokhtari @bmokhtari
 */
public class TourCreationPageController {

    @FXML
    private ResourceBundle resources;
    @FXML
    private Button initiateSnowToursCreationButton;
    @FXML
    private URL location;

    @FXML
    void handleInitiateSnowToursCreation(ActionEvent event) {
        try {
            SnowShoeTourCreationController.initiateSnowToursCreation();
            ViewUtils.makePopupWindow("", "Tour Successfully Created");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ViewUtils.makePopupWindow("", "Tour is already initialized");
        }
        
    }

    @FXML
    void initialize() {
        assert initiateSnowToursCreationButton != null : "fx:id=\"initiateSnowToursCreationButton\" was not injected: check your FXML file.";
    }
}
