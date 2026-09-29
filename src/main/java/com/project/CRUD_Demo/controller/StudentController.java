package com.project.CRUD_Demo.controller;

import com.project.CRUD_Demo.entity.Student;
import com.project.CRUD_Demo.service.StudentService;
import org.hibernate.annotations.SoftDelete;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
      Student ctstudent = studentService.create(student);
      return ResponseEntity.ok(ctstudent);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Student>> getAll(){
       List<Student> stget = studentService.getAllStudent();

        if (stget.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(stget);
    }

    @GetMapping("/get/{Id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long Id){
       Student studentResp = studentService.getStudent(Id);
       if (studentResp == null){
           return ResponseEntity.notFound().build();
       }
       return ResponseEntity.ok(studentResp);
    }

    @PutMapping("/update/{Id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long Id ,@RequestBody Student student){
        Student stUpdate = studentService.updateStudent(Id , student);

        if (stUpdate == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(stUpdate);
    }

    @DeleteMapping("/delete/{Id}")
    public  ResponseEntity<String> deleteStudent(@PathVariable Long Id){
        boolean isDeleted = studentService.deleteStudent(Id);

        if(! isDeleted){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok("Student Deleted");
    }

          @PatchMapping("/sdelete/{Id}")
          public ResponseEntity<String> softdelete(@PathVariable Long Id){
        Boolean isDeleted = studentService.softDeleted(Id);

        if (! isDeleted){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok("Record Deleted ");
    }


}
