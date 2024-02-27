package az.keytd.expensetracker.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "emailLog")
public class EmailLogs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "recipient")
    private String recipient;

    @Column(name = "body")
    private String body;

    @Column(name = "status")
    private Status3 status;

    @Column(name = "sendTimesTamp")
    private LocalDateTime sendTimesTamp;

    @Column(name = "errorMessage")
    private String errorMessage;

    @Column(name = "type")
    private String type;

}
