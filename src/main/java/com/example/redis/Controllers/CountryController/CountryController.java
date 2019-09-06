package com.example.redis.Controllers.CountryController;

import com.example.redis.Models.CountryModel.Country;
import com.example.redis.Services.CountryService.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RequestMapping("api")
@RestController("CountryController")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping("/country/{countryId}")
    @ResponseBody
    public ResponseEntity<Country> getCountryById (@PathVariable Long countryId){
        Country country = countryService.getCountryById(countryId);
        return new ResponseEntity<>(country, HttpStatus.OK);
    }

    @GetMapping("/countries")
    public ResponseEntity<List<Country>> getAllCountries() {
        List<Country> list = countryService.getAllCountries();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/country")
    public ResponseEntity<Void> addCountry(@RequestBody Country country, UriComponentsBuilder builder) {
        Country savedCountry = countryService.addCountry(country);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/country/{id}").buildAndExpand(savedCountry.getCountryId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/country/{id}")
    public ResponseEntity<Country> updateCountry(@RequestBody Country country) {
        countryService.updateCountry(country);
        return new ResponseEntity<>(country, HttpStatus.OK);
    }

    @DeleteMapping("/country/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable("id") Long countryId) {
        countryService.deleteCountry(countryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
