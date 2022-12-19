package com.bedu.tarjetas.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_location")
    private int idLocation;

    @Column(name = "name_location", nullable = false, unique = true)
    private String nameLocation;
    @Column(name = "capacity")
    private int capacity;
    @Column(name = "space_used")
    private int spaceUsed;
    @Column(name = "free_space")
    private int freeSpace;

}
