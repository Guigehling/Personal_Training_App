/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app.auxiliares;

/**
 *
 * @author Guilherme Gehling
 */
public class ServicoException extends Exception {

    public ServicoException() {
    }

    public ServicoException(String message) {
        super(message);
    }

    public ServicoException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServicoException(Throwable cause) {
        super(cause);
    }
}
