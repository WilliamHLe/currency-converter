package app;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class App extends Application{

	@Override
	public void start(final Stage primaryStage) throws Exception {
		
		primaryStage.setTitle("My Application");
		primaryStage.setScene(new Scene(FXMLLoader.load(App.class.getResource("App.fxml"))));
		primaryStage.show();
	}

	public static void main(final String[] args) {
		App.launch(args);
	}
}