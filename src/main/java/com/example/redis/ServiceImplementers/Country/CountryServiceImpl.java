package com.example.redis.ServiceImplementers.Country;

import com.example.redis.Models.CountryModel.Country;
import com.example.redis.Repositories.CountryRepository.CountryRepository;
import com.example.redis.Services.CountryService.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("CountryService")
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Override
    @Cacheable(value = "countryCache")
    public Country getCountryById(long countryId) {
        System.out.println("--- Inside getCountryById() ---");
        return countryRepository.findById(countryId).get();
    }

    @Override
    @Cacheable(value = "allCountriesCache")
    public List<Country> getAllCountries() {
        System.out.println("--- Inside getAllCountries() ---");
        List<Country> list = new ArrayList<>();
        countryRepository.findAll().forEach(e -> list.add(e));
        return list;
    }

    @Override
    @Caching(put = {@CachePut(value = "countryCache", key = "#country.countryId")},
            evict = {@CacheEvict(value = "allCountriesCache", allEntries = true)})
    public Country addCountry(Country country) {
        System.out.println("--- Inside addCountry() ---");
        return countryRepository.save(country);
    }

    @Override
    @Caching(put = {@CachePut(value = "countryCache", key = "#country.countryId")},
            evict = {@CacheEvict(value = "allCountriesCache", allEntries = true)})
    public Country updateCountry(Country country) {
        System.out.println("--- Inside updateCountry() ---");
        return countryRepository.save(country);
    }

    @Override
    @Caching(evict = { @CacheEvict(value = "countryCache", key = "#countryId"),
            @CacheEvict(value = "allCountriesCache", allEntries = true)})
    public void deleteCountry(long countryId) {
        System.out.println("--- Inside deleteCountry() ---");
        countryRepository.delete(countryRepository.findById(countryId).get());
    }
}
