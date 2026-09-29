package com.project.CRUD_Demo.repository;

import com.project.CRUD_Demo.entity.Student;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

      Optional<Student>findByIdAndDeletedIsFalse(Long Id);

       List<Student>findByDeletedIsFalse();


}
