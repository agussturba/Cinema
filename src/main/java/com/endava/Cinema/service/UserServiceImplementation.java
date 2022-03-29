package com.endava.Cinema.service;

import com.endava.Cinema.exceptions.notFoundExceptions.ClientNotFoundException;
import com.endava.Cinema.exceptions.userException.EmailExistsException;
import com.endava.Cinema.exceptions.userException.InvalidPasswordException;
import com.endava.Cinema.exceptions.userException.InvalidRoleException;
import com.endava.Cinema.exceptions.userException.UserNameExistsException;
import com.endava.Cinema.model.User;
import com.endava.Cinema.utilities.UsersValidations;
import com.endava.Cinema.vo.UserVo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.endava.Cinema.persistance.UserRepository;

import javax.xml.bind.ValidationException;
import java.util.List;

@Service
public class UserServiceImplementation implements UserService {
    private final UserRepository userRepository;

    public final PasswordEncoder passwordEncoder;

    public UserServiceImplementation(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User getUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public User saveOrUpdateUser(UserVo user, String role) throws UserNameExistsException, EmailExistsException, InvalidPasswordException, InvalidRoleException, ValidationException {
        existsUser(user);
        user.setRole(role);
        UsersValidations.validateUserData(user);
        return userRepository.save(user.toModel());
    }

    @Override
    public void deleteUser(Integer idUser) throws ClientNotFoundException {
        userRepository.delete(this.getUserById(idUser));

    }

    @Override
    public User getUserById(Integer idUser) throws ClientNotFoundException {
        return userRepository.findById(idUser).orElseThrow(ClientNotFoundException::new);
    }
    private void existsUser(UserVo userVo) throws UserNameExistsException, EmailExistsException {
        if (this.getUserByUserName(userVo.getUserName())!=null){
            throw new UserNameExistsException();
        }
        if(this.getUserByEmail(userVo.getEmail())!=null){
            throw new EmailExistsException();
        }
    }

}
