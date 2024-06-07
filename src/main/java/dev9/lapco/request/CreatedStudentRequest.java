package dev9.lapco.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatedStudentRequest {

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
