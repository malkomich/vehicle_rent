/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentacion;

import Modelo.Alquiler;
import Modelo.Factura;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.NumberFormat;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author JuanCarlos
 */
public class VistaDevolucion extends javax.swing.JFrame{
    
    //Constantes del panel de detalles de reserva
    private final int N_ROWS = 6;
    private final int N_COLUMNS = 2;
    private final int MARGIN_H = 5;
    private final int MARGIN_V = 5;
    
    //GUI Components
    JLabel labelNombre;
    JLabel labelDNI;
    JLabel labelFechaIni;
    JLabel labelFechaFin;
    JLabel labelMatricula;
    JLabel labelKm;
    JTextField formTextField;
    JList listIncidencias;
    JButton buttonMostrarAlquiler;
    JButton buttonDevolver;
   
    //Objetos funcionales
    Alquiler alquiler;
    Factura[] incidencias;
    CtrlVDevolucion controller;
    DefaultListModel listModelIncidencias;
    NumberFormat moneyFormatter = NumberFormat.getCurrencyInstance();
    NumberFormat numberFormatter = NumberFormat.getNumberInstance();
    DateFormat dateFormatter = DateFormat.getDateInstance();
    
    /**
     * Constructor genérico de la interfaz de vista de devolucion.
     */
    public VistaDevolucion(){
        super("DEVOLUCIÓN DE VEHÍCULO");
        initComponents();
        setLocationRelativeTo(null);
        
        controller = new CtrlVDevolucion(this);
    }
    
