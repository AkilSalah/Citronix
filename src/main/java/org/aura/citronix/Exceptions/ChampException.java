package org.aura.citronix.Exceptions;

public class ChampException extends RuntimeException {
    public ChampException(int id) {
        super("Champ n'exist pas avec id : " + id);
    }
}
