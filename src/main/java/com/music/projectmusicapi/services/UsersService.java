package com.music.projectmusicapi.services;

import com.music.projectmusicapi.dao.user.UserFactory;
import com.music.projectmusicapi.dto.UserDto;
import com.music.projectmusicapi.entities.UserEntity;
import com.music.projectmusicapi.dao.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.xml.ws.http.HTTPException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UserRepository userRepository;

    public UserEntity createUser(UserDto usersDTO) {
        if (!usersDTO.getPassword().equals(usersDTO.getConfirmPassword()))
            throw new HTTPException(400);

        UserEntity userEntity = UserFactory.toUser(usersDTO);
        return  this.userRepository.save(userEntity);

    }

    public void deleteUser(Long id) {
        Optional<UserEntity> userEntity = this.userRepository.findById(id);
        if (!userEntity.isPresent())
            throw new HTTPException(400);

        this.userRepository.delete(userEntity.get());
    }

    public Iterable<UserEntity> findAll() {
        return this.userRepository.findAll();
    }

    public UserEntity findOne(Long id) {
        Optional<UserEntity> userEntity = this.userRepository.findById(id);

        if (!userEntity.isPresent())
            throw new HTTPException(400);

        return userEntity.get();

    }
}
