package com.bedu.tarjetas.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity
@Table(name = "branches")
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_branch")
    private long idBranch;

    @Column(name = "name_branch")
    private String nameBranch;

    @Column(name = "number_branch")
    private String numberBranch;

    @ManyToOne
    private Module module;
    private String tipoSucursal;
    private String territorial;
    private String regional;
    private String calle;
    private String colonia;
    private String municipio;
    private String ciudad;
    private String estado;
    private int cp;
    private String horario;
    private String director;
    private String telefono;
    private String email;
    private String status;
    private int idRuta;



}
