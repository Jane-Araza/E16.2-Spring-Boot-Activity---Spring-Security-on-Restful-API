package com.example.studentsecurity.service;


import com.example.studentsecurity.entity.StudentEntity;
import com.example.studentsecurity.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public String addStudent(String firstname, String lastname, String email, String course, Double gpa) {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setFirstName(firstname);
        studentEntity.setLastName(lastname);
        studentEntity.setEmail(email);
        studentEntity.setCourse(course);
        studentRepository.save(studentEntity);
        return "succesfully Saved!";

    }

    public Iterable<StudentEntity> getAllStudents() {
        return studentRepository.findAll();
    }

    public ResponseEntity<List<StudentEntity>> getByid(Long id) {
        return new ResponseEntity<>(studentRepository.findByid(id), HttpStatus.OK);

    }
}
