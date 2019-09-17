package com.example.redis.Models.CountryModel;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "Country")
@Data
@Table(name = "country")
public class Country implements Serializable {
    private static final long serialVersionUID = -1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long countryId;

    @Column(name = "country")
    private String country;

    @Column(name = "country_code")
    private int country_code;

    @Column(name = "abbreviation")
    private String abbreviation;

    @Column(name = "prefix")
    private String prefix;

    public Country() {
    }

    public Country(long countryId, String country, int country_code, String abbreviation, String prefix) {
        this.countryId = countryId;
        this.country = country;
        this.country_code = country_code;
        this.abbreviation = abbreviation;
        this.prefix = prefix;
    }

    public long getCountryId() {
        return countryId;
    }

    public void setCountryId(long countryId) {
        this.countryId = countryId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getCountry_code() {
        return country_code;
    }

    public void setCountry_code(int country_code) {
        this.country_code = country_code;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
