package dev9.lapco.response;

import dev9.lapco.constant.ERole;
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
public class StudentResponse extends BaseResponse {

    private String username;

    private String phoneNumber;

    private ERole role;

}
