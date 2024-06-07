package dev9.lapco.service;

import dev9.lapco.request.CreatedStudentRequest;
import dev9.lapco.response.StudentResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface StudentService {
    StudentResponse insert(CreatedStudentRequest studentDTO, HttpServletRequest request);
}
