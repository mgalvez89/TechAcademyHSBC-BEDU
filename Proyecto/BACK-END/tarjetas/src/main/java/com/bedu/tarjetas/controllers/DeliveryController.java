package com.bedu.tarjetas.controllers;

import com.bedu.tarjetas.entities.Request;
import com.bedu.tarjetas.helper.ExcelHelper;
import com.bedu.tarjetas.validations.ResponseMessage;
import com.bedu.tarjetas.services.IDeliveryService;
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
import java.util.Map;


@Slf4j
@RestController
@RequestMapping("/api/v1/deliveries")
public class DeliveryController {

    IDeliveryService iDeliveryService;


    @Autowired
    public DeliveryController(IDeliveryService iDeliveryService){
        this.iDeliveryService = iDeliveryService;
    }

    @PostMapping("/excel/upload/{user}")
    public ResponseEntity<?> uploadFile(@Valid @RequestParam("file") MultipartFile file, HttpServletRequest request, @PathVariable String user){
        Map<String, String> errors = new HashMap<>();
        String message = "";
        if (ExcelHelper.hasExcelFormat(file))
        {
            Map<String,String> messages = iDeliveryService.createDeliveries(file, user);
            if(messages.containsKey("ok"))
            {
                return ResponseEntity.created(URI.create("1")).build();
            }
            else
            {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(messages, HttpStatus.BAD_REQUEST.value(), request.getRequestURI()));
            }
        }
        message = "El tipo de archivo que se intentó cargar es: '"+file.getContentType() + "' y se esperaba un archivo de tipo excel.";
        errors.put("message", message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(errors, HttpStatus.BAD_REQUEST.value(), request.getRequestURI()));

    }

    @PutMapping
    public ResponseEntity<?> releaseRequest(@RequestBody Request req, @RequestParam String idRequest, HttpServletRequest request){
        Long id = Long.parseLong(idRequest);
        Map<String,String> messages  = iDeliveryService.updateRequest(id);
        if(messages.containsKey("ok"))
            return ResponseEntity.created(URI.create("1")).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(messages, HttpStatus.BAD_REQUEST.value(), request.getRequestURI()));
    }
}
