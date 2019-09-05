package app;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class AppIO implements AppIOInterface {

	@Override
	public void save(String filename, Valuta nok, Valuta usd) throws IOException {
		PrintWriter writer = new PrintWriter(filename);

		String s = nok.getNOK() + " " + usd.getUSD();

		writer.print(s);

		writer.flush();
		writer.close();

	}

	@Override
	public ValutaObjectLoader load(String filename) throws IOException {

		Scanner scanner = new Scanner(new File(filename));

		String valuta[] = scanner.nextLine().split(" ");
		String nok_string = valuta[0];
		String usd_string = valuta[1];

	

		double nok_verdi = Double.parseDouble(nok_string);
		double usd_verdi = Double.parseDouble(usd_string);

	

		scanner.close();

		
		 Valuta nok = new Valuta();
		 nok.setNOK(nok_verdi); 
		 
		 Valuta usd = new Valuta();
		 usd.setUSD(usd_verdi);
		  
		 ValutaObjectLoader loader = new ValutaObjectLoader();
		// loader.USD = usd;
		// loader.nok = nok;
		 
		 return loader;
		 
		

	}

}
