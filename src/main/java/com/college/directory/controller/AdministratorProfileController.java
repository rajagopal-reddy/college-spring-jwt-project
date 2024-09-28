package com.college.directory.controller;

import com.college.directory.model.AdministratorProfile;
import com.college.directory.service.AdministratorProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/administratorProfiles")
public class AdministratorProfileController {

    @Autowired
    private AdministratorProfileService administratorProfileService;

    @PostMapping
    public ResponseEntity<AdministratorProfile> createAdministratorProfile(@RequestBody AdministratorProfile administratorProfile) {
        AdministratorProfile createdProfile = administratorProfileService.createAdministratorProfile(administratorProfile);
        return new ResponseEntity<>(createdProfile, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<AdministratorProfile> getAdministratorProfileByUserId(@PathVariable Long userId) {
        AdministratorProfile administratorProfile = administratorProfileService.getAdministratorProfileByUserId(userId);
        return new ResponseEntity<>(administratorProfile, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AdministratorProfile>> getAllAdministratorProfiles() {
        List<AdministratorProfile> administratorProfiles = administratorProfileService.getAllAdministratorProfiles();
        return new ResponseEntity<>(administratorProfiles, HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<AdministratorProfile> updateAdministratorProfile(@PathVariable Long userId, @RequestBody AdministratorProfile administratorProfile) {
        AdministratorProfile updatedProfile = administratorProfileService.updateAdministratorProfile(userId, administratorProfile);
        return new ResponseEntity<>(updatedProfile, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteAdministratorProfile(@PathVariable Long userId) {
        administratorProfileService.deleteAdministratorProfile(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

