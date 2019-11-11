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
	public Button change;
    public AnchorPane showCurrency;
	public Label euroLabel;
	public ImageView euroPicture;
	public ImageView dollarPicture;
	public ImageView pundPicture;
	public Label dollarLabel;
	public Label pundLabel;
	public ImageView dkPicture;
	public Label dkLabel;
	public ImageView sekPicture;
	public Label sekLabel;
	public ImageView zlotyPicture;
	public Label zlotyLabel;
	public Label showCurrencyHead;
	@FXML private TextField NOKInpField, dollarInpField;
	@FXML private ComboBox<String> combOld, combNew;
	@FXML private Label errorTxt;

        double utValuta;
		JSONObject obj = new JSONObject();
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
			errorTxt.setText(errorTxt.getText() + "Vennligst velg to gyldige og forskjellige valuta");
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
			obj = JSON.ValtutaJSON(combOld.getValue(),combNew.getValue(),savedInn,savedUt); //Setter til JSON objekt
			ValutaService.put(obj);//Gjør en HTTP PUT forespørsel
		}
	}



	public void load() throws Exception{
		//Hentingen av data fra JSON-filen og viser dette i UI-et
		JSONArray info = ValutaService.load();
		StringBuilder stringInn = new StringBuilder();
		StringBuilder stringUt = new StringBuilder();
		int j = 1;
		int len = info.size()-1;
		//Går gjennom JSON arrayet og finner de fire nyeste utregningene
		for(int i = len;i>=0;i--) {
			if (j >= 5) { //Hvis j er høyere eller lik 5, stopp loopen
				break;
			}
			//Lager strings med innholdet og prøver å formatere slik at hvert objekt havner på samme linje
			JSONObject o = (JSONObject)info.get(i);
			String tempInn = o.get("valuta1") + " " + o.get("valuta1amount")+", \t";
			String tempUt = o.get("valuta2") + " " + o.get("valuta2amount")+", \t";
			if (tempInn.length()<11) {
				tempInn += "\t";
			}
			if (tempUt.length()<11){
				tempUt += "\t";
			}
			stringInn.append(tempInn);
			stringUt.append(tempUt);
			j++;
		}
		errorTxt.setText(stringInn + "\n" + stringUt);
	}

	//Sletter fra json arrayet
	public void delete() {
		ValutaService.delete();
	}

}
