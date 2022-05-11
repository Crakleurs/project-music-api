package com.music.projectmusicapi.controllers;

import com.music.projectmusicapi.dto.UserDto;
import com.music.projectmusicapi.entities.UserEntity;
import com.music.projectmusicapi.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("users")
@RequiredArgsConstructor
public class UsersController {
    private final UsersService usersService;

    @PostMapping()
    public UserEntity createUser(@RequestBody UserDto userDTO) {
        return usersService.createUser(userDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        usersService.deleteUser(id);
    }

    @GetMapping("")
    public Iterable<UserEntity> findAll() {
        return usersService.findAll();
    }

    @GetMapping("/{id}")
    public UserEntity findOne(@PathVariable Long id) {
        return usersService.findOne(id);
    }
}
