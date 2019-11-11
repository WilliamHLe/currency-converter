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
		//Setter opp valutaer for testing

	}

	@Test (expected = IllegalArgumentException.class)
	public void testSetValutaNegative() {
		USD.setUSD(-50);
		NOK.setNOK(-50);
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
	}

	@Test
	public void testSaveAndLoad() {
		//Tester knappene "Lagre" og "Gjenoppta"
		double ny = Valuta.calc("NOK","USD",50);
		try {
			lagre.saveJSON("NOK", "USD", 50, ny);
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


