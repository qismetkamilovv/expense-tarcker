package az.keytd.expensetracker.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "emailLog")
public class EmailLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Long id;

    @Column(name = "recipient")
    private String recipient;

    @Column(name = "body")
    private String body;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private EmailSendStatus status;

    @Column(name = "sendTimes_tamp")
    private LocalDateTime sendTimesTamp;

    @Column(name = "error_message")
    private String errorMessage;

    @Column(name = "type")
    private String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public EmailSendStatus getStatus() {
        return status;
    }

    public void setStatus(EmailSendStatus status) {
        this.status = status;
    }

    public LocalDateTime getSendTimesTamp() {
        return sendTimesTamp;
    }

    public void setSendTimesTamp(LocalDateTime sendTimesTamp) {
        this.sendTimesTamp = sendTimesTamp;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
