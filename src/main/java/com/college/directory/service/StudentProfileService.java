package com.college.directory.service;

import com.college.directory.model.StudentProfile;

import java.util.List;

public interface StudentProfileService {
    StudentProfile createStudentProfile(StudentProfile studentProfile);
    StudentProfile getStudentProfileByUserId(Long userId);
    List<StudentProfile> getAllStudentProfiles();
    StudentProfile updateStudentProfile(Long userId, StudentProfile studentProfile);
    void deleteStudentProfile(Long userId);
}
