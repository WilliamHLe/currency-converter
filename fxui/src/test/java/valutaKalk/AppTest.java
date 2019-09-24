package valutaKalk;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class AppTest {
	
	Valuta USD;
	Valuta NOK;
	AppIO lagre = new AppIO();
	
	
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
			Double load = lagre.load("valuta.txt").nok.getNOK();
			assertTrue(load == 20.0);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
