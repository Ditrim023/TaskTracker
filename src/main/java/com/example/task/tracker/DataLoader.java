package com.example.task.tracker;

import com.example.task.tracker.model.entity.TrackerRole;
import com.example.task.tracker.model.entity.TrackerUser;
import com.example.task.tracker.repository.TrackerRoleRepository;
import com.example.task.tracker.repository.TrackerUserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {
    private final TrackerUserRepository trackerUserRepository;
    private final TrackerRoleRepository trackerRoleRepository;

    private final TrackerRole admin = new TrackerRole("ROLE_ADMIN");
    private final TrackerRole user = new TrackerRole("ROLE_USER");
    private static final String PASS = "123456";
    private final List<TrackerUser> userList = new ArrayList<>();


    public DataLoader(TrackerUserRepository trackerUserRepository, TrackerRoleRepository trackerRoleRepository) {
        this.trackerUserRepository = trackerUserRepository;
        this.trackerRoleRepository = trackerRoleRepository;
    }

    @Override
    public void run(ApplicationArguments args){
        insertRoles();
        insertUser();
    }

    void insertRoles(){
        trackerRoleRepository.save(admin);
        trackerRoleRepository.save(user);
    }

    void insertUser() {
        TrackerRole userRole = trackerRoleRepository.getOne(2L);
        TrackerUser user1 = new TrackerUser("user1", "Arnold", "Schwarzenegger", "arnold@gmail.com", new BCryptPasswordEncoder(10).encode(PASS));
        user1.setRole(userRole);
        userList.add(user1);
        TrackerUser user2 = new TrackerUser("user2", "Sylvester", "Stallone", "sylvester@gmail.com", new BCryptPasswordEncoder(10).encode(PASS));
        user2.setRole(userRole);
        userList.add(user2);
        TrackerUser user3 = new TrackerUser("user3", "Jason", "Statham", "jason@gmail.com", new BCryptPasswordEncoder(10).encode(PASS));
        user3.setRole(userRole);
        userList.add(user3);
        TrackerUser user4 = new TrackerUser("user4", "Dolph", "Lundgren", "dolph@gmail.com", new BCryptPasswordEncoder(10).encode(PASS));
        user4.setRole(userRole);
        userList.add(user4);
        TrackerUser user5 = new TrackerUser("user5", "Jackie", "Chan", "jackie@gmail.com", new BCryptPasswordEncoder(10).encode(PASS));
        user5.setRole(userRole);
        userList.add(user5);
        trackerUserRepository.saveAll(userList);
    }
}
