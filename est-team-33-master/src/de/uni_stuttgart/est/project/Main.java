package de.uni_stuttgart.est.project;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 
 * @author MikeAshi
 *
 */
public class Main extends Application {

	private static Stage primaryStage;
	private static Parent mainLayout;

	public static void main(String[] args) {
		launch(args);
	}

	public static void showadminView() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("Views/Login/admin.fxml"));
		try {
			mainLayout = loader.load();
			Scene s = new Scene(mainLayout);
			primaryStage.setScene(s);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void showUserView() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("Views/User/UserCP.fxml"));
		try {
			mainLayout = loader.load();
			Scene s = new Scene(mainLayout);
			primaryStage.setScene(s);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void showMainView() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("Views/Login/Login.fxml"));
		try {
			mainLayout = loader.load();
			Scene scene = new Scene(mainLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void start(Stage primaryStage) {
		Main.primaryStage = primaryStage;
		Main.primaryStage.setTitle("azE System");
		Main.primaryStage.setResizable(false);
		showMainView();
	}
}
