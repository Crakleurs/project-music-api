package com.music.projectmusicapi.services;

import com.music.projectmusicapi.dao.user.UserFactory;
import com.music.projectmusicapi.dto.UserDto;
import com.music.projectmusicapi.entities.UserEntity;
import com.music.projectmusicapi.dao.user.UserRepository;
import com.music.projectmusicapi.exceptions.HttpBadRequestException;
import com.music.projectmusicapi.exceptions.HttpNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UserRepository userRepository;

    public UserEntity createUser(UserDto usersDTO) {
        if (!usersDTO.getPassword().equals(usersDTO.getConfirmPassword()))
            throw new HttpBadRequestException("Les deux mots de passes sont différents");

        UserEntity userEntity = UserFactory.toUser(usersDTO);
        return this.userRepository.save(userEntity);

    }

    public void deleteUser(Long id) {
        UserEntity userEntity = getUser(id);
        this.userRepository.delete(userEntity);
    }

    public Iterable<UserEntity> findAll() {
        return this.userRepository.findAll();
    }

    public UserEntity findOne(Long id) {
        return this.getUser(id);

    }

    public UserEntity getUser(Long id) {
        Optional<UserEntity> userEntity = this.userRepository.findById(id);

        if (userEntity.isEmpty())
            throw new HttpNotFoundException("L'utilisateur avec l'id " + id + "n'a pas été trouvé");

        return userEntity.get();
    }
}
