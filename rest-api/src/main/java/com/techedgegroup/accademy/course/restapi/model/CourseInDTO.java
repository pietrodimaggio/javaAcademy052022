package com.techedgegroup.accademy.course.restapi.model;

import java.util.Date;

public class CourseInDTO {
	 private Integer teacherId;

	    private String courseName;

	    private String courseCategory;

	    private Date courseDate;

		public Integer getTeacherId() {
			return teacherId;
		}

		public void setTeacherId(Integer teacherId) {
			this.teacherId = teacherId;
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
	    	    
}
