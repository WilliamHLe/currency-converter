package valutaKalk;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import valutaKalk.core.AppIO;
import valutaKalk.core.Valuta;

import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class AppTest {

	private Valuta USD;
	private Valuta NOK;
	private AppIO lagre = new AppIO();


	@Before
	public void setUp() {
		USD = new Valuta();
		NOK = new Valuta();

	}

	@Test (expected = IllegalArgumentException.class)
	public void testSetUSDNegative() {
		USD.setUSD(-50);
	}

	@Test
	public void testCalculateDollarToNOK() {
		assertEquals(86.8, Valuta.calculateDollarToNOK(10), 0.5);
	}

	@Test
	public void testCalculateDollarToEuro() {
		assertEquals(8.8, Valuta.calculateDollarToEuro(10), 0.5);
	}

	@Test
	public void testSaveAndLoad() {
		NOK.setNOK(20);
		USD.setUSD(30);
		try {
			lagre.save("valuta.txt", NOK, USD, NOK.getName(), USD.getName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		try {
			Double load = lagre.load("valuta.txt").gammel.getNOK();
			Assert.assertTrue(load == 20);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Test
	public void testRestAPI() {
		NOK.setNOK(30);
		USD.setUSD(Valuta.calculateNOKToDollar(30));
		JSONParser parser = new JSONParser();
		String valuta1 = null;
		String valuta2 = null;
		try {

			Object obj = parser.parse(new FileReader("valuta.json"));

			JSONObject jsonObject = (JSONObject) obj;

			valuta1 = (String) jsonObject.get("valuta1");
			valuta2 = (String) jsonObject.get("valuta2");

		} catch (Exception e) {
			e.printStackTrace();
		}
		//assertTrue(NOK.equals(valuta1));
		Assert.assertTrue(NOK.equals(valuta1));

	}
}


