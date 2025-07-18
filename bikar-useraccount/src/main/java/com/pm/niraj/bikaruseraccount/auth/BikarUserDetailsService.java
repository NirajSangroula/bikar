package com.pm.niraj.bikaruseraccount.auth;

import com.pm.niraj.bikaruseraccount.model.User;
import com.pm.niraj.bikaruseraccount.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BikarUserDetailsService implements UserDetailsService {
    private final UserRepository repository;

    @Autowired
    public BikarUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = repository.findByEmail(email);
        Optional<UserDetails> userDetails = user.map(u ->
            org.springframework.security.core.userdetails.User
                    .withUsername(u.getEmail())
                    .password(u.getPassword())
                    .roles(u.getRole()).build()
        );
        return userDetails.orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
