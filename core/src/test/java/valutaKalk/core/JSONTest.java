package valutaKalk.core;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;

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

	@Test (expected = IllegalArgumentException.class)
	//Tester at en ikke kan sette USD-verdien til <= 0
	public void testSetUSDNegative() {
		Valuta.setUSD(-50);
	}

	@Test
	public void testSaveAndLoad() {
    	//Tester selve lagre- og gjenoppta-metoden til JSON-filen
		try {
			lagre.saveJSON("NOK", "USD", 50, 5.75);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		try {
			lagre.loadJSON();
			assertEquals(5.75, 5.75, 0.5);

		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    @Test
    public void testRestAPI() {
    	//Tester at REST-API fungerer slik
		try {
			Valuta.setNOK(30);
			double ny = Valuta.calc("NOK", "USD", Valuta.getNOK());
			lagre.saveJSON("NOK", "USD", Valuta.getNOK(), ny);

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


    }
}

