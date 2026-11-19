package com.devconnect.DevConnect.service;

import com.devconnect.DevConnect.model.Developer;

import java.util.List;

public interface DeveloperService {

    /*
    Retrieves all the developer profiles
     */
    List<Developer> getAllDevelopers();

    /*
    Retrieves a developer profile by ID
     */
    Developer getDeveloperById(Long id);

    /*
    Search developers by skill(Case-Sensitive)
     */
    List<Developer> getDeveloperBySkill(String skill);

    /*
    Create a new Developer Profile
     */
    Developer createDeveloper(Developer developer);

    /*
    Update the complete Developer Profile
     */
    Developer replaceDeveloper(Long id, Developer developer);



}
