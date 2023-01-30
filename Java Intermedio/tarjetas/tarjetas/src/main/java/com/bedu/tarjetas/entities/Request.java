package com.bedu.tarjetas.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "requests")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_request")
    private long idRequest;
    @Column(name = "type_request")
    private String typeRequest;

    @Column(name = "storage_date")
    private Date storageDate;

    @Column(name = "delivery_date")
    private Date deliveryDate;

    @Column(name = "request_date")
    private Date requestDate;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "status")
    private String status;

}
