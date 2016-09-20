/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DespliegueEntrega;

import DominioReserva.Alquiler;
import javax.ejb.Local;

/**
 *
 * @author malkomich
 */
@Local
public interface IEntregas {

    public void registrarEntrega(Alquiler alquilerDevolucion);
    
}
