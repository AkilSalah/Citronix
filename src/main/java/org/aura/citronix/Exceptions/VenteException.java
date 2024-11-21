package org.aura.citronix.Exceptions;

public class VenteException extends RuntimeException {
    public VenteException(int id) {
        super("Vente n'exist pas avec id : " + id);
    }
}
