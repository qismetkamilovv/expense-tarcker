package az.keytd.expensetracker.entities;

import java.text.DecimalFormat;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Asset")
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Long id;

    @Column(name = "assetType")
    private String assetType;

    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private DecimalFormat value;

    @Column(name = "acquisitionDate")
    private LocalDateTime acquisitionDate;

    @Column(name = "description")
    private String description;

    @Column(name = "ownerId")
    private Long ownerId;

    @ManyToOne
    @JoinColumn(name = "ownerId")
    private User user; 
}
