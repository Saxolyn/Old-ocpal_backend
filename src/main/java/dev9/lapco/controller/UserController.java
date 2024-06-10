package dev9.lapco.controller;

import dev9.lapco.request.CreatedUserRequest;
import dev9.lapco.response.CreatedUserResponse;
import dev9.lapco.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("add-user")
    public CreatedUserResponse addUser(@RequestBody CreatedUserRequest createdUserRequest) {
        return userService.createdNew(createdUserRequest);
    }
}
