package com.endava.Cinema.controllers;

import com.endava.Cinema.exceptions.userException.EmailExistsException;
import com.endava.Cinema.exceptions.userException.InvalidPasswordException;
import com.endava.Cinema.exceptions.userException.InvalidRoleException;
import com.endava.Cinema.exceptions.userException.UserNameExistsException;
import com.endava.Cinema.model.User;
import com.endava.Cinema.vo.UserVo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.endava.Cinema.service.UserService;

import javax.xml.bind.ValidationException;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }
    @GetMapping("/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserByEmail(email));
    }
    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUserName(@PathVariable String userName){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserByUserName(userName));
    }
    @PostMapping
    public ResponseEntity<User> saveUserClient(@RequestBody UserVo userVo) throws ValidationException, UserNameExistsException, InvalidPasswordException, InvalidRoleException, EmailExistsException {
        return ResponseEntity.status(HttpStatus.OK).body(userService.saveOrUpdateUser(userVo,"ROLE_CLIENT"));
    }
    @PostMapping("/admin")
    public ResponseEntity<User> saveUserAdmin(@RequestBody UserVo userVo) throws ValidationException, UserNameExistsException, InvalidPasswordException, InvalidRoleException, EmailExistsException {
        return ResponseEntity.status(HttpStatus.OK).body(userService.saveOrUpdateUser(userVo,"ROLE_ADMIN"));
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody UserVo userVo) throws ValidationException, UserNameExistsException, InvalidPasswordException, InvalidRoleException, EmailExistsException {
        return ResponseEntity.status(HttpStatus.OK).body(userService.saveOrUpdateUser(userVo,userVo.getRole()));
    }
}
