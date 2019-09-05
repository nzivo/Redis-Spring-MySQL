package com.example.redis.Service.ServiceImpl;

import com.example.redis.Model.Client;
import com.example.redis.Repository.ClientRepository;
import com.example.redis.Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@CacheConfig(cacheNames = "allClientsCache")
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    @Cacheable(value = "clientCache", key = "{#clientId}")
    public Client getClientById(long clientId) {
        System.out.println("--- Inside getClientById() ---");
        return clientRepository.findById(clientId).get();
    }


    @Override
    @Cacheable(value = "allClientsCache")
    public List<Client> getAllClients() {
        System.out.println("--- Inside getAllClients() ---");
        List<Client> list = new ArrayList<>();
        clientRepository.findAll().forEach(e -> list.add(e));
        return list;
    }

    @Override
    @Caching(put = {@CachePut(value = "clientCache", key = "#client.clientId")},
            evict = {@CacheEvict(value = "allClientsCache", allEntries = true)})
    public Client addClient(Client client) {
        System.out.println("--- Inside addClient() ---");
        return clientRepository.save(client);
    }

    @Override
    @Caching(put = {@CachePut(value = "clientCache", key = "#client.clientId")},
            evict = {@CacheEvict(value = "allClientsCache", allEntries = true)})
    public Client updateClient(Client client) {
        System.out.println("--- Inside updateClient() ---");
        return clientRepository.save(client);
    }

    @Override
    @Caching(evict = { @CacheEvict(value = "clientCache", key = "#clientId"),
            @CacheEvict(value = "allClientsCache", allEntries = true)})
    public void deleteClient(long clientId) {
        System.out.println("--- Inside deleteClient() ---");
        clientRepository.delete(clientRepository.findById(clientId).get());
    }
}
