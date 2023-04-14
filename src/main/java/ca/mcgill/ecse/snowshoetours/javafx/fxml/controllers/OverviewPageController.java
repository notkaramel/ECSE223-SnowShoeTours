package ca.mcgill.ecse.snowshoetours.javafx.fxml.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import ca.mcgill.ecse.snowshoetours.application.SnowShoeToursApplication;
import ca.mcgill.ecse.snowshoetours.controller.TOParticipantCost;
import ca.mcgill.ecse.snowshoetours.controller.TOParticipant;
import ca.mcgill.ecse.snowshoetours.controller.TOSnowShoeTour;
import ca.mcgill.ecse.snowshoetours.javafx.fxml.MainPageView;

public class OverviewPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<TOParticipant> ParticipantOverviewTable;

    @FXML
    private TableView<TOSnowShoeTour> TourOverviewTable;

    @FXML
    void refreshOverview(ActionEvent event) {
        System.out.println("refreshing overview!!");
        SnowShoeToursApplication.getSnowShoeTour();
        MainPageView.getInstance().refresh();
        initialize();
    }

    @FXML
    void initialize() {
        assert ParticipantOverviewTable != null : "fx:id=\"ParticipantOverviewTable\" was not injected: check your FXML file 'OverviewPage.fxml'.";
        assert TourOverviewTable != null : "fx:id=\"TourOverviewTable\" was not injected: check your FXML file 'OverviewPage.fxml'.";
        makeParticipantOverviewTable();
        makeTourOverviewTable();
    }

    /**
     * Helper method to make ParticipantOverviewTable
     * @author Antoine Phan @notkaramel
     */
    private void makeParticipantOverviewTable() {
        ParticipantOverviewTable.getColumns().clear();
        ParticipantOverviewTable.getColumns().add(createParticipantColumn("Name", "participantName"));
        ParticipantOverviewTable.getColumns().add(createParticipantColumn("Email", "participantEmail"));
        ParticipantOverviewTable.getColumns().add(createParticipantColumn("Total Cost", "totalCost"));
        ParticipantOverviewTable.getColumns().add(createParticipantColumn("Auth Code", "authorizationCode"));
        ParticipantOverviewTable.getColumns().add(createParticipantColumn("Status", "status"));

        ParticipantOverviewTable.addEventHandler(MainPageView.REFRESH_EVENT,
                e -> ParticipantOverviewTable.setItems(ViewUtils.getParticipantsInfo()));

        MainPageView.getInstance().registerRefreshEvent(ParticipantOverviewTable);
    }


    /**
     * Helper method to make TourOverviewTable
     * @author Antoine Phan @notkaramel
     */
    public void makeTourOverviewTable() {
        TourOverviewTable.getColumns().clear();
        TourOverviewTable.getColumns().add(createTourColumn("Number", "id"));
        TourOverviewTable.getColumns().add(createTourColumn("Start Week", "startWeek"));
        TourOverviewTable.getColumns().add(createTourColumn("End Week", "endWeek"));
        TourOverviewTable.getColumns().add(createTourColumn("Guide Name", "guideName"));
        TourOverviewTable.getColumns().add(createTourColumn("Guide Cost", "totalCostForGuide"));

        TourOverviewTable.addEventHandler(MainPageView.REFRESH_EVENT,
                e -> TourOverviewTable.setItems(ViewUtils.getSnowShoeTours()));

        MainPageView.getInstance().registerRefreshEvent(TourOverviewTable);
    }
    /**
     * Helper method to create a table column
     * 
     * Given to us from Tutorial 9
     * 
     * @param header
     * @param propertyName
     * @return
     */
    private TableColumn<TOSnowShoeTour, ?> createTourColumn(String header,
            String propertyName) {
        TableColumn<TOSnowShoeTour, ?> column = new TableColumn<>(header);
        column.setCellValueFactory(new PropertyValueFactory<>(propertyName));
        return column;
    }

    /**
     * Helper method to create a table column
     * 
     * Given to us from Tutorial 9
     * 
     * @param header
     * @param propertyName
     * @return
     */
    private TableColumn<TOParticipant, ?> createParticipantColumn(String header, String propertyName) {
        TableColumn<TOParticipant, ?> column = new TableColumn<>(header);
        column.setCellValueFactory(new PropertyValueFactory<>(propertyName));
        return column;
    }
}
