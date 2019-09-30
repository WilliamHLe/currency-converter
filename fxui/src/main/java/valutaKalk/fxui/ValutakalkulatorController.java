package valutaKalk.fxui;

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
	
	
        Valuta NOK = new Valuta("NOK");
        Valuta USD = new Valuta("USD");
        Valuta EURO = new Valuta("EURO");
        double utValuta;
        double innValuta;
        double savedInn;
        double savedUt;
 
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
			double innValuta = Double.valueOf(NOKInpField.getText());
			if(combOld.getValue().equals(NOK) ) {
				NOK.setNOK(innValuta);
				if(combNew.getValue().equals(USD)) {
					utValuta = Valuta.calculateNOKToDollar(innValuta);
					dollarInpField.setText("" + utValuta);
					USD.setUSD(utValuta);
				}
				if(combNew.getValue().equals(EURO)) {
					utValuta = Valuta.calculateNOKToEuro(innValuta);
					dollarInpField.setText("" + utValuta);
					EURO.setEURO(utValuta);
				}
			}
			else if(combOld.getValue().equals(USD) ) {
				USD.setUSD(innValuta);
				if(combNew.getValue().equals(NOK)) {
					utValuta = Valuta.calculateDollarToNOK(innValuta);
					dollarInpField.setText("" + utValuta);
					NOK.setNOK(utValuta);
				}
				if(combNew.getValue().equals(EURO)) {
					utValuta = Valuta.calculateDollarToEuro(innValuta);
					dollarInpField.setText("" + utValuta);
					EURO.setEURO(utValuta);
				}
			}
			else if(combOld.getValue().equals(EURO) ) {
				EURO.setEURO(innValuta);
				if(combNew.getValue().equals(NOK)) {
					utValuta = Valuta.calculateEUROToNOK(innValuta);
					dollarInpField.setText("" + utValuta);
					NOK.setNOK(utValuta);
				}
				if(combNew.getValue().equals(USD)) {
					utValuta = Valuta.calculateEUROToUSD(innValuta);
					dollarInpField.setText("" + utValuta);
					USD.setUSD(utValuta);
				}
			
			}
				
		}
		catch(Exception e){
			errorTxt.setText(errorTxt.getText() + "Sørg for å ha valgt to gyldige og forskjellige valuta");
		}
			
	}
	
	
	public void save() {
		try {
			savedInn = Double.valueOf(NOKInpField.getText());
			savedUt = utValuta;
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
			
			String stringInn = "" + savedInn + " " + nok.getName();
			//errorTxt.setText(stringNOK);
			
			String stringUt = "" + savedUt + " " + usd.getName();
			errorTxt.setText(stringInn + "\n" + stringUt);
		
			
			
		} catch (IOException e){
			e.printStackTrace();
			errorTxt.setText("Filnavnet finnes ikke");
		}
	}
	
	
	
}
