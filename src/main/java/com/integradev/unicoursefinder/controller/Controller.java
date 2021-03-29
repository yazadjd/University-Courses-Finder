/*

Author: Yazad Jamshed Davur <yazadjd@yahoo.com>

Controller.java is a SpringBoot REST Controller which accepts 'GET' requests from
mapped endpoints and returns back the corresponding responses to the UI.

The end points are defined as follows:

/country: To populate the UI with a set of countries for the user to choose from.
/city: To populate the UI with a set of cities depending on the chosen country.
/hobby: To populate the UI with a set of hobbies.
/submit: Accepts the users preferences and returns a Set of Courses back to the UI.

*/

package com.integradev.unicoursefinder.controller;

import com.integradev.unicoursefinder.dto.CourseDTO;
import com.integradev.unicoursefinder.service.ICourseService;
import com.integradev.unicoursefinder.service.IHobbyService;
import com.integradev.unicoursefinder.service.ILocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class Controller {
    @Autowired
    ILocationService locationService;
    @Autowired
    IHobbyService hobbyService;
    @Autowired
    ICourseService courseService;


    @GetMapping(value = "/country")
    public ResponseEntity<Set<String>> getCountry() {
        Set<String> countries = new HashSet<String>();
        countries = locationService.getCountries();
        return new ResponseEntity<Set<String>>(countries, HttpStatus.OK);
    }

    @GetMapping(value = "/city")
    public ResponseEntity<Set<String>> getCity(@RequestParam(value = "chosenCountry") String chosenCountry) {
        Set<String> cities = new HashSet<String>();
        cities = locationService.getCities(chosenCountry);
        return new ResponseEntity<Set<String>>(cities, HttpStatus.OK);
    }

    @GetMapping(value = "/hobby")
    ResponseEntity<Set<String>> getHobby() {
        Set<String> hobbies = new HashSet<String>();
        hobbies = hobbyService.getHobbies();
        return new ResponseEntity<Set<String>>(hobbies, HttpStatus.OK);
    }

    @GetMapping(value="/submit")
    ResponseEntity<Set<CourseDTO>> getCourses(@RequestParam(value = "countrySelected") String countrySelected,
                                              @RequestParam(value = "citySelected") String citySelected,
                                              @RequestParam(value = "hobbiesSelected", required = false) Set<String> hobbiesSelected) {
        Set<CourseDTO> courses = new HashSet<CourseDTO>();
        courses = courseService.getCourses(countrySelected, citySelected, hobbiesSelected);
        return new ResponseEntity<Set<CourseDTO>>(courses, HttpStatus.OK);
    }
}

