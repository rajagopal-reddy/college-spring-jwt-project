package com.college.directory.service;

import com.college.directory.exception.ResourceNotFoundException;
import com.college.directory.model.FacultyProfile;
import com.college.directory.repository.FacultyProfileRepository;
import com.college.directory.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacultyProfileServiceImpl implements FacultyProfileService {

    @Autowired
    private FacultyProfileRepository facultyProfileRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public FacultyProfile createFacultyProfile(FacultyProfile facultyProfile) {
        if (!userRepository.existsById(facultyProfile.getUserId())) {
            throw new ResourceNotFoundException("User not found with id: " + facultyProfile.getUserId());
        }
        return facultyProfileRepository.save(facultyProfile);
    }

    @Override
    public FacultyProfile getFacultyProfileByUserId(Long userId) {
        Optional<FacultyProfile> facultyProfile = facultyProfileRepository.findById(userId);
        return facultyProfile.orElseThrow(() -> new ResourceNotFoundException("FacultyProfile not found for user id: " + userId));
    }

    @Override
    public List<FacultyProfile> getAllFacultyProfiles() {
        return facultyProfileRepository.findAll();
    }

    @Override
    public FacultyProfile updateFacultyProfile(Long userId, FacultyProfile facultyProfileDetails) {
        FacultyProfile facultyProfile = getFacultyProfileByUserId(userId);
        facultyProfile.setPhoto(facultyProfileDetails.getPhoto());
        facultyProfile.setDepartment(facultyProfileDetails.getDepartment());
        facultyProfile.setOfficeHours(facultyProfileDetails.getOfficeHours());
        return facultyProfileRepository.save(facultyProfile);
    }

    @Override
    public void deleteFacultyProfile(Long userId) {
        FacultyProfile facultyProfile = getFacultyProfileByUserId(userId);
        facultyProfileRepository.delete(facultyProfile);
    }
}

