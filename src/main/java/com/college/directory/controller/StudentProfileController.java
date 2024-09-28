package com.college.directory.controller;

import com.college.directory.model.StudentProfile;
import com.college.directory.service.StudentProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/studentProfiles")
public class StudentProfileController {

    @Autowired
    private StudentProfileService studentProfileService;

    @PostMapping
    public ResponseEntity<StudentProfile> createStudentProfile(@RequestBody StudentProfile studentProfile) {
        StudentProfile createdProfile = studentProfileService.createStudentProfile(studentProfile);
        return new ResponseEntity<>(createdProfile, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<StudentProfile> getStudentProfileByUserId(@PathVariable Long userId) {
        StudentProfile studentProfile = studentProfileService.getStudentProfileByUserId(userId);
        return new ResponseEntity<>(studentProfile, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<StudentProfile>> getAllStudentProfiles() {
        List<StudentProfile> studentProfiles = studentProfileService.getAllStudentProfiles();
        return new ResponseEntity<>(studentProfiles, HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<StudentProfile> updateStudentProfile(@PathVariable Long userId, @RequestBody StudentProfile studentProfile) {
        StudentProfile updatedProfile = studentProfileService.updateStudentProfile(userId, studentProfile);
        return new ResponseEntity<>(updatedProfile, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteStudentProfile(@PathVariable Long userId) {
        studentProfileService.deleteStudentProfile(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

