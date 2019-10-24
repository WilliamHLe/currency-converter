package valutaKalk.core;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Scanner;

public class AppIO implements AppIOInterface {

	JSONObject obj = new JSONObject();
	public static String valuta1;
	public static String valuta2;
	public static double valuta1amount;
	public static double valuta2amount;

	@Override
	public void save(String filename, Valuta nok, Valuta result, String old, String ny) throws IOException {
		PrintWriter writer = new PrintWriter(filename);

		String s = nok.getNOK() + " " + result.getNOK() + " " + nok.getName() + " " + result.getName();

		writer.print(s);

		writer.flush();
		writer.close();

		FileReader fileReader =
				new FileReader(filename);
		BufferedReader bufferedReader =
				new BufferedReader(fileReader);

		while ((s = bufferedReader.readLine()) != null) {
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

	@Override
	public JSONObject saveJSON(String nok, String result, double old, double ny) throws IOException{
		obj = JSON.ValtutaJSON(nok, result, old, ny);

		PrintWriter pw = new PrintWriter("valuta.json");
		pw.write(obj.toJSONString());
		pw.flush();
		pw.close();
		return null;
	}

	public void loadJSON() throws IOException, ParseException {
		Object obj = new JSONParser().parse(new FileReader("valuta.json"));
		JSONObject info = (JSONObject) obj;
		valuta1 = (String) info.get("valuta1");
		valuta2 = (String) info.get("valuta2");
		valuta1amount = (double) info.get("valuta1amount");
		valuta2amount = (double) info.get("valuta2amount");
	}

}