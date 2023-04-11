package ca.mcgill.ecse.snowshoetours.javafx.fxml.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import ca.mcgill.ecse.snowshoetours.controller.SnowShoeTourCreationController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import ca.mcgill.ecse.snowshoetours.javafx.fxml.controllers.ViewUtils;

public class TourCreationPageController {

    @FXML
    private ResourceBundle resources;
    @FXML
    private Button initiateSnowToursCreationButton;

    @FXML
    private URL location;

    @FXML
    void handleInitiateSnowToursCreation(ActionEvent event) {
        String result = SnowShoeTourCreationController.initiateSnowToursCreation();
        if (!result.isEmpty()) {
            ViewUtils.showError(result);
        }else{
            ViewUtils.makePopupWindow("", "Tour Successfully Created");
        }
    }




    @FXML
    void initialize() {
        assert initiateSnowToursCreationButton != null : "fx:id=\"initiateSnowToursCreationButton\" was not injected: check your FXML file.";
    }

}
