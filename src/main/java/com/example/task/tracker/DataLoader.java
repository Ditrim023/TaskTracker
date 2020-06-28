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
        TrackerUser user0 = new TrackerUser("user1", "Arnold", "Schwarzenegger", "arnold@gmail.com", new BCryptPasswordEncoder(10).encode("123456"));
        user0.setRole(trackerRoleRepository.getOne(2L));
        trackerUserRepository.save(user0);
//        trackerUserRepository.save(new TrackerUser("admin2", "new2", "test2", "example2@gmail.com", "123456"));
//        trackerUserRepository.saveA(userList);
    }
}
