package dev9.lapco.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChangPasswordRequest {
     private String oldPassword;
     private String newPassword;
     private String confirmPassword;

}
