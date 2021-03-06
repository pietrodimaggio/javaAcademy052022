package com.techedgegroup.accademy.course.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.techedgegroup.accademy.course.datamodel.Course;
import com.techedgegroup.accademy.course.datamodel.CourseSummary;
import com.techedgegroup.accademy.course.datamodel.Student;
import com.techedgegroup.accademy.course.datamodel.Teacher;
import com.techedgegroup.accademy.course.repository.CourseRepository;
import com.techedgegroup.accademy.course.repository.TeacherRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Transactional(readOnly = true)
    public List<Course> getAllCourses() {
        return courseRepository.findAllCourses();
    }

    @Transactional(readOnly = true)
    public List<Course> getCoursesByCategory(String courseCategory) {
        return courseRepository.findByCourseCategory(courseCategory);
    }

    @Transactional(readOnly = true)
    public List<String> getAllCourseCategories() {
        return courseRepository.findAllCategories();
    }

    @Transactional(readOnly = true)
    public List<CourseSummary> getCourseSummary() {
        return courseRepository.getCourseSummary();
    }

    @Transactional
    public Course createNewCourse(Integer teacherId, String courseName, String courseCategory, Date courseDate)
            throws Exception {
        Optional<Teacher> teacherOwner = teacherRepository.findById(teacherId);

        if (teacherOwner.isPresent()) {
            Teacher teacher = teacherOwner.get();

            Course newCourse = new Course();
            newCourse.setCourseName(courseName);
            newCourse.setCourseCategory(courseCategory);
            newCourse.setCourseDate(new java.sql.Date(courseDate.getTime()));

            newCourse.setTeacher(teacher);

            teacher.getCourses().add(newCourse);

            return newCourse;
        } else {
            throw new Exception("Teacher not found");
        }
    }

    @Transactional
    public Course updateCourse(Integer id, Integer teacherId, String courseName, String courseCategory,
            Date courseDate) throws Exception {
        Optional<Course> courseQuery = courseRepository.findById(id);
        if (!courseQuery.isPresent()) {
            throw new Exception("Course not found");
        }

        Optional<Teacher> teacherOwner = teacherRepository.findById(teacherId);
        if (!teacherOwner.isPresent()) {
            throw new Exception("Teacher not found");
        }

        Course course = courseQuery.get();

        course.setTeacher(teacherOwner.get());
        course.setCourseName(courseName);
        course.setCourseCategory(courseCategory);
        course.setCourseDate(new java.sql.Date(courseDate.getTime()));

        return course;
    }

    @Transactional
    public void deleteCourse(Integer id) throws Exception {
        Optional<Course> courseQuery = courseRepository.findById(id);
        if (!courseQuery.isPresent()) {
            throw new Exception("Course not found");
        }

        Course course = courseQuery.get();
        course.getStudents().stream().forEach(student -> { //
            course.getStudents().remove(student);
            student.getCourses().remove(course);
        });

        // courseRepository.deleteCourseStudents(id);

        courseRepository.deleteById(id);
    }
    
    @Transactional
    public Course getCourse(Integer id) throws Exception {

        Optional<Course> courseQuery = courseRepository.findById(id);
        if (!courseQuery.isPresent()) {
            throw new Exception("Course not found");
        }

        Course course = courseQuery.get(); 
        course.getStudents().size();
        course.getTeacher().getId();
        course.getTeacher().getCourses().size();
        return course;
    }

}
