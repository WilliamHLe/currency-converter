package valutaKalk.fxui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.glassfish.grizzly.http.server.HttpServer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import valutaKalk.restserver.ValutaGrizzlyApp;

public class App extends Application {
	private HttpServer restServer = null;
	/*@Override
    public void start(final Stage primaryStage) throws Exception {

        primaryStage.setTitle("My Application");
        primaryStage.setScene(new Scene(FXMLLoader.load(App.class.getResource("App.fxml"))));
        primaryStage.show();
    }

    public static void main(final String[] args) {
        App.launch(args);
    }*/
	@Override
	public void start(final Stage stage) throws Exception {
		URI baseUri = null;
		final List < String > args = getParameters().getRaw();
		if (args.size() >= 1) {
			final List < String > serverArgs = new ArrayList < String > ();
			baseUri = URI.create(args.get(0));
			serverArgs.add(baseUri.toString());
			if (args.size() >= 2) {
				// json of initial data
				serverArgs.add(args.get(1));
			}
			restServer = ValutaGrizzlyApp.startServer(serverArgs.toArray(new String[serverArgs.size()]), 5);
		}
		stage.setTitle("My Application");
		stage.setScene(new Scene(FXMLLoader.load(App.class.getResource("App.fxml"))));
		stage.show();
	}

	@Override
	public void stop() throws Exception {
		if (restServer != null) {
			restServer.shutdown();
		}
		super.stop();
	}

	public static void main(final String[] args) {
		// only needed on ios
		System.setProperty("os.target", "ios");
		System.setProperty("os.name", "iOS");
		System.setProperty("glass.platform", "ios");
		System.setProperty("targetos.name", "iOS");
		launch(args);
	}
}