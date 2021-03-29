/*

Author: Yazad Jamshed Davur <yazadjd@yahoo.com>

Purpose: Interface for Location Service.

Defines the function declarations that are implemented in Location Service.

*/

package com.integradev.unicoursefinder.service;

import java.util.Set;


public interface ILocationService {
  Set<String> getCountries();

  Set<String> getCities(String chosenCountry);
}
