package com.bedu.tarjetas.entities;

import lombok.Data;

import javax.persistence.*;
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

    @Column(name = "date_upload")
    private Date dateUpload;

    @Column(name = "date_delivery")
    private Date dateDelivery;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_path")
    private String filePath;


}
