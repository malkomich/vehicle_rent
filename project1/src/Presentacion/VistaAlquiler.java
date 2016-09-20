/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentacion;

import Modelo.Reserva;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author JuanCarlos
 */
public class VistaAlquiler extends javax.swing.JFrame{
    
    //Constantes del panel de detalles de reserva
    private final int N_ROWS = 7;
    private final int N_COLUMNS = 2;
    private final int MARGIN_H = 5;
    private final int MARGIN_V = 5;
    
    //GUI Components
    JLabel labelIdReserva;
    JLabel labelNombre;
    JLabel labelDNI;
    JLabel labelFechaIni;
    JLabel labelFechaFin;
    JLabel labelMatricula;
    JLabel labelKm;
    JTextField formTextField;
    JButton buttonMostrarReservas;
    JButton buttonCrearAlquiler;
    JList list;
   
    //Objetos funcionales
    Reserva currentRes;
    Reserva reservas[];
    DefaultListModel listModel;
    CtrlVAlquiler controller;
    NumberFormat moneyFormatter = NumberFormat.getCurrencyInstance();
    NumberFormat numberFormatter = NumberFormat.getNumberInstance();
    DateFormat dateFormatter = DateFormat.getDateInstance();
    
    /**
     * Constructor genérico de la interfaz de vista de alquiler.
     */
    public VistaAlquiler(){
        super("RECOGIDA DE VEHÍCULO");
        initComponents();
        setLocationRelativeTo(null);
        
        controller = new CtrlVAlquiler(this);
    }
    
