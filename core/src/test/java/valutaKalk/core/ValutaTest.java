package valutaKalk.core;

import org.junit.Test;
import org.junit.Assert;

public class ValutaTest {
	@Test
    public void testCalc() {
        double NOKUSD = Valuta.calc("NOK","USD",50);
        double NOKEURO = Valuta.calc("NOK","EURO",50);
        double USDNOK = Valuta.calc("USD","NOK",50);
        double USDEUR = Valuta.calc("USD","EURO",50);
        double EURONOK = Valuta.calc("EURO","NOK",50);
        double EUROUSD = Valuta.calc("EURO","USD",50);
        Assert.assertEquals(Double.toString(5.75),NOKUSD);
        Assert.assertEquals(Double.toString(5.1),NOKEURO);
        Assert.assertEquals(Double.toString(434.0),USDNOK);
        Assert.assertEquals(Double.toString(44.0),USDEUR);
        Assert.assertEquals(Double.toString(499.5),EURONOK);
        Assert.assertEquals(Double.toString(55.50000000000001),EUROUSD);
    }
}