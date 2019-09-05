package app;

import java.io.IOException;


public interface AppIOInterface {
	void save(String filename, ValutaNOK nok, ValutaUSD usd) throws IOException;
	
	ValutaObjectLoader load(String filename) throws IOException;
}

