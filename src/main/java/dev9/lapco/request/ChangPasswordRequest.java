package dev9.lapco.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChangPasswordRequest {

     @Schema(example = "lapco")
     private String oldPassword;

     @Schema(example = "Abc@1234")
     private String newPassword;

     @Schema(example = "Abc@1234")
     private String confirmPassword;

}
