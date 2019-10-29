package valutaKalk.fxui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.glassfish.grizzly.http.server.HttpServer;
import valutaKalk.restserver.ValutaGrizzlyApp;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class App extends Application {
	private HttpServer restServer = null;

	@Override
	public void start(final Stage stage) throws Exception {
		URI baseUri;
		final List < String > args = getParameters().getRaw();
		if (args.size() >= 1) {
			final List < String > serverArgs = new ArrayList<>();
			baseUri = URI.create(args.get(0));
			serverArgs.add(baseUri.toString());
			if (args.size() >= 2) {
				// json of initial data
				serverArgs.add(args.get(1));
			}
			restServer = ValutaGrizzlyApp.startServer(serverArgs.toArray(new String[0]), 5);
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