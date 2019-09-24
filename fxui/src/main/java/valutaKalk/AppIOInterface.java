package valutaKalk;

import java.io.IOException;


public interface AppIOInterface {
	void save(String filename, Valuta nok, Valuta result, String old, String ny) throws IOException;
	
	ValutaObjectLoader load(String filename) throws IOException;

	
}

