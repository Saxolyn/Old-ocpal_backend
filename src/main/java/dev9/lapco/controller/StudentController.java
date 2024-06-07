package dev9.lapco.controller;

import dev9.lapco.request.CreatedStudentRequest;
import dev9.lapco.response.StudentResponse;
import dev9.lapco.service.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StudentController {


    private final StudentService studentService;

    @PostMapping("add-student")
    public StudentResponse addStudent(CreatedStudentRequest studentDTO, HttpServletRequest request){
                return studentService.insert(studentDTO, request);
    }
}
