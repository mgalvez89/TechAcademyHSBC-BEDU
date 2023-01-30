package com.bedu.tarjetas.services.impl;

import com.bedu.tarjetas.entities.Request;
import com.bedu.tarjetas.repositories.IRequestRepository;
import com.bedu.tarjetas.services.IResquestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IRequestService implements IResquestService {

    IRequestRepository iRequestRepository;

    @Autowired
    public IRequestService(IRequestRepository iRequestRepository){
        this.iRequestRepository = iRequestRepository;
    }

    @Override
    public List<Request> getRequestStatus(String status) {
        return iRequestRepository.getRequestByStatus(status);
    }
}
