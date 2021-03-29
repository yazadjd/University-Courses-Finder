package com.integradev.unicoursefinder.service;

import org.springframework.stereotype.Service;

import java.util.Set;


public interface ILocationService {
    public Set<String> getCountries();
    public Set<String> getCities(String chosenCountry);
}
