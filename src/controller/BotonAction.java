package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import javax.swing.JFrame;

import view.FuncionArchivo;
import view.FuncionColor;
import view.FuncionFormato;

public class BotonAction extends AbstractAction{
	
	FuncionArchivo archivo = new FuncionArchivo(this);
	FuncionFormato formato = new FuncionFormato(this);
	FuncionColor color = new FuncionColor(this);	
	JFrame window;
	
	public BotonAction(String strComand) {
		this.putValue( MNEMONIC_KEY, strComand);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command = e.getActionCommand();
		switch(command) {
			case "Nuevo": archivo.archivoNuevo();break;
			case "Abrir": archivo.archivoAbrir();break;
			case "Guardar": archivo.guardarArchivo();break;
			case "Guardar Como": archivo.guardarArchivoComo();break;
			//case "Exit": archivo.exit(); break;
			case "Word Wrap": formato.wordWrap(); break;
			case "Arial": formato.setFont(command); break;
			case "Comic Sans MS": formato.setFont(command); break;
			case "Times New Roman": formato.setFont(command); break;
			
			case "size8": formato.crearFont(8); break;
			case "size12": formato.crearFont(12); break;
			case "size16": formato.crearFont(16); break;
			case "size20": formato.crearFont(20); break;
			case "size24": formato.crearFont(24); break;
			case "size28": formato.crearFont(28); break;
			
			//color
			case "Azul": color.cambiarColorLetra(Color.BLUE); break;
			case "Negro": color.cambiarColorLetra(Color.BLACK); break;
			
			case "Imprimir": archivo.imprimirArchivo(); break;		
		
		}
	}
}
