package dev9.lapco.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDTO {

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String username;

    private String identityNumber;

    private String address;

    private String joinedClass;

    private String mainTeacher;
}
