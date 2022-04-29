package com.techedgegroup.accademy.course.restapi.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class TeacherInDTO {
    @NotBlank(message = "Name is mandatory")
    private String teacherName;
    
    @NotBlank(message = "Surame is mandatory")
    private String teacherSurname;

    @Email
    @NotBlank(message = "Emailis mandatory")
    private String teacherEmail;
}
