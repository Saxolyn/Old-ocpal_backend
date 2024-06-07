package dev9.lapco.response;

import dev9.lapco.commonUtil.LapcoUtil;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class BaseResponse {
    private String status;

    private String message;

    @Builder.Default
    private String timestamp = LapcoUtil.localDateTimeToString(LocalDateTime.now());

    private String path;

    /*
    false là thành công, true là lỗi
     */
    private Boolean errorCode;

//    BaseResponse() {
//        this.timestamp = LapcoUtil.localDateTimeToString(LocalDateTime.now());
//    }



}
