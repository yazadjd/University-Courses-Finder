package com.integradev.unicoursefinder.service.impl;

import com.integradev.unicoursefinder.entity.Location;
import com.integradev.unicoursefinder.repository.LocationRepository;
import com.integradev.unicoursefinder.service.ILocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LocationService implements ILocationService {
    @Autowired
    LocationRepository locationRepository;


    @Override
    @Transactional
    public Set<String> getCountries() {
        Set<String> allCountries = locationRepository.findDistinctCountries();
        if (!CollectionUtils.isEmpty(allCountries)) {
            allCountries.add("All Countries");
        }
        return allCountries;
    }

    @Override
    @Transactional
    public Set<String> getCities(String chosenCountry) {
        if (!chosenCountry.equals("All Countries")) {
            Set<String> allCities = locationRepository.findDistinctCitiesGivenCountry(chosenCountry);
            if (!CollectionUtils.isEmpty(allCities)) {
                allCities.add("All Cities");
            }
            return allCities;
        }
        Set<String> allCities = locationRepository.findDistinctCitiesGivenAllCountry();
        if (!CollectionUtils.isEmpty(allCities)) {
            allCities.add("All Cities");
        }
        return allCities;
    }

}
