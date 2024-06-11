package dev9.lapco.request;

import dev9.lapco.constant.ERole;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeletedUserRequest {

    private String phoneNumber;

    private String username;

    private ERole role;
}
