package valutaKalk.core;

import org.json.simple.JSONObject;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;


public class AppIO {

    public String old = "NOK";
    public String ny = "USD";

    public void change(String old, String ny){
        this.old = ny;
        this.ny = old;
    }
}
