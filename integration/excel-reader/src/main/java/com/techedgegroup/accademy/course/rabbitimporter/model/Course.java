package com.techedgegroup.accademy.course.rabbitimporter.model;

import java.util.Date;

import lombok.Data;

@Data
public class Course {
    private String courseName;
    private String courseCategory;
    private Date courseDate;

}
