/*

Author: Yazad Jamshed Davur <yazadjd@yahoo.com>

LocationRepository.java defines an interface that inherits from JPARepository.
It declares the query methods that will be used to retrieve relational data
from the relational DB (Location Table) and store them into corresponding objects.

*/

package com.integradev.unicoursefinder.repository;

import com.integradev.unicoursefinder.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
    @Query(value =  "select distinct country from location", nativeQuery = true)
    Set<String> findDistinctCountries();

    @Query(value = "select distinct city from location where country= :chosenCountry", nativeQuery = true)
    Set<String> findDistinctCitiesGivenCountry(@Param("chosenCountry") String chosenCountry);

    @Query(value = "select distinct city from location", nativeQuery = true)
    Set<String> findDistinctCitiesGivenAllCountry();

    Set<Location> findByCountryAndCity(String country, String city);
    Set<Location> findByCountry(String country);
}
