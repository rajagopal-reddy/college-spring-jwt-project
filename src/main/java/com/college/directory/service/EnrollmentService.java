package com.college.directory.service;

import com.college.directory.model.Enrollment;

import java.util.List;

public interface EnrollmentService {
    Enrollment createEnrollment(Enrollment enrollment);
    Enrollment getEnrollmentById(Long id);
    List<Enrollment> getEnrollmentsByStudentId(Long studentId);
    List<Enrollment> getEnrollmentsByCourseId(Long courseId);
    void deleteEnrollment(Long id);
}

