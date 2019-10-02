package valutaKalk.restapi;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import valutaKalk.core.Valuta;

@Path(ValutaService.VALUTA_SERVICE_PATH)
public class ValutaService {

  public static final String VALUTA_SERVICE_PATH = "valuta";

  double result;

  @GET
  @Path("/{valuta1}.{valuta2}.{antall}")
  @Produces(MediaType.APPLICATION_JSON)
  public String calculate(@PathParam("valuta1") String valuta1,@PathParam("valuta2") String valuta2, @PathParam("antall") double antall) {
    if(valuta1.equals("NOK")) {
      if(valuta2.equals("USD")) {
        result = Valuta.calculateNOKToDollar(antall);
      } else if(valuta2.equals("EUR")) {
        result = Valuta.calculateNOKToEuro(antall);
      }
    } else if(valuta1.equals("USD")) {
      if(valuta2.equals("NOK")) {
        result = Valuta.calculateDollarToNOK(antall);
      } else if(valuta2.equals("EUR")) {
        result = Valuta.calculateDollarToEuro(antall);
      }
    } else if(valuta1.equals("EUR")) {
      if(valuta2.equals("NOK")) {
        result = Valuta.calculateEUROToNOK(antall);
      } else if(valuta2.equals("USD")) {
        result = Valuta.calculateEUROToUSD(antall);
      }
    }
    return "{'valuta1':'"+valuta1+"','valuta1amount':"+antall+",'valuta2':'"+valuta2+"','valuta2amount':"+result+"}";
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
