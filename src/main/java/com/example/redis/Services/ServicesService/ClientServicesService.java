package com.example.redis.Services.ServicesService;

import com.example.redis.Models.ServiceModel.ClientServiceModel;

import java.util.List;

public interface ClientServicesService {
    List<ClientServiceModel> getAllClientServices();
    ClientServiceModel getClientServiceById(long clientServiceId);
    ClientServiceModel addClientService(ClientServiceModel clientServiceModel);
    ClientServiceModel updateClientService(ClientServiceModel clientServiceModel);
    void deleteClientService(long clientServiceId);
}
