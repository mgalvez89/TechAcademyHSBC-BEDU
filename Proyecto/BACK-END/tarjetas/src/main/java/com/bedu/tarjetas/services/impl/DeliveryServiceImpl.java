package com.bedu.tarjetas.services.impl;

import com.bedu.tarjetas.entities.Branch;
import com.bedu.tarjetas.entities.Delivery;
import com.bedu.tarjetas.entities.Package;
import com.bedu.tarjetas.entities.Request;
import com.bedu.tarjetas.helper.ExcelHelper;
import com.bedu.tarjetas.helper.Status;
import com.bedu.tarjetas.repositories.*;
import com.bedu.tarjetas.services.IDeliveryService;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.regex.Pattern;

@Service
public class DeliveryServiceImpl implements IDeliveryService {

    IRequestRepository iRequestRepository;
    IPackageRepository iPackageRepository;
    IBranchRepository iBranchRepository;
    IDeliveryRepository iDeliveryRepository;

    ILocationRepository iLocationRepository;

    @Autowired
    public DeliveryServiceImpl(IRequestRepository iRequestRepository, IPackageRepository iPackageRepository, IBranchRepository iBranchRepository, IDeliveryRepository iDeliveryRepository, ILocationRepository iLocationRepository){
        this.iRequestRepository = iRequestRepository;
        this.iPackageRepository = iPackageRepository;
        this.iBranchRepository = iBranchRepository;
        this.iDeliveryRepository = iDeliveryRepository;
        this.iLocationRepository = iLocationRepository;
    }

    @Transactional
    @Override
    public Map<String, String> createDeliveries(MultipartFile file) {
        Map<String, String> messages = new HashMap<>();
        try
        {
            Request request = new Request();
            request.setTypeRequest("DELIVERY");
            request.setRequestDate(new Date());
            request.setFileName(file.getOriginalFilename());
            request.setStatus(Status.PENDING);
            Request request1 = iRequestRepository.save(request);

            List<Delivery> deliveryList = ExcelHelper.excelToDelivery(file.getInputStream());


            boolean sigueAdelante = false;
            // valida que paquete y sucursales esten antes de procesar alguna solicitud de envio
            for(int i = 0; i < deliveryList.size(); i++)
            {
                String numberBranch = deliveryList.get(i).getBranch().getNumberBranch().trim();
                String namePackage = deliveryList.get(i).getAPackage().getNamePackage().trim();

                int auxBranch = Integer.parseInt(numberBranch);

                if(auxBranch <= 0){
                    messages.put("numberBranch", "El número de Sucursal no puede ser negetivo, cero ó letras, favor de verificar");
                    iRequestRepository.requestFail(request1.getIdRequest(), Status.FAIL);
                    return messages;
                }else if (auxBranch > 500) {
                    messages.put("numberBranch", "El número de Sucursal debe ser menor a 500, favor de verificar");
                    iRequestRepository.requestFail(request1.getIdRequest(), Status.FAIL);
                    return messages;
                }else if(namePackage.length() < 10 || namePackage.length() > 50){
                    messages.put("namePackage", "El nombre del paquete debe tener al menos 10 letras y ser menor a 50, favor de verificar");
                    iRequestRepository.requestFail(request1.getIdRequest(), Status.FAIL);
                    return messages;
                }

                Optional<Branch> branch = iBranchRepository.findOneByNumberBranch(numberBranch);
                if(branch.isPresent())
                {
                    Optional<Package> byNamePackage = iPackageRepository.findOneByNamePackage(namePackage);
                    if(byNamePackage.isPresent())
                    {
                        sigueAdelante = true;
                    }
                    else
                    {
                        messages.put("message", "El nombre del Paquete: '" + namePackage+ "' NO existe en la Base de DAtos, favor de verificar");
                        iRequestRepository.requestFail(request1.getIdRequest(), Status.FAIL);
                        return messages;
                    }
                }
                else
                {
                    messages.put("message", "La Sucursal: '" + numberBranch + "' NO existe en la Base de Datos, favor de verificar");
                    iRequestRepository.requestFail(request1.getIdRequest(), Status.FAIL);
                    return messages;
                }
            }

            if(sigueAdelante)
            {
                for(int i = 0; i < deliveryList.size(); i++)
                {
                    String numberBranch = deliveryList.get(i).getBranch().getNumberBranch().trim();
                    String namePackage = deliveryList.get(i).getAPackage().getNamePackage().trim();

                    Optional<Package> byNamePackage = iPackageRepository.findOneByNamePackage(namePackage);

                    if(byNamePackage.isPresent())
                    {
                        if(byNamePackage.get().getStatus().equals(Status.SITUATED))
                        {

                            int result = iPackageRepository.updateStatusPackage(byNamePackage.get().getIdPackage(), request1.getIdRequest(), Status.PENDING);

                            Optional<Branch> branch = iBranchRepository.findOneByNumberBranch(numberBranch);

                            if (branch.isPresent())
                            {
                                Delivery deliveryN = new Delivery();
                                deliveryN.setBranch(branch.get());
                                //deliveryN.setNumberBranch(byNumberBranch.get().getNumberBranch());
                                deliveryN.setNumberCards(byNamePackage.get().getNumberCards());
                                deliveryN.setAPackage(byNamePackage.get());
                                //deliveryN.setNamePackage(byNamePackage.get().getNamePackage());
                                deliveryN.setStatus(Status.PENDING);
                                Delivery deliveryCreated = iDeliveryRepository.save(deliveryN);
                                if (deliveryCreated != null)
                                    messages.put("ok", "ok");
                            }
                            else{
                                messages.put("message", "La Sucursal: '" + numberBranch + "' NO existe en la Base de Datos, favor de verificar");
                                iRequestRepository.requestFail(request1.getIdRequest(), Status.FAIL);
                                return messages;
                            }

                        }
                        else{
                            messages.put("message", "No se puede generar el envío del Paquete: '" + namePackage + "' porque ya se encuentra en status: '" + byNamePackage.get().getStatus() + "', favor de verificar");
                            iRequestRepository.requestFail(request1.getIdRequest(), Status.FAIL);
                            return messages;
                        }

                    }
                    else{
                        messages.put("message", "El nombre del Paquete: '" + namePackage+ "' NO existe en la Base de DAtos, favor de verificar");
                        iRequestRepository.requestFail(request1.getIdRequest(), Status.FAIL);
                        return messages;
                    }
                }
            }else{
                messages.put("message", "La sucursal o el paquete no existen en la base de datos, favor de verificar");
                iRequestRepository.requestFail(request1.getIdRequest(), Status.FAIL);
                return messages;
            }


        }
        catch (IOException e)
        {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }

        return messages;
    }

