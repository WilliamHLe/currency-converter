package valutaKalk;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import valutaKalk.core.Valuta;
import valutaKalk.core.AppIO;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class AppTest {

	Valuta USD;
	Valuta NOK;
	AppIO lagre = new AppIO();


	@Before
	public void setUp() {
		USD = new Valuta();
		NOK = new Valuta();

	}

	@Test(expected = IllegalArgumentException.class)
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
			assertTrue(load == 20.0);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void APIServer()
	{
		//JSON parser object to parse read file
		JSONParser jsonParser = new JSONParser();

		try (FileReader reader = new FileReader("valuta.json"))
		{
			//Read JSON file
			Object obj = jsonParser.parse(reader);

			JSONArray valutaList = (JSONArray) obj;
			System.out.println(valutaList);

			//Iterate over employee array
			valutaList.forEach( emp -> parseValutaObject( (JSONObject) emp ) );

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	private static void parseValutaObject(JSONObject valuta)
	{
		//Get employee object within list
		JSONObject valutaObject = (JSONObject) valuta.get("v");

		//Get employee first name
		String firstName = (String) valutaObject.get("firstName");
		System.out.println(firstName);

		//Get employee last name
		String lastName = (String) valutaObject.get("lastName");
		System.out.println(lastName);

		//Get employee website name
		String website = (String) valutaObject.get("website");
		System.out.println(website);
	}


}