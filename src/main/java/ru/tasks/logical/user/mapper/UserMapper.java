package ru.tasks.logical.user.mapper;

import ru.tasks.logical.user.dto.UserInfo;
import ru.tasks.logical.user.entity.User;

public class UserMapper {

    public static UserInfo mapUserToUserInfo(User user) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(user.getId());
        userInfo.setEmail(user.getEmail());
        userInfo.setFirstName(user.getFirstName());
        userInfo.setLastName(user.getLastName());
        return userInfo;
    }
}
