package com.aromano.authentication.controller;

import com.aromano.authentication.dao.UserRepository;
import com.aromano.authentication.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final String ROLE_ADMIN = "ROLE_ADMIN";
    private static final String ROLE_USER = "ROLE_USER";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Secured({ROLE_ADMIN})
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<UserEntity> save(@RequestBody UserEntity user){
        user = this.userRepository.save(user);
        return new ResponseEntity<UserEntity>(user, HttpStatus.OK);
    }

    @Secured({ROLE_ADMIN})
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ResponseEntity<UserEntity> update(@RequestBody UserEntity user){

        if (user.getId() == null){
            throw new HttpClientErrorException(HttpStatus.BAD_GATEWAY);
        }
        user = this.userRepository.save(user);
        return new ResponseEntity<UserEntity>(user, HttpStatus.OK);
    }

    @Secured({ROLE_USER, ROLE_ADMIN})
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Page<UserEntity>> list(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ){
        Pageable pageable = PageRequest.of(page, size, Sort.by("name"));
        return new ResponseEntity<Page<UserEntity>>(this.userRepository.findAll(pageable), HttpStatus.OK);
    }

}
