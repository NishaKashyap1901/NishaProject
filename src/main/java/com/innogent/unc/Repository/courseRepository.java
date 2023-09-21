package com.innogent.unc.Repository;

import com.innogent.unc.Entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface courseRepository extends JpaRepository <Course,Long>{
}
