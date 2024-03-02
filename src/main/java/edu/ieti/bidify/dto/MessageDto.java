package edu.ieti.bidify.dto;
import java.util.Date;
import lombok.Data;

/**
 * Clase que representa un objeto de transferencia de datos (DTO) para un mensaje.
 */
@Data
public class MessageDto {
    private String senderEmail;
    private String time;
    private String replymessage;

    /**
     * Constructor por defecto de la clase MessageDto.
     * Inicializa la fecha y hora de envío del mensaje con la fecha y hora actual.
     */
    public MessageDto() {
        this.time = new Date(System.currentTimeMillis()).toString();
    }

    /**
     * Constructor de la clase MessageDto.
     *
     * @param senderEmail Correo electrónico del remitente del mensaje.
     * @param time Fecha y hora de envío del mensaje en formato de cadena de caracteres.
     * @param replymessage Contenido del mensaje de respuesta.
     */
    public MessageDto( String senderEmail,  String time,  String replymessage) {
        this.senderEmail = senderEmail;
        this.time = time;
        this.replymessage = replymessage;
    }

    /**
     * Constructor de la clase MessageDto.
     * Inicializa la fecha y hora de envío del mensaje con la fecha y hora actual.
     *
     * @param senderEmail Correo electrónico del remitente del mensaje.
     * @param replymessage Contenido del mensaje de respuesta.
     */
    public MessageDto( String senderEmail, String replymessage) {
        this.senderEmail = senderEmail;
        this.time = new Date(System.currentTimeMillis()).toString();
        this.replymessage = replymessage;
    }
}