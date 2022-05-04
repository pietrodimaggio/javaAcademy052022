package com.techedgegroup.accademy.course.restapi.model;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class CourseInDTO {
	@NotNull
	private Integer teacherId;

	@NotBlank(message = "Name is mandatory")
	private String courseName;

	@NotBlank(message = "Category is mandatory")
	private String courseCategory;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = "Date is mandatory")
	private Date courseDate;

}
