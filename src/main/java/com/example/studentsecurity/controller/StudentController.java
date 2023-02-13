package com.example.studentsecurity.controller;

import com.example.studentsecurity.entity.StudentEntity;
import com.example.studentsecurity.repository.StudentRepository;
import com.example.studentsecurity.service.StudentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("/student")
    public @ResponseBody String addStudent(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String email, @RequestParam String course, @RequestParam Double gpa) {
        return studentService.addStudent(firstName, lastName, email, course, gpa);

    }

    @GetMapping(path = "/students")
    public @ResponseBody Iterable<StudentEntity> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("student_id")
    public ResponseEntity<List<StudentEntity>> getByid(@RequestParam Long id) {
        return studentService.getByid(id);

    }
    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<List<StudentEntity>> deleteById(@PathVariable long id){
        return new ResponseEntity<>(studentRepository.deleteById(id), HttpStatus.OK);
    }
    @PutMapping("/update/{student_id}")
    public ResponseEntity<StudentEntity> updateStudent(@PathVariable int student_id,@RequestBody StudentEntity studentEntity ){
        StudentEntity student = studentRepository.getById((long) student_id);
        student.setFirstName(studentEntity.getFirstName());
        student.setLastName(studentEntity.getLastName());
        student.setEmail(studentEntity.getEmail());
        student.setCourse(studentEntity.getCourse());
        student.setGpa(studentEntity.getGpa());
        return new ResponseEntity<>(studentRepository.save(student), HttpStatus.OK);
    }

}
