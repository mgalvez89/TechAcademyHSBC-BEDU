package com.bedu.tarjetas.controllers;

import com.bedu.tarjetas.entities.Location;
import com.bedu.tarjetas.helper.ExcelHelper;
import com.bedu.tarjetas.message.ResponseMessage;
import com.bedu.tarjetas.services.ILocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/locations")
public class LocationController {

    ILocationService iLocationService;

    @Autowired
    public LocationController(ILocationService iLocationService){
        this.iLocationService = iLocationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Location create(@RequestBody Location location){
        return iLocationService.create(location);
    }

    @PostMapping("/excel/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (ExcelHelper.hasExcelFormat(file))
        {
            try
            {
                iLocationService.createLocations(file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            }
            catch (Exception e)
            {
                message = "Could not upload the file: " + file.getOriginalFilename() + " !  " + e.getMessage() + "  Cause" + e.getCause();
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }
        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));

    }

    @GetMapping
    public List<Location> allLocations(){
        return iLocationService.getAllLocations();
    }

    @PutMapping("/{idLocation}/{capacity}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCapacity(@PathVariable int idLocation, @PathVariable int capacity){
        iLocationService.updateCapacity(idLocation, capacity);
    }

    @DeleteMapping("/{idLocation}")
    public void deleteLocation(@PathVariable int idLocation){
        iLocationService.deleteLocation(idLocation);
    }



}
