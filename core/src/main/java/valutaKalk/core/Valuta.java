package valutaKalk.core;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;

public class Valuta {


    private static double NOK;
    private static double USD;
    private String valutaType;
    public static int error;

	public Valuta() {

	}

	public Valuta(String name) {
		this.valutaType = name;
	}

    public static double getNOK() {
	    return NOK;
    }

    @Override
	public String toString() {
		return this.valutaType;
	}


	public static void setNOK(double nok) {
		if (NOK < 0) {
			throw new IllegalArgumentException("Beløpet må være mer enn 0.");
		}
		else{
		    NOK = nok;
        }
	}


    public static void setUSD(double usd) {
        if (usd < 0) {
            throw new IllegalArgumentException("Beløpet må være mer enn 0.");
        }
        else{
            USD = usd;
        }
    }

    public static double getUSD() {
        return USD;
    }

    //calc sjekker hvilken valutatype den skal konvertere fra og til og konverterer med riktig kurs deretter.
    public static double calc(String valuta1,String valuta2,double antall) {
        try {
            Object obj = new JSONParser().parse(new FileReader("valutalist.json"));

            JSONObject json = (JSONObject) obj;
            //Finner riktig kurs og regner ut
            JSONObject jsonVal1 = (JSONObject) json.get(valuta1);
            double rate = (double) jsonVal1.get(valuta2);
            double result = antall * rate;
            error = 0;
            return result;
        } catch (Exception e) {
            error = 1;
            throw new IllegalArgumentException("STFU");
        }
    }
}