package de.uni_stuttgart.est.project.Views.Login;

import java.net.URL;
import java.util.ResourceBundle;

import de.uni_stuttgart.est.project.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

/**
 * 
 * @author MikeAshi
 *
 */
public class LoginController implements Initializable {
	@FXML
	public TextField username;
	@FXML
	public PasswordField password;

	@FXML
	private void Close() {
		System.exit(0);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		password.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				Login();
			}
		});
	}

	@FXML
	private void Login() {
		String loginUsername = de.uni_stuttgart.est.project.Controllers.LoginSystemController.login(username.getText(),
				password.getText());
		if (loginUsername != null) {
			if (de.uni_stuttgart.est.project.Controllers.LoginSystemController.currentUser.isAdmin()) {
				Main.showadminView();
			} else {
				Main.showUserView();
			}

		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Login Error");
			alert.setHeaderText("Login Error");
			alert.setContentText("You have entered invalid username or password");
			alert.showAndWait();
			Rest();
		}
	}

	@FXML
	private void Rest() {
		username.setText(null);
		password.setText(null);
	}

}
