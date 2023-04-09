package ca.mcgill.ecse.snowshoetours.javafx.fxml.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import ca.mcgill.ecse.snowshoetours.controller.SnowShoeTourCreationController;
public class TourCreationPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void initiateSnowToursCreation(MouseEvent event) {
        SnowShoeTourCreationController.initiateSnowToursCreation();
    }

    @FXML
    void initialize() {

    }

}

