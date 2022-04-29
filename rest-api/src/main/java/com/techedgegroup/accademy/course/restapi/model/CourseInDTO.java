package com.techedgegroup.accademy.course.restapi.model;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CourseInDTO {
	@NotNull
    private Integer teacherId;

	@NotBlank(message = "Name is mandatory")
    private String courseName;

	@NotBlank(message = "Category is mandatory")
    private String courseCategory;

	@NotNull(message = "Date is mandatory")
    private Date courseDate;

}
