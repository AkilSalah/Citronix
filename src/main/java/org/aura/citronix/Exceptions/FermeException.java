package org.aura.citronix.Exceptions;

public class FermeException extends RuntimeException {
    public FermeException(int id) {
        super("Ferme n'exist pas avec id : " + id);
    }
}
