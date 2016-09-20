/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MyExceptions;

/**
 *
 * @author Carlos
 */
public class OpenDBException extends Exception {

    /**
     * Creates a new instance of
     * <code>OpenDBException</code> without detail message.
     */
    public OpenDBException() {
    }

    /**
     * Constructs an instance of
     * <code>OpenDBException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public OpenDBException(String msg) {
        super(msg);
    }
}
