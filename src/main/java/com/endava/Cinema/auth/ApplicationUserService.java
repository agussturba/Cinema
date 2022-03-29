package com.endava.Cinema.auth;

import com.endava.Cinema.model.User;
import com.endava.Cinema.persistance.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService implements UserDetailsService {
    @Autowired
    private  UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
       if (user==null){
           throw new UsernameNotFoundException("User Not Found");
       }
       UserDetails userDetails = new ApplicationUser(user);
       return userDetails;
    }
}
