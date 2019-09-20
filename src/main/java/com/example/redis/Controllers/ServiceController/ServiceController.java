package com.example.redis.Controllers.ServiceController;

import com.example.redis.Models.ServiceModel.Service;
import com.example.redis.Services.ServicesService.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RequestMapping("api")
@RestController("ServiceController")
public class ServiceController {

    @Autowired
    private ServicesService servicesService;

    @GetMapping("/service/{serviceId}")
    @ResponseBody
    public ResponseEntity<Service> getServiceById (@PathVariable Long serviceId){
        Service service = servicesService.getServiceById(serviceId);
        return new ResponseEntity<>(service, HttpStatus.OK);
    }

    @GetMapping("/services")
    public ResponseEntity<List<Service>> getAllServices() {
        List<Service> list = servicesService.getAllServices();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/service")
    public ResponseEntity<Void> addService(@RequestBody Service service, UriComponentsBuilder builder) {
        Service savedService = servicesService.addService(service);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/service/{id}").buildAndExpand(savedService.getServiceId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/service/{id}")
    public ResponseEntity<Service> updateService(@RequestBody Service service) {
        servicesService.updateService(service);
        return new ResponseEntity<>(service, HttpStatus.OK);
    }

    @DeleteMapping("/service/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable("id") Long serviceId) {
        servicesService.deleteService(serviceId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
