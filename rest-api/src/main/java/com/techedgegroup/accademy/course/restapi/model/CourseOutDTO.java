package com.techedgegroup.accademy.course.restapi.model;

import java.util.Date;

import lombok.Data;

@Data
public class CourseOutDTO {
    private Integer id;
    private String courseName;
    private String courseCategory;
    private Date courseDate;
    
    private Integer teacherId;
    private String teacherEmail;
}
