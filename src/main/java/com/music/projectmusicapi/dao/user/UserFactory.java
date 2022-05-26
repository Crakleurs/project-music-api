package com.music.projectmusicapi.dao.user;

import com.music.projectmusicapi.dto.UserDto;
import com.music.projectmusicapi.entities.UserEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class UserFactory {
    public static UserEntity toUser(UserDto userDto) {

        UserEntity userEntity = new UserEntity();
        userEntity.setCoins(100F);
        userEntity.setEmail(userDto.getEmail());
        userEntity.setFirstname(userDto.getFirstname());
        userEntity.setName(userDto.getName());
        userEntity.setIsAdmin(false);
        userEntity.setIsBanned(false);
        userEntity.setPassword(BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt()));
        return userEntity;
    }
}
