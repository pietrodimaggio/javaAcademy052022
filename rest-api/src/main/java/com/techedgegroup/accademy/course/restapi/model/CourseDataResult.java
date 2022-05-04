package com.techedgegroup.accademy.course.restapi.model;

import java.util.Date;

import lombok.Data;

@Data
public class CourseDataResult {
    private Integer id;
    private String courseName;
    private String courseCategory;
    private Date courseDate;
    private String teacher;
    private int students;
}
