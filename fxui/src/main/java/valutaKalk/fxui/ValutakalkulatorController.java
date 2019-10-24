package valutaKalk.fxui;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import valutaKalk.core.Valuta;
import valutaKalk.core.AppIO;
import org.json.simple.JSONObject;


public class ValutakalkulatorController {

	@FXML private TextField NOKInpField, dollarInpField;
	@FXML private ComboBox<Valuta> combOld, combNew;
	@FXML private Label errorTxt;

	private AppIO io = new AppIO();


	private Valuta NOK = new Valuta("NOK");
	private Valuta USD = new Valuta("USD");
	private Valuta EURO = new Valuta("EURO");
	private double utValuta;

	private ObservableList<Valuta> list //
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
            utValuta = Valuta.calc(combOld.getValue().toString(),combNew.getValue().toString(),innValuta);
            dollarInpField.setText("" + utValuta);
		}
		catch(Exception e){
			errorTxt.setText(errorTxt.getText() + "Sørg for å ha valgt to gyldige og forskjellige valuta");
		}

	}

	public void save() {
		try {
			double savedInn = Double.parseDouble(NOKInpField.getText());
			double savedUt = utValuta;
			JSONObject obj = new JSONObject();
			if(combOld.getValue().equals(NOK) ) {

				if(combNew.getValue().equals(USD)) {
					obj = io.saveJSON(NOK.getName(),USD.getName(), savedInn, savedUt);
				}
				if(combNew.getValue().equals(EURO)) {
					obj = io.saveJSON(NOK.getName(),EURO.getName(), savedInn, savedUt);
				}
			}
			else if(combOld.getValue().equals(USD) ) {

				if(combNew.getValue().equals(NOK)) {
					obj = io.saveJSON(USD.getName(),NOK.getName(), savedInn, savedUt);
				}
				if(combNew.getValue().equals(EURO)) {
					obj = io.saveJSON(USD.getName(),EURO.getName(), savedInn, savedUt);
				}
			}
			else if(combOld.getValue().equals(EURO) ) {

				if(combNew.getValue().equals(NOK)) {
					obj = io.saveJSON(EURO.getName(),NOK.getName(), savedInn, savedUt);
				}
				if(combNew.getValue().equals(USD)) {
					obj = io.saveJSON(EURO.getName(),USD.getName(), savedInn, savedUt);
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
			errorTxt.setText("Noe gikk galt ved skriving til fil");
		}
	}



	public void load() throws Exception{
		try {
			io.loadJSON();
			String stringInn = io.valuta1amount + " " + io.valuta1;

			String stringUt = io.valuta2amount + " " + io.valuta2;
			errorTxt.setText(stringInn + "\n" + stringUt);
		
			
			
		} catch (IOException e){
			e.printStackTrace();
			errorTxt.setText("Filnavnet finnes ikke");
		}
	}
	
	
	
}
