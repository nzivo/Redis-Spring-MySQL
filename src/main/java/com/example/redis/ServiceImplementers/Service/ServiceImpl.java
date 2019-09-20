package com.example.redis.ServiceImplementers.Service;

import com.example.redis.Repositories.ServiceRepository.ServiceRepository;
import com.example.redis.Services.ServicesService.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("ServiceModel")
@CacheConfig(cacheNames = "allServicesCache")
public class ServiceImpl implements ServicesService {

    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    @Cacheable(value = "serviceCache", key = "{#serviceId}")
    public com.example.redis.Models.ServiceModel.Service getServiceById(long serviceId) {
        System.out.println("--- Inside getServiceById() ---");
        return serviceRepository.findById(serviceId).get();
    }


    @Override
    @Cacheable(value = "allServicesCache")
    public List<com.example.redis.Models.ServiceModel.Service> getAllServices() {
        System.out.println("--- Inside getAllServices() ---");
        List<com.example.redis.Models.ServiceModel.Service> list = new ArrayList<>();
        serviceRepository.findAll().forEach(e -> list.add(e));
        return list;
    }

    @Override
    @Caching(put = {@CachePut(value = "serviceCache", key = "#service.serviceId")},
            evict = {@CacheEvict(value = "allServicesCache", allEntries = true)})
    public com.example.redis.Models.ServiceModel.Service addService(com.example.redis.Models.ServiceModel.Service service) {
        System.out.println("--- Inside addService() ---");
        return serviceRepository.save(service);
    }

    @Override
    @Caching(put = {@CachePut(value = "serviceCache", key = "#service.serviceId")},
            evict = {@CacheEvict(value = "allServicesCache", allEntries = true)})
    public com.example.redis.Models.ServiceModel.Service updateService(com.example.redis.Models.ServiceModel.Service service) {
        System.out.println("--- Inside updateService() ---");
        return serviceRepository.save(service);
    }

    @Override
    @Caching(evict = { @CacheEvict(value = "serviceCache", key = "#serviceId"),
            @CacheEvict(value = "allServicesCache", allEntries = true)})
    public void deleteService(long serviceId) {
        System.out.println("--- Inside deleteService() ---");
        serviceRepository.delete(serviceRepository.findById(serviceId).get());
    }
}
