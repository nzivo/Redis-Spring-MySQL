package com.example.redis.ServiceImplementers.Service;

import com.example.redis.Models.ServiceModel.ClientServiceModel;
import com.example.redis.Repositories.ServiceRepository.ClientServiceRepository;
import com.example.redis.Services.ServicesService.ClientServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("ClientServiceServicesModel")
@CacheConfig(cacheNames = "allClientServicesCache")
public class ClientServiceModelImpl implements ClientServicesService {

    @Autowired
    private ClientServiceRepository clientServiceRepository;

    @Override
    @Cacheable(value = "clientServiceCache", key = "{#clientServiceId}")
    public ClientServiceModel getClientServiceById(long clientServiceId) {
        System.out.println("--- Inside getClientServiceById() ---");
        return clientServiceRepository.findById(clientServiceId).get();
    }


    @Override
    @Cacheable(value = "allClientServicesCache")
    public List<ClientServiceModel> getAllClientServices() {
        System.out.println("--- Inside getAllClientServices() ---");
        List<ClientServiceModel> list = new ArrayList<>();
        clientServiceRepository.findAll().forEach(e -> list.add(e));
        return list;
    }

    @Override
    @Caching(put = {@CachePut(value = "clientServiceCache", key = "#clientServiceModel.clientServiceId")},
            evict = {@CacheEvict(value = "allClientServicesCache", allEntries = true)})
    public ClientServiceModel addClientService(ClientServiceModel clientServiceModel) {
        System.out.println("--- Inside addClientService() ---");
        return clientServiceRepository.save(clientServiceModel);
    }

    @Override
    @Caching(put = {@CachePut(value = "clientServiceCache", key = "#clientServiceModel.clientServiceId")},
            evict = {@CacheEvict(value = "allClientServicesCache", allEntries = true)})
    public ClientServiceModel updateClientService(ClientServiceModel clientServiceModel) {
        System.out.println("--- Inside updateClientService() ---");
        return clientServiceRepository.save(clientServiceModel);
    }

    @Override
    @Caching(evict = { @CacheEvict(value = "clientServiceCache", key = "#clientServiceId"),
            @CacheEvict(value = "allClientServicesCache", allEntries = true)})
    public void deleteClientService(long clientServiceId) {
        System.out.println("--- Inside deleteClientService() ---");
        clientServiceRepository.delete(clientServiceRepository.findById(clientServiceId).get());
    }
}
