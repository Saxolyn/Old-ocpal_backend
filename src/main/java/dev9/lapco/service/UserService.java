package dev9.lapco.service;

import dev9.lapco.request.CreatedUserRequest;
import dev9.lapco.response.CreatedUserResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface UserService {
    CreatedUserResponse createdNew(CreatedUserRequest createdUserRequest);
}
