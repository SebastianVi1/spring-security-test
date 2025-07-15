package org.sebas.securitydemo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.sebas.securitydemo.model.Student;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {


    private List<Student> students = new ArrayList<>(List.of(
            new Student(1, "sebastian", 70),
            new Student(2, "andre", 93)
    ));

    @GetMapping("/students")
    public List<Student> getStudents(){
        return students;
    }

    @PostMapping("/students")
    public void newStudent(@RequestBody Student student){

        students.add(student);
    }

    @GetMapping("/")
    public String greet(HttpServletRequest request){
        return "Welcome to Sebastian" ;
    }

    @GetMapping("/csrf-token")
    public CsrfToken getCrfsToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }
}