    @Transactional
    @Override
    public Map<String, String> updateRequest(long idRequest) {
        Map<String, String> messages = new HashMap<>();
        Optional<Request> request1 = iRequestRepository.findById(idRequest);
        if(request1.isPresent()){
            if(request1.get().getStatus().equals(Status.SUBMITTED)){
                messages.put("message", "La Solicitud con idRequest: '"+ idRequest + "' ya se encuentra con estatus: '" + request1.get().getStatus()+"'");
                return messages;
            }
        }
        int result = iRequestRepository.realeaseRequest(idRequest, new Date(), Status.SUBMITTED);
//        System.out.println("result: " + result);

        //obtener lista de paquetes por cada solicitud
        Request request = new Request();
        request.setIdRequest(idRequest);
        List<Package> packageList = iPackageRepository.findByRequestAndStatus(request, Status.PENDING);
//        packageList.forEach(System.out::println);
        if(packageList.isEmpty()){
            messages.put("message", "La Solicitud con idRequest: '"+ idRequest + "' no existe en la Base de Datos, favor de verificar. ");
            return messages;
        }


        //liberando espacio en las ubicaciones, establecer status subbmited
        packageList.forEach(
                (aPackage) -> {
                    int resultPackage = iPackageRepository.releasePackage(aPackage.getIdPackage(), Status.SUBMITTED);
//                    System.out.println("resultPackage: " + resultPackage);

                   int resultLocation =  iLocationRepository.updateSpaceAdd(aPackage.getLocation().getIdLocation(), aPackage.getNumberCards());
//                    System.out.println("resultLoaction: " + resultLocation);

                    int resultDelivery = iDeliveryRepository.releaseDelivery(aPackage.getIdPackage(), Status.SUBMITTED);
//                    System.out.println("resultDelivery: " + resultDelivery);
                }
        );
        messages.put("ok", "ok");
        return messages;
    }
}
