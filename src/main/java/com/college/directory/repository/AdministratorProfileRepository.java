package com.college.directory.repository;

import com.college.directory.model.AdministratorProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministratorProfileRepository extends JpaRepository<AdministratorProfile, Long> {
}
