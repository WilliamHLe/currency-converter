package valutaKalk.core;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;

public class JSONTest {
	@Test
	public void testJSON() {
		JSONObject obj = new JSONObject();
		obj = JSON.ValtutaJSON("NOK","USD",50,5.75);
		Assert.assertEquals("{\"valuta1amount\":50,\"valuta1\":\"NOK\",\"valuta2\":\"USD\",\"valuta2amount\":5.75,}",JSONObject.toJSONString(obj));
	}
}

