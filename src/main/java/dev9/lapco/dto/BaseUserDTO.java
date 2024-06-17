package dev9.lapco.dto;

import dev9.lapco.constant.ERole;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class BaseUserDTO implements Serializable {

    private String phoneNumber;

    private String username;

    private ERole role;

    private Boolean isDeleted;

    private Boolean isLocked;
}
