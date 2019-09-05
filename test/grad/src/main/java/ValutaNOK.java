package app;

public class ValutaNOK {
	private double NOK;
	private double usd = 0.115;
	private double eur = 0.102;
	
	public ValutaNOK() {
		this.usd = 0.115;
		this.eur = 0.102;
	}
	
	public void setNOK(double NOK) {
		if(NOK < 0) {
			throw new IllegalArgumentException("Beløpet må være mer enn 0.");
		}
		this.NOK = NOK;
	}

	public double getNOK() {
		return NOK;
	}
	
	public double calculateNOKToDollar(double inpNOK) {
		return inpNOK * usd;
	}
	
	public double calculateNOKToEuro(double inpNOK) {
		return inpNOK * eur;
	}
	
	
}
