package dev9.lapco.response;

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
    private String status;

    private String message;

    @Builder.Default
    private String timestamp = OcpalUtil.localDateTimeToString(LocalDateTime.now());

    private String path;

    @Builder.Default
    private Boolean errorCode = false;

}
