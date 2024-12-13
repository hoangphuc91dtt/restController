package com.example.buoi1.sercurity;

import com.example.buoi1.model.Role;
import com.example.buoi1.model.UserDemo;

import com.example.buoi1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Service
public class UserDetailsServiceIml implements UserDetailsService {
    @Autowired
    private UserRepository userRepository; // Tiêm UserRepository vào

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Gọi phương thức findByEmail từ userRepository
        UserDemo user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + username);
        }

        // Log thông tin user và role
        System.out.println("User found: " + user.getEmail());
        user.getRoles().forEach(role -> System.out.println("Role loaded: " + role.getName()));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                true, true, true, true,
                getAuthorities(user.getRoles())
        );
    }

    private List<GrantedAuthority> getAuthorities(Set<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            String authority = "ROLE_" + role.getName();
            authorities.add(new SimpleGrantedAuthority(authority));
            System.out.println("GrantedAuthority: " + authority);
        }
        return authorities;
    }

}

