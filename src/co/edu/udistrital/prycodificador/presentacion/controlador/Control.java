package co.edu.udistrital.prycodificador.presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


import co.edu.udistrital.prycodificador.presentacion.modelo.Modelo;
import co.edu.udistrital.prycodificador.presentacion.vistas.VistaPrincipal;

public class Control implements ActionListener, ListSelectionListener{
	private Modelo modelo;
	private VistaPrincipal vistaprincipal;
	public Control(VistaPrincipal principal) {
		vistaprincipal = principal;
	}
	public VistaPrincipal getPrincipal(){
		return vistaprincipal;
	}
	public void valueChanged(ListSelectionEvent lwidget) {
		if(lwidget.getSource() instanceof JList){
			int pos = getPrincipal().getListRes().getSelectedIndex();
			if(pos >= 0) {
				System.out.println("Seleccionamos el elemento "+pos+" de la Lista.");
				getPrincipal().getLblResNum().setText(""+getModelo().getHuffman().getMatPalab().get(pos).getAparicion());
				getPrincipal().getLblResProb().setText(""+getModelo().getHuffman().getMatPalab().get(pos).getAparicion()+" / "+getModelo().getHuffman().getCantPal());
			}			
		}
	}
	public void actionPerformed(ActionEvent widget) {
		JButton boton;
		if(widget.getSource() instanceof JButton){
			System.out.println("Presionamos boton ..");
			boton = (JButton) widget.getSource();
			if(boton == getPrincipal().getBtnExam()){
				System.out.println("...Examinar.");
				getModelo().abrirArchivo();
			}else 
			if(boton == getPrincipal().getBtnComp()){
				System.out.println("...Comprimir.");
				getModelo().comprimir();
				getModelo().anadeLista();
				getPrincipal().getBtnSave().setEnabled(true);
				getPrincipal().getBtnComp().setEnabled(false);
			}else
			if(boton == getPrincipal().getBtnBorr()){
				System.out.println("...Borrar.");
				getModelo().borrar();
				getPrincipal().getBtnSave().setEnabled(false);
				getPrincipal().getBtnComp().setEnabled(false);
			}else
			if(boton == getPrincipal().getBtnSave()){
				System.out.println("...Guardar.");
				getModelo().guardar();
			}
		}
	}
	public Modelo getModelo(){
		if(modelo==null){
			modelo = new Modelo(this);
		}
		return modelo;
	}
}
