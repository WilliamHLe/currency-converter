package valutaKalk.fxui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import valutaKalk.core.AppIO;
import valutaKalk.core.Valuta;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class ValutakalkulatorController {

	public Button saveBtn;
	public Button loadBtn;
	public Button button;
	@FXML private TextField NOKInpField, dollarInpField;
	@FXML private ComboBox<String> combOld, combNew;
	@FXML private Label errorTxt;

	private AppIO io = new AppIO();

        private double utValuta;

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
			double innValuta = Double.parseDouble(NOKInpField.getText());
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
			errorTxt.setText(errorTxt.getText() + "Velg to gyldige og forskjellige valuta");
		}
	}

	//Funksjon som lar deg bytte mellom de to valgte valutaene
	public void change() {
	    if(combOld.getValue() == null || combNew.getValue() == null) { //Hvis man ikke har valgt to valutaer
            errorTxt.setText("Velg to valutaer.");
        } else {
	    	io.change(combOld.getValue(), combNew.getValue());
            combOld.setValue(io.old);
            combNew.setValue(io.ny);
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
