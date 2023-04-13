package ca.mcgill.ecse.snowshoetours.javafx.fxml;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author Antoine Phan @notkaramel, Jennifer Tram Su @jennifertramsu
 */
public class MainPageView extends Application{

  @FXML
  private Tab GearAndComboPage;

  @FXML
  private Tab GuidePage;

  @FXML
  private Tab LodgePage;

  @FXML
  private VBox MainPage;

  @FXML
  private Tab OverviewPage;

  @FXML
  private Tab ParticipantPage;

  @FXML
  private Tab PaymentPage;

  @FXML
  private Tab SSTSettingsPage;

  @FXML
  private Tab TourCreationPage;

  @FXML
  private Tab TourStatusPage;

  @FXML
  private Tab WelcomePage;

  @FXML
  private TabPane tabPane;
    
  public static final EventType<Event> REFRESH_EVENT = new EventType<>("REFRESH");
  private static MainPageView instance;
  private List<Node> refreshableNodes = new ArrayList<>();
  @Override
  public void start(Stage primaryStage) {
    instance = this;
    try {
      var root = (Pane) FXMLLoader.load(getClass().getResource("MainPage.fxml"));
      var scene = new Scene(root);
      primaryStage.setScene(scene);
      primaryStage.setMinWidth(800);
      primaryStage.setMinHeight(600);
      primaryStage.setTitle("SnowShoeTours Management System");
      primaryStage.show();
      refresh();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void initialize() {
    tabPane.getSelectionModel().selectedItemProperty().addListener((obs, oldTab, newTab) -> {
      if (newTab == null) {
        tabPane.getSelectionModel().select(oldTab);
      }
    });
  }

  // Register the node for receiving refresh events
  public void registerRefreshEvent(Node node) {
    refreshableNodes.add(node);
  }

  // Register multiple nodes for receiving refresh events
  public void registerRefreshEvent(Node... nodes) {
    for (var node: nodes) {
      refreshableNodes.add(node);
    }
  }

  // remove the node from receiving refresh events
  public void removeRefreshableNode(Node node) {
    refreshableNodes.remove(node);
  }

  // fire the refresh event to all registered nodes
  public void refresh() {
    for (Node node : refreshableNodes) {
      node.fireEvent(new Event(REFRESH_EVENT));
    }
  }

  public static MainPageView getInstance() {
    return instance;
  }

  public TabPane getTabPane() {
    return tabPane;
  }

  public Tab getOverviewPage() {
    return OverviewPage;
  }

  public Tab getParticipantPage() {
    return ParticipantPage;
  }

  public Tab getPaymentPage() {
    return PaymentPage;
  }

  public Tab getTourCreationPage() {
    return TourCreationPage;
  }

  public Tab getTourStatusPage() {
    return TourStatusPage;
  }

  public Tab getWelcomePage() {
    return WelcomePage;
  }

  public Tab getGearAndComboPage() {
    return GearAndComboPage;
  }

  public Tab getGuidePage() {
    return GuidePage;
  }

  public Tab getLodgePage() {
    return LodgePage;
  }

  public Tab getSSTSettingsPage() {
    return SSTSettingsPage;
  }
}
