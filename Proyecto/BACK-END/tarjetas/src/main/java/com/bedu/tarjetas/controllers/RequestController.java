package com.bedu.tarjetas.controllers;


import com.bedu.tarjetas.entities.Request;
import com.bedu.tarjetas.repositories.IRequestRepository;
import com.bedu.tarjetas.services.impl.IRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/request")
public class RequestController {

    IRequestService iRequestService;

    @Autowired
    public RequestController (IRequestService iRequestService){
        this.iRequestService = iRequestService;
    }

    @GetMapping
    public List<Request> getRequestStatus(@RequestParam String status){
        return iRequestService.getRequestStatus(status);
    }

    @GetMapping("/{type}")
    public List<Request> getRequestType(@PathVariable String type){
        return iRequestService.getRequestType(type);
    }
}
