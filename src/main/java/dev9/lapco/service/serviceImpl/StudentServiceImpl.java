package dev9.lapco.service.serviceImpl;

import dev9.lapco.commonUtil.ConvertedUtil;
import dev9.lapco.commonUtil.ValidateUtil;
import dev9.lapco.config.security.UserDetailsImpl;
import dev9.lapco.constant.ERole;
import dev9.lapco.constant.Message;
import dev9.lapco.constant.StatusCode;
import dev9.lapco.entity.AccountEntity;
import dev9.lapco.entity.AdminEntity;
import dev9.lapco.entity.StudentEntity;
import dev9.lapco.entity.TeacherEntity;
import dev9.lapco.repository.AccountRepository;
import dev9.lapco.repository.AdminRepository;
import dev9.lapco.repository.StudentRepository;
import dev9.lapco.repository.TeacherRepository;
import dev9.lapco.request.CreatedUserRequest;
import dev9.lapco.response.CreatedUserResponse;
import dev9.lapco.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements UserService, StatusCode, Message {

    private final StudentRepository studentRepository;

    private final TeacherRepository teacherRepository;

    private final AdminRepository adminRepository;

    private final AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;

    private final ConvertedUtil convertedUtil;

    @Override
    public CreatedUserResponse createdNew(CreatedUserRequest createdUserRequest) {

        UserDetailsImpl loggedUser = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (loggedUser == null) {
            return CreatedUserResponse.builder().status(UNAUTHORIZED).message(ME0002).build();
        }

        Optional<AccountEntity> checkAccount = accountRepository.findAccount(createdUserRequest.getPhoneNumber());
        if (checkAccount.isPresent()) {
            return CreatedUserResponse.builder().status(BAD_REQUEST).message(ME0005).errorCode(false).build();
        }

        if (!ValidateUtil.isValidIdentityCard(createdUserRequest.getIdentityNumber()) || !ValidateUtil.isValidPhoneNumber(createdUserRequest.getPhoneNumber())) {
            return CreatedUserResponse.builder().status(BAD_REQUEST).message(ME0006).errorCode(false).build();
        } else {
            if (createdUserRequest.getRole() == null) {
                createdUserRequest.setRole(ERole.STUDENT);
            }
            try {
                switch (createdUserRequest.getRole()) {
                    case ADMIN:
                        AdminEntity newAdmin = (AdminEntity) convertedUtil.convertNewUserRequestToStudentEntity(createdUserRequest);
                        adminRepository.save(newAdmin);
                        break;
                    case TEACHER:
                        TeacherEntity newTeacher = (TeacherEntity) convertedUtil.convertNewUserRequestToStudentEntity(createdUserRequest);
                        teacherRepository.save(newTeacher);
                        break;
                    case STUDENT:
                        StudentEntity newStudent = (StudentEntity) convertedUtil.convertNewUserRequestToStudentEntity(createdUserRequest);
                        studentRepository.save(newStudent);
                        break;
                    default:
                }
                createdUserRequest.setIdentityNumber(passwordEncoder.encode(createdUserRequest.getIdentityNumber()));
                AccountEntity newAccount = convertedUtil.convertToAccountEntity(createdUserRequest);
                accountRepository.save(newAccount);
            } catch (Exception e) {
                e.printStackTrace();
                return CreatedUserResponse.builder().status(BAD_REQUEST).message(MF0001).errorCode(true).build();
            }

        }
        return CreatedUserResponse.builder()
                .status(SUCCESS)
                .message(MI0004)
                .phoneNumber(createdUserRequest.getPhoneNumber())
                .username(createdUserRequest.getUsername())
                .role(createdUserRequest.getRole())
                .build();
    }


}
