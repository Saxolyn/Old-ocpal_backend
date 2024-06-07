package dev9.lapco.service.serviceImpl;

import dev9.lapco.config.jwt.JwtUtils;
import dev9.lapco.constant.ERole;
import dev9.lapco.constant.StatusCode;
import dev9.lapco.request.CreatedStudentRequest;
import dev9.lapco.entity.AccountEntity;
import dev9.lapco.entity.StudentEntity;
import dev9.lapco.repository.AccountRepository;
import dev9.lapco.repository.StudentRepository;
import dev9.lapco.response.StudentResponse;
import dev9.lapco.service.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService, StatusCode {

    private final PasswordEncoder passwordEncoder;

    private final StudentRepository studentRepository;

    private final AccountRepository accountRepository;

    private final JwtUtils jwtUtils;


    @Override
    public StudentResponse insert(CreatedStudentRequest studentDTO, HttpServletRequest request) {

        if(jwtUtils.getPhoneNumberFromRequest(request).isEmpty()){
            return StudentResponse.builder().status(UNAUTHORIZED).build();
        }


        //save student to account collection
        studentDTO.setIdentityNumber(passwordEncoder.encode(studentDTO.getIdentityNumber()));
        accountRepository.save(convertStudentDTOToAccountEntity(studentDTO)) ;


        return StudentResponse.builder().status(SUCCESS).build();
    }


    private StudentEntity convertStudentDTOToStudentEntity(CreatedStudentRequest studentDTO) {
        return StudentEntity.builder()
                .firstName(studentDTO.getFirstName())
                .lastName(studentDTO.getLastName())
                .username(studentDTO.getUsername())
                .address(studentDTO.getAddress())
                .email(studentDTO.getEmail())
                .phoneNumber(studentDTO.getPhoneNumber())
                //Todo : chưa convert đủ trường
                .build();
    }

    private AccountEntity convertStudentDTOToAccountEntity(CreatedStudentRequest studentDTO) {
        return AccountEntity.builder()
                .username(studentDTO.getUsername())
                .phoneNumber(studentDTO.getPhoneNumber())
                .password(studentDTO.getIdentityNumber())
                .role(ERole.STUDENT)
                .build();
    }
}
