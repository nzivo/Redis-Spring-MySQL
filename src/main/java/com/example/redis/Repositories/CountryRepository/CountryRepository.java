package com.example.redis.Repositories.CountryRepository;

import com.example.redis.Models.CountryModel.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("CountryRepository")
public interface CountryRepository extends CrudRepository<Country, Long> {
}
