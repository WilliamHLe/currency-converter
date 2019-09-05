package app;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class ValutakalkulatorController {
	
	@FXML private TextField NOKInpField, dollarInpField;
	@FXML private TextArea NOKOutpArea, dollarOutpArea;
	@FXML private ComboBox<Valuta> combOld, combNew;
	
	private Valuta ValutaNOK;
	private Valuta ValutaUSD;
	private String button;
	private AppIO io = new AppIO();
	
	
        Valuta NOK = new Valuta("NOK", "NOK");
        Valuta USD = new Valuta("USD", "USD");
        Valuta EURO = new Valuta("EURO", "EURO");
 
        ObservableList<Valuta> list //
                = FXCollections.observableArrayList(NOK, USD, EURO);
	
	@FXML
	public void initialize() {
		Valuta Valuta = new Valuta();
		combOld.setItems(list);
		combNew.setItems(list);
		dollarInpField.setText("0");
		
	}
	
	@FXML
	public void calculate() {
		String result = "";
		
		try {
			if(combOld.getValue().equals(NOK) ) {
				if(combNew.getValue().equals(USD)) {
					double innNOK = Double.valueOf(NOKInpField.getText());
					
					result = "" + Valuta.calculateNOKToDollar(innNOK);
					dollarInpField.setText(result);
				}
				if(combNew.getValue().equals(EURO)) {
					double innNOK = Double.valueOf(NOKInpField.getText());
					
					result = "" + Valuta.calculateNOKToEuro(innNOK);
					dollarInpField.setText(result);
				}
			}
			else if(combOld.getValue().equals(USD) ) {
				if(combNew.getValue().equals(NOK)) {
					double innNOK = Double.valueOf(NOKInpField.getText());
					
					result = "" + Valuta.calculateDollarToNOK(innNOK);
					dollarInpField.setText(result);
				}
				if(combNew.getValue().equals(EURO)) {
					double innNOK = Double.valueOf(NOKInpField.getText());
					
					result = "" + Valuta.calculateDollarToEuro(innNOK);
					dollarInpField.setText(result);
				}
			}
			else if(combOld.getValue().equals(EURO) ) {
				if(combNew.getValue().equals(NOK)) {
					double innNOK = Double.valueOf(NOKInpField.getText());
					
					result = "" + Valuta.calculateEUROToNOK(innNOK);
					dollarInpField.setText(result);
				}
				if(combNew.getValue().equals(USD)) {
					double innNOK = Double.valueOf(NOKInpField.getText());
					
					result = "" + Valuta.calculateEUROToUSD(innNOK);
					dollarInpField.setText(result);
				}
			
			}
		}
		catch(Exception e){
			
		}
			
	}
	
	
	/*public void save() {
		try {
			System.out.println(ValutaNOK.getNOK());
			io.save("valuta.txt", ValutaNOK, ValutaUSD);
		} catch (IOException e) {
			e.printStackTrace();
			NOKOutpArea.setText("Noe gikk galt ved skriving til fil");
			dollarOutpArea.setText("Noe gikk galt ved skriving til fil");
		}
	}*/
	
	
	
	/*public void load() {
		try {
			ValutaObjectLoader loader = io.load("valuta.txt");
			
			Valuta usd = loader.usd;
			Valuta nok = loader.nok;
			
			String stringNOK = "" + nok.getNOK() + "\n"+ nok.calculateNOKToDollar(nok.getNOK()) + "\n" + nok.calculateNOKToEuro(nok.getNOK());
			NOKOutpArea.setText(stringNOK);
			
			String stringUSD = "" + usd.getUSD() + "\n" + usd.calculateDollarToNOK(usd.getUSD()) + "\n" + usd.calculateDollarToEuro(usd.getUSD());
			dollarOutpArea.setText(stringUSD);
		
			
			
		} catch (IOException e){
			e.printStackTrace();
			NOKOutpArea.setText("Filnavnet finnes ikke");
		}
	}*/
	
	
	
}
