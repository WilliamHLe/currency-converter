package valutaKalk.core;

import org.json.simple.JSONObject;

public class JSON {

	public static JSONObject ValtutaJSON(String valuta1,String valuta2,double valuta1amount,double valuta2amount) {
		JSONObject json = new JSONObject();
		json.put("valuta1",valuta1);
		json.put("valuta1amount",valuta1amount);
		json.put("valuta2",valuta2);
		json.put("valuta2amount",valuta2amount);
		return json;
	}

	//public static calculates() {

	//}
}

class ValJSON {
	private String valuta1;
	private double valuta1amount;
	private String valuta2;
	private double valuta2amount;
	public ValJSON(){}
	public String getVal1() {
		return valuta1;
	}
	public void setVal1(String valuta1) {
		this.valuta1 = valuta1;
	}
	public double getVal1amount() {
		return valuta1amount;
	}
	public void setVal1amount(double valuta1amount) {
		this.valuta1amount = valuta1amount;
	}
	public String getVal2() {
		return valuta2;
	}
	public void setVal2(String valuta2) {
		this.valuta2 = valuta2;
	}
	public double getVal2amount() {
		return valuta2amount;
	}
	public void setVal2amount(double valuta2amount) {
		this.valuta2amount = valuta2amount;
	}
	public String toString(){
		return "Valuta [ valuta1: "+valuta1+", valuta1amount: "+ valuta1amount+ ",valuta2: "+valuta2+", valuta2amount: "+ valuta2amount+ " ]";
	}
}