/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MyExceptions;

/**
 *
 * @author Carlos
 */
public class MiTiendaException extends Exception {
    
    private String mensaje;

    /**
     * Creates a new instance of
     * <code>MiTiendaException</code> without detail message.
     */
    public MiTiendaException() {
    }

    /**
     * Constructs an instance of
     * <code>MiTiendaException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public MiTiendaException(String msg) {
        this.mensaje = msg;
    }

    @Override
    public String toString() {
        return this.mensaje;
    }
    
    
}
