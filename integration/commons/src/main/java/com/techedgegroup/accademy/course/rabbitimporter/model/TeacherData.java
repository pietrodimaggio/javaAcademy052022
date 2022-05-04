package com.techedgegroup.accademy.course.rabbitimporter.model;

import java.util.List;

import lombok.Data;

@Data
public class TeacherData {
    private Teacher teacher;
    private List<Course> courses;
}
