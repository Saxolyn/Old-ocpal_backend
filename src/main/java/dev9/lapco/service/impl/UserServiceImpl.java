package dev9.lapco.service.impl;

import dev9.lapco.util.ConvertedUtil;
import dev9.lapco.util.ValidateUtil;
import dev9.lapco.config.security.UserDetailsImpl;
import dev9.lapco.constant.ERole;
import dev9.lapco.constant.Message;
import dev9.lapco.constant.StatusCode;
import dev9.lapco.dto.BaseUserDTO;
import dev9.lapco.entity.AccountEntity;
import dev9.lapco.entity.AdminEntity;
import dev9.lapco.entity.StudentEntity;
import dev9.lapco.entity.TeacherEntity;
import dev9.lapco.repository.AccountRepository;
import dev9.lapco.repository.AdminRepository;
import dev9.lapco.repository.StudentRepository;
import dev9.lapco.repository.TeacherRepository;
import dev9.lapco.request.CreatedUserRequest;
import dev9.lapco.request.DeletedUserRequest;
import dev9.lapco.response.BaseResponse;
import dev9.lapco.response.CreatedUserResponse;
import dev9.lapco.response.GetUserResponse;
import dev9.lapco.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, StatusCode, Message {

    private final StudentRepository studentRepository;

    private final TeacherRepository teacherRepository;

    private final AdminRepository adminRepository;

    private final AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;

    private final ConvertedUtil convertedUtil;

    @Override
    public CreatedUserResponse createdNew(CreatedUserRequest createdUserRequest) {
        UserDetailsImpl currentUser = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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
                switch (currentUser.getRole()) {
                    case SUPER_ADMIN:
                        switch (createdUserRequest.getRole()){
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
                            case SUPER_ADMIN:
                            default:
                                return CreatedUserResponse.builder().status(BAD_REQUEST).message(ME0002).errorCode(true).build();
                        }
                    case ADMIN:
                        switch (createdUserRequest.getRole()){
                            case TEACHER:
                                TeacherEntity newTeacher = (TeacherEntity) convertedUtil.convertNewUserRequestToStudentEntity(createdUserRequest);
                                teacherRepository.save(newTeacher);
                                break;
                            case STUDENT:
                                StudentEntity newStudent = (StudentEntity) convertedUtil.convertNewUserRequestToStudentEntity(createdUserRequest);
                                studentRepository.save(newStudent);
                                break;
                            case SUPER_ADMIN:
                            case ADMIN:
                            default:
                                return CreatedUserResponse.builder().status(BAD_REQUEST).message(ME0002).errorCode(true).build();
                        }
                    case TEACHER:
                    case STUDENT:
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

    @Override
    public GetUserResponse getUserList() {
        UserDetailsImpl currentUser = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<AccountEntity> accountList = accountRepository.findAllAccount();
        if (accountList.isEmpty()) {
            //TODO tìm cách sửa thaành ném ra exception
            return GetUserResponse.builder().status(SUCCESS).message(MI0005).errorCode(false).build();
        }
        switch (currentUser.getRole()) {
            case SUPER_ADMIN:
                return GetUserResponse.builder().status(SUCCESS).message(MI0006).errorCode(false).baseUserDTOList(                        accountList.stream()
                        .filter(m -> (!m.getRole().equals(ERole.SUPER_ADMIN)))
                        .map(n -> BaseUserDTO.builder()
                                .username(n.getUsername())
                                .phoneNumber(n.getPhoneNumber())
                                .role(n.getRole()).build())
                        .collect(Collectors.toList())).build();
            case ADMIN:
                return GetUserResponse.builder().status(SUCCESS).message(MI0006).errorCode(false).baseUserDTOList(                        accountList.stream()
                        .filter(m -> (m.getRole().equals(ERole.STUDENT) || m.getRole().equals(ERole.TEACHER)))
                        .map(n -> BaseUserDTO.builder()
                                .username(n.getUsername())
                                .phoneNumber(n.getPhoneNumber())
                                .role(n.getRole()).build())
                        .collect(Collectors.toList())).build();
            case TEACHER:
                return GetUserResponse.builder().status(SUCCESS).message(MI0006).errorCode(false).baseUserDTOList(
                        accountList.stream()
                                .filter(m -> (m.getRole().equals(ERole.STUDENT)))
                                .map(n -> BaseUserDTO.builder()
                                        .username(n.getUsername())
                                        .phoneNumber(n.getPhoneNumber())
                                        .role(n.getRole()).build())
                                .collect(Collectors.toList())
                ).build();
            default:
                return GetUserResponse.builder().status(BAD_REQUEST).message(ME0002).errorCode(true).build();
        }

    }

    @Override
    public BaseResponse deleteUser(DeletedUserRequest deletedUserRequest) {
        UserDetailsImpl currentUser = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<AccountEntity> accountRequest = accountRepository.findAccount(deletedUserRequest.getPhoneNumber());
        if (accountRequest.isEmpty()) {
            return BaseResponse.builder().status(BAD_REQUEST).message(ME0007).errorCode(true).build();
        }
        switch (currentUser.getRole()) {
            case SUPER_ADMIN:
                if (deletedUserRequest.getRole().equals(ERole.SUPER_ADMIN)) {
                    return BaseResponse.builder().status(BAD_REQUEST).message(ME0002).errorCode(true).build();
                }
                break;
            case ADMIN:
                if (deletedUserRequest.getRole().equals(ERole.SUPER_ADMIN) || deletedUserRequest.getRole().equals(ERole.ADMIN)) {
                    return BaseResponse.builder().status(BAD_REQUEST).message(ME0002).errorCode(true).build();
                }
                break;
            default:
                return BaseResponse.builder().status(SUCCESS).message(ME0010).errorCode(true).build();
        }
        try {
            accountRepository.softDelete(deletedUserRequest.getPhoneNumber());
            return BaseResponse.builder().status(SUCCESS).message(ME0010).errorCode(false).build();
        } catch (Exception e) {
            return BaseResponse.builder().status(BAD_REQUEST).message(MF0002).errorCode(true).build();
        }

    }

}
