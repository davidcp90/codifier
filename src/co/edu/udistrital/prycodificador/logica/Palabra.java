package co.edu.udistrital.prycodificador.logica;

import co.edu.udistrital.prycodificador.presentacion.modelo.Modelo;

public class Palabra {
	private Modelo modelado;
	private String palabra, cod;
	private int aparicion,pos;
	
	public Palabra(String pal,int ap,String cod,int pos){
		palabra=pal;
		aparicion=ap;
		this.cod=cod;
		this.pos=pos;		
	}

	public String getPalabra() {
		return palabra;
	}

	public void setPalabra(String palabra) {
		this.palabra = palabra;
	}

	public int getAparicion() {
		return aparicion;
	}

	public void setAparicion(int aparicion) {
		this.aparicion = aparicion;
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}
	
	public Modelo getModelado() {
		return modelado;
	}
	public Palabra(Modelo modelo) {
		modelado = modelo;
	}

}
