/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MyExceptions;

/**
 *
 * @author Carlos
 */
public class AccessDBException extends Exception {

    /**
     * Creates a new instance of
     * <code>AccessDBException</code> without detail message.
     */
    public AccessDBException() {
    }

    /**
     * Constructs an instance of
     * <code>AccessDBException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public AccessDBException(String msg) {
        super(msg);
    }
}
