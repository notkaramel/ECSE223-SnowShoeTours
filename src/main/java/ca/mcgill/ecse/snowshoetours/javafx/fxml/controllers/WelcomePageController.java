package ca.mcgill.ecse.snowshoetours.javafx.fxml.controllers;

import java.io.FileInputStream;
import java.io.IOException;
import ca.mcgill.ecse.snowshoetours.javafx.fxml.MainPageView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * @author Angela Zhu @angelaxzhu
 */
public class WelcomePageController {
	@FXML
	private ImageView logo;

	@FXML
	private Button goToOverview;
	@FXML
	private Button goToGearAndCombo;
	@FXML
	private Button goToLodge;
	@FXML
	private Button goToParticipants;
	@FXML
	private Button goToGuide;
	@FXML
	private Button goToSettings;
	@FXML
	private Button goToTourCreation;
	@FXML
	private Button goToPayment;
	@FXML
	private Button goToStatus;

	private Stage stage;
	private Scene scene;
	private Parent root;

	public void goToOverviewClicked(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("OverviewPage.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void goToGearAndComboClicked(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("GearAndComboPage.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

	public void goToLodgeClicked(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("LodgePage.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

	public void goToParticipantsClicked(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("ParticipantPage.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void goToGuideClicked(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("GuidePage.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void goToSettingsClicked(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("SSTSettingsPage.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void goToTourCreationClicked(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("TourCreationPage.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void goToPaymentClicked(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("../pages/PaymentPage.fxml"));
		// stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void goToStatusClicked(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("TourStatusPage.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void initialize() {
		logo = new ImageView();
		Image LOGO = new Image(
				"file:src/main/java/ca/mcgill/ecse/snowshoetours/javafx/fxml/controllers/logo.png");
		logo.setImage(LOGO);
		MainPageView.getInstance().refresh();
	}

}
