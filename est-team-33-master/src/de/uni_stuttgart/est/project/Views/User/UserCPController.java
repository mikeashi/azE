package de.uni_stuttgart.est.project.Views.User;

import java.net.URL;
import java.util.ResourceBundle;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import de.uni_stuttgart.est.project.Controllers.LoginSystemController;
import de.uni_stuttgart.est.project.models.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class UserCPController implements Initializable{
	User user = LoginSystemController.currentUser;
	
	@FXML
	public Label username;

	@FXML
	public Label date;

	@FXML
	public TextField kommzeit;

	@FXML
	public TextField gehzeit;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		username.setText(user.getFirstName());
		LocalDate today = new DateTime().toLocalDate();
		date.setText(today.toString("Y") + "-" + today.toString("M") + "-" + today.toString("d"));				
	}

	
}
