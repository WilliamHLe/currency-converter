package valutaKalk.core;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.net.URL;

public class Valuta {



	private String valutaType;
    public static int error;

	public Valuta() {

	}

	public Valuta(String name) {
		this.valutaType = name;
	}

	public String getName() {
		return valutaType;
	}

	@Override
	public String toString() {
		return this.valutaType;
	}

	private double NOK;
	private static double NOKusd = 0.115;


	public void setNOK(double NOK) {
		if (NOK < 0) {
			throw new IllegalArgumentException("Beløpet må være mer enn 0.");
		}
		this.NOK = NOK;
	}

	public static double calculateNOKToDollar(double inpNOK) {
		double NOKusd = 0.115;
		return inpNOK * NOKusd;
	}

	private double USD;
	private static double USDnok = 8.68;
	private static double USDeur = 0.88;


	public void setUSD(double USD) {
		if (USD < 0) {
			throw new IllegalArgumentException("Beløpet må være mer enn 0.");
		}
		this.USD = USD;
	}

	public static double calculateDollarToNOK(double inpUSD) {
		double USDnok = 8.68;
		return inpUSD * USDnok;
	}

	public static double calculateDollarToEuro(double inpUSD) {
		double USDeur = 0.88;
		return inpUSD * USDeur;
	}

    static double result;
	//calc sjekker hvilken valutatype den skal konvertere fra og til og konverterer med riktig kurs deretter.
    public static double calc(String valuta1,String valuta2,double antall) {
        try {
            if(valuta1.equals(valuta2) || antall <= 0){
                error = 1;
                return result;
            }
            else {
				File f = new File(Valuta.class.getResource("/valutaKalk/fxui/valutalist.json").getFile()); //Henter json fil fra fxui pakken
                Object obj = new JSONParser().parse(new FileReader(f));
                JSONObject json = (JSONObject) obj;
                //Finner riktig kurs og regner ut
                JSONObject jsonVal1 = (JSONObject) json.get(valuta1);
                double rate = (double) jsonVal1.get(valuta2);
                result = antall * rate;
                result = Math.round(result*100.0)/100.0;
                error = 0;
                return result;
            }
        } catch (Exception e) {
            error = 1;
            throw new IllegalArgumentException("Beløpet må være mer enn 0.");
        }
    }
}