package com.example.task.tracker.security.config;

import com.example.task.tracker.model.entity.TrackerUser;
import com.example.task.tracker.service.TrackerUserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    private final TrackerUserService trackerUserService;

    public UserService(TrackerUserService trackerUserService) {
        this.trackerUserService = trackerUserService;
    }

    @Override
    public UserDetails loadUserByUsername(String username){
        Optional<TrackerUser> user = trackerUserService.findByUsername(username);

        if (!user.isPresent()) {
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        }
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(user.get().getRole().getName()));
        return new org.springframework.security.core.userdetails.User(user.get().getUsername(), user.get().getPassword(), roles);
    }
}
