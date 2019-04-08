package it.polito.tdp.alien;

/**
 * Sample Skeleton for 'Alien.fxml' Controller Class
 */



import java.net.URL;
import java.util.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AlienController {
	
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private TextField txtWord;
    @FXML
    private TextArea txtResult;
    @FXML
    private Button btnTranslate;
    @FXML
    private Button btnReset;
    
    private Map<String, WordEnhanced> word=new TreeMap<String, WordEnhanced>();
        
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	assert txtWord != null : "fx:id=\"txtWord\" was not injected: check your FXML file 'Alien.fxml'.";
    	assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Alien.fxml'.";
    	assert btnTranslate != null : "fx:id=\"bntTranslate\" was not injected: check your FXML file 'Alien.fxml'.";
    	assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Alien.fxml'.";
    }
  
    
    @FXML
    void doTranslate(ActionEvent event) {
		String ts[] = txtWord.getText().split(" ");
		String aliena=ts[0].toLowerCase().trim();
		String WildCard=null;
		boolean tentativo=aliena.matches("[a-zA-Z]*");
		boolean tentativo1=aliena.matches("[a-zA-Z?]*");
		txtWord.clear();
		
		if(!tentativo1) {
			txtResult.appendText("Il formato della parola aliena non è valido\n");				
			return ;
		}
		
		//parola non normale
		if(!tentativo) {
			WildCard=aliena.replaceAll("\\?", ".");
			txtResult.appendText(WildCard);
			
			/*if(word.containsKey(WildCard))
				txtResult.appendText("ok");
			for(WordEnhanced t:word.values()) {
				if(t.equals(WildCard))
					txtResult.appendText("ok1");
			}*/
			
			
		}
		txtWord.clear();
		
		//se devo aggiungere al dizionario
		if(ts.length==2) {
			String traduzione=ts[1].toLowerCase().trim();
			boolean tentativo2=traduzione.matches("[a-zA-Z]*");
			if(!tentativo2) {
				txtResult.appendText("Il formato della parola tradotta non è valido\n");
				return ;
			}
			
			WordEnhanced e=new WordEnhanced(aliena, traduzione);
			if(word.containsKey(aliena)) {
				for(WordEnhanced t:word.values()) {
					if(t.getAlienWord().equals(aliena))
						t.addTranslation(traduzione);
				}
				txtResult.appendText("La parola è stata inserita correttamente nel dizionario\n");
			}
			else {
				word.put(aliena, e);
				txtResult.appendText("La parola è stata inserita correttamente nel dizionario\n");
			}
		}
		
		//se devo tradurla
		else if(ts.length==1) {
			if(word.containsKey(aliena)) {
			for(WordEnhanced w:word.values()) {
				if(w.getAlienWord().equals(aliena))
					txtResult.appendText(String.format("La traduzione della parola %s è %s\n", aliena, w.getTranslation()));
				}
			}
			else
				txtResult.appendText("La parola aliena inserita non è presente nel dizionario\n");
		}
    }
    
    
    @FXML
    void doReset(ActionEvent event) {
    	txtResult.clear();
    }
    
}
