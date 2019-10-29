package valutaKalk.fxui;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
			savedInn = Double.valueOf(NOKInpField.getText());
			savedUt = utValuta;
			if(combOld.getValue().equals(NOK) ) {

				if(combNew.getValue().equals(USD)) {
					io.save("valuta.txt", NOK, USD, NOK.getName(), USD.getName());
					obj = JSON.ValtutaJSON(NOK.getName(),USD.getName(),savedInn,savedUt);
				}
				if(combNew.getValue().equals(EURO)) {
					io.save("valuta.txt", NOK, EURO, NOK.getName(), EURO.getName());
					obj = JSON.ValtutaJSON(NOK.getName(),EURO.getName(),savedInn,savedUt);
				}
			}
			else if(combOld.getValue().equals(USD) ) {

				if(combNew.getValue().equals(NOK)) {
					io.save("valuta.txt", USD, NOK, USD.getName(), NOK.getName());
					obj = JSON.ValtutaJSON(USD.getName(),NOK.getName(),savedInn,savedUt);
				}
				if(combNew.getValue().equals(EURO)) {
					io.save("valuta.txt", USD, EURO, USD.getName(), EURO.getName());
					obj = JSON.ValtutaJSON(USD.getName(),EURO.getName(),savedInn,savedUt);
				}
			}
			else if(combOld.getValue().equals(EURO) ) {

				if(combNew.getValue().equals(NOK)) {
					io.save("valuta.txt", EURO, NOK, EURO.getName(), NOK.getName());
					obj = JSON.ValtutaJSON(EURO.getName(),NOK.getName(),savedInn,savedUt);
				}
				if(combNew.getValue().equals(USD)) {
					io.save("valuta.txt", EURO, USD, EURO.getName(), USD.getName());
					obj = JSON.ValtutaJSON(EURO.getName(),USD.getName(),savedInn,savedUt);
				}

			}
			/*PrintWriter pw = new PrintWriter("valuta.json");
			pw.write(obj.toJSONString());
			pw.flush();
			pw.close();*/
			//io.save("valuta.txt", , );
		} catch (IOException e) {
			e.printStackTrace();
			errorTxt.setText("Noe gikk galt ved skriving til fil");
		}
	}



	public void load() throws Exception{
		try {
			ValutaObjectLoader loader = io.load("valuta.txt");
			Object obj = new JSONParser().parse(new FileReader("valuta.json"));
			JSONObject info = (JSONObject) obj;
			Valuta ny = loader.ny;
			Valuta gammel = loader.gammel;

			String stringInn = "" + savedInn + " " + gammel.getName();
			String stringInn2 = info.get("valuta1") + " " + info.get("valuta1amount");
			//errorTxt.setText(stringNOK);

			String stringUt = "" + savedUt + " " + ny.getName();
			String stringUt2 = info.get("valuta2") + " " + info.get("valuta2amount");
			errorTxt.setText(stringInn2 + "\n" + stringUt2);
		
			
			
		} catch (IOException e){
			e.printStackTrace();
			errorTxt.setText("Filnavnet finnes ikke");
		}
	}
	
	
	
}
