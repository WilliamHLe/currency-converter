package valutaKalk.core;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;
import valutaKalk.restapi.ValutaService;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.Assert.assertEquals;

public class JSONTest {

    private AppIO lagre = new AppIO();

	@Test
	public void testJSON() {
    	//Tester at JSON-filen inneholder riktig informasjon
		JSONObject obj;
		obj = JSON.ValutaJSON("NOK","USD",50.0,5.75);
		Assert.assertEquals("{\"valuta1amount\":50.0,\"valuta1\":\"NOK\",\"valuta2\":\"USD\",\"valuta2amount\":5.75}",JSONObject.toJSONString(obj));
	}

	/*@Test (expected = IllegalArgumentException.class)
	//Tester at en ikke kan sette USD-verdien til <= 0
	public void testSetUSDNegative() {
		//Valuta.setUSD(-50);
	}*/

	@Test
	public void testSaveAndLoad() {
    	//Tester selve lagre- og gjenoppta-metoden til JSON-filen
		JSONObject json = JSON.ValutaJSON("NOK", "USD", 50, 5.75);
		ValutaService.save(json);


		JSONArray jsonArray = ValutaService.load();
		assertEquals("[{\"valuta1amount\":50.0,\"valuta1\":\"NOK\",\"valuta2\":\"USD\",\"valuta2amount\":5.75}]", jsonArray.toJSONString());

	}

    /*@Test
    public void testRestAPI() {
    	//Tester at REST-API fungerer slik
		try {
			//Valuta.setNOK(30);
			double ny = Valuta.calc("NOK", "USD", 30);
			lagre.saveJSON("NOK", "USD", 30, ny);

			JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader("valuta.json"));
            JSONObject jsonObject = (JSONObject) obj;
            PrintWriter pw = new PrintWriter("valuta.json");
            pw.write(((JSONObject) obj).toJSONString());
            pw.flush();
            pw.close();

            double valuta2 = (double) jsonObject.get("valuta2amount");
			Assert.assertEquals(ny, valuta2, 0);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }*/
}