    /**
     * Método llamado para la inicialización de componentes de la interfaz.
     */
    void initComponents(){
        
        labelIdReserva = new JLabel("", JLabel.LEFT);
        labelNombre = new JLabel("", JLabel.LEFT);
        labelDNI = new JLabel("", JLabel.LEFT); 
        labelFechaIni = new JLabel("", JLabel.LEFT);
        labelFechaFin = new JLabel("", JLabel.LEFT);
        labelMatricula = new JLabel("", JLabel.LEFT);
        labelKm = new JLabel("", JLabel.LEFT);
        formTextField = new JTextField();
        buttonMostrarReservas = new JButton("Mostrar Reservas");
        buttonCrearAlquiler = new JButton("Crear Alquiler...");
        
        //Comportamiento del botón de mostrar reservas
        buttonMostrarReservas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                controller.procesaMostrarReservas();
            }
        });
        
        //establecer comportamiento al cerrar ventana
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        //Establecer el tipo de layout del JFrame y sus características
//        GridLayout layout = new GridLayout(2, 1);
        GridLayout layout = new GridLayout(2, 1);
        setLayout(layout);
        setMinimumSize(new Dimension(600, 600));
        
        //crear panel formulario para buscar reservas por DNI de cliente
        JPanel formPanel = new JPanel(new GridLayout(3, 1));
        formPanel.setPreferredSize(new Dimension(500, 200));
        formPanel.add(new JLabel("Introduzca el DNI del cliente: ", JLabel.CENTER));
        JPanel textFieldPanel = new JPanel();
        formTextField.setPreferredSize(new Dimension(300,50));
        textFieldPanel.add(formTextField);
        JPanel buttonMostrarPanel = new JPanel();
        buttonMostrarReservas.setPreferredSize(new Dimension(400, 50));
        buttonMostrarPanel.add(buttonMostrarReservas);
        formPanel.add(textFieldPanel);
        formPanel.add(buttonMostrarPanel);
        getContentPane().add(formPanel);
        
        JPanel contentPanel = new JPanel();
        
        
        //crear lista de reservas y seleccionar la primera
        listModel = new DefaultListModel();
        list = new JList(listModel);
        JScrollPane sp = new JScrollPane(list);
        sp.setBorder(new TitledBorder("Reservas"));
        contentPanel.add(sp, BorderLayout.WEST);

        //actualizar cuando se cambia la selección de reserva
        list.addListSelectionListener(new ListSelectionListener() {
           @Override
           public void valueChanged(ListSelectionEvent e) {
              int idx = list.getSelectedIndex();
              if(idx != -1)
                updateGUI(reservas[idx]);
           }
        });
      
        //panel que muestra los detalles de reserva
        JPanel info = new JPanel(new GridLayout(N_ROWS, N_COLUMNS, MARGIN_H, MARGIN_V));
        TitledBorder inside = new TitledBorder("Reserva Seleccionada");
        EmptyBorder outside = new EmptyBorder(0, 5, 5, 5);
        CompoundBorder cpBorder = new CompoundBorder(outside, inside);
        info.setBorder(cpBorder);
        info.add(new JLabel("ID: ", JLabel.RIGHT));
        info.add(labelIdReserva);
        labelIdReserva.setForeground(Color.black);
        info.add(new JLabel("Nombre del Cliente: ", JLabel.RIGHT));
        info.add(labelNombre);
        labelNombre.setForeground(Color.black);
        info.add(new JLabel("DNI: ", JLabel.RIGHT));
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

        //panel para insertar botón de crear alquiler
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(buttonCrearAlquiler);
        buttonCrearAlquiler.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
              controller.procesaCrearAlquiler(currentRes);
           }
        });
        
        //integrar los 2 últimos paneles en uno conjunto
        JPanel details = new JPanel(new BorderLayout(0, 10));
        details.setBorder(new EmptyBorder(5, 5, 5, 5));
        details.add(info, BorderLayout.NORTH);
        details.add(buttonPanel, BorderLayout.SOUTH);
        contentPanel.add(details);
        getContentPane().add(contentPanel);
        
        
    }
    
    /**
     * Método llamado para actualizar la ventana de detalles de la reserva,
     * llamado al cambiar la selección en la lista de reservas.
     * @param r La reserva a mostrar.
     */
    void updateGUI(Reserva r){
        this.currentRes = r;
        this.labelIdReserva.setText(numberFormatter.format(r.getId()));
        this.labelNombre.setText(r.getCliente().getNombre());
        this.labelDNI.setText(r.getCliente().getNif());
        this.labelFechaIni.setText(dateFormatter.format(r.getInitDate()));
        this.labelFechaFin.setText(dateFormatter.format(r.getEndDate()));
        this.labelMatricula.setText(r.getVehiculo().getMatricula());
        this.labelKm.setText(numberFormatter.format(r.getVehiculo().getKilometraje()));
    }
    
    public static void main(String args[]) throws Exception {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VistaAlquiler().setVisible(true);
            }
        });
    }
    
    /**
     * Obtiene el DNI del cliente.
     * @return Valor del campo de texto(input) de DNI.
     */
    String getDni(){
        return formTextField.getText();
    }

    /**
     * Actualiza la lista de reservas disponibles
     * @param reservas Array de reservas a añadir en el componente JList de la interfaz.
     */
    void setReservas(Reserva[] reservas) {
        this.reservas = reservas;
        listModel.removeAllElements();
        for (Reserva reserva : reservas) {
            listModel.addElement(reserva);
        }
        list.setSelectedIndex(0);
    }

    /**
     * Esta función pide al usuario el kilometraje a través de una ventana prompt.
     * @return Número(real) actual de kilómetros del vehículo.
     */
    float getKilometraje() {
        String km;
        km = JOptionPane.showInputDialog(this,
                           "Introduce el kilometraje actual del vehículo " + currentRes.getVehiculo().getMatricula() + ":",
                           "Kilometraje actual: " + currentRes.getVehiculo().getKilometraje(),
                           JOptionPane.QUESTION_MESSAGE);
        if(km != null)
            return new Float(km);
        else
            return -1;
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
     * @param msg Mensaje del error.
     * @param tittle Título del mensaje de error.
     */
    void showInfo(Object msg, String tittle) {
        JOptionPane.showMessageDialog(this, msg, tittle, JOptionPane.INFORMATION_MESSAGE);
    }
}
