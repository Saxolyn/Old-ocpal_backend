package dev9.lapco.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev9.lapco.util.OcpalUtil;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class BaseResponse {
    @JsonIgnore
    private String status;

    @JsonIgnore
    private String message;

    @Builder.Default
    @JsonIgnore
    private String timestamp = OcpalUtil.localDateTimeToString(LocalDateTime.now());

    @JsonIgnore
    private String path;

    @Builder.Default
    @JsonIgnore
    private Boolean errorCode = false;

}
