package dev9.lapco.request;

import dev9.lapco.constant.ERole;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatedUserRequest {

    @Schema(example = "not set yet" )
    private String firstName;

    @Schema(example = "not set yet" )
    private String lastName;

    @Schema(example = "not set yet" )
    private String email;

    @NotNull
    private String phoneNumber;

    @Schema(example = "not set yet" )
    private String username;

    @NotNull
    @Schema(example = "001091001240" )
    private String identityNumber;

    @Schema(example = "not set yet" )
    private String address;

    @Schema(example = "not set yet" )
    private String joinedClass;

    @Schema(example = "not set yet" )
    private String mainTeacher;

    @Schema(example = "not set yet" )
    private String ownClass;

    @NotNull
    private ERole role;
}
