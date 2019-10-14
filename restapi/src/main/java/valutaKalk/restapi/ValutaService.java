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

  @GET
  @Path("/{valuta1}.{valuta2}.{antall}")
  @Produces(MediaType.APPLICATION_JSON)
  public JSONObject calculate(@PathParam("valuta1") String valuta1,@PathParam("valuta2") String valuta2, @PathParam("antall") double antall) {
    try {
      result = Valuta.calc(valuta1,valuta2,antall);
      test = JSON.ValtutaJSON(valuta1,valuta2,antall,result);
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

  @GET
  @Path("/save/{valuta1}.{valuta2}.{antall}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public JSONObject calculateSave(@PathParam("valuta1") String valuta1,@PathParam("valuta2") String valuta2, @PathParam("antall") double antall) {
    try {
      result = Valuta.calc(valuta1,valuta2,antall);
      test = JSON.ValtutaJSON(valuta1,valuta2,antall,result);
      PrintWriter pw = new PrintWriter("valuta.json");
      pw.write(test.toJSONString());
      pw.flush();
      pw.close();

      return test;
    } catch (Exception e) {
      return test;
    }
  }

  @GET
  @Path("/load")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public JSONObject load() {
    try {
      Object obj = new JSONParser().parse(new FileReader("valuta.json"));
      test = (JSONObject) obj;
      return test;
    } catch (Exception e) {
      return test;
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
