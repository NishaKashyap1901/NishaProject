package com.innogent.unc.Entity;

import jakarta.persistence.*;

import java.util.List;
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) @Column(name="id",nullable = false)private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    private String name;
    public String course;

    private String clg;
//    private List<Course> Course;
//    private List<Student> students;
    public Student() {
    }
    @OneToMany(mappedBy = "student")
    public List<Student> get()
{
    return null;
}
    public Student(String name, String course, String clg) {
        this.name = name;
        this.course = course;
        this.clg = clg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getClg() {
        return clg;
    }

    public void setClg(String clg) {
        this.clg = clg;
    }

}
