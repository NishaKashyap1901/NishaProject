package com.innogent.unc.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Course {
    @ManyToOne
    @JsonIgnore
    private Student student;
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) @Column(name="id",nullable = false)private Long id;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String coursename;


    public Course() {
    }

    public Course(String coursename) {
        this.coursename = coursename;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }


}
