package valutaKalk;

import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;
import valutaKalk.core.AppIO;
import valutaKalk.core.Valuta;

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
		NOK.setNOK(50);
		double ny = NOK.calculateNOKToDollar(NOK.getNOK());
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


	/*@Test
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


	}*/
}


