package com.music.projectmusicapi.dao.user;

import com.music.projectmusicapi.dto.UserDto;
import com.music.projectmusicapi.entities.UserEntity;

public class UserFactory {
    public static UserEntity toUser(UserDto userDto) {

        UserEntity userEntity = new UserEntity();
        userEntity.setCoins(100F);
        userEntity.setEmail(userDto.getEmail());
        userEntity.setFirstname(userDto.getFirstname());
        userEntity.setName(userDto.getName());
        userEntity.setIsAdmin(false);
        userEntity.setIsBanned(false);
        userEntity.setPassword(userDto.getPassword());
        return userEntity;
    }
}
