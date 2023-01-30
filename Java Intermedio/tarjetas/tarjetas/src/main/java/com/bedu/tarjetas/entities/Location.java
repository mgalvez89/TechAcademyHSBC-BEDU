package com.bedu.tarjetas.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;


@Data
@Entity
@Table(name = "locations")
public class Location {

    @PositiveOrZero(message = "El identificador no puede ser un número negativo o letras")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_location")
    private long idLocation;

    @NotEmpty(message = "El nombre de la ubicación no puede estar vacío, favor de verificar")
    @Pattern(regexp = "[G]-[0-9][0-9][0-9]", message = "El nombre de la ubicación debe tener el siguiente formato: G-NNN dónde N es cualquier número, favor de verificar")
    @Column(name = "name_location", nullable = false, unique = true)
    private String nameLocation;


    @NotNull(message = "La capacidad de la ubicacion no puede estar vacío, favor de verificar")
    @Min(value = 100, message = "La capacidad NO debe ser menor de 100, favor de verificar")
    @Max(value = 500, message = "La capacidad NO debe ser mayor de 500, favor de verificar")
    @Column(name = "capacity", nullable = false)
    private int capacity;

    @Column(name = "space_used")
    private int spaceUsed;

    @Column(name = "free_space")
    private int freeSpace;
}

