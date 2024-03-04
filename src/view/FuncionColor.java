package view;
import java.awt.Color;

import controller.BotonAction;

public class FuncionColor {
	FrmEditor frmeditor;
	
	
	public FuncionColor(FrmEditor botonAction) {
		this.frmeditor = botonAction;
	}
	
	public FuncionColor(BotonAction botonAction) {
		// TODO Auto-generated constructor stub
	}

	public void cambiarColorLetra(Color color) {
		frmeditor.textArea.setForeground(color);
	}
		
}
