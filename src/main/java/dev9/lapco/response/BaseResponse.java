package dev9.lapco.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import dev9.lapco.util.OcpalUtil;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse {

    private String status;

    private String message;

    @Builder.Default
    private String timestamp = OcpalUtil.localDateTimeToString(LocalDateTime.now());

    @Builder.Default
    private Boolean errorCode = false;

}
