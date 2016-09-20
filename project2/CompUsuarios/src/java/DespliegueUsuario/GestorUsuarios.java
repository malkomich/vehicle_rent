/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DespliegueUsuario;

import Dominio.Cliente;
import Dominio.Empleado;
import PersistenciaUsuario.ClienteFacadeLocal;
import PersistenciaUsuario.EmpleadoFacadeLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author malkomich
 */
@Stateless
public class GestorUsuarios implements IUsuarios {

    //EJB's internos
    @EJB
    private ClienteFacadeLocal clienteFacade;
    @EJB
    private EmpleadoFacadeLocal empleadoFacade;
    
    public Cliente getClient(String nif) {
        Cliente cliente = clienteFacade.find(nif);
        return cliente;
    }

    @Override
    public Empleado empleadoDeafult() {
        return empleadoFacade.findAll().get(0);
    }
}
