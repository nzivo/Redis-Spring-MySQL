package com.example.redis.Controllers.ClientController;

import com.example.redis.Models.ClientModel.Client;
import com.example.redis.Models.ServiceModel.ClientServiceModel;
import com.example.redis.Models.ServiceModel.Service;
import com.example.redis.Repositories.ServiceRepository.ClientServiceRepository;
import com.example.redis.Repositories.ServiceRepository.ServiceRepository;
import com.example.redis.ServiceImplementers.Client.ClientServiceImpl;
import com.example.redis.Services.ClientService.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("api")
@RestController("ClientController")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientServiceRepository clientServiceRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @GetMapping("/client/{clientId}")
    @ResponseBody
    public ResponseEntity<Client> getClientById (@PathVariable Long clientId){
        Client client = clientService.getClientById(clientId);
        List<ClientServiceModel> list = clientServiceRepository.findByClientId(client.getClientId());
        List<String> services = new ArrayList<>();
        for(ClientServiceModel model : list){
           Service service = serviceRepository.findByServiceId(model.getServiceId());
            services.add(service.getServiceName());
        }
        client.setServices(services);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @GetMapping("/clients")
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> list = clientService.getAllClients();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/client")
    public ResponseEntity<Void> addClient(@RequestBody Client client, UriComponentsBuilder builder) {
        Client savedClient = clientService.addClient(client);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/client/{id}").buildAndExpand(savedClient.getClientId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/client/{id}")
    public ResponseEntity<Client> updateClient(@RequestBody Client client) {
        clientService.updateClient(client);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @DeleteMapping("/client/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable("id") Long id) {
        clientService.deleteClient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
