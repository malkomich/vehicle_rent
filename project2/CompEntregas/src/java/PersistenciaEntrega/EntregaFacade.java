/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PersistenciaEntrega;

import DominioEntrega.Entrega;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author malkomich
 */
@Stateless
public class EntregaFacade extends AbstractFacade<Entrega> implements EntregaFacadeLocal {
    @PersistenceContext(unitName = "CompEntregasPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EntregaFacade() {
        super(Entrega.class);
    }
    
}
