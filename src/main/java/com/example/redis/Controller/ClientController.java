package com.example.redis.Controller;

import com.example.redis.Model.Client;
import com.example.redis.Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RequestMapping("api")
@Controller
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping("/client/{clientId}")
    @ResponseBody
    public ResponseEntity<Client> getClientById (@PathVariable Long clientId){
        Client client = clientService.getClientById(clientId);
        return new ResponseEntity<Client>(client, HttpStatus.OK);
    }

    @GetMapping("/clients")
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> list = clientService.getAllClients();
        return new ResponseEntity<List<Client>>(list, HttpStatus.OK);
    }

    @PostMapping("/client")
    public ResponseEntity<Void> addClient(@RequestBody Client client, UriComponentsBuilder builder) {
        Client savedClient = clientService.addClient(client);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/client/{id}").buildAndExpand(savedClient.getClientId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/client/{id}")
    public ResponseEntity<Client> updateClient(@RequestBody Client client) {
        clientService.updateClient(client);
        return new ResponseEntity<Client>(client, HttpStatus.OK);
    }

    @DeleteMapping("/client/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable("id") Long id) {
        clientService.deleteClient(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
