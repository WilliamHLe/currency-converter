package valutaKalk.core;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileReader;
import java.io.PrintWriter;

public class JSONTest {

    Valuta USD;
    Valuta NOK;
    AppIO lagre = new AppIO();

    @Before
    public void setUp() {
        USD = new Valuta();
        NOK = new Valuta();

    }

	@Test
	public void testJSON() {
		JSONObject obj = new JSONObject();
		obj = JSON.ValtutaJSON("NOK","USD",50,5.75);
		Assert.assertEquals("{\"valuta1amount\":50,\"valuta1\":\"NOK\",\"valuta2\":\"USD\",\"valuta2amount\":5.75,}",JSONObject.toJSONString(obj));
	}

    @Test
    public void testRestAPI() {
        NOK.setNOK(30);
        USD.setUSD(Valuta.calculateNOKToDollar(30));
        JSONParser parser = new JSONParser();
        double valuta1;
        double valuta2;
        try {

            Object obj = parser.parse(new FileReader("valuta.json"));

            JSONObject jsonObject = (JSONObject) obj;

            PrintWriter pw = new PrintWriter("valuta.json");
            pw.write(((JSONObject) obj).toJSONString());
            pw.flush();
            pw.close();

            valuta1 = (double) jsonObject.get("valuta1amount");
            valuta2 = (double) jsonObject.get("valuta2amount");
            System.out.println(valuta1);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}

