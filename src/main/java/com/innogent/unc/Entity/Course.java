package com.innogent.unc.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Course {

   @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) @Column(name="id",nullable = false)private Long id;
    private String description;


    public Course() {
    }

    public Course(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @ManyToOne
    private Student student;
}
