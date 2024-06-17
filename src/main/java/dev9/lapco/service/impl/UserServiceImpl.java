package dev9.lapco.service.impl;

import dev9.lapco.config.security.UserDetailsImpl;
import dev9.lapco.constant.Constants;
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
import dev9.lapco.util.ConvertedUtil;
import dev9.lapco.util.ValidateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static dev9.lapco.util.ValidateUtil.isValidAuthority;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, StatusCode, Message, Constants {

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
                            case SUPER_ADMIN:
                            default:
                                return CreatedUserResponse.builder().status(BAD_REQUEST).message(ME0002).errorCode(true).build();
                        }
                    case ADMIN:
                        switch (createdUserRequest.getRole()) {
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
        //TODO : not had paginator yet
        switch (currentUser.getRole()) {
            case SUPER_ADMIN:
                return GetUserResponse.builder().status(SUCCESS).message(MI0006).errorCode(false).baseUserDTOList(accountList.stream()
                        .filter(m -> (!m.getRole().equals(ERole.SUPER_ADMIN)))
                        .map(n -> BaseUserDTO.builder()
                                .username(n.getUsername())
                                .phoneNumber(n.getPhoneNumber())
                                .role(n.getRole())
                                .isLocked(n.getIsLocked())
                                .isDeleted(n.getIsDeleted())
                                .build())
                        .collect(Collectors.toList())).build();
            case ADMIN:
                return GetUserResponse.builder().status(SUCCESS).message(MI0006).errorCode(false).baseUserDTOList(accountList.stream()
                        .filter(m -> (m.getRole().equals(ERole.STUDENT) || m.getRole().equals(ERole.TEACHER)))
                        .map(n -> BaseUserDTO.builder()
                                .username(n.getUsername())
                                .phoneNumber(n.getPhoneNumber())
                                .role(n.getRole())
                                .isLocked(n.getIsLocked())
                                .isDeleted(n.getIsDeleted())
                                .build())
                        .collect(Collectors.toList())).build();
            case TEACHER:
                return GetUserResponse.builder().status(SUCCESS).message(MI0006).errorCode(false).baseUserDTOList(
                        accountList.stream()
                                .filter(m -> (m.getRole().equals(ERole.STUDENT)))
                                .map(n -> BaseUserDTO.builder()
                                        .username(n.getUsername())
                                        .phoneNumber(n.getPhoneNumber())
                                        .role(n.getRole())
                                        .isLocked(n.getIsLocked())
                                        .isDeleted(n.getIsDeleted())
                                        .build())
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

    @Override
    public BaseResponse lock(String phoneNumber) {
        if (isValidAuthority()) {
            return BaseResponse.builder().status(UNAUTHORIZED).message(ME0002).errorCode(true).build();
        }

        UserDetailsImpl currentUser = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<AccountEntity> account = accountRepository.findAccount(phoneNumber);
        if (account.isEmpty()) {
            return BaseResponse.builder().status(BAD_REQUEST).message(ME0008).errorCode(true).build();
        }

        switch (currentUser.getRole()) {
            case SUPER_ADMIN:
                switch (account.get().getRole()) {
                    case ADMIN:
                        Optional<AdminEntity> admin = adminRepository.findOne(Example.of(AdminEntity.builder().phoneNumber(phoneNumber).build()));
                        if (admin.isEmpty()) {
                            return BaseResponse.builder().status(BAD_REQUEST).message(ME0015).errorCode(true).build();
                        }
                        admin.get().setLock(true);
                        adminRepository.save(admin.get());
                        break;
                    case TEACHER:
                        Optional<TeacherEntity> teacher = teacherRepository.findOne(Example.of(TeacherEntity.builder().phoneNumber(phoneNumber).build()));
                        if (teacher.isEmpty()) {
                            return BaseResponse.builder().status(BAD_REQUEST).message(ME0015).errorCode(true).build();
                        }
                        teacher.get().setLock(true);
                        teacherRepository.save(teacher.get());
                        break;
                    default:
                        return BaseResponse.builder().status(BAD_REQUEST).message(ME0015).errorCode(true).build();
                }
                break;
            case ADMIN:
                Optional<TeacherEntity> teacher = teacherRepository.findOne(Example.of(TeacherEntity.builder().phoneNumber(phoneNumber).build()));
                if (teacher.isEmpty()) {
                    return BaseResponse.builder().status(BAD_REQUEST).message(ME0015).errorCode(true).build();

                }
                teacher.get().setLock(true);
                teacherRepository.save(teacher.get());
                break;
            case TEACHER:
            case STUDENT:
            default:
                return BaseResponse.builder().status(BAD_REQUEST).message(ME0015).errorCode(true).build();

        }
        account.get().setIsLocked(true);
        accountRepository.save(account.get());
        return BaseResponse.builder().status(SUCCESS).message(MI0009).errorCode(false).build();


    }

}
