package com.example.redis.Services.CountryService;

import com.example.redis.Models.CountryModel.Country;

import java.util.List;

public interface CountryService {
    List<Country> getAllCountries();
    Country getCountryById(long countryId);
    Country addCountry(Country country);
    Country updateCountry(Country country);
    void deleteCountry(long countryId);
}
