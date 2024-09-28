package com.college.directory.service;

import com.college.directory.exception.ResourceNotFoundException;
import com.college.directory.model.Course;
import com.college.directory.repository.CourseRepository;
import com.college.directory.repository.DepartmentRepository;
import com.college.directory.repository.FacultyProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private FacultyProfileRepository facultyProfileRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Course createCourse(Course course) {
        if (course.getFaculty() != null && !facultyProfileRepository.existsById(course.getFaculty().getUserId())) {
            throw new ResourceNotFoundException("Faculty not found with id: " + course.getFaculty().getUserId());
        }
        if (course.getDepartment() != null && !departmentRepository.existsById(course.getDepartment().getId())) {
            throw new ResourceNotFoundException("Department not found with id: " + course.getDepartment().getId());
        }
        return courseRepository.save(course);
    }

    @Override
    public Course getCourseById(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        return course.orElseThrow(() -> new ResourceNotFoundException("Course not found for id: " + id));
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public List<Course> getCoursesByDepartmentId(Long departmentId) {
        return courseRepository.findByDepartmentId(departmentId);
    }

    @Override
    public Course updateCourse(Long id, Course courseDetails) {
        Course course = getCourseById(id);
        course.setTitle(courseDetails.getTitle());
        course.setDescription(courseDetails.getDescription());

        if (courseDetails.getFaculty() != null) {
            course.setFaculty(courseDetails.getFaculty());
        }

        if (courseDetails.getDepartment() != null) {
            course.setDepartment(courseDetails.getDepartment());
        }

        return courseRepository.save(course);
    }

    @Override
    public void deleteCourse(Long id) {
        Course course = getCourseById(id);
        courseRepository.delete(course);
    }
}
