/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DespliegueReserva;

import DespliegueUsuario.IUsuarios;
import DespliegueVehiculo.IVehiculos;
import Dominio.Vehiculo;
import DominioReserva.Alquiler;
import DominioReserva.Reserva;
import PersistenciaReserva.AlquilerFacadeLocal;
import PersistenciaReserva.ReservaFacadeLocal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author malkomich
 */
@Stateless
public class GestorReservas implements IReservas {

    //EJB's internos
    @EJB
    private ReservaFacadeLocal reservaFacade;
    @EJB
    private AlquilerFacadeLocal alquilerFacade;
    
    //EJB's externos
    @EJB
    private IUsuarios gestorUsuarios;
    @EJB
    private IVehiculos gestorVehiculos;
    
    @Override
    public void crearReserva(Date fechaIni, Date fechaFin, String cliente, String vehiculo) {
        int index = reservaFacade.count() + 1;
        Reserva r = new Reserva(new Date(), fechaIni, fechaFin, 'F', cliente, vehiculo);
        r.setIdreserva(index);
        reservaFacade.create(r);
    }
    
    @Override
    public List<Reserva> mostrarReservasPendientes(String cliente) {
        List<Reserva> reservas = new ArrayList<>();
        for(Reserva i: reservaFacade.findAll()){
//            if(i.getEjecutada().equals("F") && i.getNif().equals(cliente) && i.getFechainicioalquiler().equals(new Date())){
            if(i.getEjecutada().equals('F') && i.getNif().equals(cliente)){
                reservas.add(i);
            }
        }
        return reservas;
    }

    @Override
    public void crearAlquiler(Reserva r, float km) {
        int index = alquilerFacade.count() + 1;
        Vehiculo v = gestorVehiculos.getVehiculo(r.getMatricula());
        float precioPorDia = v.getIdmodelo().getCostealquiler();
        Date today = new Date();
        int dias = r.getFechafinalquiler().compareTo(today);
        float precio = precioPorDia * dias;
        Alquiler alquiler = new Alquiler(index, today, r.getFechafinalquiler(), precio, km, r.getMatricula(), gestorUsuarios.empleadoDeafult().getNumeroempleado(), r.getNif());
        alquilerFacade.create(alquiler);
        r.setEjecutada('T'); reservaFacade.edit(r);
    }

    @Override
    public Alquiler getAlquilerByVehicle(String vehiculo) {
        Date today = new Date();
        for(Alquiler i:alquilerFacade.findAll()){
            if((i.getFechafin().after(today) || i.getFechafin().equals(today)) && i.getMatricula().equals(vehiculo))
                return i;
        }
        return null;
    }

    @Override
    public void modificarAlquiler(Alquiler alquilerDevolucion) {
        alquilerFacade.edit(alquilerDevolucion);
    }
    
}
