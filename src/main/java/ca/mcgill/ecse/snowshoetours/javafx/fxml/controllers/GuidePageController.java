package ca.mcgill.ecse.snowshoetours.javafx.fxml.controllers;

import ca.mcgill.ecse.snowshoetours.controller.GuideController;
import ca.mcgill.ecse.snowshoetours.controller.TOGuide;
import ca.mcgill.ecse.snowshoetours.javafx.fxml.MainPageView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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
    private TableView<TOGuide> guideTableView;

    @FXML
    void initialize() {
        guideChoiceBox.addEventHandler(MainPageView.REFRESH_EVENT, e -> {
            guideChoiceBox.setItems(ViewUtils.getGuides());
            guideChoiceBox.setValue(null);
        });
        makeGuideTable();
        // Register the refreshable nodes
        MainPageView.getInstance().registerRefreshEvent(guideChoiceBox);
        MainPageView.getInstance().registerRefreshEvent(guideTableView);
    }

    @FXML
    void deleteGuideClicked(ActionEvent event) {
        try {

            String email = guideChoiceBox.getValue().toString();
            GuideController.deleteGuide(email);
            
            // Refresh the choice box
            MainPageView.getInstance().refresh();
        } catch (Exception e) {
            ViewUtils.makePopupWindow("Error","Please select a guide to delete");
        }
    }

    @FXML
    void registerGuideClicked(ActionEvent event) {
        String name = guideNameTextField.getText();
        String email = guideEmailTextField.getText();
        String password = guidePasswordTextField.getText();
        String emergencyContact = guideEmergencyTextField.getText();

        if (ViewUtils.successful(
                GuideController.registerGuide(email, password, name, emergencyContact))) {
            guideNameTextField.clear();
            guideEmailTextField.clear();
            guidePasswordTextField.clear();
            guideEmergencyTextField.clear();
        }
        MainPageView.getInstance().refresh();
    }

    /**
     * Helper method to make Guide Table
     * 
     * @author Antoine Phan @notkaramel
     */
    public void makeGuideTable() {
        guideTableView.getColumns().clear();
        guideTableView.getColumns().add(createGuideColumn("Name", "guideName", 120));
        guideTableView.getColumns().add(createGuideColumn("Email", "guideEmail", 200));

        guideTableView.addEventHandler(MainPageView.REFRESH_EVENT,
                e -> guideTableView.setItems(ViewUtils.getGuidesInfo()));

        MainPageView.getInstance().registerRefreshEvent(guideTableView);
    }

    /**
     * Helper method to create a table column

     * @param header
     * @param propertyName
     * @return
     */
    private TableColumn<TOGuide, ?> createGuideColumn(String header, String propertyName, Integer width) {
        TableColumn<TOGuide, ?> column = new TableColumn<>(header);
        column.setCellValueFactory(new PropertyValueFactory<>(propertyName));
        column.setPrefWidth(width);
        return column;
    }

}
