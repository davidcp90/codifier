package co.edu.udistrital.prycodificador.presentacion.modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import co.edu.udistrital.prycodificador.logica.Codificador;
import co.edu.udistrital.prycodificador.logica.Huffman;
import co.edu.udistrital.prycodificador.logica.Palabra;
import co.edu.udistrital.prycodificador.presentacion.controlador.Control;

public class Modelo {
	private Control controlador;
	private Palabra palabra;
	private Huffman huffman;
	private Codificador codifica;
	private JFileChooser archivo = null;
	private String fileName,linea;
	private DefaultListModel modelo = new DefaultListModel();
	public void abrirArchivo(){
		System.out.println("abriendo archivo..");
		if (archivo == null){
			archivo = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt");
			archivo.setFileFilter(filter);
		}
		int retVal = archivo.showOpenDialog(getControlador().getPrincipal());
		System.out.println(retVal);
		if (retVal == 0){
			fileName = archivo.getSelectedFile().getAbsolutePath();
			getControlador().getPrincipal().getBtnComp().setEnabled(true);
			try{
				this.borrar();
				getControlador().getPrincipal().getTxtExam().setText(fileName);
			}
			catch (Exception ioe){
				getControlador().getPrincipal().getTxtAIni().setText(ioe.getMessage());
			}
		}else{
			getControlador().getPrincipal().getBtnSave().setEnabled(false);
			getControlador().getPrincipal().getBtnComp().setEnabled(false);
		}
		if(retVal == 0){
			File f = new File(fileName);
			Scanner s;
			try {
				s = new Scanner(f);
				while (s.hasNextLine()) {
					linea = s.nextLine();
					getControlador().getPrincipal().getTxtAIni().append(linea+"\n");											
				}
				s.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (Exception e){
				e.printStackTrace();
			}
		}		
	}
	public void borrar(){
		getControlador().getPrincipal().getTxtExam().setText("");
		getControlador().getPrincipal().getTxtAIni().setText("");
		getControlador().getPrincipal().getTxtACod().setText("");
		getControlador().getPrincipal().getLblResNum().setText("");
		getControlador().getPrincipal().getLblResProb().setText("");
		modelo.clear();
	}
	public void comprimir(){
		getHuffman().aplicarHuffman(fileName);
		getCodifica().codifica();
	}
	public void anadeLista(){
		for(int x=0; x<Huffman.getMatPalab().size();x++){
			modelo.addElement(Huffman.getMatPalab().get(x).getPalabra()+"  -  "+Huffman.getMatPalab().get(x).getCod());
		}
		getControlador().getPrincipal().getListRes().setModel(modelo);
	}
	public void guardar(){
		String texto = getControlador().getPrincipal().getTxtACod().getText();
		String texto2 = texto;
		System.out.print(texto);
		System.out.println(texto2);
		try { 
			JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
			FileNameExtensionFilter filter2 = new FileNameExtensionFilter("Documentos de texto (*.rtf)",".txt");
			fc.setFileFilter(filter2);
			int seleccion = fc.showSaveDialog(getControlador().getPrincipal());
			if (seleccion == JFileChooser.APPROVE_OPTION){
			   File fichero = fc.getSelectedFile();
			   FileWriter  Guardx = new FileWriter(fichero+".rtf");
			   Guardx.write(texto);
			   Guardx.close(); 		
			   JOptionPane.showMessageDialog(getControlador().getPrincipal(), "Fichero guardado correctamente", "Almacenamiento correcto", JOptionPane.INFORMATION_MESSAGE);
			}
		}catch (Exception e) { 
		    JOptionPane jOP = new JOptionPane(); 
		    jOP.showMessageDialog(null, "Error de escritura, archivo de errores","", 0); 
		} 
	}
	public Modelo(Control control) {
		controlador = control;
	}
	public Control getControlador() {
		return controlador;
	}
	public Palabra getPalabra() {
		if (palabra == null){
			palabra = new Palabra (this);
		}
		return palabra;
	}
	public Huffman getHuffman() {
		if (huffman == null){
			huffman = new Huffman (this);
		}
		return huffman;
	}
	public Codificador getCodifica() {
		if(codifica == null){
			codifica = new Codificador(this);
		}
		return codifica;
	}
}
