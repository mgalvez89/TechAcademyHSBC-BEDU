package com.bedu.tarjetas.controllers;

import com.bedu.tarjetas.entities.Package;
import com.bedu.tarjetas.helper.ExcelHelper;
import com.bedu.tarjetas.services.impl.PackageServiceImpl;
import com.bedu.tarjetas.validations.ResponseMessage;
import com.bedu.tarjetas.services.IPackageService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/packages")
public class PackageController {

    IPackageService iPackageService;


    @Autowired
    public PackageController(IPackageService iPackageService) {
        this.iPackageService = iPackageService;
    }

    @PostMapping
    ResponseEntity<?> create(@Valid @RequestBody Package aPackage) {
        iPackageService.create(aPackage);
        return ResponseEntity.created(URI.create("1")).build();
    }

    @PostMapping("/excel/upload")
    public ResponseEntity<?> uploadFile(@Valid @RequestParam("file") MultipartFile file, HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();
        String message = "";

        if (ExcelHelper.hasExcelFormat(file))
        {
            iPackageService.createPackages(file);
            return ResponseEntity.created(URI.create("1")).build();
        }
        message = "El tipo de archivo que se intentó cargar es: '"+file.getContentType() + "' y se esperaba un archivo de tipo excel.";
        errors.put("message", message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(errors, HttpStatus.BAD_REQUEST.value(), request.getRequestURI()));

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