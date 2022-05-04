package com.techedgegroup.accademy.course.restapi.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class StudentInDTO {

    @NotBlank(message = "Name is mandatory")
    private String studentName;

    @NotBlank(message = "Surame is mandatory")
    private String studentSurname;

    @Email
    @NotBlank(message = "Emailis mandatory")
    private String studentEmail;

}
