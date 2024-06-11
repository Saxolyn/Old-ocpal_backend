package dev9.lapco.commonUtil;

import dev9.lapco.entity.AccountEntity;
import dev9.lapco.entity.AdminEntity;
import dev9.lapco.entity.StudentEntity;
import dev9.lapco.entity.TeacherEntity;
import dev9.lapco.request.CreatedUserRequest;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ConvertedUtil {

    @Value("${default.image.address}")
    private String defaultImageAddress;

    private final OcpalUtil lapcoUtil;

    public Object convertNewUserRequestToStudentEntity(CreatedUserRequest newUserRequest) {
        switch (newUserRequest.getRole()) {
            case ADMIN:
                return AdminEntity.builder()
                        .firstName(Strings.isBlank(newUserRequest.getFirstName()) ? null : newUserRequest.getFirstName())
                        .lastName(Strings.isBlank(newUserRequest.getLastName()) ? null : newUserRequest.getLastName())
                        .username(Strings.isBlank(newUserRequest.getUsername()) ? null : newUserRequest.getUsername())
                        .address(Strings.isBlank(newUserRequest.getAddress()) ? null : newUserRequest.getAddress())
                        .email(Strings.isBlank(newUserRequest.getEmail()) ? null : newUserRequest.getEmail())
                        .phoneNumber(newUserRequest.getPhoneNumber())
                        .IdentifyNo(newUserRequest.getIdentityNumber())
                        .image(defaultImageAddress)
                        //TODO :chưa có logic tạo mã admin
                        .userCode("admin")
                        .isLock(false)
                        .isDeleted(false)
                        .password(null)
                        .build();

            case TEACHER:
                return TeacherEntity.builder()
                        .firstName(Strings.isBlank(newUserRequest.getFirstName()) ? null : newUserRequest.getFirstName())
                        .lastName(Strings.isBlank(newUserRequest.getLastName()) ? null : newUserRequest.getLastName())
                        .username(Strings.isBlank(newUserRequest.getUsername()) ? null : newUserRequest.getUsername())
                        .address(Strings.isBlank(newUserRequest.getAddress()) ? null : newUserRequest.getAddress())
                        .email(Strings.isBlank(newUserRequest.getEmail()) ? null : newUserRequest.getEmail())
                        .phoneNumber(newUserRequest.getPhoneNumber())
                        .IdentifyNo(newUserRequest.getIdentityNumber())
                        .image(defaultImageAddress)
                        .userCode(lapcoUtil.teacherCodeGenerator(newUserRequest.getIdentityNumber()))
                        .isDeleted(false)
                        //TODO :chưa có logic tạo mã lớp
                        .classCodes(List.of("0001"))
                        //TODO :chưa có logic tạo mã chương trình
                        .programCodes(List.of("0001"))
                        .isLock(false)
                        .build();

            case STUDENT:
            default:
                return StudentEntity.builder()
                        .firstName(Strings.isBlank(newUserRequest.getFirstName()) ? null : newUserRequest.getFirstName())
                        .lastName(Strings.isBlank(newUserRequest.getLastName()) ? null : newUserRequest.getLastName())
                        .username(Strings.isBlank(newUserRequest.getUsername()) ? null : newUserRequest.getUsername())
                        .address(Strings.isBlank(newUserRequest.getAddress()) ? null : newUserRequest.getAddress())
                        .email(Strings.isBlank(newUserRequest.getEmail()) ? null : newUserRequest.getEmail())
                        .phoneNumber(newUserRequest.getPhoneNumber())
                        .IdentifyNo(newUserRequest.getIdentityNumber())
                        .image(defaultImageAddress)
                        .userCode(lapcoUtil.studentCodeGenerator(newUserRequest.getIdentityNumber()))
                        .isDeleted(false)
                        .isRemote(false)
                        .completionRate(0F)
                        .build();
        }


    }

    public AccountEntity convertToAccountEntity(CreatedUserRequest newUserRequest) {
        return AccountEntity.builder()
                .username(newUserRequest.getUsername())
                .phoneNumber(newUserRequest.getPhoneNumber())
                .password(newUserRequest.getIdentityNumber())
                .role(newUserRequest.getRole())
                .isDeleted(false)
                .build();
    }


}
