package edu.ieti.bidify.dto;

/**
 * Clase que representa un mensaje genérico.
 */
public class Mensaje {
    private String mensaje;

    /**
     * Constructor de la clase Mensaje.
     *
     * @param mensaje Contenido del mensaje.
     */
    public Mensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * Obtiene el contenido del mensaje.
     *
     * @return Contenido del mensaje.
     */

    public String getMensaje() {
        return mensaje;
    }

    /**
     * Establece el contenido del mensaje.
     *
     * @param mensaje Contenido del mensaje.
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}