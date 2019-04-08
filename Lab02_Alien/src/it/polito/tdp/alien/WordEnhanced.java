package it.polito.tdp.alien;

import java.util.*;

public class WordEnhanced {
	
	private String alienWord;
	private List<String> translations=new LinkedList<String>();
	
	public WordEnhanced(String alienWord, String translation) {
		this.alienWord = alienWord;
		translations.add(translation);
	}
	
	public void addTranslation(String tr) {
		translations.add(tr);
	}

	public String getAlienWord() {
		return alienWord;
	}

	public String getTranslation() {
		String s="";
		for(String t:translations) {
			s+=t+"; ";
		}
		return s;
	}

}
