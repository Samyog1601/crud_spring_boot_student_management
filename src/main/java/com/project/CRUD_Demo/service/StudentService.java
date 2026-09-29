package com.project.CRUD_Demo.service;

import com.project.CRUD_Demo.entity.Student;
import com.project.CRUD_Demo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student create(Student studentReq){
       studentReq.setDeleted(false);
        Student studentresp = studentRepository.save(studentReq);
        return studentresp;
    }

    public Student getStudent( Long Id ){

       Optional<Student> stresp = studentRepository.findByIdAndDeletedIsFalse(Id);

       if (stresp.isPresent()){
           return stresp.get();
       }
       return null;
    }

    public List<Student> getAllStudent(){
        List<Student> stlist = studentRepository.findByDeletedIsFalse();
        return stlist;
    }

    public Student updateStudent(Long Id , Student student){

        Optional<Student> existingStudent = studentRepository.findByIdAndDeletedIsFalse(Id);

        if (existingStudent.isEmpty()){
            return null;
        }

        Student studentToSave = existingStudent.get();

        studentToSave.setName(student.getName());
        studentToSave.setAge(student.getAge());
        studentToSave.setContact(student.getContact());
        studentToSave.setEmail(student.getEmail());
        studentToSave.setCourse(student.getCourse());

        studentToSave.setDeleted(false);

        return studentRepository.save(studentToSave);
    }

    public boolean deleteStudent(Long Id){
        boolean isStudent = studentRepository.existsById(Id);

        if (! isStudent) {
            return false;
        }

        studentRepository.deleteById(Id);

        return true;
    }

    public Boolean softDeleted(Long Id){

        Optional<Student> existingStudent = studentRepository.findByIdAndDeletedIsFalse(Id);

        if(existingStudent.isEmpty()){
            return false;
        }

        Student studentToSave = existingStudent.get();

        studentToSave.setDeleted(true);

        studentRepository.save(studentToSave);
     return true;
    }
}
