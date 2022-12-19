package com.bedu.tarjetas.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "packages")
public class Package {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_package")
    private int idPaquete;
    @Column(name = "name_package", nullable = false, unique = true)
    private String namePackage;
    @Column(name = "number_cards", nullable = false)
    private int numberCards;
    @Column(name = "name_product", nullable = false)
    private String nameProduct;
    @Column(name = "status")
    private String status;

    @ManyToOne
    private Location location;

}
