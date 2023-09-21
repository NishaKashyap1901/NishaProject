package com.innogent.unc.Repository;

import com.innogent.unc.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface studentRepository extends JpaRepository<Student, Long> {

}
