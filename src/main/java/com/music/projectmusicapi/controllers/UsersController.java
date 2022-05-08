package com.music.projectmusicapi.controllers;

import com.music.projectmusicapi.entities.UserEntity;
import com.music.projectmusicapi.repositories.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UsersController {
    private final UserRepository userRepository;
    public UsersController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("me")
    public String hello() {
        UserEntity test = new UserEntity();
        test.setCoins((long) 100.0);
        test.setEmail("eti.faviere@gmail.com");
        test.setIsAdmin(false);
        test.setIsBanned(true);
        test.setPassword("salut");
        userRepository.save(test);
        return "Salut Etienne";
    }

    @GetMapping("all")
    public Iterable<UserEntity> all() {
        return userRepository.findAll();
    }
}
