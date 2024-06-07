package dev9.lapco.response;

import dev9.lapco.entity.AccountEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class LoginResponse extends BaseResponse {

    private AccountEntity account;

    private String token;

    }
