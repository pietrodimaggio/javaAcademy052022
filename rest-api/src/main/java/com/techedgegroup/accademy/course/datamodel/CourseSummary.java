package com.techedgegroup.accademy.course.datamodel;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CourseSummary {
    private int id;
    private String courseName;
    private String courseCategory;
    private Date courseDate;
    private String teacher;
    private int students;
}
