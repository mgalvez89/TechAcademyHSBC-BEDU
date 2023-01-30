package com.bedu.tarjetas.controllers;


import com.bedu.tarjetas.entities.Request;
import com.bedu.tarjetas.repositories.IRequestRepository;
import com.bedu.tarjetas.services.impl.IRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
