package dev9.lapco.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestorePasswordRequest {
    private String phoneNumber;
}
