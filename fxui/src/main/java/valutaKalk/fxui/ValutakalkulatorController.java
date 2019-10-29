package valutaKalk.fxui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import valutaKalk.core.AppIO;
import valutaKalk.core.ValutaObjectLoader;
import valutaKalk.core.JSON;
import org.json.simple.parser.*;
import org.json.simple.JSONArray;
import java.io.PrintWriter;
import org.json.simple.JSONObject;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;


public class ValutakalkulatorController {

	public Button saveBtn;
	public Button loadBtn;
	public Button button;
	@FXML private TextField NOKInpField, dollarInpField;
	@FXML private ComboBox<String> combOld, combNew;
	@FXML private Label errorTxt;

	private AppIO io = new AppIO();


        Valuta NOK = new Valuta("NOK");
        Valuta USD = new Valuta("USD");
        Valuta EURO = new Valuta("EURO");
        double utValuta;
        double innValuta;
        double savedInn;
        double savedUt;
		JSONObject obj = new JSONObject();

        ObservableList<String> list; //
		ObservableList<String> list2; //
                //= FXCollections.observableArrayList(NOK, USD, EURO);

	@FXML
	public void initialize() {
		try {
			Object hm = new JSONParser().parse(new FileReader("valutaer.json"));
			JSONObject json = (JSONObject) hm;
			ObservableList liste = FXCollections.observableArrayList(json.keySet());
			combOld.setItems(liste);
			combNew.setItems(liste);
			dollarInpField.setText("0");
		} catch (Exception e) {
			errorTxt.setText("Noe feil skjedde.");
		}
	}

	@FXML
	public void calculate() {
		//Sørger for at reglene opprettholdes og at UI-et viser kalkulasjonene til Valuta.calc()
		errorTxt.setText("");
		try {
			double innValuta = Double.parseDouble(NOKInpField.getText());
			utValuta = Valuta.calc(combOld.getValue().toString(),combNew.getValue().toString(),innValuta);
			if(innValuta <= 0 || Valuta.error == 1){
				Valuta.error = 1;
				errorTxt.setText(errorTxt.getText() + "Vennligst velg to gyldige og forskjellige valuta");
			}
			else{
				Valuta.error = 0;
				errorTxt.setText("");
				dollarInpField.setText("" + utValuta);
			}


		}
		catch(Exception e){
			errorTxt.setText(errorTxt.getText() + "Vennligst velg to gyldige og forskjellige valuta");
		}

	}

	//Funksjon som lar deg bytte mellom de to valgte valutaene
	public void change() {
	    if(combOld.getValue() == null || combNew.getValue() == null) { //Hvis man ikke har valgt to valutaer
            errorTxt.setText("Velg to valutaer.");
        } else {
	        String Old = combOld.getValue();
            combOld.setValue(combNew.getValue());
            combNew.setValue(Old);
            calculate();
        }
    }


	public void save() {
		try {
			double innValuta = Double.parseDouble(NOKInpField.getText());
			utValuta = Valuta.calc(combOld.getValue().toString(),combNew.getValue().toString(),innValuta);
			if(Valuta.error == 1){
				//Dersom brukeren prøver å lagre en ugyldig konvertering
				errorTxt.setText("Noe gikk galt ved skriving til fil");
			}
			else {
				//Verdiene i de forskjellige input-enhetene bestemmes og sendes videre til lagring
				double savedInn = Double.parseDouble(NOKInpField.getText());
				double savedUt = utValuta;
				if(combOld.getValue().equals(NOK) ) {

					if(combNew.getValue().equals(USD)) {
						io.saveJSON(NOK.getName(),USD.getName(), savedInn, savedUt);
					}
					if(combNew.getValue().equals(EURO)) {
						io.saveJSON(NOK.getName(),EURO.getName(), savedInn, savedUt);
					}
				}
				else if(combOld.getValue().equals(USD) ) {

					if(combNew.getValue().equals(NOK)) {
						io.saveJSON(USD.getName(),NOK.getName(), savedInn, savedUt);
					}
					if(combNew.getValue().equals(EURO)) {
						io.saveJSON(USD.getName(),EURO.getName(), savedInn, savedUt);
					}
				}
				else if(combOld.getValue().equals(EURO) ) {

					if(combNew.getValue().equals(NOK)) {
						io.saveJSON(EURO.getName(),NOK.getName(), savedInn, savedUt);
					}
					if(combNew.getValue().equals(USD)) {
						io.saveJSON(EURO.getName(),USD.getName(), savedInn, savedUt);
					}

				}
			}

		} catch (IOException e) {
			e.printStackTrace();
			errorTxt.setText("Noe gikk galt ved skriving til fil");
		}
	}



	public void load() throws Exception{
		//Hentingen av data fra JSON-filen og viser dette i UI-et
		try {
			io.loadJSON();
			String stringInn = AppIO.valuta1amount + " " + AppIO.valuta1;

			String stringUt = AppIO.valuta2amount + " " + AppIO.valuta2;
			errorTxt.setText(stringInn + "\n" + stringUt);
		
			
			
		} catch (IOException e){
			e.printStackTrace();
			errorTxt.setText("Filnavnet finnes ikke");
		}
	}
	
	
	
}
