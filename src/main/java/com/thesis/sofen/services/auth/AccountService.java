package com.thesis.sofen.services.auth;

import com.thesis.sofen.entities.User;
import com.thesis.sofen.repositories.RoleRepository;
import com.thesis.sofen.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Qualifier("passwordEncoder")
    private final PasswordEncoder passwordEncoder;


    public User find(String email) {
        return userRepository.findByEmail(email).get();
    }

    public User update(String email, User command) {
        System.out.println(command);
        User user = userRepository.findByEmail(email).get();
        user.setFullName(command.getFullName());
        user.setPhoneCode(command.getPhoneCode());
        user.setAvatar(command.getAvatar());
        user.setAddress(command.getAddress());
        user.setPhoneNumber(command.getPhoneNumber());
        user.setWomen(command.isWomen());
        user.setDateOfBirth(command.getDateOfBirth());
        if(command.getPassword() != null && command.getPassword().length() >=8)
         user.setPassword(passwordEncoder.encode(command.getPassword()));
        return userRepository.save(user);
    }

}
