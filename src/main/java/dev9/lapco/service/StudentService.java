package dev9.lapco.service;

import dev9.lapco.dto.StudentDTO;
import dev9.lapco.response.StudentResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface StudentService {
    StudentResponse insert(StudentDTO studentDTO, HttpServletRequest request);
}
