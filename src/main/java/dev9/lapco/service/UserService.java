package dev9.lapco.service;

import dev9.lapco.request.CreatedUserRequest;
import dev9.lapco.request.DeletedUserRequest;
import dev9.lapco.response.BaseResponse;
import dev9.lapco.response.CreatedUserResponse;
import dev9.lapco.response.GetUserResponse;

public interface UserService {
    CreatedUserResponse createdNew(CreatedUserRequest createdUserRequest);

    GetUserResponse getUserList();

    BaseResponse deleteUser(DeletedUserRequest deletedUserRequest);
}
