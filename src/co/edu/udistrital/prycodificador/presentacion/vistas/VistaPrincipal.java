package co.edu.udistrital.prycodificador.presentacion.vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.TextArea;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import co.edu.udistrital.prycodificador.presentacion.controlador.Control;

public class VistaPrincipal extends JFrame {
	private Control control;
	private JPanel panelCarga = null;
	private JPanel panelRes = null;
	private JPanel panelCod = null;
	private JPanel panelPpal = null;
	private JButton btnExam = null;
	private JButton btnComp = null;
	private JButton btnBorr = null;
	private JButton btnSave = null;
	private JTextField txtExam = null;
	private JLabel lblDir = null;
	private JLabel lblRes = null;
	private JLabel lblEstado = null;
	private TextArea txtAIni = null;
	private TextArea txtACod = null;
	private JList listRes = null;
	private JLabel lblNum = null;
	private JLabel lblResNum = null;
	private JLabel lblProb = null;
	private JLabel lblResProb = null;
	public VistaPrincipal(){
		super();
		initialize();
	}
	private void initialize(){
		this.setSize(700, 700);
		this.setContentPane(getPanelPpal());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("UD Compressor 2010");
		this.setResizable(false);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		//this.getJContentPane().addMouseMotionListener(getControl());
	}
	public JPanel getPanelPpal() {
		if(panelPpal == null){
			panelPpal = new JPanel();
			panelPpal.setLayout(null);
			panelPpal.add(getPanelCarga(),null);
			panelPpal.add(getPanelCod(),null);
			panelPpal.add(getPanelRes(),null);			
		}
		return panelPpal;
	}
	public JPanel getPanelCarga() {
		if(panelCarga == null){
			lblEstado = new JLabel();
			lblEstado.setBounds(15, 115, 100, 25);
			lblEstado.setText("Archivo Original : ");
			lblDir = new JLabel();
			lblDir.setBounds(20, 20, 150, 25);
			lblDir.setText("Seleccione el Archivo:");
			panelCarga = new JPanel();
			panelCarga.setBounds(10, 15, 420, 360);
			panelCarga.setLayout(null);
			TitledBorder rotulo;
			rotulo = BorderFactory.createTitledBorder(" Inicio ");
			rotulo.setTitleColor(new Color(0,0,128));
			panelCarga.setBorder(rotulo);
			panelCarga.add(lblEstado);
			panelCarga.add(lblDir);
			panelCarga.add(getTxtExam(),null);
			panelCarga.add(getBtnExam(),null);
			panelCarga.add(getBtnComp(),null);
			panelCarga.add(getBtnBorr(),null);
			panelCarga.add(getBtnSave(),null);
			panelCarga.add(getTxtAIni(),null);
		}
		return panelCarga;
	}
	public JPanel getPanelRes() {
		if(panelRes == null){
			lblRes = new JLabel();
			lblRes.setBounds(20, 15, 150, 25);
			lblRes.setText("Palabra / Codigo :");
			lblNum = new JLabel();
			lblNum.setBounds(25, 530, 100, 25);
			lblNum.setText("No. de veces: ");
			lblProb = new JLabel();
			lblProb.setBounds(25, 570, 100, 25);
			lblProb.setText("Probabilidad: ");
			panelRes = new JPanel();	
			panelRes.setBounds(450, 15, 230, 620);
			panelRes.setLayout(null);
			TitledBorder rotulo;
			rotulo = BorderFactory.createTitledBorder(" Resultados ");
			rotulo.setTitleColor(new Color(0,0,128));
			panelRes.setBorder(rotulo);
			panelRes.add(lblProb);
			panelRes.add(getLblResProb(),null);
			panelRes.add(lblNum);
			panelRes.add(getLblResNum(),null);
			panelRes.add(lblRes);
			panelRes.add(getListRes(),null);
		}
		return panelRes;
	}
	public JPanel getPanelCod() {
		if(panelCod == null){
			panelCod = new JPanel();
			panelCod.setBounds(10, 380, 420, 270);
			panelCod.setLayout(null);
			TitledBorder rotulo;
			rotulo = BorderFactory.createTitledBorder(" Codificación ");
			rotulo.setTitleColor(new Color(0,0,128));
			panelCod.setBorder(rotulo);
			panelCod.add(getTxtACod(),null);
		}
		return panelCod;
	}
	public JButton getBtnExam() {
		if(btnExam == null){
			btnExam = new JButton();
			btnExam.setBounds(320, 50, 90, 25);
			btnExam.setText("Examinar");
			btnExam.addActionListener(getControl());
		}
		return btnExam;
	}
	public JButton getBtnSave() {
		if(btnSave == null){
			btnSave = new JButton();
			btnSave.setBounds(290, 85, 100, 25);
			btnSave.setText("Guardar");
			btnSave.setEnabled(false);
			btnSave.addActionListener(getControl());
		}
		return btnSave;
	}
	public JButton getBtnComp() {
		if(btnComp == null){
			btnComp = new JButton();
			btnComp.setBounds(60, 85, 100, 25);
			btnComp.setText("Comprimir");
			btnComp.setEnabled(false);
			btnComp.addActionListener(getControl());
		}
		return btnComp;
	}
	public JButton getBtnBorr() {
		if(btnBorr == null){
			btnBorr = new JButton();
			btnBorr.setBounds(175, 85, 100, 25);
			btnBorr.setText("Borrar");
			btnBorr.addActionListener(getControl());
		}
		return btnBorr;
	}
	public JTextField getTxtExam() {
		if(txtExam == null){
			txtExam = new JTextField();
			txtExam.setBounds(15, 50, 300, 25);
			txtExam.setHorizontalAlignment(JTextField.LEFT);
		}
		return txtExam;
	}
	public TextArea getTxtAIni() {
		if(txtAIni == null){
			txtAIni = new TextArea();
			txtAIni.setBounds(15,145,390,200);
			txtAIni.setText("");
			txtAIni.setEditable(false);
			txtAIni.setBackground(new Color(255,255,255));
		}
		return txtAIni;
	}
	public TextArea getTxtACod() {
		if(txtACod == null){
			txtACod = new TextArea();
			txtACod.setBounds(15 ,25, 390, 235);
			txtACod.setText("");
			txtACod.setEditable(false);
			txtACod.setBackground(new Color(255,255,255));
		}
		return txtACod;
	}
	public JList getListRes() {
		if(listRes == null){
			listRes = new JList();
			listRes.setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
			listRes.setBounds(5,50, 220, 450);
			listRes.addListSelectionListener(getControl());
		}
		return listRes;
	}
	public JLabel getLblResNum(){
		if(lblResNum == null){
			lblResNum = new JLabel();
			lblResNum.setBounds(150,530, 150, 25);
			lblResNum.setText("");
		}
		return lblResNum;
	}
	public JLabel getLblResProb(){
		if(lblResProb == null){
			lblResProb = new JLabel();
			lblResProb.setBounds(150,570, 150, 25);
			lblResProb.setText("");
		}		
		return lblResProb;
	}
	public Control getControl() {
		if(control==null){
			control = new Control(this);
		}
		return control;
	}
}
