package com.college.directory.service;

import com.college.directory.model.AdministratorProfile;

import java.util.List;

public interface AdministratorProfileService {
    AdministratorProfile createAdministratorProfile(AdministratorProfile administratorProfile);
    AdministratorProfile getAdministratorProfileByUserId(Long userId);
    List<AdministratorProfile> getAllAdministratorProfiles();
    AdministratorProfile updateAdministratorProfile(Long userId, AdministratorProfile administratorProfile);
    void deleteAdministratorProfile(Long userId);
}

