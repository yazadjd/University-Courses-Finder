/*

Author: Yazad Jamshed Davur <yazadjd@yahoo.com>

LocationService.java defines the body of the functions declared in the
corresponding Location Service Interface, i.e getCountries() and getCities().

The getCountries() method finds all the distinct countries present in the
database and returns a set of Strings object which is used in turn by the
Controller to populate the UI with a dropdown of countries.

The getCities() method takes as input a country name and finds all the distinct
cities present in the database for that country and returns a set of Strings
object which is used in turn by the Controller to populate the UI with a
dropdown of cities.

*/

package com.integradev.unicoursefinder.service.impl;

import com.integradev.unicoursefinder.repository.LocationRepository;
import com.integradev.unicoursefinder.service.ILocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.Set;

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
