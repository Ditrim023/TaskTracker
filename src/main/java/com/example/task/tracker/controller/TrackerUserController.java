package com.example.task.tracker.controller;

import com.example.task.tracker.model.dto.AuthenticationRequestDto;
import com.example.task.tracker.model.dto.NewTrackerUserDto;
import com.example.task.tracker.model.dto.TrackerUserDto;
import com.example.task.tracker.model.entity.TrackerUser;
import com.example.task.tracker.security.jwt.JwtTokenProvider;
import com.example.task.tracker.service.TrackerUserService;
import com.example.task.tracker.utils.TrackerConverter;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class TrackerUserController {
    private final TrackerUserService trackerUserService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private TrackerConverter trackerConverter = new TrackerConverter();

    public TrackerUserController(TrackerUserService trackerUserService, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.trackerUserService = trackerUserService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @GetMapping("/users")
    public List<TrackerUserDto> displayAllUsers() {
        List<TrackerUser> userList = trackerUserService.getAllUsers();
        return userList.stream().map(trackerConverter::convertUserEntityToDto).collect(Collectors.toList());
    }

    @GetMapping("/users/date")
    public List<TrackerUserDto> getAllOrderedByDate(){
        List<TrackerUser> userList = trackerUserService.findAllOrderByDateCreatedDesc();
        return userList.stream().map(trackerConverter::convertUserEntityToDto).collect(Collectors.toList());
    }

    @PostMapping("/registration")
    public ResponseEntity createUser(@RequestBody NewTrackerUserDto trackerUserDto) {
        trackerUserService.createTrackerUser(trackerUserDto);
        return ResponseEntity.ok("User created");
    }

    @PatchMapping("/user/{id}")
    public ResponseEntity userUpdate(@PathVariable Long id, @RequestBody NewTrackerUserDto trackerUserDto) {
        trackerUserService.updateTrackerUser(id, trackerUserDto);
        return ResponseEntity.ok("User updated");
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity deleteUserById(@PathVariable Long id) {
        trackerUserService.deleteTrackerUser(id);
        return ResponseEntity.ok("User deleted");
    }

    @GetMapping("/user/{username}")
    public TrackerUserDto getUser(@PathVariable String username) {
        Optional<TrackerUser> user = trackerUserService.findByUsername(username);
        if (!user.isPresent()) {
            return new TrackerUserDto();
        } else {
            return trackerConverter.convertUserEntityToDto(user.get());
        }
    }


    @PostMapping("login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            String username = requestDto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            Optional<TrackerUser> user = trackerUserService.findByUsername(username);

            if (!user.isPresent()) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }

            String token = jwtTokenProvider.createToken(username, user.get().getRole());

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

}
