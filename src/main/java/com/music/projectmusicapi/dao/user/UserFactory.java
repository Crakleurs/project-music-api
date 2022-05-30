package com.music.projectmusicapi.dao.user;

import com.music.projectmusicapi.dto.UserDto;
import com.music.projectmusicapi.entities.UserEntity;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserFactory {
    private final PasswordEncoder passwordEncoder;

    public UserFactory(@Lazy PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity toUser(UserDto userDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setCoins(100F);
        userEntity.setEmail(userDto.getEmail());
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setIsAdmin(false);
        userEntity.setIsBanned(false);
        userEntity.setPassword(this.passwordEncoder.encode(userDto.getPassword()));
        return userEntity;
    }
}
