package valutaKalk.core;

public class Valuta {


	private String valutaType;

	public Valuta() {

	}

	public Valuta(String name) {
		this.valutaType = name;
	}

	public String getName() {
		return valutaType;
	}

	public void setName(String name) {
		this.valutaType = name;
	}

	@Override
	public String toString() {
		return this.valutaType;
	}

	private double NOK;
	private static double NOKusd = 0.115;
	private static double NOKeur = 0.102;


	public void setNOK(double NOK) {
		if (NOK < 0) {
			throw new IllegalArgumentException("Beløpet må være mer enn 0.");
		}
		this.NOK = NOK;
	}

	public double getNOK() {
		return NOK;
	}

	public static double calculateNOKToDollar(double inpNOK) {
		return inpNOK * NOKusd;
	}

	public static double calculateNOKToEuro(double inpNOK) {
		return inpNOK * NOKeur;
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

	public double getUSD() {
		return USD;
	}

	public static double calculateDollarToNOK(double inpUSD) {
		return inpUSD * USDnok;
	}

	public static double calculateDollarToEuro(double inpUSD) {
		return inpUSD * USDeur;
	}

	private double EURO;
	private static double EUROnok = 9.99;
	private static double EUROusd = 1.11;


	public void setEURO(double EURO) {
		if (EURO < 0) {
			throw new IllegalArgumentException("Beløpet må være mer enn 0.");
		}
	}

	public double getEURO() {
		return EURO;
	}

	public static double calculateEUROToNOK(double inpEURO) {
		return inpEURO * EUROnok;
	}

	public static double calculateEUROToUSD(double inpEURO) {
		return inpEURO * EUROusd;
	}


}