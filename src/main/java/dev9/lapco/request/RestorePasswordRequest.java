package dev9.lapco.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestorePasswordRequest {

    @Schema(example = "0964204669")
    private String phoneNumber;
}
