package view;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.BotonAction;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.Toolkit;
import java.awt.Button;
import javax.swing.ImageIcon;

public class FrmEditor extends JFrame implements ActionListener{

	private JPanel contentPane;
	JTextArea textArea;
	private JMenuBar menuBar;
	private JMenu menuArchivo, menuFormato, menuAyuda;
	private JMenuItem itmNuevo, itmAbrir, itmGuardar, itmGuardarComo, itmImprimir, itmSalir;
	private JMenuItem itmEspacioAyuda, itmAcerca; 
		
	private JMenu menuColor;
	private JMenuItem mntmNewMenuItem;
	
	boolean wordWrapOn =false;
	
	JMenuItem itmWrap, itmFontArial, itmFontCSMS, itmFontTNR, itmFontSize8, itmFontSize12, itmFontSize16, itmFontSize20;
	JMenuItem itmFontSize24, itmFontSize28, itmColorTextoAzul, itmColorTextoNegro;
	JMenu menuFont, menuFontSize;
		
	FuncionArchivo archivo = new FuncionArchivo(this);
	FuncionFormato formato = new FuncionFormato(this);
	FuncionColor color = new FuncionColor(this);
	
	JFrame window;
	private JMenu mnNewMenu;
	private JPanel panel;
	private JToolBar toolBar;
	private JButton btnNuevo;
	private JButton btnAbrir;
	private JButton btnGuardar;
	private JButton btnImprimir;
	
	
			
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmEditor frame = new FrmEditor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmEditor() {
		setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmEditor.class.getResource("/imagen/chi--3-white.png")));
		
		crearJFrame();
		crearMenuBar();	
		crearTextArea();				
		crearArchivoMenu();
		crearAyudaMenu();
		crearFormatoMenu();
		crearColor();
		
		formato.selectedFont = "Arial";
		formato.crearFont(16);
		formato.wordWrap();
		
	}
	
	public void crearJFrame() {
		setTitle("Notepad UACM");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
	}
	
	public void crearTextArea() {
		
		textArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(textArea);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		panel = new JPanel();
		panel.setBackground(new Color(48, 48, 48));
		panel.setForeground(new Color(48, 48, 48));
		contentPane.add(panel, BorderLayout.NORTH);
		
		toolBar = new JToolBar();
		toolBar.setBackground(new Color(48, 48, 48));
		panel.add(toolBar);
		
		btnNuevo = new JButton("");		
		btnNuevo.addActionListener(new BotonAction("Nuevo"));
		btnNuevo.setBackground(new Color(48, 48, 48));
		btnNuevo.setIcon(new ImageIcon(FrmEditor.class.getResource("/imagen/add_W.png")));
		toolBar.add(btnNuevo);
		
		btnAbrir = new JButton("");
		btnAbrir.addActionListener(new BotonAction("Abrir"));
		btnAbrir.setBackground(new Color(48, 48, 48));
		btnAbrir.setIcon(new ImageIcon(FrmEditor.class.getResource("/imagen/open_W.png")));
		toolBar.add(btnAbrir);
		
		btnGuardar = new JButton("");
		btnGuardar.addActionListener(new BotonAction("Guardar"));
		btnGuardar.setBackground(new Color(48, 48, 48));
		btnGuardar.setIcon(new ImageIcon(FrmEditor.class.getResource("/imagen/save_w.png")));
		toolBar.add(btnGuardar);
		
		btnImprimir = new JButton("");
		btnImprimir.addActionListener(new BotonAction("Imprimir"));
		btnImprimir.setBackground(new Color(48, 48, 48));
		btnImprimir.setIcon(new ImageIcon(FrmEditor.class.getResource("/imagen/print_w.png")));
		toolBar.add(btnImprimir);
		
	}
		
	public void crearMenuBar() {
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		menuArchivo = new JMenu("Archivo");
		menuArchivo.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		menuBar.add(menuArchivo);
				
		menuFormato = new JMenu("Formato");
		menuFormato.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		menuBar.add(menuFormato);
		
		menuColor = new JMenu("Color");
		menuColor.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		menuBar.add(menuColor);
				
		menuAyuda = new JMenu("Ayuda");
		menuAyuda.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		menuBar.add(menuAyuda);
		
		
		
	}
	
	public void crearArchivoMenu() {
		itmNuevo = new JMenuItem("Nuevo");
		itmNuevo.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		itmNuevo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
		
		itmNuevo.addActionListener(this);
		itmNuevo.setActionCommand("Nuevo");
		menuArchivo.add(itmNuevo);				
		
		itmAbrir = new JMenuItem("Abrir");
		itmAbrir.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		itmAbrir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
		
		itmAbrir.addActionListener(this);
		itmAbrir.setActionCommand("Abrir");
		menuArchivo.add(itmAbrir);
		
		itmGuardar = new JMenuItem("Guardar");
		itmGuardar.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		itmGuardar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_DOWN_MASK));
		
		itmGuardar.addActionListener(this);
		itmGuardar.setActionCommand("Guardar");
		menuArchivo.add(itmGuardar);
		
		itmGuardarComo = new JMenuItem("Guardar como...");
		itmGuardarComo.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		
		itmGuardarComo.addActionListener(this);
		itmGuardarComo.setActionCommand("Guardar como...");
		menuArchivo.add(itmGuardarComo);
		
		JSeparator separator_1 = new JSeparator();
		menuArchivo.add(separator_1);
		
		itmImprimir = new JMenuItem("Imprimir");
	    itmImprimir.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
	    itmImprimir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK));
	    itmImprimir.addActionListener(this);
	    itmImprimir.setActionCommand("Imprimir"); 
	    menuArchivo.add(itmImprimir);
		
		JSeparator separator = new JSeparator();
		menuArchivo.add(separator);
		
		itmSalir = new JMenuItem("Salir");
		itmSalir.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		
		itmSalir.addActionListener(this);
		itmSalir.setActionCommand("Salir");
		menuArchivo.add(itmSalir);
	}
	
	public void crearAyudaMenu() {
		itmEspacioAyuda = new JMenuItem("Espacio de ayuda");
		itmEspacioAyuda.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		itmEspacioAyuda.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		menuAyuda.add(itmEspacioAyuda);
		
		JSeparator separator_2 = new JSeparator();
		menuAyuda.add(separator_2);
		
		itmAcerca = new JMenuItem("Acerca NotePad UACM");
		itmAcerca.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		menuAyuda.add(itmAcerca);
	}

	public void crearFormatoMenu() {
		itmWrap = new JMenuItem("Ajuste de Línea: off");
		itmWrap.addActionListener(this);
		itmWrap.setActionCommand("Word Wrap");
		menuFormato.add(itmWrap);
		
		menuFont = new JMenu("Font");
		menuFormato.add(menuFont);
		
		menuFontSize = new JMenu("Font Size");
		menuFormato.add(menuFontSize);
		
		itmFontArial = new JMenuItem("Arial");
		itmFontArial.addActionListener(this);
		itmFontArial.setActionCommand("Arial");
		menuFont.add(itmFontArial);
	
		
		itmFontCSMS = new JMenuItem("Comic Sans MS");
		itmFontCSMS.addActionListener(this);
		itmFontCSMS.setActionCommand("Comic Sans MS");
		menuFont.add(itmFontCSMS);
		
		
		itmFontTNR = new JMenuItem("Times New Roman");
		itmFontTNR.addActionListener(this);
		itmFontTNR.setActionCommand("Times New Roman");
		menuFont.add(itmFontTNR);
				
		itmFontSize8 = new JMenuItem("8");
		itmFontSize8.addActionListener(this);
		itmFontSize8.setActionCommand("size8");
		menuFontSize.add(itmFontSize8);
		
		itmFontSize12 = new JMenuItem("12");
		itmFontSize12.addActionListener(this);
		itmFontSize12.setActionCommand("size12");
		menuFontSize.add(itmFontSize12);
		
		itmFontSize16 = new JMenuItem("16");
		itmFontSize16.addActionListener(this);
		itmFontSize16.setActionCommand("size16");
		menuFontSize.add(itmFontSize16);
		
		itmFontSize20 = new JMenuItem("20");
		itmFontSize20.addActionListener(this);
		itmFontSize20.setActionCommand("size20");
		menuFontSize.add(itmFontSize20);
		
		itmFontSize24 = new JMenuItem("24");
		itmFontSize24.addActionListener(this);
		itmFontSize24.setActionCommand("size24");
		menuFontSize.add(itmFontSize24);

		itmFontSize28 = new JMenuItem("28");
		itmFontSize28.addActionListener(this);
		itmFontSize28.setActionCommand("size28");
		menuFontSize.add(itmFontSize28);
		
	}
	
	public void crearColor() {

	    mnNewMenu = new JMenu("Texto");
	    mnNewMenu.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
	    menuColor.add(mnNewMenu);

	    itmColorTextoAzul = new JMenuItem("Azul");
	    itmColorTextoAzul.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
	    itmColorTextoAzul.addActionListener(this);
	    itmColorTextoAzul.setActionCommand("Azul");
	    mnNewMenu.add(itmColorTextoAzul);

	    itmColorTextoNegro = new JMenuItem("Negro");
	    itmColorTextoNegro.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
	    itmColorTextoNegro.addActionListener(this);
	    itmColorTextoNegro.setActionCommand("Negro");
	    mnNewMenu.add(itmColorTextoNegro);
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
		case "Arial": formato.setFont(command);
		case "Comic Sans MS": formato.setFont(command);
		case "Times New Roman": formato.setFont(command);
		
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
