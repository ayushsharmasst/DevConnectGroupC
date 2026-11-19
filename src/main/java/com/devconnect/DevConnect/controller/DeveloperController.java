package com.devconnect.DevConnect.controller;

import com.devconnect.DevConnect.model.Developer;
import com.devconnect.DevConnect.service.DeveloperService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/v1/developers")
public class DeveloperController {

    private final DeveloperService developerService;

    DeveloperController(DeveloperService developerService) {
        this.developerService = developerService;
    }


    @GetMapping
    public List<Developer> getAllDevelopers() {

        return ResponseEntity.ok(developerService.getAllDevelopers()).getBody();
    }

    // Path Variable
    @GetMapping("/{id}")
    public ResponseEntity<Developer> getDeveloperByID(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(developerService.getDeveloperById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(developerService.getDeveloperById(id));
        }
    }

    @GetMapping("/search")
    public List<Developer> getDevelopersBySkill(
            @RequestParam String skill,
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") int cpunt
    ) {

        return ResponseEntity.ok().body(developerService.getDeveloperBySkill(skill)).getBody();
    }

    @PostMapping

    public ResponseEntity<Developer> createDeveloper(@RequestBody Developer developer) {

        return ResponseEntity.status(HttpStatus.CREATED).
                body(developerService.createDeveloper(developer));

//
    }

    @PutMapping("/{id}")
    public ResponseEntity<Developer> replaceDeveloper(@PathVariable Long id, @RequestBody Developer developer) {

        return ResponseEntity.ok(developerService.replaceDeveloper(id, developer));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Developer> updateDeveloper(@PathVariable Long id,
                                                     @RequestBody Map<String, Object> updates) {
        return ResponseEntity.ok(developerService.updateDeveloper(id, updates));
    }

}
