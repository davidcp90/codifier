package co.edu.udistrital.prycodificador.logica;



import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import co.edu.udistrital.prycodificador.presentacion.modelo.Modelo;

public class Codificador {
	Modelo modelado;
	String replace[];// = {"a","e","i","o","u"};
	
	String texto;
	public void codifica(){		
		Scanner s;
		String linea,word,texto,cod="";	
		int cont=0;
		int tam=Huffman.getMatPalab().size();
		try {
			texto=getModelado().getControlador().getPrincipal().getTxtAIni().getText();
			s = new Scanner(texto);				
			while (s.hasNextLine()) {
				linea = s.nextLine();				
				Scanner sl = new Scanner(linea);						
				while(sl.hasNext()){					
					word=sl.next();
					for (int i = 0; i < tam; i++) {
						if(word.equals(Huffman.getMatPalab().get(i).getPalabra())){
							cod=cod+Huffman.getMatPalab().get(i).getCod();
						}
					}
					if(sl.hasNext())cod+=" ";
				}
				cod=cod+"\n";
			}
			
			getModelado().getControlador().getPrincipal().getTxtACod().setText(cod);
			s.close();						
		} catch (Exception e){
			e.printStackTrace();
		}	
		tam=Huffman.getMatPalab().size();
		replace=new String[tam];
		for (int i = 0; i < tam; i++) {
			replace[i]=Huffman.getMatPalab().get(i).getCod();
		}		
		texto = cod;
		for(int x=0; x<(Huffman.getMatPalab().size());x++){
			if(Huffman.getMatPalab().get(x).getPalabra().equals(" "))texto = texto.replaceAll(Huffman.getMatPalab().get(x).getPalabra(), replace[x]);
		}
		getModelado().getControlador().getPrincipal().getTxtACod().setText(texto);
		
	}
	public Codificador(Modelo modelo) {
		modelado = modelo;
	}
	public Modelo getModelado() {
		return modelado;
	}
	/*public String getReplace(int x) {
		return replace[x];
	}*/
}
