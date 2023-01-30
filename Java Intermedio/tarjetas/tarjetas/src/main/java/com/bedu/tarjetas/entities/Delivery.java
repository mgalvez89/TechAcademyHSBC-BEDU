package com.bedu.tarjetas.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Entity
@Table(name = "deliveries")
public class Delivery {

    @PositiveOrZero(message = "El identificador no puede ser un número negativo o letras")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_delivery")
    private long idDelivery;

    @Column(name = "number_cards", nullable = false)
    private int numberCards;

    @Column(name = "status")
    private String status;

    @ManyToOne
    private Branch branch;

    @ManyToOne
    private Package aPackage;

}
