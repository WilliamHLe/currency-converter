package valutaKalk.core;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.Assert.assertEquals;

public class JSONTest {

    private Valuta USD;
    private Valuta NOK;
    private AppIO lagre = new AppIO();

    @Before
    public void setUp() {
        USD = new Valuta();
        NOK = new Valuta();

    }

	@Test
	public void testJSON() {
		JSONObject obj;
		obj = JSON.ValtutaJSON("NOK","USD",50.0,5.75);
		Assert.assertEquals("{\"valuta1amount\":50.0,\"valuta1\":\"NOK\",\"valuta2\":\"USD\",\"valuta2amount\":5.75}",JSONObject.toJSONString(obj));
	}

	@Test (expected = IllegalArgumentException.class)
	public void testSetUSDNegative() {
		USD.setUSD(-50);
	}

	@Test
	public void testSaveAndLoad() {
		//double ny = Valuta.calc("NOK", "USD", 50);
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
        NOK.setNOK(30);
        USD.setUSD(Valuta.calc("NOK", "USD", 30));
        JSONParser parser = new JSONParser();
        double valuta1;
		try {

            Object obj = parser.parse(new FileReader("valuta.json"));

            JSONObject jsonObject = (JSONObject) obj;

            PrintWriter pw = new PrintWriter("valuta.json");
            pw.write(((JSONObject) obj).toJSONString());
            pw.flush();
            pw.close();

            valuta1 = (double) jsonObject.get("valuta1amount");
			System.out.println(valuta1);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}

