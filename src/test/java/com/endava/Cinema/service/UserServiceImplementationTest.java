package com.endava.Cinema.service;

import com.endava.Cinema.model.User;
import com.endava.Cinema.persistance.UserRepository;
import com.endava.Cinema.vo.UserVo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class UserServiceImplementationTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImplementation userServiceImplementation;
    private User testUser;
    private User testUser1;
    private List<User> userList;
    private UserVo testUserVo;

    @BeforeEach
    void setUp() {
        userList = new ArrayList<>();
        testUser1 = new User("asturba", "ADMIN", true, "agus@mail.com");
        testUser = new User("kcowes", "CLIENT", true, "kenneth@mail.com");
        testUserVo = new UserVo(testUser);
        userList.add(testUser);
        userList.add(testUser1);
    }

    @Test
    void getAllUsers() {
        when(userRepository.findAll()).thenReturn(userList);
        List<User> users = userServiceImplementation.getAllUsers();
        assertEquals(2,users.size());
        assertNotNull(users);
        assertEquals(testUser,users.get(0));
    }

    @Test
    void getUserByEmail() {
        when(userRepository.findByEmail(any(String.class))).thenReturn(testUser1);
        User user = userServiceImplementation.getUserByEmail("agus@mail.com");
        assertNotNull(user);
        assertEquals(testUser1,user);
    }

    @Test
    void getUserByUserName() {
    }

    @Test
    void saveOrUpdateUser() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void getUserById() {
    }
}