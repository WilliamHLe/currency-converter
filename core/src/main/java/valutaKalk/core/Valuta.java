package valutaKalk.core;

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
		double NOKusd = 0.115;
		return inpNOK * NOKusd;
	}

	private static double calculateNOKToEuro(double inpNOK) {
		double NOKeur = 0.102;
		return inpNOK * NOKeur;
	}


	public void setUSD(double USD) {
		if (USD < 0) {
			throw new IllegalArgumentException("Beløpet må være mer enn 0.");
		}
	}

	public static double calculateDollarToNOK(double inpUSD) {
		double USDnok = 8.68;
		return inpUSD * USDnok;
	}

	public static double calculateDollarToEuro(double inpUSD) {
		double USDeur = 0.88;
		return inpUSD * USDeur;
	}


	private static double calculateEUROToNOK(double inpEURO) {
		double EUROnok = 9.99;
		return inpEURO * EUROnok;
	}

	private static double calculateEUROToUSD(double inpEURO) {
		double EUROusd = 1.11;
		return inpEURO * EUROusd;
	}


    private static double result;
	//calc sjekker hvilken valutatype den skal konvertere fra og til og konverterer med riktig kurs deretter.
    public static double calc(String valuta1,String valuta2,double antall) {
        try {
        	if(valuta1.equals(valuta2) || antall <= 0){
				error = 1;
			}
            else {
				switch (valuta1) {
					case "NOK":
						if (valuta2.equals("USD")) {
							result = calculateNOKToDollar(antall);
						} else if (valuta2.equals("EURO") || valuta2.equals("EUR")) {
							result = calculateNOKToEuro(antall);
						}
						break;
					case "USD":
						if (valuta2.equals("NOK")) {
							result = calculateDollarToNOK(antall);
						} else if (valuta2.equals("EURO") || valuta2.equals("EUR")) {
							result = calculateDollarToEuro(antall);
						}
						break;
					case "EURO":
					case "EUR":
						if (valuta2.equals("NOK")) {
							result = calculateEUROToNOK(antall);
						} else if (valuta2.equals("USD")) {
							result = calculateEUROToUSD(antall);
						}
						break;
				}
            	error = 0;
			}
            return result;
        } catch (Exception e) {
        	error = 1;
			throw new IllegalArgumentException("Beløpet må være mer enn 0.");
        }
    }


}