/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PersistenciaReserva;

import DominioReserva.Alquiler;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author malkomich
 */
@Stateless
public class AlquilerFacade extends AbstractFacade<Alquiler> implements AlquilerFacadeLocal {
    @PersistenceContext(unitName = "CompReservasPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AlquilerFacade() {
        super(Alquiler.class);
    }
    
}
