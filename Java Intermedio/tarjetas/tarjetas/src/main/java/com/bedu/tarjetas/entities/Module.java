package com.bedu.tarjetas.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_module")
    private long idModule;
    private String modulo;
    private int cve_mod;
    @Column(name = "name_module")
    private String nameModule;
    private String responsable;
    private String calle;
    private String colonia;
    private String municipio;
    private String ciudad;
    private int cp;
    private String estado;
    private String telefono;
    private String email;
    private int estatus;

}
