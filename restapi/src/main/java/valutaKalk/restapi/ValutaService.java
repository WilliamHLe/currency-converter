package valutaKalk.restapi;

import javax.ws.rs.*;

import org.json.simple.JSONArray;
import org.json.simple.parser.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import org.json.simple.JSONObject;
import javax.ws.rs.core.MediaType;

import valutaKalk.core.Valuta;
import valutaKalk.core.JSON;

@Path(ValutaService.VALUTA_SERVICE_PATH)
public class ValutaService {

  public static final String VALUTA_SERVICE_PATH = "valuta";

  double result;
  JSONObject test = new JSONObject();

  //API som regner ut mellom valutaene
  @GET
  @Path("/{valuta1}.{valuta2}.{antall}") //Parameterne som er nødvendig. valuta1 er hvilken valuta som skal regnes fra, valuta2 er valuta som skal regnes til og antall hvor mya av valuta1 det skal regnes med
  @Produces(MediaType.APPLICATION_JSON)
  public JSONObject calculate(@PathParam("valuta1") String valuta1,@PathParam("valuta2") String valuta2, @PathParam("antall") double antall) {
    try {
      result = Valuta.calc(valuta1,valuta2,antall); //Regner ut
      test = JSON.ValtutaJSON(valuta1,valuta2,antall,result); //Setter til JSON objekt

      return test;
    } catch (Exception e) {
      return test;
    }

  }

  //API lagrer til JSON fil
  @POST
  @Path("/")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public static void save(JSONObject json) {
    try {
      JSONArray jsonArray = new JSONArray();
      jsonArray.add(json);
      PrintWriter pw = new PrintWriter("valuta.json"); //Skriver til fil
      pw.write(jsonArray.toJSONString());//Overskriver det som ligger i filen
      pw.flush();
      pw.close();
    } catch (Exception e) {
    }
  }

  //API som legger til nytt element i JSON fil
  @PUT
  @Path("/")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public static void put(JSONObject json) {
    try {
      Object obj = new JSONParser().parse(new FileReader("valuta.json"));
      JSONArray jsonArray = (JSONArray) obj;
      jsonArray.add(json); //Legger til nytt element i arrayet
      //FileWriter fileWriter = new FileWriter("valuta.json", true);
      PrintWriter pw = new PrintWriter("valuta.json"); //Skriver til fil
      pw.write(jsonArray.toJSONString());
      pw.flush();
      pw.close();
    } catch (Exception e) {
    }
  }

  //API som fjerner fra JSON fil
  @DELETE
  @Path("/")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public static void delete() {
    try {
      JSONArray jsonArray = new JSONArray();
      PrintWriter pw = new PrintWriter("valuta.json"); //Skriver til fil
      pw.write(jsonArray.toJSONString());
      pw.flush();
      pw.close();
    } catch (Exception e) {
    }
  }

  //API som henter fra JSON fil
  @GET
  @Path("/")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public static JSONArray load() {
    JSONArray json = new JSONArray();
    try {
      Object obj = new JSONParser().parse(new FileReader("valuta.json"));
      json = (JSONArray) obj;
      return json;
    } catch (Exception e) {
      return json;
    }
  }
}
