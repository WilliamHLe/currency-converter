package valutaKalk.restapi;

import javax.ws.rs.*;

import org.json.simple.parser.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
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

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public JSONObject info() throws Exception{
    try {
      Object obj = new JSONParser().parse(new FileReader("valuta.json"));
      test = (JSONObject) obj;
      return test;
    } catch(Exception e){
      test.put("Empty","True");
      return test;
    }
  }

  //API som regner ut mellom valutaene, og viser resultatet på siden som et JSON objekt
  @GET
  @Path("/{valuta1}.{valuta2}.{antall}") //Parameterne som er nødvendig. valuta1 er hvilken valuta som skal regnes fra, valuta2 er valuta som skal regnes til og antall hvor mya av valuta1 det skal regnes med
  @Produces(MediaType.APPLICATION_JSON)
  public JSONObject calculate(@PathParam("valuta1") String valuta1,@PathParam("valuta2") String valuta2, @PathParam("antall") double antall) {
    try {
      result = Valuta.calc(valuta1,valuta2,antall); //Regner ut
      test = JSON.ValtutaJSON(valuta1,valuta2,antall,result); //Setter til JSON objekt
    /*test.put("valuta1",valuta1);
    test.put("valuta1amount",antall);
    test.put("valuta2",valuta2);
    test.put("valuta2amount",result);

    /*PrintWriter pw = new PrintWriter("valuta.json");
    pw.write(test.toJSONString());
    pw.flush();
    pw.close();*/

      return test;
    } catch (Exception e) {
      return test;
    }

  }

  //API som regner ut og lagrer til JSON fil, og viser resulatet på siden
  @GET
  @Path("/save/{valuta1}.{valuta2}.{antall}") //Parameterne som er nødvendig. valuta1 er hvilken valuta som skal regnes fra, valuta2 er valuta som skal regnes til og antall hvor mya av valuta1 det skal regnes med
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public JSONObject calculateSave(@PathParam("valuta1") String valuta1,@PathParam("valuta2") String valuta2, @PathParam("antall") double antall) {
    try {
      result = Valuta.calc(valuta1,valuta2,antall); //Regner ut
      test = JSON.ValtutaJSON(valuta1,valuta2,antall,result); //Setter til JSON objekt
      PrintWriter pw = new PrintWriter("valuta.json"); //Skriver til fil
      pw.write(test.toJSONString());
      pw.flush();
      pw.close();

      return test;
    } catch (Exception e) {
      return test;
    }
  }

  //API som henter fra JSON fil
  @GET
  @Path("/load")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public static JSONObject load() {
    JSONObject json = new JSONObject();
    try {
      Object obj = new JSONParser().parse(new FileReader("valuta.json"));
      json = (JSONObject) obj;
      return json;
    } catch (Exception e) {
      return json;
    }
  }

  @GET
  @Path("/{valuta}")
  @Produces(MediaType.TEXT_PLAIN)
  public double calculateNOKToDollar(@PathParam("valuta") int valuta) {
    return Valuta.calculateNOKToDollar(valuta);
  }

  @GET
  @Path("/tests")
  @Produces(MediaType.TEXT_PLAIN)
  public String saySup() {
    return "Sup";
  }
}
