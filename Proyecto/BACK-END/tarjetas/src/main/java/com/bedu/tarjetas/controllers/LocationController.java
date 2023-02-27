package com.bedu.tarjetas.controllers;

import com.bedu.tarjetas.entities.Location;
import com.bedu.tarjetas.helper.ExcelHelper;

import com.bedu.tarjetas.validations.ResponseMessage;
import com.bedu.tarjetas.services.ILocationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping("/api/v1/locations")
public class LocationController {

    ILocationService iLocationService;

    @Autowired
    public LocationController(ILocationService iLocationService){
        this.iLocationService = iLocationService;
    }



    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody Location location){
        location.setFreeSpace(location.getCapacity());
        Location location1 = iLocationService.create(location);
        return ResponseEntity.created(URI.create("1")).build();
    }

    @PostMapping("/excel/upload")
    public ResponseEntity<?> uploadFile(@Valid @RequestParam("file") MultipartFile file, HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();
        String message = "";

        if (ExcelHelper.hasExcelFormat(file))
        {
                iLocationService.createLocations(file);
                return ResponseEntity.created(URI.create("1")).build();
        }
        message = "El tipo de archivo que se intentó cargar es: '"+file.getContentType() + "' y se esperaba un archivo de tipo excel.";
        errors.put("message", message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(errors, HttpStatus.BAD_REQUEST.value(), request.getRequestURI()));

    }

    @GetMapping
    public List<Location> allLocations(){
        List<Location> locationList = iLocationService.getAllLocations();
        return locationList;
    }

    @PutMapping("/{idLocation}/{capacity}")
    public ResponseEntity<?> updateCapacity(@Valid @PathVariable int idLocation, @PathVariable int capacity, HttpServletRequest request){
        Map<String, String> errors = new HashMap<>();
        String message = "";

        if(idLocation <= 0){
            errors.put("idLocation", "El identificador no puede ser un número negativo o cero, favor de vericar.");
        }
        else if(capacity <= 0){
            errors.put("capacity", "La capacidad no puede ser un número negativo o cero, favor de verificar.");
        }
        else if(capacity < 100 || capacity > 500){
            errors.put("capacity", "La capacidad no puede ser menor a 100 o mayor a 500, favor de verificar.");
        }
        else {
            int status = iLocationService.updateCapacity(idLocation, capacity);
            if(status == 1){
                return ResponseEntity.created(URI.create("1")).build();
            } else if (status == 0) {
                errors.put("message", "Las ubicaciones que contienen paquetes NO se pueden actualizar, favor de verificar ubicacion con id: " + idLocation);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(errors, HttpStatus.BAD_REQUEST.value(), request.getRequestURI()));
            } else if (status == 2) {
                errors.put("message", "No se actualizó la ubicación con id: '"+ idLocation + "' porque NO existe en la base de datos, favor de verificar");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(errors, HttpStatus.BAD_REQUEST.value(), request.getRequestURI()));
            }


        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(errors, HttpStatus.BAD_REQUEST.value(), request.getRequestURI()));

    }

    @DeleteMapping("/{idLocation}")
    public ResponseEntity<?> deleteLocation(@Valid @PathVariable int idLocation, HttpServletRequest request){
        Map<String, String> errors = new HashMap<>();
        String message = "";

        if(idLocation <= 0){
            errors.put("idLocation", "El identificador no puede ser un número negativo o cero, favor de vericar.");
        }
        else {
            int status = iLocationService.deleteLocation(idLocation);
            if(status == 1){
                return ResponseEntity.created(URI.create("1")).build();
            } else if (status == 0) {
                errors.put("message", "Las ubicaciones que contienen paquetes NO se pueden eliminar, favor de verificar ubicacion con id: " + idLocation);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(errors, HttpStatus.BAD_REQUEST.value(), request.getRequestURI()));
            } else if (status == 2) {
                errors.put("message", "No se elimino la ubicación con id: '"+ idLocation + "' porque NO existe en la base de datos, favor de verificar");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(errors, HttpStatus.BAD_REQUEST.value(), request.getRequestURI()));
            }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(errors, HttpStatus.BAD_REQUEST.value(), request.getRequestURI()));

    }



}
