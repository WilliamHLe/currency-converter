package valutaKalk.core;

import org.json.simple.parser.ParseException;

import java.io.IOException;


public interface AppIOInterface {
	void saveJSON(String nok, String result, double old, double ny) throws IOException;
	void loadJSON() throws IOException, ParseException;


}
