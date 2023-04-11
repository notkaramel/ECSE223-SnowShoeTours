package ca.mcgill.ecse.snowshoetours.javafx.fxml.controllers;

import java.sql.Date;
import ca.mcgill.ecse.snowshoetours.controller.SnowShoeTourController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


/**
 * @author Angela Zhu @angelaxzhu
 */
public class SSTSettingsPageController {
	
		@FXML
		private Button setSeasonButton;
		@FXML
		private TextField startDateTextField;
		@FXML
		private TextField noWeeksTextField;
		
		@FXML
		private TextField guideCostTextField;
		

		@FXML
		public void setSeasonClicked(ActionEvent event) {
			Date start_week = Date.valueOf(startDateTextField.getText());
			int no_weeks = Integer.parseInt(noWeeksTextField.getText());
			int cost = Integer.parseInt(guideCostTextField.getText());
			if (ViewUtils.successful(SnowShoeTourController.updateSnowShoeTour(start_week, no_weeks, cost))) {
				
				startDateTextField.setText("");
				noWeeksTextField.setText("");
				guideCostTextField.setText("");
			}
		}    
}
