package com.college.directory.controller;

import com.college.directory.model.FacultyProfile;
import com.college.directory.service.FacultyProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facultyProfiles")
public class FacultyProfileController {

    @Autowired
    private FacultyProfileService facultyProfileService;

    @PostMapping
    public ResponseEntity<FacultyProfile> createFacultyProfile(@RequestBody FacultyProfile facultyProfile) {
        FacultyProfile createdProfile = facultyProfileService.createFacultyProfile(facultyProfile);
        return new ResponseEntity<>(createdProfile, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<FacultyProfile> getFacultyProfileByUserId(@PathVariable Long userId) {
        FacultyProfile facultyProfile = facultyProfileService.getFacultyProfileByUserId(userId);
        return new ResponseEntity<>(facultyProfile, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<FacultyProfile>> getAllFacultyProfiles() {
        List<FacultyProfile> facultyProfiles = facultyProfileService.getAllFacultyProfiles();
        return new ResponseEntity<>(facultyProfiles, HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<FacultyProfile> updateFacultyProfile(@PathVariable Long userId, @RequestBody FacultyProfile facultyProfile) {
        FacultyProfile updatedProfile = facultyProfileService.updateFacultyProfile(userId, facultyProfile);
        return new ResponseEntity<>(updatedProfile, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteFacultyProfile(@PathVariable Long userId) {
        facultyProfileService.deleteFacultyProfile(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

