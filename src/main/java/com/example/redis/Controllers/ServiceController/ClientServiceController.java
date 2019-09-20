package com.example.redis.Controllers.ServiceController;

import com.example.redis.Models.ServiceModel.ClientServiceModel;
import com.example.redis.Services.ServicesService.ClientServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RequestMapping("api")
@RestController("ClientServiceController")
public class ClientServiceController {

    @Autowired
    private ClientServicesService clientServicesService;

    @GetMapping("/clientService/{clientServiceId}")
    @ResponseBody
    public ResponseEntity<ClientServiceModel> getClientServiceById (@PathVariable Long clientServiceId){
        ClientServiceModel clientServiceModel = clientServicesService.getClientServiceById(clientServiceId);
        return new ResponseEntity<>(clientServiceModel, HttpStatus.OK);
    }

    @GetMapping("/clientServices")
    public ResponseEntity<List<ClientServiceModel>> getAllCountries() {
        List<ClientServiceModel> list = clientServicesService.getAllClientServices();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/clientService")
    public ResponseEntity<Void> addClientService(@RequestBody ClientServiceModel clientServiceModel, UriComponentsBuilder builder) {
        ClientServiceModel savedClientService = clientServicesService.addClientService(clientServiceModel);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/clientService/{id}").buildAndExpand(savedClientService.getClientServiceId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/clientService/{id}")
    public ResponseEntity<ClientServiceModel> updateClientService(@RequestBody ClientServiceModel clientServiceModel) {
        clientServicesService.updateClientService(clientServiceModel);
        return new ResponseEntity<>(clientServiceModel, HttpStatus.OK);
    }

    @DeleteMapping("/clientService/{id}")
    public ResponseEntity<Void> deleteClientService(@PathVariable("id") Long clientServiceId) {
        clientServicesService.deleteClientService(clientServiceId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
