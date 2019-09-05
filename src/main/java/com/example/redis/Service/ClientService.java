package com.example.redis.Service;

import com.example.redis.Model.Client;

import java.util.List;

public interface ClientService {
    List<Client> getAllClients();
    Client getClientById(long clientId);
    Client addClient(Client client);
    Client updateClient(Client client);
    void deleteClient(long clientId);
}
