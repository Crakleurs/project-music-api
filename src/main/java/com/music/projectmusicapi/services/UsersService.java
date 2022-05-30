package com.music.projectmusicapi.services;

import com.music.projectmusicapi.dao.CategoryRepository;
import com.music.projectmusicapi.dao.user.UserFactory;
import com.music.projectmusicapi.dto.UserDto;
import com.music.projectmusicapi.entities.CategoryEntity;
import com.music.projectmusicapi.entities.UserEntity;
import com.music.projectmusicapi.dao.user.UserRepository;
import com.music.projectmusicapi.exceptions.HttpBadRequestException;
import com.music.projectmusicapi.exceptions.HttpNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
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
    private final UserFactory userFactory;
    private final CategoryRepository categoryRepository;


    public UserEntity createUser(UserDto usersDTO) {
        UserEntity userEntity = userFactory.toUser(usersDTO);
        if (userEntity.getPassword().length() < 4)
            throw new HttpBadRequestException("Le mot de passe doit contenir au moins 4 caractères");

        Optional<UserEntity> oldUser = this.userRepository.findByEmail(usersDTO.getEmail());
        if (oldUser.isPresent())
            throw new HttpBadRequestException("L'addresse mail est déjà utilisée");

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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("yo");

        Optional<UserEntity> userEntity = this.userRepository.findByEmail(username);

        if (!userEntity.isPresent())
            throw new UsernameNotFoundException("L'utilistaeur n'a pas été trouvé");
        UserEntity user = userEntity.get();
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        SimpleGrantedAuthority simpleGrantedAuthority = user.getIsAdmin() ? new SimpleGrantedAuthority("ADMIN") : new SimpleGrantedAuthority("USER");
        authorities.add(simpleGrantedAuthority);
        return new User(user.getEmail(), user.getPassword(), authorities);
    }

    public UserEntity findMe(Authentication authentication) {
        Optional<UserEntity> userEntity = this.userRepository.findByEmail(authentication.getName());

        if (!userEntity.isPresent())
            throw new HttpNotFoundException("L'utilisateur avec l'email " + authentication.getName() + "n'a pas été trouvé");

        return userEntity.get();
    }

    public void resetDatabase() {
        UserDto adminDto = new UserDto();
        adminDto.setFirstName("Admin");
        adminDto.setLastName("Admin");
        adminDto.setEmail("admin@gmail.com");
        adminDto.setPassword("admin");

        UserEntity admin = userFactory.toUser(adminDto);
        admin.setIsAdmin(true);
        admin.setCoins(99999F);
        admin.setIsBanned(false);
        this.userRepository.save(admin);

        String[] names = {"dj", "enceintes", "instruments"};
        for (String name : names) {
            CategoryEntity category = new CategoryEntity();
            category.setName(name);
            this.categoryRepository.save(category);
        }



    }
}
