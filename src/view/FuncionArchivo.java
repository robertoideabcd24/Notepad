package view;
import java.io.File;
import java.io.IOException;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileView;

import controller.BotonAction;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class FuncionArchivo {
	FrmEditor frmeditor;
	File archivoActual;
	
	public FuncionArchivo(FrmEditor botonAction) {
		this.frmeditor = botonAction;
	}
	
	public FuncionArchivo(BotonAction botonAction) {
		// TODO Auto-generated constructor stub
	}

	public void archivoNuevo() {
		frmeditor.textArea.setText("");
		frmeditor.setTitle("Nuevo");
	}
	
	public void archivoAbrir() {
				
		FileDialog fileDialog = new FileDialog(new Frame(), "Abrir archivo", FileDialog.LOAD);
        fileDialog.setFile("*.txt");
        fileDialog.setVisible(true);

        String filename = fileDialog.getFile();
        String directory = fileDialog.getDirectory();

        if (filename != null && directory != null) {
            File archivoSeleccionado = new File(directory, filename);
            System.out.println("Ruta del archivo: " + archivoSeleccionado.getAbsolutePath());
            System.out.println("Nombre del archivo: " + archivoSeleccionado.getName());
            try (BufferedReader br = new BufferedReader(new FileReader(archivoSeleccionado))) {
                StringBuilder contenido = new StringBuilder();
                String linea;
				int[] contadorLetras = new int[26];
				int[] contadorSimbolos = new int[180];
				int[] contadorNumeros = new int[10];
				
				while ((linea = br.readLine()) != null) {
					contenido.append(linea).append("\n");
					
					contarLetras(linea, contadorLetras);
					contarSimbolosYNumeros(linea, contadorSimbolos, contadorNumeros);
				}
				
				imprimirConteo("Letras", contadorLetras);
				imprimirConteo("Símbolos", contadorSimbolos);
				imprimirConteo("Números", contadorNumeros);
				
				frmeditor.textArea.setText(contenido.toString());
				frmeditor.setTitle("Notepad UACM: " + archivoSeleccionado.getName());
				
			} catch (IOException e) {
				System.out.println("El archivo no abre");
				e.printStackTrace();
			}
		}
	}//termina el metodo
	
	public void guardarArchivo() {
		if (archivoActual != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoActual))) {
                writer.write(frmeditor.textArea.getText());
            } catch (IOException e) {
                System.out.println("Error al guardar el archivo");
                e.printStackTrace();
            }
        } else {
            guardarArchivoComo(); // Si no hay un archivo actual, llamar a guardarArchivoComo
        }
	}
	
	public void guardarArchivoComo() {	
		FileDialog fileDialog = new FileDialog(new Frame(), "Guardar archivo", FileDialog.SAVE);
        fileDialog.setFile("*.txt");
        fileDialog.setVisible(true);

        String filename = fileDialog.getFile();
        String directory = fileDialog.getDirectory();

        if (filename != null && directory != null) {
            File archivo = new File(directory, filename);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
                writer.write(frmeditor.textArea.getText());
                archivoActual = archivo; // Actualiza el archivo actualmente abierto
            } catch (IOException e) {
                System.out.println("Error al guardar el archivo");
                e.printStackTrace();
            }
        }
		
	}//fin metodo
	
	private void contarLetras(String texto, int[] contadorLetras) {
		for (char c : texto.toCharArray()) {
			if (Character.isLetter(c)) {
				char letra = Character.toLowerCase(c);
				int indice = letra - 'a';
				if (indice >= 0 && indice < 26) {
					contadorLetras[indice]++;
				}
				
			}
		}
	}//fin del metodo
	
	private void contarSimbolosYNumeros(String texto, int[] contadorSimbolos, int[] contadorNumeros) {
		
		for (char c : texto.toCharArray()) {
			if (Character.isDigit(c)) {
				int digito = c - '0';
				contadorNumeros[digito]++;
			}else if (!Character.isLetter(c) && !Character.isWhitespace(c)) {
				int ascii = c & 0xFFFF; 
				if (ascii >= 0 && ascii < contadorSimbolos.length) {
					contadorSimbolos[ascii]++;
				}  else {
					System.out.println("Símbolo fuera de rango: " + c);
				}
			}
		}
	}//fin metodo
		
	private void imprimirConteo(String tipo, int[] contador) {
		
		for (int i = 0; i < contador.length; i++) {
			if (contador[i] > 0) {
				if (tipo.equals("Letras")) {
					char letra = (char) ('a' + i);
					System.out.println(letra + ": " + contador[i]);
				} else if (tipo.equals("Símbolos")) {
					System.out.println((char) i + ": " + contador[i]);
				} else if (tipo.equals("Números")) {
					System.out.println(i + ": " + contador[i]);
				}
				
			}
		}
	}//termina metodo
	
	
	public void imprimirArchivo() {
		try {
			frmeditor.textArea.print();
		}catch (PrinterException e1) {
			
			e1.printStackTrace();
		}
	}
	
	
	
}
