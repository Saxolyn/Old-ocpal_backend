package dev9.lapco.service;

import dev9.lapco.dto.AccountDTO;
import dev9.lapco.response.LoginResponse;

public interface AccountService {

    LoginResponse login(AccountDTO account);

    LoginResponse loginByAdmin(String username, String password);


}
