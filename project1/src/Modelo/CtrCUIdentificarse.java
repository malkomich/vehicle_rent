/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import java.sql.SQLException;
import Exceptions.LoginException;

/**
 *
 * @author JuanCarlos
 */
public class CtrCUIdentificarse {
    public Cliente identificarUsuario(String id, String pass) throws SQLException, ClassNotFoundException, LoginException{
        Cliente u = GestorUsuarios.recuperaClientePorId(id);
        if (u != null){
            boolean ok = u.passwordCorrecto(pass);
            if(ok)
                return u;
            else
                throw new LoginException("Datos erróneos. Contraseña incorrecta");
        } else{
            return null;
        }
    }
}
