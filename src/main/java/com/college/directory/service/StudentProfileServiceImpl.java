package com.college.directory.service;

import com.college.directory.exception.ResourceNotFoundException;
import com.college.directory.model.StudentProfile;
import com.college.directory.repository.StudentProfileRepository;
import com.college.directory.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentProfileServiceImpl implements StudentProfileService {

    @Autowired
    private StudentProfileRepository studentProfileRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public StudentProfile createStudentProfile(StudentProfile studentProfile) {
        if (!userRepository.existsById(studentProfile.getId())) {
            throw new ResourceNotFoundException("User not found with id: " + studentProfile.getId());
        }
        return studentProfileRepository.save(studentProfile);
    }

    @Override
    public StudentProfile getStudentProfileByUserId(Long userId) {
        Optional<StudentProfile> studentProfile = studentProfileRepository.findById(userId);
        return studentProfile.orElseThrow(() -> new ResourceNotFoundException("StudentProfile not found for user id: " + userId));
    }

    @Override
    public List<StudentProfile> getAllStudentProfiles() {
        return studentProfileRepository.findAll();
    }

    @Override
    public StudentProfile updateStudentProfile(Long userId, StudentProfile studentProfileDetails) {
        StudentProfile studentProfile = getStudentProfileByUserId(userId);
        studentProfile.setPhoto(studentProfileDetails.getPhoto());
        studentProfile.setDepartment(studentProfileDetails.getDepartment());
        studentProfile.setYear(studentProfileDetails.getYear());
        return studentProfileRepository.save(studentProfile);
    }

    @Override
    public void deleteStudentProfile(Long userId) {
        StudentProfile studentProfile = getStudentProfileByUserId(userId);
        studentProfileRepository.delete(studentProfile);
    }
}

