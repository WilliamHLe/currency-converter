package valutaKalk.restserver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import valutaKalk.restapi.ValutaService;


public class ValutaGrizzlyApp {

	private static URI BASE_URI = URI.create("http://localhost:8080/");

	public static HttpServer startServer(final String[] args, int waitSecondsForServer) throws IOException {
		final URI baseUri = (args.length >= 1 ? URI.create(args[0]) : BASE_URI);
		final ResourceConfig resourceConfig = (args.length >= 2 ? new ValutaConfig() : new ValutaConfig());
		final HttpServer httpServer = GrizzlyHttpServerFactory.createHttpServer(baseUri, resourceConfig);
		JSONObject json = new JSONObject();
		File file = new File("valuta.json");
		if (file.length() == 0) {
			json.put("valuta1","USD");
			json.put("valuta1amount",1);
			json.put("valuta2","NOK");
			json.put("valuta2amount",8.68);
			ValutaService.save(json);
		}
		if (waitSecondsForServer < 0) {
			return httpServer;
		}
		while (waitSecondsForServer > 0) {
			try {
				final URL clientUrl = new URL(baseUri + ValutaService.VALUTA_SERVICE_PATH);
				final HttpURLConnection connection = (HttpURLConnection) clientUrl.openConnection();
				final int responseCode = connection.getResponseCode();
				System.out.println("Trying " + clientUrl + ": " + responseCode);
				connection.disconnect();
				if (responseCode == 200) {
					return httpServer;
				}
			} catch (final Exception e) {
			}
			try {
				Thread.sleep(1000);
				waitSecondsForServer -= 1;
			} catch (final InterruptedException e) {
				return null;
			}
		}
		return null;
	}

	public static void stopServer(final HttpServer server) throws IOException {
		server.shutdown();
	}

	public static void main(final String[] args) throws IOException {
		try {
			final HttpServer server = startServer(args, -1);
			Runtime.getRuntime().addShutdownHook(new Thread(server::shutdownNow));
			Thread.currentThread().join();
		} catch (final InterruptedException ex) {
			Logger.getLogger(ValutaGrizzlyApp.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
