package dev9.lapco.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dev9.lapco.request.CreatedUserRequest;
import dev9.lapco.response.CreatedUserResponse;
import dev9.lapco.service.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("add-user")
    public CreatedUserResponse addUser(@RequestBody CreatedUserRequest createdUserRequest) {
        return userService.createdNew(createdUserRequest);
    }
}
