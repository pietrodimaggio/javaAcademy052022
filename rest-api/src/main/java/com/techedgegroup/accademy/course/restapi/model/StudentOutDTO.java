package com.techedgegroup.accademy.course.restapi.model;

import java.util.List;

import lombok.Data;

@Data
public class StudentOutDTO {

    @Data
    public static class CourseSummary {
        private Integer id;
        private String courseName;
    }

    private int id;

    private String studentName;
    private String studentSurname;
    private String studentEmail;

    private List<CourseSummary> courses;
}
