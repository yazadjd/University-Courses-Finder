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
