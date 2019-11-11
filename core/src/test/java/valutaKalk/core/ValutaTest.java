package valutaKalk.core;

import org.junit.Test;
import org.junit.Assert;

public class ValutaTest {
	@Test
    public void testCalc() {
	    //Tester calc-metoden
        double NOKUSD = Valuta.calc("NOK","USD",50);
        double NOKEURO = Valuta.calc("NOK","EURO",50);
        double USDNOK = Valuta.calc("USD","NOK",50);
        double USDEUR = Valuta.calc("USD","EURO",50);
        double EURONOK = Valuta.calc("EURO","NOK",50);
        double EUROUSD = Valuta.calc("EURO","USD",50);
        Assert.assertEquals(5.75,NOKUSD,0.5);
        Assert.assertEquals(5.1,NOKEURO, 0.5);
        Assert.assertEquals(434.0,USDNOK, 0.5);
        Assert.assertEquals(44.0,USDEUR, 0.5);
        Assert.assertEquals(499.5,EURONOK, 0.5);
        Assert.assertEquals(55.50,EUROUSD, 0.5);
    }
}