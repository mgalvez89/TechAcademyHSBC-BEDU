package com.bedu.tarjetas.controllers;

import com.bedu.tarjetas.entities.Package;
import com.bedu.tarjetas.helper.ExcelHelper;
import com.bedu.tarjetas.message.ResponseMessage;
import com.bedu.tarjetas.services.IPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/packages")
public class PackageController {

    IPackageService iPackageService;

    @Autowired
    public PackageController(IPackageService iPackageService) {
        this.iPackageService = iPackageService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Package create(@RequestBody Package aPackage) {
        return iPackageService.create(aPackage);
    }

    @PostMapping("/excel/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (ExcelHelper.hasExcelFormat(file))
        {
            try
            {
                iPackageService.createPackages(file);
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

    @PutMapping("/{idPackage}/{idNewLocation}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changeLocation(@PathVariable int idPackage, @PathVariable int idNewLocation){
        iPackageService.changeLocation(idPackage, idNewLocation);
    }

    @GetMapping
    public List<Package> allPackages(){
        return iPackageService.getAllPackages();
    }

    @DeleteMapping("/{idPackage}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int idPackage){
        iPackageService.deletePackage(idPackage);
    }
}