package com.techedgegroup.accademy.course.restapi.model;

import java.util.List;

import lombok.Data;

@Data
public class TeacherOutDTO {
    
    @Data
    public static class CourseSummary {
        private Integer id;
        private String courseName;
    }

    private Integer id;
    private String teacherName;
    private String teacherSurname;
    private String teacherEmail;

    private List<CourseSummary> courses;
}
