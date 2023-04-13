package ca.mcgill.ecse.snowshoetours.javafx.fxml.controllers;

import java.io.IOException;
import ca.mcgill.ecse.snowshoetours.javafx.fxml.MainPageView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * @author Angela Zhu @angelaxzhu, Jennifer Tram Su @jennifertramsu
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
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../MainPage.fxml"));
		root = (Parent) loader.load();
		MainPageView mainPageView = loader.getController();

		try {
			mainPageView.init();
			mainPageView.getTabPane().getSelectionModel().select(mainPageView.getOverviewPage());
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goToGearAndComboClicked(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../MainPage.fxml"));
		root = (Parent) loader.load();
		MainPageView mainPageView = loader.getController();

		try {
			mainPageView.init();
			mainPageView.getTabPane().getSelectionModel().select(mainPageView.getGearAndComboPage());
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goToLodgeClicked(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../MainPage.fxml"));
		root = (Parent) loader.load();
		MainPageView mainPageView = loader.getController();

		try {
			mainPageView.init();
			mainPageView.getTabPane().getSelectionModel().select(mainPageView.getLodgePage());
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goToParticipantsClicked(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../MainPage.fxml"));
		root = (Parent) loader.load();
		MainPageView mainPageView = loader.getController();

		try {
			mainPageView.init();
			mainPageView.getTabPane().getSelectionModel().select(mainPageView.getParticipantPage());
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goToGuideClicked(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../MainPage.fxml"));
		root = (Parent) loader.load();
		MainPageView mainPageView = loader.getController();

		try {
			mainPageView.init();
			mainPageView.getTabPane().getSelectionModel().select(mainPageView.getGuidePage());
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goToSettingsClicked(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../MainPage.fxml"));
		root = (Parent) loader.load();
		MainPageView mainPageView = loader.getController();

		try {
			mainPageView.init();
			mainPageView.getTabPane().getSelectionModel().select(mainPageView.getSSTSettingsPage());
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goToTourCreationClicked(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../MainPage.fxml"));
		root = (Parent) loader.load();
		MainPageView mainPageView = loader.getController();

		try {
			mainPageView.init();
			mainPageView.getTabPane().getSelectionModel().select(mainPageView.getTourCreationPage());
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goToPaymentClicked(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../MainPage.fxml"));
		root = (Parent) loader.load();
		MainPageView mainPageView = loader.getController();

		try {
			mainPageView.init();
			mainPageView.getTabPane().getSelectionModel().select(mainPageView.getPaymentPage());
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goToStatusClicked(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../MainPage.fxml"));
		root = (Parent) loader.load();
		MainPageView mainPageView = loader.getController();

		try {
			mainPageView.init();
			mainPageView.getTabPane().getSelectionModel().select(mainPageView.getTourStatusPage());
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
