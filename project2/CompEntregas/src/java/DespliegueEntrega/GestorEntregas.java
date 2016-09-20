/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DespliegueEntrega;

import DespliegueReserva.IReservas;
import DespliegueUsuario.IUsuarios;
import DominioEntrega.Entrega;
import DominioReserva.Alquiler;
import PersistenciaEntrega.EntregaFacadeLocal;
import PersistenciaEntrega.FacturaFacadeLocal;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author malkomich
 */
@Stateless
public class GestorEntregas implements IEntregas {
    
    //EJB's internos
    @EJB
    private EntregaFacadeLocal entregaFacade;
    @EJB
    private FacturaFacadeLocal facturaFacade;
    
    //EJB's externos
    @EJB
    private IUsuarios gestorUsuarios;
    @EJB
    private IReservas gestorReservas;

    @Override
    public void registrarEntrega(Alquiler alquilerDevolucion) {
        int index = entregaFacade.count() + 1;
        Date today = new Date();
        Entrega entrega = new Entrega(index, today, 'F', gestorUsuarios.empleadoDeafult().getNumeroempleado(), alquilerDevolucion.getIdalquiler());
        entregaFacade.create(entrega);
        alquilerDevolucion.setFechafin(today); gestorReservas.modificarAlquiler(alquilerDevolucion);
    }
    
}
