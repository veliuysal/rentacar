package com.bilgeadam.rentacar.services.auth;

import com.bilgeadam.rentacar.config.UserInfoUserDetails;
import com.bilgeadam.rentacar.entities.Personal;
import com.bilgeadam.rentacar.repository.PersonalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {

    @Autowired
    private PersonalRepository personalRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Personal> userInfo = personalRepository.findByFirstName(username);

        return userInfo.map(UserInfoUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));

    }
}
