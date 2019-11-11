package valutaKalk;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import valutaKalk.core.AppIO;
import valutaKalk.core.JSON;
import valutaKalk.core.Valuta;
import valutaKalk.restapi.ValutaService;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class AppTest {
	private AppIO lagre = new AppIO();


	/*@Test (expected = IllegalArgumentException.class)
	public void testSetValutaNegative() {
		Valuta.setUSD(-50);
		Valuta.setNOK(-50);
		//Sjekker at en ikke kan bruke negative verdier
	}

	@Test
	public void testCalculateDollarToNOK() {
		assertEquals(86.8, Valuta.calc("USD","NOK",10), 0.5);
		//Tester korrekt kalkulasjon av calc-funksjonen
	}

	@Test
	public void testCalculateDollarToEuro() {
		assertEquals(8.8, Valuta.calc("USD", "EURO", 10), 0.5);
		//Tester korrekt kalkulasjon av calc-funksjonen
	}*/

	@Test
	public void testSaveAndLoad() {
		//Tester selve lagre- og gjenoppta-metoden til JSON-filen
		JSONObject json = JSON.ValutaJSON("NOK", "USD", 50, 5.75);
		ValutaService.save(json);


		JSONArray jsonArray = ValutaService.load();
		assertEquals("[{\"valuta1amount\":50.0,\"valuta1\":\"NOK\",\"valuta2\":\"USD\",\"valuta2amount\":5.75}]", jsonArray.toJSONString());

	}

	@Test
	//Tester change-knappen (jacoco ville av en eller annen grunn ikke vise at vi tester at change-knappen/metoden
	public void testChange(){
		String old = lagre.old;
		String ny = lagre.ny;
		lagre.change(old, ny);
		assertEquals(old, lagre.ny);
		assertEquals(ny, lagre.old);

	}
}


