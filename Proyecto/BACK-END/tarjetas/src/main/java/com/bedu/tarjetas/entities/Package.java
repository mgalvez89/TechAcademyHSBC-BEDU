package com.bedu.tarjetas.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;


@Data
@Entity
@Table(name = "packages")
public class Package {

    @PositiveOrZero(message = "El identificador no puede ser un número negativo o letras")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_package")
    private long idPackage;

    @Size(min = 10, max = 50, message = "El nombre del paquete debe tener al menos 10 letras y ser menor a 50")
    @NotEmpty(message = "El nombre del paquete NO puede estar vacío, favor de verificar")
    @Column(name = "name_package", nullable = false, unique = true)
    private String namePackage;

    @NotNull(message = "El número de tarjetas no puede estar vacío, favor de verificar")
    @Max(value = 500, message = "El número de tarjetas debe ser menor de 500, favor de verificar")
    @Min(value = 50, message = "El número de tarjetas debe ser mayor de 50, favor de verificar")
    @Column(name = "number_cards", nullable = false)
    private int numberCards;

    @Size(min = 5, max = 30, message = "El nombre de tarjetas debe tener al menos 5 letras y ser menor a 30")
    @NotEmpty(message = "El nombre de tarjetas NO puede estar vacío, favor de verificar")
    @Column(name = "name_product", nullable = false)
    private String nameProduct;

    @Column(name = "status")
    private String status;

    @Transient
    private String branch;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne
    private Location location;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne
    private Request request;

}
