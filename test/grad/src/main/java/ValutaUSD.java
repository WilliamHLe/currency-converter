package app;

public class ValutaUSD {
	private double USD;
	private double nok = 8.68;
	private double eur = 0.88;
	
	public ValutaUSD() {
		this.nok = 8.68;
		this.eur = 0.88;
	}
	
	public void setUSD(double USD) {
		if(USD < 0) {
			throw new IllegalArgumentException("Beløpet må være mer enn 0.");
		}
		this.USD = USD;
	}

	public double getUSD() {
		return USD;
	}
	
	public double calculateDollarToNOK(double inpUSD) {
		return inpUSD * nok;
	}
	
	public double calculateDollarToEuro(double inpUSD) {
		return inpUSD * eur;
	}
	
	
}
