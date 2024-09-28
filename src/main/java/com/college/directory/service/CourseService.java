package com.college.directory.service;

import com.college.directory.model.Course;

import java.util.List;

public interface CourseService {
    Course createCourse(Course course);
    Course getCourseById(Long id);
    List<Course> getAllCourses();
    List<Course> getCoursesByDepartmentId(Long departmentId);
    Course updateCourse(Long id, Course courseDetails);
    void deleteCourse(Long id);
}

