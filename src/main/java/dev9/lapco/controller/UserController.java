package dev9.lapco.controller;

import dev9.lapco.request.CreatedUserRequest;
import dev9.lapco.request.DeletedUserRequest;
import dev9.lapco.response.BaseResponse;
import dev9.lapco.response.CreatedUserResponse;
import dev9.lapco.response.GetUserResponse;
import dev9.lapco.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/add")
    public CreatedUserResponse addUser(@RequestBody CreatedUserRequest createdUserRequest) {
        return userService.createdNew(createdUserRequest);
    }

    @GetMapping(value = "/get")
    public GetUserResponse getUserList (){
        return userService.getUserList();
    }

    @PutMapping("/delete-one")
    public BaseResponse deleteUser (@RequestBody DeletedUserRequest deletedUserRequest) {
        return userService.deleteUser(deletedUserRequest);
    }

    @PutMapping("/lock")
    public BaseResponse lockAccount(@RequestParam String phoneNumber){
        return userService.lock(phoneNumber);
    }

}
