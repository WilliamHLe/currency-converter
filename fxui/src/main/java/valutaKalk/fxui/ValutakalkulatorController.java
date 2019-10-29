package valutaKalk.fxui;

import java.io.File;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import valutaKalk.core.Valuta;
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

		public Button saveBtn;
		public Button loadBtn;
		public Button button;
        double utValuta;
		JSONObject obj = new JSONObject();

	@FXML
	public void initialize() {
		try {
			//Henter liste over valutaer, og legger de til drop-down menyen
			File f = new File(getClass().getResource("valutalist.json").getFile());
			Object hm = new JSONParser().parse(new FileReader(f));
			JSONObject json = (JSONObject) hm;
			ObservableList list = FXCollections.observableArrayList(json.keySet());
			combOld.setItems(list);
			combNew.setItems(list);
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
			double innValuta = Double.valueOf(NOKInpField.getText());
            utValuta = Valuta.calc(combOld.getValue(),combNew.getValue(),innValuta);
			if(innValuta <= 0 || Valuta.error == 1){
				Valuta.error = 1;
				errorTxt.setText(errorTxt.getText() + "Vennligst velg to gyldige og forskjellige valuta");
			} else {
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
			utValuta = Valuta.calc(combOld.getValue(),combNew.getValue(),innValuta);
			if(Valuta.error == 1){
				//Dersom brukeren prøver å lagre en ugyldig konvertering
				errorTxt.setText("Noe gikk galt ved skriving til fil");
			}
			else {
				//Verdiene i de forskjellige input-enhetene bestemmes og sendes videre til lagring
				double savedInn = Double.parseDouble(NOKInpField.getText());
				double savedUt = utValuta;
				io.saveJSON(combOld.getValue(),combNew.getValue(),savedInn,savedUt);
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
