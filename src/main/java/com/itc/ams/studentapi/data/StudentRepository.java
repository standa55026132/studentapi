package com.itc.ams.studentapi.data;

import com.itc.ams.studentapi.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    public List<Student> findById(int id);
    public List<Student> findByNameContainingIgnoreCaseOrCityContainingIgnoreCase(String name, String city);
}
