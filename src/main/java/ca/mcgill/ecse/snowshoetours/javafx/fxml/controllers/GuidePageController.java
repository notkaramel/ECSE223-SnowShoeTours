package ca.mcgill.ecse.snowshoetours.javafx.fxml.controllers;

import ca.mcgill.ecse.snowshoetours.controller.GuideController;
import ca.mcgill.ecse.snowshoetours.javafx.fxml.MainPageView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/*
 * @author Jennifer Tram Su (@jennifertramsu)
 */
public class GuidePageController {

    @FXML
    private Button deleteGuideButton;

    @FXML
    private ChoiceBox<String> guideChoiceBox;

    @FXML
    private TextField guideEmailTextField;

    @FXML
    private TextField guideEmergencyTextField;

    @FXML
    private TextField guideNameTextField;

    @FXML
    private PasswordField guidePasswordTextField;

    @FXML
    private Button registerGuideButton;

    @FXML
    void initialize() {
        guideChoiceBox.addEventHandler(MainPageView.REFRESH_EVENT, e -> {
            guideChoiceBox.setItems(ViewUtils.getGuides());
            guideChoiceBox.setValue(null);
        });

        // Register the refreshable nodes
        MainPageView.getInstance().registerRefreshEvent(guideChoiceBox);
    }

    @FXML
    void deleteGuideClicked(ActionEvent event) {
        String email = guideChoiceBox.getValue().toString();
        GuideController.deleteGuide(email);

        // Refresh the choice box
        MainPageView.getInstance().refresh();
    }

    @FXML
    void registerGuideClicked(ActionEvent event) {
        String name = guideNameTextField.getText();
        String email = guideEmailTextField.getText();
        String password = guidePasswordTextField.getText();
        String emergencyContact = guideEmergencyTextField.getText();

        if (ViewUtils.successful(GuideController.registerGuide(email, password, name, emergencyContact))) {
            guideNameTextField.clear();
            guideEmailTextField.clear();
            guidePasswordTextField.clear();
            guideEmergencyTextField.clear();
        }
    }
}
