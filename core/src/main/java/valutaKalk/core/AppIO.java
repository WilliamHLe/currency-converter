package valutaKalk.core;

import org.json.simple.JSONObject;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;


public class AppIO implements AppIOInterface {

    public static String valuta1;
    public static String valuta2;
    public static double valuta1amount;
    public static double valuta2amount;

    //En JSON-fil opprettes og det blir skrevet inn data
    public void saveJSON(String nok, String result, double old, double ny) throws IOException{
        JSONObject obj = JSON.ValtutaJSON(nok, result, old, ny);

        PrintWriter pw = new PrintWriter("valuta.json");
        pw.write(obj.toJSONString());
        pw.flush();
        pw.close();
    }

    public void loadJSON() throws IOException, ParseException {
        //Det leses fra JSON-filen og informasjonen blir hentet
        Object obj = new JSONParser().parse(new FileReader("valuta.json"));
        JSONObject info = (JSONObject) obj;
        valuta1 = (String) info.get("valuta1");
        valuta2 = (String) info.get("valuta2");
        valuta1amount = (double) info.get("valuta1amount");
        valuta2amount = (double) info.get("valuta2amount");
    }

}
