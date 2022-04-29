package com.techedgegroup.accademy.course.datamodel;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

import lombok.Data;

@Entity
@Table(name = "COURSES")
@Data
public class Course {
    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue
    private Integer id;

    @Column(name = "NAME")
    private String courseName;

    @Column(name = "CATEGORY")
    private String courseCategory;

    @Column(name = "COURSE_DATE")
    private Date courseDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Teacher teacher;

    @ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY)
    private Set<Student> students= new HashSet<Student>();
}
