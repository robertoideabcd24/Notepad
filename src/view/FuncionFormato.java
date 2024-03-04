package view;

import java.awt.Font;

import controller.BotonAction;

public class FuncionFormato {
	
	FrmEditor frmeditor;
	Font arial, comicSansMS, timesNewRoman;
	String selectedFont;
	
	
	public FuncionFormato(FrmEditor botonAction) {
		this.frmeditor = botonAction;
	}
	
	public FuncionFormato(BotonAction botonAction) {
		// TODO Auto-generated constructor stub
	}

	public void wordWrap() {
		if(frmeditor.wordWrapOn==false) {
			frmeditor.wordWrapOn=true;
			frmeditor.textArea.setLineWrap(true);
			frmeditor.textArea.setWrapStyleWord(true);
			frmeditor.itmWrap.setText("Ajuste de Línea: On");
			
		}else if(frmeditor.wordWrapOn==true) {
			frmeditor.wordWrapOn=false;
			frmeditor.textArea.setLineWrap(false);
			frmeditor.textArea.setWrapStyleWord(false);
			frmeditor.itmWrap.setText("Ajuste de Línea: Off");
			
		}
	}//fon metodo
	
	public void crearFont(int fontSize) {
		arial = new Font("Arial", Font.PLAIN, fontSize);
		comicSansMS = new Font("Comic Sans MS", Font.PLAIN, fontSize);
		timesNewRoman = new Font("Times New Roman", Font.PLAIN, fontSize);
		
		setFont(selectedFont);
		
	}
	
	public void setFont(String font) {
		selectedFont =font;
		
		switch(selectedFont) {
		case "Arial":
			frmeditor.textArea.setFont(arial);
			break;
		case "Comic Sans MS":
			frmeditor.textArea.setFont(comicSansMS);
			break;
		case "Times New Roman":
			frmeditor.textArea.setFont(timesNewRoman);
			break;					
			
		}		
	}
		
	
	
	
}
