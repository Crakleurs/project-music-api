package com.music.projectmusicapi.services;

import com.music.projectmusicapi.dao.user.UserFactory;
import com.music.projectmusicapi.dto.UserDto;
import com.music.projectmusicapi.entities.UserEntity;
import com.music.projectmusicapi.dao.user.UserRepository;
import com.music.projectmusicapi.exceptions.HttpBadRequestException;
import com.music.projectmusicapi.exceptions.HttpNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersService implements UserDetailsService {
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

        if (!userEntity.isPresent())
            throw new HttpNotFoundException("L'utilisateur avec l'id " + id + "n'a pas été trouvé");

        return userEntity.get();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserEntity> userEntity = this.userRepository.findByEmail(email);

        if (!userEntity.isPresent())
            throw new UsernameNotFoundException("L'utilistaeur n'a pas été trouvé");
        UserEntity user = userEntity.get();
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        SimpleGrantedAuthority simpleGrantedAuthority = user.getIsAdmin() ? new SimpleGrantedAuthority("ADMIN") : new SimpleGrantedAuthority("USER");
        authorities.add(simpleGrantedAuthority);
        return new User(user.getEmail(), user.getPassword(), authorities);
    }
}
