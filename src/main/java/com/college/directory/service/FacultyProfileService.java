package com.college.directory.service;

import com.college.directory.model.FacultyProfile;

import java.util.List;

public interface FacultyProfileService {
    FacultyProfile createFacultyProfile(FacultyProfile facultyProfile);
    FacultyProfile getFacultyProfileByUserId(Long userId);
    List<FacultyProfile> getAllFacultyProfiles();
    FacultyProfile updateFacultyProfile(Long userId, FacultyProfile facultyProfile);
    void deleteFacultyProfile(Long userId);
}

