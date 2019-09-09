package app;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class ValutakalkulatorController {
	
	@FXML private TextField NOKInpField, dollarInpField;
	@FXML private ComboBox<Valuta> combOld, combNew;
	@FXML private Label errorTxt;
	
	private AppIO io = new AppIO();
	
	
        Valuta NOK = new Valuta("NOK", "NOK");
        Valuta USD = new Valuta("USD", "USD");
        Valuta EURO = new Valuta("EURO", "EURO");
        double innNOK;
        double result;
 
        ObservableList<Valuta> list //
                = FXCollections.observableArrayList(NOK, USD, EURO);
	
	@FXML
	public void initialize() {
		combOld.setItems(list);
		combNew.setItems(list);
		dollarInpField.setText("0");
		
	}
	
	@FXML
	public void calculate() {
		errorTxt.setText("");
		try {
			double innNOK = Double.valueOf(NOKInpField.getText());
			if(combOld.getValue().equals(NOK) ) {
				NOK.setNOK(innNOK);
				if(combNew.getValue().equals(USD)) {
					result = Valuta.calculateNOKToDollar(innNOK);
					dollarInpField.setText("" + result);
					USD.setNOK(result);
				}
				if(combNew.getValue().equals(EURO)) {
					result = Valuta.calculateNOKToEuro(innNOK);
					dollarInpField.setText("" + result);
					EURO.setNOK(result);
				}
			}
			else if(combOld.getValue().equals(USD) ) {
				USD.setNOK(innNOK);
				if(combNew.getValue().equals(NOK)) {
					result = Valuta.calculateDollarToNOK(innNOK);
					dollarInpField.setText("" + result);
					NOK.setNOK(result);
				}
				if(combNew.getValue().equals(EURO)) {
					result = Valuta.calculateDollarToEuro(innNOK);
					dollarInpField.setText("" + result);
					EURO.setNOK(result);
				}
			}
			else if(combOld.getValue().equals(EURO) ) {
				EURO.setNOK(innNOK);
				if(combNew.getValue().equals(NOK)) {
					result = Valuta.calculateEUROToNOK(innNOK);
					dollarInpField.setText("" + result);
					NOK.setNOK(result);
				}
				if(combNew.getValue().equals(USD)) {
					result = Valuta.calculateEUROToUSD(innNOK);
					dollarInpField.setText("" + result);
					USD.setNOK(result);
				}
			
			}
				
		}
		catch(Exception e){
			errorTxt.setText(errorTxt.getText() + "Sørg for å ha valgt to gyldige og forskjellige valuta");
		}
			
	}
	
	
	public void save() {
		try {
			if(combOld.getValue().equals(NOK) ) {
				
				if(combNew.getValue().equals(USD)) {
					io.save("valuta.txt", NOK, USD, NOK.getName(), USD.getName());
				}
				if(combNew.getValue().equals(EURO)) {
					io.save("valuta.txt", NOK, EURO, NOK.getName(), EURO.getName());
				}
			}
			else if(combOld.getValue().equals(USD) ) {
				
				if(combNew.getValue().equals(NOK)) {
					io.save("valuta.txt", USD, NOK, USD.getName(), NOK.getName());
				}
				if(combNew.getValue().equals(EURO)) {
					io.save("valuta.txt", USD, EURO, USD.getName(), EURO.getName());
				}
			}
			else if(combOld.getValue().equals(EURO) ) {
				
				if(combNew.getValue().equals(NOK)) {
					io.save("valuta.txt", EURO, NOK, EURO.getName(), NOK.getName());
				}
				if(combNew.getValue().equals(USD)) {
					io.save("valuta.txt", EURO, USD, EURO.getName(), USD.getName());
				}
			
			}
			//io.save("valuta.txt", , );
		} catch (IOException e) {
			e.printStackTrace();
			errorTxt.setText("Noe gikk galt ved skriving til fil");
		}
	}
	
	
	
	public void load() {
		try {
			ValutaObjectLoader loader = io.load("valuta.txt");
			
			Valuta usd = loader.usd;
			Valuta nok = loader.nok;
			
			String stringNOK = "" + nok.getNOK() + " " + nok.getName();
			//errorTxt.setText(stringNOK);
			
			String stringUSD = "" + usd.getNOK() + " " + usd.getName();
			errorTxt.setText(stringNOK + "\n" + stringUSD);
		
			
			
		} catch (IOException e){
			e.printStackTrace();
			errorTxt.setText("Filnavnet finnes ikke");
		}
	}
	
	
	
}
