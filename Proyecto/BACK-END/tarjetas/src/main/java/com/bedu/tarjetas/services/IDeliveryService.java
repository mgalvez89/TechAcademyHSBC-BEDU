package com.bedu.tarjetas.services;

import com.bedu.tarjetas.entities.Delivery;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface IDeliveryService {

    Map<String, String> createDeliveries(MultipartFile file, String user);

    Map<String, String> updateRequest(long idRequest);

}
