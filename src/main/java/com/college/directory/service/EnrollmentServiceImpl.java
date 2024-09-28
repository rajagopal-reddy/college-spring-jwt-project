package com.college.directory.service;

import com.college.directory.exception.ResourceNotFoundException;
import com.college.directory.model.Enrollment;
import com.college.directory.repository.CourseRepository;
import com.college.directory.repository.EnrollmentRepository;
import com.college.directory.repository.StudentProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private StudentProfileRepository studentProfileRepository; // To validate student existence

    @Autowired
    private CourseRepository courseRepository; // To validate course existence

    @Override
    public Enrollment createEnrollment(Enrollment enrollment) {
        // Ensure the student and course exist before creating an enrollment
        if (!studentProfileRepository.existsById(enrollment.getStudent().getId())) {
            throw new ResourceNotFoundException("Student not found with id: " + enrollment.getStudent().getId());
        }
        if (!courseRepository.existsById(enrollment.getCourse().getId())) {
            throw new ResourceNotFoundException("Course not found with id: " + enrollment.getCourse().getId());
        }
        return enrollmentRepository.save(enrollment);
    }

    @Override
    public Enrollment getEnrollmentById(Long id) {
        Optional<Enrollment> enrollment = enrollmentRepository.findById(id);
        return enrollment.orElseThrow(() -> new ResourceNotFoundException("Enrollment not found for id: " + id));
    }

    @Override
    public List<Enrollment> getEnrollmentsByStudentId(Long studentId) {
        return enrollmentRepository.findByStudentId(studentId);
    }

    @Override
    public List<Enrollment> getEnrollmentsByCourseId(Long courseId) {
        return enrollmentRepository.findByCourseId(courseId);
    }

    @Override
    public void deleteEnrollment(Long id) {
        Enrollment enrollment = getEnrollmentById(id); // Check if enrollment exists
        enrollmentRepository.delete(enrollment);
    }
}

