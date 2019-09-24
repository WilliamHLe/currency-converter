package valutaKalk.fxui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class AppIO implements AppIOInterface {

	@Override
	public void save(String filename, Valuta nok, Valuta result, String old, String ny) throws IOException {
		PrintWriter writer = new PrintWriter(filename);

		String s = nok.getNOK() + " " + result.getNOK() + " " +  nok.getName() + " " + result.getName();

		writer.print(s);

		writer.flush();
		writer.close();
		
		FileReader fileReader = 
                new FileReader(filename);
		BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((s = bufferedReader.readLine()) != null) {
                System.out.println(s);
            }   

            // Always close files.
            bufferedReader.close();         

	}

	@Override
	public ValutaObjectLoader load(String filename) throws IOException {

		Scanner scanner = new Scanner(new File(filename));

		String valuta[] = scanner.nextLine().split(" ");
		String nok_string = valuta[0];
		String usd_string = valuta[1];
		String nok_name = valuta[2];
		String usd_name = valuta[3];

	

		double nok_verdi = Double.parseDouble(nok_string);
		double usd_verdi = Double.parseDouble(usd_string);

	

		scanner.close();

		
		 Valuta nok = new Valuta();
		 nok.setNOK(nok_verdi); 
		 nok.setName(nok_name);
		 
		 Valuta usd = new Valuta();
		 usd.setNOK(usd_verdi);
		 usd.setName(usd_name);
		  
		 ValutaObjectLoader loader = new ValutaObjectLoader();
		 loader.usd = usd;
		 loader.nok = nok;
		 
		 return loader;
		 
		

	}

}
