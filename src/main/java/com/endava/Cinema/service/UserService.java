package com.endava.Cinema.service;

import com.endava.Cinema.exceptions.notFoundExceptions.ClientNotFoundException;
import com.endava.Cinema.exceptions.userException.EmailExistsException;
import com.endava.Cinema.exceptions.userException.InvalidPasswordException;
import com.endava.Cinema.exceptions.userException.InvalidRoleException;
import com.endava.Cinema.exceptions.userException.UserNameExistsException;
import com.endava.Cinema.model.User;
import com.endava.Cinema.vo.UserVo;

import javax.xml.bind.ValidationException;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserByEmail(String email);

    User getUserByUserName(String userName);

    User getUserById(Integer idClient) throws ClientNotFoundException;

    User saveOrUpdateUser(UserVo user,String role) throws UserNameExistsException, EmailExistsException, InvalidPasswordException, InvalidRoleException, ValidationException;

    void deleteUser(Integer idUser) throws ClientNotFoundException;


}
