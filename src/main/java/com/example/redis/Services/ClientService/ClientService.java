package com.example.redis.Services.ClientService;

import com.example.redis.Models.ClientModel.Client;

import java.util.List;

public interface ClientService {
    List<Client> getAllClients();
    Client getClientById(long clientId);
    Client addClient(Client client);
    Client updateClient(Client client);
    void deleteClient(long clientId);
}
