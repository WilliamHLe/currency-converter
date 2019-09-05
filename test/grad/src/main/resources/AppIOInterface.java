package app;

import java.io.IOException;


public interface AppIOInterface {
	void save(String filename, Valuta nok, Valuta usd) throws IOException;
	
	ValutaObjectLoader load(String filename) throws IOException;
}

