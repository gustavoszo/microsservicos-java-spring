package br.com.mensageria_kafka.pix.entities;

import br.com.mensageria_kafka.pix.entities.enums.StatusPix;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "pix")
public class Pix {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String identifier;

    @Column(name = "origin_key")
    private String originKey;

    @Column(name = "destination_key")
    private String destinationKey;

    private BigDecimal value;

    @Column(name = "transfer_date")
    private LocalDateTime transferDate;

    @Enumerated(EnumType.STRING)
    private StatusPix status;

}
