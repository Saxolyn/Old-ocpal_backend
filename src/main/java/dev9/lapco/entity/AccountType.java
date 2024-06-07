package dev9.lapco.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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

    private String phoneNumber;

    /**
     * Số căn cước công dân
     */
    private String IdentifyNo;

    /**
     * Đường dẫn ảnh đại diện
     */
    private String image;

    private String userCode;

}
