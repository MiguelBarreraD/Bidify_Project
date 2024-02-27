package edu.eci.arsw.bidify.dto;
import java.util.Date;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Embeddable
@Data
public class MessageDto {
    
    @NotBlank
    private String senderEmail;
    @NotBlank
    private String time; // Cambiado a String
    @NotBlank
    private String replymessage;

    public MessageDto() {
        this.time = new Date(System.currentTimeMillis()).toString(); // Convertir a cadena
    }

    public MessageDto(@NotBlank String senderEmail, @NotBlank String time, @NotBlank String replymessage) {
        this.senderEmail = senderEmail;
        this.time = time;
        this.replymessage = replymessage;
    }
    public MessageDto(@NotBlank String senderEmail, @NotBlank String replymessage) {
        this.senderEmail = senderEmail;
        this.time = new Date(System.currentTimeMillis()).toString();
        this.replymessage = replymessage;
    }
}

