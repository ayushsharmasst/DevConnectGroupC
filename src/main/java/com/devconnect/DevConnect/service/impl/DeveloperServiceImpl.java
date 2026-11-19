package com.devconnect.DevConnect.service.impl;

import com.devconnect.DevConnect.model.Developer;
import com.devconnect.DevConnect.service.DeveloperService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class DeveloperServiceImpl implements DeveloperService {

    // In Memory Storage
    // ConcurrentHashMap - Thread safe hashmap - safe for concurrent requests.
    private final Map<Long, Developer> developerStore = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    @Override
    public List<Developer> getAllDevelopers() {
        return new ArrayList<>(developerStore.values());
    }

    @Override
    public Developer getDeveloperById(Long id) {
        if(!developerStore.containsKey(id)) {
            return new Developer();
        }

        return developerStore.get(id);
    }

    @Override
    public List<Developer> getDeveloperBySkill(String skill) {
        List<Developer> developerWithSkill = new ArrayList<>();

        for(Developer developer : developerStore.values()) {
            if (developer.getEmail().contains(skill)) {
                developerWithSkill.add(developer);
            }
        }

        return developerWithSkill;
    }

    @Override
    public Developer createDeveloper(Developer developer) {
        // Check if the email or phoneNumber already exists
        for(Developer existingDeveloper : developerStore.values()) {
            if(existingDeveloper.getPhoneNumber().equals(developer.getPhoneNumber()) ||
            existingDeveloper.getEmail().equals(developer.getEmail())) {
                return developer;
            }
        }

        //Assign ID
        developer.setId(idCounter.getAndIncrement());

        // Assign Sj=kills
        if(developer.getId() > 3) {
            developer.addSkill("React");
            developer.addSkill("JS");
            developer.addSkill("Numpy");
        }
        developer.addSkill("Python");
        developer.addSkill("C++");
        developer.addSkill("TensorFlow");

        // Add the developer to the Store
        developerStore.put(developer.getId(), developer);

        return developer;
    }

    @Override
    public Developer replaceDeveloper(Long id, Developer developer) {
        if(!developerStore.containsKey(id)) {
            return null;
        }
        developerStore.remove(id);
        developerStore.put(id, developer);

        return developer;
    }

    @Override
    public Developer updateDeveloper(Long id, Map<String, Object> updates) {
        if(!developerStore.containsKey(id)) {
            return new Developer();
        }


    }

    public Developer updateDeveloperName(Long id, String name) {

    }

    public Developer updateDeveloperEmail(Long id, String email) {

    }

}
