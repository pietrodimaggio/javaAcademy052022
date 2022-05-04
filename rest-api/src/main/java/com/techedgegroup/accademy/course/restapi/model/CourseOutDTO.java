package com.techedgegroup.accademy.course.restapi.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CourseOutDTO {
    private Integer id;
    private String courseName;
    private String courseCategory;
    
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date courseDate;
    
    private Integer teacherId;
    private String teacherEmail;

    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseCategory() {
		return courseCategory;
	}
	public void setCourseCategory(String courseCategory) {
		this.courseCategory = courseCategory;
	}
	public Date getCourseDate() {
		return courseDate;
	}
	public void setCourseDate(Date courseDate) {
		this.courseDate = courseDate;
	}
	public Integer getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	public String getTeacherEmail() {
		return teacherEmail;
	}
	public void setTeacherEmail(String teacherEmail) {
		this.teacherEmail = teacherEmail;
	}

    
}
