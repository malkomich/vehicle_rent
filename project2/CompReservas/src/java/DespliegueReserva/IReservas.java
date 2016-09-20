/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DespliegueReserva;

import DominioReserva.Alquiler;
import DominioReserva.Reserva;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author malkomich
 */
@Local
public interface IReservas {
    
    public void crearReserva(Date fechaIni, Date fechaFin, String cliente, String vehiculo);
    
    public List<Reserva> mostrarReservasPendientes(String cliente);
    public void crearAlquiler(Reserva r, float km);

    public Alquiler getAlquilerByVehicle(String vehiculo);

    public void modificarAlquiler(Alquiler alquilerDevolucion);
    
}
