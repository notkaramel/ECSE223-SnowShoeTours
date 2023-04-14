package ca.mcgill.ecse.snowshoetours.javafx.fxml.controllers;

import ca.mcgill.ecse.snowshoetours.controller.GearController;
import ca.mcgill.ecse.snowshoetours.javafx.fxml.MainPageView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

/**
 * @author Sameer Riaz @SRIAZ77
 */
public class GearAndComboPageController {
    @FXML
    private TextField newGearCostBox;
    @FXML
    private TextField newGearNameBox;
    @FXML
    private Button addGearButton;
    @FXML
    private TextField newComboDiscountBox;
    @FXML
    private TextField newComboNameBox;
    @FXML
    private Button addComboButton;
    @FXML
    private ChoiceBox<String> gearToRemoveSelectBox;
    @FXML
    private ChoiceBox<String> comboToRemoveSelectBox;
    @FXML
    private Button removeGearButton;
    @FXML
    private Button removeComboButton;
    @FXML
    private ChoiceBox<String> comboSelectBox;
    @FXML
    private ChoiceBox<String> gearToAddToComboSelectBox;
    @FXML
    private ChoiceBox<String> gearToRemoveFromComboSelectBox;

    @FXML
    public void initialize() {
        // the combo boxes are refreshable
        gearToRemoveSelectBox.addEventHandler(MainPageView.REFRESH_EVENT, e -> {
            gearToRemoveSelectBox.setItems(ViewUtils.getGears());
            gearToRemoveSelectBox.setValue(null);

        });

        comboToRemoveSelectBox.addEventHandler(MainPageView.REFRESH_EVENT, e -> {
            comboToRemoveSelectBox.setItems(ViewUtils.getCombos());
            comboToRemoveSelectBox.setValue(null);
        });

        comboSelectBox.addEventHandler(MainPageView.REFRESH_EVENT, e -> {
            comboSelectBox.setItems(ViewUtils.getCombos());
            comboSelectBox.setValue(null);
        });

        gearToRemoveFromComboSelectBox.addEventHandler(MainPageView.REFRESH_EVENT, e -> {
            gearToRemoveFromComboSelectBox.setItems(ViewUtils.getGears());
            gearToRemoveFromComboSelectBox.setValue(null);
        });

        gearToAddToComboSelectBox.addEventHandler(MainPageView.REFRESH_EVENT, e -> {
            gearToAddToComboSelectBox.setItems(ViewUtils.getGears());
            gearToAddToComboSelectBox.setValue(null);
        });

        // register the refreshable nodes
        MainPageView.getInstance().registerRefreshEvent(gearToRemoveSelectBox,
                comboToRemoveSelectBox, comboSelectBox, gearToRemoveFromComboSelectBox,
                gearToAddToComboSelectBox);
    }

    @FXML
    public void addGearButtonClicked(ActionEvent event) {
        Integer cost = Integer.parseInt(newGearCostBox.getText());
        String name = newGearNameBox.getText();

        if (ViewUtils.successful(GearController.addGear(name, cost))) {
            newGearCostBox.setText("");
            newGearNameBox.setText("");
            ViewUtils.makePopupWindow("Success", "Gear added Successfully");
        }
    }

    @FXML
    public void addComboButtonClicked(ActionEvent event) {
        Integer discount = Integer.parseInt(newComboDiscountBox.getText());
        String name = newComboNameBox.getText();

        if (ViewUtils.successful(GearController.addCombo(name, discount))) {
            newComboDiscountBox.setText("");
            newComboNameBox.setText("");
            ViewUtils.makePopupWindow("Success", "Combo added Successfully");
        }
    }

    public void removeGearButtonClicked(ActionEvent event) {
        var gearToRemove = gearToRemoveSelectBox.getValue();

        if (ViewUtils.successful(GearController.deleteGear(gearToRemove))) {
            ViewUtils.makePopupWindow("Success", "Gear removed Successfully");
        }
    }

    public void removeComboButtonClicked(ActionEvent event) {
        var comboToRemove = comboToRemoveSelectBox.getValue();
        if (ViewUtils.successful("")) {
            GearController.deleteCombo(comboToRemove);
            MainPageView.getInstance().refresh();
            ViewUtils.makePopupWindow("Success", "Gear removed Successfully");
        }
    }

    public void removeGearFromComboButtonClicked(ActionEvent event) {
        var gearToRemove = gearToRemoveFromComboSelectBox.getValue();
        var selectedCombo = comboSelectBox.getValue();

        if (ViewUtils.successful(GearController.removeGearFromCombo(gearToRemove, selectedCombo))) {
            ViewUtils.makePopupWindow("Success", "Gear removed Successfully from Combo");
            MainPageView.getInstance().refresh();
        }
    }

    public void addGearToComboButtonClicked(ActionEvent event) {
        var gearToAdd = gearToAddToComboSelectBox.getValue();
        var selectedCombo = comboSelectBox.getValue();

        if (ViewUtils.successful(GearController.addGearToCombo(gearToAdd, selectedCombo))) {
            ViewUtils.makePopupWindow("Success", "Gear added Successfully to Combo");
            MainPageView.getInstance().refresh();
        }
    }
}

