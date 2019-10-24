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