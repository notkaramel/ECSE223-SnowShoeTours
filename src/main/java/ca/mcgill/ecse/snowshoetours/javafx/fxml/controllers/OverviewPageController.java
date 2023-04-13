package ca.mcgill.ecse.snowshoetours.javafx.fxml.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import ca.mcgill.ecse.snowshoetours.application.SnowShoeToursApplication;
import ca.mcgill.ecse.snowshoetours.controller.TOSnowShoeTour;
import ca.mcgill.ecse.snowshoetours.javafx.fxml.MainPageView;

public class OverviewPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<TOSnowShoeTour> overviewTable;

    @FXML
    void refreshOverview(MouseEvent event) {
        if (event.getClickCount() == 1) {
            System.out.println("refreshing overview!!");
            SnowShoeToursApplication.getSnowShoeTour();
            MainPageView.getInstance().refresh();
        }
        initialize();
    }

    @FXML
    void initialize() {
        // SnowShoeTourCreationController.initiateSnowToursCreation();

        assert overviewTable != null : "fx:id=\"overviewTable\" was not injected: check your FXML file 'OverviewPage.fxml'.";
        overviewTable.getColumns().clear();
        overviewTable.getColumns().add(createTableColumn("Number", "id"));
        overviewTable.getColumns().add(createTableColumn("Start Week", "startWeek"));
        overviewTable.getColumns().add(createTableColumn("End Week", "endWeek"));

        overviewTable.getColumns().add(createTableColumn("Guide Name", "guideName"));
        overviewTable.getColumns()
                .add(createTableColumn("Guide Cost", "totalCostForGuide"));
        // overviewTable.getColumns().add(createTableColumn("Participant Name", "participantName"));
        overviewTable.getColumns().add(createTableColumn("Participant Cost", "participantCosts"));

        
        // overviewTable.getItems().addAll(MainPageView.getSnowShoeTour().getTOSnowShoeTours());
        // overview table if a refreshable element
        
        overviewTable.addEventHandler(MainPageView.REFRESH_EVENT, e -> overviewTable.setItems(ViewUtils.getSnowShoeTours()));
        
        // overviewTable.getItems().addAll(ViewUtils.getSnowShoeTours());

        // register refreshable nodes
        MainPageView.getInstance().registerRefreshEvent(overviewTable);

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
    public static TableColumn<TOSnowShoeTour, ?> createTableColumn(String header,
            String propertyName) {
        TableColumn<TOSnowShoeTour, ?> column = new TableColumn<>(header);
        column.setCellValueFactory(new PropertyValueFactory<>(propertyName));
        return column;
    }
}
