package com.college.directory.repository;

import com.college.directory.model.FacultyProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyProfileRepository extends JpaRepository<FacultyProfile, Long> {
}
