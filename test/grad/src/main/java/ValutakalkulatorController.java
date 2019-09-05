package app;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class ValutakalkulatorController {
	
	@FXML private TextField NOKInpField, dollarInpField;
	@FXML private TextArea NOKOutpArea, dollarOutpArea;
	
	private ValutaNOK ValutaNOK;
	private ValutaUSD ValutaUSD;
	@FXML
	private String button;
	private AppIO io = new AppIO();
	
	
	@FXML
	public void initialize() {
		ValutaNOK = new ValutaNOK();
		ValutaUSD = new ValutaUSD();
		
	}
	
	@FXML
	public void calculate() {
		try {

			double innNOK = Double.valueOf(NOKInpField.getText());
			
			double utDollar = Math.round(ValutaNOK.calculateNOKToDollar(innNOK));
			double utEuro = Math.round(ValutaNOK.calculateNOKToEuro(innNOK));
			
			String result = "Det blir " + utDollar + " dollar. \n\n"
					+ "Det blir " + utEuro + " euro. ";
			
			 NOKOutpArea.setText(result);
			 this.ValutaNOK.setNOK(innNOK);
		}
		catch(Exception e){
			
		}
		
		try {
			double innDollar = Double.valueOf(dollarInpField.getText());
			
			double utNOK = Math.round(ValutaUSD.calculateDollarToNOK(innDollar));
			double utEuro = Math.round(ValutaUSD.calculateDollarToEuro(innDollar));
			
			String result = "Det blir " + utNOK + " NOK. \n\n"
					+ "Det blir " + utEuro + " euro. ";
			
			 dollarOutpArea.setText(result);
			 this.ValutaUSD.setUSD(innDollar);
		}
		catch(Exception e){
			System.out.println("Feilmelding: " + e);
			
		}
			
	}
	
	
	public void save() {
		try {
			System.out.println(ValutaNOK.getNOK());
			io.save("valuta.txt", ValutaNOK, ValutaUSD);
		} catch (IOException e) {
			e.printStackTrace();
			NOKOutpArea.setText("Noe gikk galt ved skriving til fil");
			dollarOutpArea.setText("Noe gikk galt ved skriving til fil");
		}
	}
	
	
	
	public void load() {
		try {
			ValutaObjectLoader loader = io.load("valuta.txt");
			
			ValutaUSD usd = loader.usd;
			ValutaNOK nok = loader.nok;
			
			String stringNOK = "" + nok.getNOK() + "\n"+ nok.calculateNOKToDollar(nok.getNOK()) + "\n" + nok.calculateNOKToEuro(nok.getNOK());
			NOKOutpArea.setText(stringNOK);
			
			String stringUSD = "" + usd.getUSD() + "\n" + usd.calculateDollarToNOK(usd.getUSD()) + "\n" + usd.calculateDollarToEuro(usd.getUSD());
			dollarOutpArea.setText(stringUSD);
		
			
			
		} catch (IOException e){
			e.printStackTrace();
			NOKOutpArea.setText("Filnavnet finnes ikke");
		}
	}
	
	
	
}
