package ca.mcgill.ecse.snowshoetours.javafx.fxml.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import ca.mcgill.ecse.snowshoetours.controller.LodgeController;
import ca.mcgill.ecse.snowshoetours.javafx.fxml.MainPageView;
import ca.mcgill.ecse.snowshoetours.model.Lodge.LodgeRating;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

/**
 * @author Emma Friesen @emma-friesen
 */
public class LodgePageController implements Initializable {
	@FXML
	private Button lodgeAddButton;

	@FXML
	private TextField lodgeAddressTextField;

	@FXML
	private ChoiceBox<String> lodgeChoiceBox;

	@FXML
	private Button lodgeDeleteButton;

	@FXML
	private TextField lodgeNameTextField;

	@FXML
	private ChoiceBox<String> lodgeRatingChoiceBox;

	String name;
	String address;
	String stars;
	int nrStars;
	LodgeRating rating;

	private String[] lodgeRating =
			{"One Star", "Two Stars", "Three Stars", "Four Stars", "Five Stars"};

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lodgeChoiceBox.addEventHandler(MainPageView.REFRESH_EVENT, e -> {
			lodgeChoiceBox.setItems(ViewUtils.getLodges());
			lodgeChoiceBox.setValue(null);
		});

		lodgeRatingChoiceBox.getItems().addAll(lodgeRating);
		MainPageView.getInstance().registerRefreshEvent(lodgeChoiceBox);
		MainPageView.getInstance().registerRefreshEvent(lodgeRatingChoiceBox);

	}

	@FXML
	void addLodge(ActionEvent event) {
		name = lodgeNameTextField.getText();
		address = lodgeAddressTextField.getText();
		stars = lodgeRatingChoiceBox.getValue();

		if (stars == "One Star") {
			nrStars = 1;
		} else if (stars == "Two Stars") {
			nrStars = 2;
		} else if (stars == "Three Stars") {
			nrStars = 3;
		} else if (stars == "Four Stars") {
			nrStars = 4;
		} else if (stars == "Five Stars") {
			nrStars = 5;
		}

		if (ViewUtils.successful(LodgeController.addLodge(name, address, nrStars))) {
			lodgeNameTextField.clear();
			lodgeAddressTextField.clear();
			lodgeRatingChoiceBox.setValue(null);
		}
		MainPageView.getInstance().refresh();
	}

	@FXML
	void deleteLodge(ActionEvent event) {
		String lodgeNameToDelete = lodgeChoiceBox.getValue();
		LodgeController.deleteLodge(lodgeNameToDelete);
		MainPageView.getInstance().refresh();
	}
}
