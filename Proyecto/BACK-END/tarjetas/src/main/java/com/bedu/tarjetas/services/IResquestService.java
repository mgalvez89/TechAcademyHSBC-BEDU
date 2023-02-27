package com.bedu.tarjetas.services;

import com.bedu.tarjetas.entities.Request;

import java.util.List;

public interface IResquestService {

    List<Request> getRequestStatus(String status);

    List<Request> getRequestType(String type);
}