    /**
     * Método llamado para la inicialización de componentes de la interfaz.
     */
    void initComponents(){
        
        labelNombre = new JLabel("", JLabel.LEFT);
        labelDNI = new JLabel("", JLabel.LEFT); 
        labelFechaIni = new JLabel("", JLabel.LEFT);
        labelFechaFin = new JLabel("", JLabel.LEFT);
        labelMatricula = new JLabel("", JLabel.LEFT);
        labelKm = new JLabel("", JLabel.LEFT);
        formTextField = new JTextField();
        buttonMostrarAlquiler = new JButton("Mostrar Alquiler");
        buttonDevolver = new JButton("Devolver Vehículo");
        
        //Comportamiento del botón de mostrar alquiler
        buttonMostrarAlquiler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                controller.mostrarAlquiler();
            }
        });
        
        //establecer comportamiento al cerrar ventana
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        //Establecer el tipo de layout del JFrame y sus características
        GridLayout layout = new GridLayout(3, 1);
        setLayout(layout);
        setMinimumSize(new Dimension(400, 650));
        
        //crear panel formulario para buscar alquiler actual del vehículo
        JPanel formPanel = new JPanel(new GridLayout(2,1));
        JPanel inputGridPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        inputGridPanel.add(new JLabel("Introduzca la matrícula: "), c);
        c.gridx = 1;
        c.gridy = 0;
        formTextField.setPreferredSize(new Dimension(120, 30));
        inputGridPanel.add(formTextField, c);
        formPanel.add(inputGridPanel);
        JPanel buttonMostrarPanel = new JPanel();
        buttonMostrarAlquiler.setPreferredSize(new Dimension(150, 50));
        buttonMostrarPanel.add(buttonMostrarAlquiler);
        formPanel.add(buttonMostrarPanel);
        getContentPane().add(formPanel);
        
        //panel que muestra los detalles de alquiler
        JPanel info = new JPanel(new GridLayout(N_ROWS, N_COLUMNS, MARGIN_H, MARGIN_V));
        TitledBorder inside = new TitledBorder("Datos de Alquiler");
        EmptyBorder outside = new EmptyBorder(0, 5, 5, 5);
        CompoundBorder cpBorder = new CompoundBorder(outside, inside);
        info.setBorder(cpBorder);
        info.add(new JLabel("Nombre cliente: ", JLabel.RIGHT));
        info.add(labelNombre);
        labelNombre.setForeground(Color.black);
        info.add(new JLabel("DNI Cliente: ", JLabel.RIGHT));
        info.add(labelDNI);
        labelDNI.setForeground(Color.black);
        info.add(new JLabel("Fecha de inicio: ", JLabel.RIGHT));
        info.add(labelFechaIni);
        labelFechaIni.setForeground(Color.black);
        info.add(new JLabel("Fecha de fin: ", JLabel.RIGHT));
        info.add(labelFechaFin);
        labelFechaFin.setForeground(Color.black);
        info.add(new JLabel("Matrícula: ", JLabel.RIGHT));
        info.add(labelMatricula);
        labelMatricula.setForeground(Color.black);
        info.add(new JLabel("Kilometraje: ", JLabel.RIGHT));
        info.add(labelKm);
        labelKm.setForeground(Color.black);

        //crear lista de incidencias
        listModelIncidencias = new DefaultListModel();
        listIncidencias = new JList(listModelIncidencias);
        JScrollPane spIncidencias = new JScrollPane(listIncidencias);
        spIncidencias.setBorder(new TitledBorder("Incidencias"));
        
        //panel para insertar botón de devolver vehiculo
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(spIncidencias);
        buttonPanel.add(buttonDevolver);
        buttonDevolver.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
              controller.procesaEventoDevolucion(alquiler);
           }
        });
        
        //integrar los paneles
        JPanel details = new JPanel(new BorderLayout(0, 10));
        details.setBorder(new EmptyBorder(5, 5, 5, 5));
        details.add(info, BorderLayout.NORTH);
        getContentPane().add(details);
        getContentPane().add(buttonPanel);
        
    }
    
    /**
     * Método llamado para actualizar la ventana de detalles de alquiler.
     * @param a El alquiler a mostrar.
     */
    void updateGUI(Alquiler a){
        this.alquiler = a;
        this.labelNombre.setText(a.getCliente().getNombre());
        this.labelDNI.setText(a.getCliente().getNif());
        this.labelFechaIni.setText(dateFormatter.format(a.getFechaInicio()));
        this.labelFechaFin.setText(dateFormatter.format(a.getFechaFin()));
        this.labelMatricula.setText(a.getVehiculo().getMatricula());
        this.labelKm.setText(numberFormatter.format(a.getVehiculo().getKilometraje()));
    }
    
    public static void main(String args[]) throws Exception {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VistaDevolucion().setVisible(true);
            }
        });
    }
    
    /**
     * Obtiene la matrícula del vehículo.
     * @return Valor del campo de texto(input) de Matrícula.
     */
    String getMatricula(){
        return formTextField.getText();
    }

    /**
     * Actualiza el objeto alquiler a mostrar.
     * @param alquiler Objeto alquiler a mostrar en la interfaz.
     */
    void setAlquiler(Alquiler alquiler) {
        this.alquiler = alquiler;
        updateGUI(alquiler);
    }
    
    public void setFacturas(Factura[] facts) {
        this.incidencias = facts;
        listModelIncidencias.removeAllElements();
        for (Factura factura : facts) {
            listModelIncidencias.addElement(factura);
        }
    }

    /**
     * Esta función pide al usuario el kilometraje a través de una ventana prompt.
     * @return Número(real) actual de kilómetros del vehículo.
     */
    float getKilometraje() {
        String km;
        km = JOptionPane.showInputDialog(this,
                           "Introduce el kilometraje actual del vehículo " + alquiler.getVehiculo().getMatricula() + ":",
                           "Kilometraje actual: " + alquiler.getVehiculo().getKilometraje(),
                           JOptionPane.QUESTION_MESSAGE);
        if(km != null)
            return new Float(km);
        else
            return -1;
    }
    
    String getIncidencia() {
        String informe = null;
        int hayIncidencia = JOptionPane.showConfirmDialog(this,
                           "¿Ha ocurrido alguna incidencia relevante en el período de alquiler?",
                           "¿Incidencia?",
                           JOptionPane.YES_NO_OPTION,
                           JOptionPane.QUESTION_MESSAGE);
        if(hayIncidencia == JOptionPane.YES_OPTION){
            informe = JOptionPane.showInputDialog(this,
                           "Introduce el informe de incidencia: ",
                           "INCIDENCIA",
                           JOptionPane.QUESTION_MESSAGE);
        }
        return informe;
    }
    
    float getRecargo() {
        String recargo_temp = JOptionPane.showInputDialog(this,
                           "Introduce el informe de incidencia: ",
                           "INCIDENCIA",
                           JOptionPane.QUESTION_MESSAGE);
        return new Float(recargo_temp);
    }
    
    /**
     * Muestra un mensaje de error al usuario.
     * @param msg Mensaje del error.
     * @param tittle Título del mensaje de error.
     */
    void showError(Object msg, String tittle) {
        JOptionPane.showMessageDialog(this, msg, tittle, JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Muestra un mensaje de información al usuario.
     * @param msg Mensaje.
     * @param tittle Título del mensaje.
     */
    void showInfo(Object msg, String tittle) {
        JOptionPane.showMessageDialog(this, msg, tittle, JOptionPane.INFORMATION_MESSAGE);
    }
    
}
