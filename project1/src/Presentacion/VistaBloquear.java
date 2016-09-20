/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentacion;

import Modelo.Cliente;
import Modelo.Factura;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author JuanCarlos
 */
public class VistaBloquear extends javax.swing.JFrame{
    
    //Constantes del panel de detalles de cliente
    private final int N_ROWS = 6;
    private final int N_COLUMNS = 2;
    private final int MARGIN_H = 5;
    private final int MARGIN_V = 5;
    
    //GUI Components
    JLabel labelNombre;
    JLabel labelDNI;
    JLabel labelDireccion;
    JLabel labelCodPostal;
    JLabel labelEmail;
    JLabel labelTotalImpagado;
    JButton buttonBloquear;
    JList listClientes;
    JList listFacturas;
   
    //Objetos funcionales
    Cliente currentClient;
    Cliente clientes[];
    Factura[] facturas;
    float totalImpagos = 0;
    DefaultListModel listModelClientes;
    DefaultListModel listModelFacturas;
    CtrlVBloquear controller;
    NumberFormat moneyFormatter = NumberFormat.getCurrencyInstance();
    NumberFormat numberFormatter = NumberFormat.getNumberInstance();
    
    /**
     * Constructor genérico de la interfaz de vista de bloquear
     */
    public VistaBloquear(){
        super("BLOQUEAR CLIENTES");
        initComponents();
        setLocationRelativeTo(null);
        
        controller = new CtrlVBloquear(this);
        controller.mostrarClientes();
    }
    
    /**
     * Método llamado para la inicialización de componentes de la interfaz.
     */
    void initComponents(){
        
        labelNombre = new JLabel("", JLabel.LEFT);
        labelDNI = new JLabel("", JLabel.LEFT); 
        labelDireccion = new JLabel("", JLabel.LEFT);
        labelCodPostal = new JLabel("", JLabel.LEFT);
        labelEmail = new JLabel("", JLabel.LEFT);
        labelTotalImpagado = new JLabel("", JLabel.LEFT);
        buttonBloquear = new JButton("Bloquear Cliente");
                
        //establecer comportamiento al cerrar ventana
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        //Establecer el tipo de layout del JFrame y sus características
        GridLayout layout = new GridLayout(2, 1);
        setLayout(layout);
        setMinimumSize(new Dimension(800, 500));
        
        //crear la lista de los clientes
        listModelClientes = new DefaultListModel();
        listClientes = new JList(listModelClientes);
        JScrollPane spClientes = new JScrollPane(listClientes);
        spClientes.setBorder(new TitledBorder("Clientes"));
        getContentPane().add(spClientes);

        //actualizar cuando se cambia la selección de cliente
        listClientes.addListSelectionListener(new ListSelectionListener() {
           @Override
           public void valueChanged(ListSelectionEvent e) {
              int idx = listClientes.getSelectedIndex();
              if(idx != -1){
                updateGUI(clientes[idx]);
              }
           }
        });

        JPanel contentPanel = new JPanel(new BorderLayout(0, 10));
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
      
        //panel que muestra los detalles del cliente
        JPanel info = new JPanel(new GridLayout(N_ROWS, N_COLUMNS, MARGIN_H, MARGIN_V));
        TitledBorder inside = new TitledBorder("Cliente seleccionado");
        EmptyBorder outside = new EmptyBorder(0, 5, 5, 5);
        CompoundBorder cpBorder = new CompoundBorder(outside, inside);
        info.setBorder(cpBorder);
        info.add(new JLabel("Nombre: ", JLabel.RIGHT));
        info.add(labelNombre);
        labelNombre.setForeground(Color.black);
        info.add(new JLabel("DNI: ", JLabel.RIGHT));
        info.add(labelDNI);
        labelDNI.setForeground(Color.black);
        info.add(new JLabel("Dirección: ", JLabel.RIGHT));
        info.add(labelDireccion);
        labelDireccion.setForeground(Color.black);
        info.add(new JLabel("Código Postal: ", JLabel.RIGHT));
        info.add(labelCodPostal);
        labelCodPostal.setForeground(Color.black);
        info.add(new JLabel("Email: ", JLabel.RIGHT));
        info.add(labelEmail);
        labelEmail.setForeground(Color.black);
        info.add(new JLabel("Total Impagado: ", JLabel.RIGHT));
        info.add(labelTotalImpagado);
        labelTotalImpagado.setForeground(Color.black);

        //crear la lista de facturas
        listModelFacturas = new DefaultListModel();
        listFacturas = new JList(listModelFacturas);
        JScrollPane spFacturas = new JScrollPane(listFacturas);
        spFacturas.setBorder(new TitledBorder("Facturas"));
        
        //panel para insertar botón de bloquear cliente
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(buttonBloquear);
        buttonBloquear.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
              controller.procesaEventoBloquear(currentClient);
           }
        });
        
        //integrar los paneles
        JPanel details = new JPanel(new GridLayout(1, 2));
        details.setBorder(new EmptyBorder(5, 5, 5, 5));
        details.add(info);
        details.add(spFacturas);
        contentPanel.add(details, BorderLayout.NORTH);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);
        getContentPane().add(contentPanel);
        
    }
    
    /**
     * Método llamado para actualizar la ventana de detalles del cliente,
     * llamado al cambiar la selección en la lista de clientes.
     * @param c Cliente a mostrar.
     */
    void updateGUI(Cliente c){
        this.currentClient = c;
        controller.mostrarDetalles(currentClient); //Actualizamos las facturas antes de mostrar la etiqueta del total impagado.
        this.labelNombre.setText(c.getNombre());
        this.labelDNI.setText(c.getNif());
        this.labelDireccion.setText(c.getDireccionPostal());
        this.labelCodPostal.setText(numberFormatter.format(c.getCodigoPostal()));
        this.labelEmail.setText(c.getEmail());
        this.labelTotalImpagado.setText(moneyFormatter.format(totalImpagos));
        
    }
    
    public static void main(String args[]) throws Exception {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VistaBloquear().setVisible(true);
            }
        });
    }
    
    void setClientes(Cliente[] clientes) {
        this.clientes = clientes;
        listModelClientes.removeAllElements();
        for (Cliente cliente : clientes) {
            listModelClientes.addElement(cliente);
        }
    }

    void setFacturas(Factura[] facturas) {
        this.facturas = facturas;
        listModelFacturas.removeAllElements();
        totalImpagos = 0;
        for (Factura factura : facturas) {
            listModelFacturas.addElement(factura);
            totalImpagos += factura.getImporte();
        }
        listFacturas.setSelectedIndex(0);
    }
    
    void showError(String msg, String tittle) {
        JOptionPane.showMessageDialog(this, msg, tittle, JOptionPane.ERROR_MESSAGE);
    }

    void showInfo(String msg, String tittle) {
        JOptionPane.showMessageDialog(this, msg, tittle, JOptionPane.INFORMATION_MESSAGE);
    }
}
