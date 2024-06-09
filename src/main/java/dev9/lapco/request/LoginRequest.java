package dev9.lapco.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @Schema(example = "0964204669")
    private String phoneNumber;

    @Schema(example = "lapco")
    private String password;
}
