package valutaKalk.core;

import org.json.simple.JSONObject;

public class JSON {

	//Funksjon som gjør om valutaer og verdier til JSON objekt
	public static JSONObject ValutaJSON(String valuta1,String valuta2,double valuta1amount,double valuta2amount) {
		JSONObject json = new JSONObject();
		json.put("valuta1",valuta1);
		json.put("valuta1amount",valuta1amount);
		json.put("valuta2",valuta2);
		json.put("valuta2amount",valuta2amount);
		return json;
	}
}