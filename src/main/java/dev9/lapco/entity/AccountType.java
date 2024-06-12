package dev9.lapco.entity;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class AccountType extends BaseEntity {

    private String username;

    private String firstName;

    private String lastName;

    private String address;

    private String email;

    @NotNull
    @NonNull
    private String phoneNumber;

    /**
     * Số căn cước công dân
     */
    @NotNull
    private String identifyNo;

    /**
     * Đường dẫn ảnh đại diện
     */
    private String image;

    private String userCode;

    private boolean isLock;

}
