package valutaKalk.core;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class JSONTest {
	@Test
	public void testJSON() {
		JSONObject obj;
		obj = JSON.ValtutaJSON("NOK","USD",50.0,5.75);
		Assert.assertEquals("{\"valuta1amount\":50.0,\"valuta1\":\"NOK\",\"valuta2\":\"USD\",\"valuta2amount\":5.75}",JSONObject.toJSONString(obj));
	}

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
		NOK.setNOK(50);
		double ny = Valuta.calculateNOKToDollar(NOK.getNOK());
		try {
			lagre.saveJSON("NOK", "USD", NOK.getNOK(), ny);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		try {
			lagre.loadJSON();
			assertEquals(5.75, ny, 0.5);

		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

