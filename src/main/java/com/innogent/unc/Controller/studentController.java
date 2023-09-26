package com.innogent.unc.Controller;

import com.innogent.unc.Repository.StudentRepository;
import com.innogent.unc.Repository.CourseRepository;


import com.innogent.unc.Entity.Student;
import com.innogent.unc.Entity.Course;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.*;

@RestController
@RequestMapping("/students")
public class studentController {
    @Autowired
    private StudentRepository studentrepository;

    @Autowired
    private CourseRepository courserepository;

    @PostMapping("/")
    public Student create(@RequestBody Student student) {
        return studentrepository.save(student);
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable Long id) {
        Optional<Student> student = studentrepository.findById(id);
        return student.get();
    }

    @GetMapping("/")
    public List<Student> get() {
        return studentrepository.findAll();
    }

    @GetMapping("/course")
    public List<Course> get1() {
        return courserepository.findAll();
    }

    @GetMapping("/{id}/course")
    public Course getById1(@PathVariable Long id) {
        Optional<Course> course = courserepository.findById(id);
        return course.get();
    }

    @DeleteMapping("/{id}")
    public Student deleteById(@PathVariable Long id) {
        studentrepository.deleteById(id);
        return null;
    }

//    @PutMapping("/{id}")
//    public Student updateUser( @PathVariable Long id) {
//    Optional<Student> studentupdated = studentrepository.findById(id);
//      Student student1=studentupdated.get();
//     student1.setName();
//        return null;
//    }
@PutMapping("/{id}")
public Student updateStudent(@RequestBody Student student)
{
    Optional<Student> student1=studentrepository.findById(student.getId());
    Student updatedstudent=student1.get();
    updatedstudent.setName(student.getName());
    updatedstudent.setCourse(student.getCourse());
    updatedstudent.setClg(student.getClg());
    Student student2=studentrepository.save(updatedstudent);
    return student2;
}
    @PostMapping("/{id}/course")
    public ResponseEntity<String> save(@PathVariable Long id, @RequestBody Course course) {
        Optional<Student> student = studentrepository.findById(id);
        if (student.isPresent()) {
            Student b = student.get();
            course.setStudent(b);
            courserepository.save(course);
            return ResponseEntity.ok("Course saved for this Student Id " + id);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}/courses")
    public Course updateCourse( @RequestBody Course updatedCourse) {
        Optional<Course> optionalCourse = courserepository.findById(updatedCourse.getId());
        Course existingCourse = optionalCourse.get();
        existingCourse.setCoursename(updatedCourse.getCoursename());
        Course updatedCourse1 = courserepository.save(existingCourse);
        return updatedCourse1;
    }

    @DeleteMapping("/{studentId}/courses/{courseId}")
    public ResponseEntity<String> deleteCourseForStudent(@PathVariable Long studentId, @PathVariable Long courseId) {
        Optional<Student> optionalStudent = studentrepository.findById(studentId);
        if (!optionalStudent.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Optional<Course> optionalCourse = courserepository.findById(courseId);
        if (!optionalCourse.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Student student = optionalStudent.get();
        Course course = optionalCourse.get();
        if (!student.getCourseList().contains(course)) {
            return ResponseEntity.badRequest().body("Course is not associated with the student.");
        }
        student.getCourseList().remove(course);
        studentrepository.save(student);
//        courserepository.delete(course);
        return ResponseEntity.ok("Course deleted for the student.");
    }

    @PutMapping("/{studentId}/courses/{courseId}")
    public Course updateCourse(@PathVariable Long studentId,@PathVariable Long courseId) {
        Optional<Student> optionalStudent = studentrepository.findById(studentId);
        Optional<Course> optionalCourse = courserepository.findById(courseId);
        if (optionalStudent.isPresent() && optionalCourse.isPresent()) {
            Student student = optionalStudent.get();
        }
          Course course = optionalCourse.get();
            String updatedcourse = course.getCoursename();
            course.setCoursename(updatedcourse);
            courserepository.save(course);

            return course;
    }

}

