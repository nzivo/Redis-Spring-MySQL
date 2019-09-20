package com.example.redis.Services.ServicesService;

import com.example.redis.Models.ServiceModel.Service;

import java.util.List;

public interface ServicesService {
    List<Service> getAllServices();
    Service getServiceById(long serviceId);
    Service addService(Service service);
    Service updateService(Service service);
    void deleteService(long serviceId);
}
