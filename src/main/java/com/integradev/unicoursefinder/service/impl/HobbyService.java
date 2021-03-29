/*

Author: Yazad Jamshed Davur <yazadjd@yahoo.com>

HobbyService.java defines the body of the functions declared in the
corresponding Hobby Service Interface, i.e getHobbies().

The getHobbies() method finds all the distinct hobbies present in the
database and returns a set of Strings object which is used in turn by the
Controller to populate the UI with checkboxes of the different hobbies/interests.

*/

package com.integradev.unicoursefinder.service.impl;

import com.integradev.unicoursefinder.repository.HobbiesRepository;
import com.integradev.unicoursefinder.service.IHobbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
public class HobbyService implements IHobbyService {
    @Autowired
    HobbiesRepository hobbiesRepository;

    @Override
    @Transactional
    public Set<String> getHobbies() {
        Set<String> hobbies = hobbiesRepository.findAllHobbies();
        return hobbies;
    }
}
