package com.pm.niraj.bikaruseraccount.auth;

import com.pm.niraj.bikaruseraccount.model.User;
import com.pm.niraj.bikaruseraccount.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Profile("auth_test")
@Component
public class AdminUserRunnable implements CommandLineRunner{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;


    @Override
    public void run(String... args) throws Exception {
        userRepository.save(User.builder().email("user@email.com").firstName("User")
                .lastName("Buser").password(bCryptPasswordEncoder.encode("password"))
                .role("ADMIN").build());
        System.out.println("Yes this ran");
    }
}
