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
		String fraValuta_string = valuta[0];
		String tilValuta_string = valuta[1];
		String fraValuta_name = valuta[2];
		String tilValuta_name = valuta[3];

	

		double fraValuta_verdi = Double.parseDouble(fraValuta_string);
		double tilValuta_verdi = Double.parseDouble(tilValuta_string);

	

		scanner.close();

		
		 Valuta gammel = new Valuta();
		 gammel.setNOK(fraValuta_verdi); 
		 gammel.setName(fraValuta_name);
		 
		 Valuta ny = new Valuta();
		 ny.setNOK(tilValuta_verdi);
		 ny.setName(tilValuta_name);
		  
		 ValutaObjectLoader loader = new ValutaObjectLoader();
		 loader.ny = ny;
		 loader.gammel = gammel;
		 
		 return loader;
		 
		

	}

}