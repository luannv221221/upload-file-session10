package com.ra.controller;

import com.ra.dto.request.UserRequestDTO;
import com.ra.dto.response.UserResponseDTO;
import com.ra.model.User;
import com.ra.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    UserService userService;
    @PostMapping("/register")
    public ResponseEntity<?>register(@RequestBody User user){
        return new ResponseEntity<>(userService.register(user), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?>login(@RequestBody UserRequestDTO userRequestDTO){

        UserResponseDTO userResponseDTO=userService.login(userRequestDTO);
        return new ResponseEntity<>(userResponseDTO,HttpStatus.OK);
    }

}
