package co.edu.udistrital.prycodificador.logica;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import co.edu.udistrital.prycodificador.presentacion.modelo.Modelo;

public class Huffman {
	private Modelo modelado;
	
	private static ArrayList<Palabra> matPalab;
	private ArrayList<Palabra> aux;	
	private ArrayList<ArrayList<Palabra>> divisiones;
	String espacio=" ";		
	private static int cantPal=0;	
	
	public void aplicarHuffman(String dir){
		contarAparicion(dir);
		creaDivisiones();
		algoritmoHuffman();
	}
	public void contarAparicion(String dir){
		File f = new File(dir);
		Scanner s;
		String linea,word;
		matPalab = new ArrayList<Palabra>();
		String espacio=" ";		
		int pal=0,espacios=0;
		boolean find=false;
		try {
			s = new Scanner(f);						
			while (s.hasNextLine()) {
				linea = s.nextLine();				
				Scanner sl = new Scanner(linea);
				pal=0;				
				while(sl.hasNext()){					
					word=sl.next();
					pal++;					
					for(int i=0;i<matPalab.size();i++){
						if(word.equals(matPalab.get(i).getPalabra())){							
							matPalab.get(i).setAparicion(matPalab.get(i).getAparicion()+1);
							i=matPalab.size();
							find=true;
						}						
					}
					if(!find)matPalab.add(new Palabra(word,1,"",-1));					
					find=false;
					System.out.println(word);					
				}
				if(pal!=0){	
					espacios+=pal-1;
					cantPal+=(pal*2)-1;				
				}
			}	
			matPalab.add(new Palabra(espacio,espacios,"",-1));
			System.out.println(cantPal);
			s.close();
			quicksort(matPalab,0, matPalab.size()-1);
			for(int i=0;i<matPalab.size();i++){					
				System.out.println(matPalab.get(i).getPalabra()+" "+matPalab.get(i).getAparicion());
			}			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}	
	}
	
	public void creaDivisiones(){
		divisiones=new ArrayList<ArrayList<Palabra>>();
		divisiones.add(matPalab);		
		Palabra palab;
		int ultpos=0,penpos=0;//metodo para crear las divisiones
		for (int i = 0; i < matPalab.size()-2; i++) {
			aux=new ArrayList<Palabra>();							
			for (int i1 = 0; i1 < divisiones.get(i).size()-2; i1++) {
				palab=new Palabra(divisiones.get(i).get(i1).getPalabra(), divisiones.get(i).get(i1).getAparicion(), "", i1);					
				aux.add(palab);					
			}
			ultpos=divisiones.get(i).size()-1;penpos=divisiones.get(i).size()-2;
			palab=new Palabra(divisiones.get(i).get(ultpos).getPalabra()+divisiones.get(i).get(penpos).getPalabra(), divisiones.get(i).get(ultpos).getAparicion()+divisiones.get(i).get(penpos).getAparicion(), "", penpos);
			aux.add(palab);
			quicksort(aux,0, aux.size()-1);
			divisiones.add(aux);		
			
		}		
	}
	public static void quicksort(ArrayList<Palabra> matPalab,int izq, int der){
		int i=izq;
		int j=der;
		int pivote=matPalab.get((izq+der)/2).getAparicion();
		do{
			while(matPalab.get(i).getAparicion()>pivote)i++;
			while(matPalab.get(j).getAparicion()<pivote)j--;
			if(i<=j){
				Palabra aux=matPalab.get(i);
				matPalab.set(i, matPalab.get(j));
				matPalab.set(j, aux);				
				i++;
				j--;
			}
		}while(i<=j);
		if(izq<j)quicksort(matPalab,izq, j);
		if(i<der)quicksort(matPalab,i, der);		
		
	}
	public void algoritmoHuffman (){
		//metodo para crear los codigos			
		int lim=divisiones.size()-1;
		int pos=0;
		aux=divisiones.get(lim);
		aux.get(aux.size()-2).setCod("0");
		aux.get(aux.size()-1).setCod("1");
		ArrayList<Palabra> ante=new ArrayList<Palabra>();
		for (int i = lim; i >0; i--) {
			aux=divisiones.get(i);
			ante=divisiones.get(i-1);
			for (int j = 0; j < aux.size(); j++) {
				pos=aux.get(j).getPos();
				if (pos==(ante.size()-2)) {
					ante.get(pos).setCod(aux.get(j).getCod()+"0");
					ante.get(pos+1).setCod(aux.get(j).getCod()+"1");						
				}else{
					ante.get(pos).setCod(aux.get(j).getCod());
				}										
			}				
		}		
		for (int i1 = 0; i1 < divisiones.get(0).size(); i1++) {
			matPalab.set(i1, divisiones.get(0).get(i1));
			System.out.println(matPalab.get(i1).getPalabra()+"-"+matPalab.get(i1).getCod());
		}
		
		
	}
	public static int getCantPal() {
		return cantPal;
	}
	public static  ArrayList<Palabra> getMatPalab() {
		return matPalab;
	}
	public Modelo getModelado() {
		return modelado;
	}
	public Huffman(Modelo modelo) {
		modelado = modelo;
	}

}
