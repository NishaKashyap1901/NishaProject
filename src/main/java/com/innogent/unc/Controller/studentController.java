package com.innogent.unc.Controller;
import com.innogent.unc.Repository.studentRepository;
import com.innogent.unc.Repository.courseRepository;


import com.innogent.unc.Entity.Student;
import com.innogent.unc.Entity.Course;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class studentController {
    @Autowired
    private studentRepository studentrepository;

    @Autowired
    private courseRepository courserepository;

    @PostMapping("/")
    public Student create(@RequestBody Student student) {
        return studentrepository.save(student);
    }
    @PostMapping("/p")
    public Course create(@RequestBody Course course) {
        return courserepository.save(course);
    }
//    @GetMapping("/")
//    public String get()
//    {
//        return "heeloo";
//    }
        @GetMapping("/{id}")
        public Student getById(@PathVariable Long id)
        {
            Optional<Student> student = studentrepository.findById(id);
            return student.get();
        }
        @GetMapping("/")
        public List<Student> get ()
        {
            return studentrepository.findAll();
        }
    @GetMapping("/show")
    public List<Course> get1 ()
    {
        return courserepository.findAll();
    }
        @DeleteMapping("/{id}")
        public ResponseEntity<Student> deleteById (@PathVariable Long id){
            try {
                Optional<Student> userToDelete = studentrepository.findById(id);
                if (userToDelete.isPresent()) {
                    Student deletedUser = userToDelete.get();
                    studentrepository.deleteById(id);
                    return ResponseEntity.ok(deletedUser); // Return the deleted user with 200 OK
                } else {
                    return ResponseEntity.notFound().build(); // Return a 404 Not Found response if the user is not found
                }
            } catch (Exception e) {
                return ResponseEntity.status(Objects.requireNonNull(HttpStatus.INTERNAL_SERVER_ERROR)).build(); // Handle other exceptions
            }
        }
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateUser(@PathVariable Long id, @RequestBody Student updatedStudent) {
        Optional<Student> optionalUser = studentrepository.findById(id);
        if (!optionalUser.isPresent()) {
            return ResponseEntity.notFound().build();
        }
       Student existingUser = optionalUser.get();
        existingUser.setName(updatedStudent.getName());
        existingUser.setCourse(updatedStudent.getCourse());
        existingUser.setClg(updatedStudent.getClg());
        Student updatedStudent1 = studentrepository.save(existingUser);
        return ResponseEntity.ok(updatedStudent1);
    }
    }

