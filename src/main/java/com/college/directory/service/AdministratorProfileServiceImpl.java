package com.college.directory.service;

import com.college.directory.exception.ResourceNotFoundException;
import com.college.directory.model.AdministratorProfile;
import com.college.directory.repository.AdministratorProfileRepository;
import com.college.directory.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministratorProfileServiceImpl implements AdministratorProfileService {

    @Autowired
    private AdministratorProfileRepository administratorProfileRepository;

    @Autowired
    private UserRepository userRepository; // To validate user existence

    @Override
    public AdministratorProfile createAdministratorProfile(AdministratorProfile administratorProfile) {
        if (!userRepository.existsById(administratorProfile.getUserId())) {
            throw new ResourceNotFoundException("User not found with id: " + administratorProfile.getUserId());
        }
        return administratorProfileRepository.save(administratorProfile);
    }

    @Override
    public AdministratorProfile getAdministratorProfileByUserId(Long userId) {
        Optional<AdministratorProfile> administratorProfile = administratorProfileRepository.findById(userId);
        return administratorProfile.orElseThrow(() -> new ResourceNotFoundException("AdministratorProfile not found for user id: " + userId));
    }

    @Override
    public List<AdministratorProfile> getAllAdministratorProfiles() {
        return administratorProfileRepository.findAll();
    }

    @Override
    public AdministratorProfile updateAdministratorProfile(Long userId, AdministratorProfile administratorProfileDetails) {
        AdministratorProfile administratorProfile = getAdministratorProfileByUserId(userId); // Fetch the existing profile
        administratorProfile.setPhoto(administratorProfileDetails.getPhoto());
        administratorProfile.setDepartment(administratorProfileDetails.getDepartment()); // Ensure to set the Department entity
        return administratorProfileRepository.save(administratorProfile);
    }

    @Override
    public void deleteAdministratorProfile(Long userId) {
        AdministratorProfile administratorProfile = getAdministratorProfileByUserId(userId); // Check if profile exists
        administratorProfileRepository.delete(administratorProfile);
    }
}


