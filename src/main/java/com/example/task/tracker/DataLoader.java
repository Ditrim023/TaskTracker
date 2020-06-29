package com.example.task.tracker;

import com.example.task.tracker.model.entity.Status;
import com.example.task.tracker.model.entity.TrackerRole;
import com.example.task.tracker.model.entity.TrackerTask;
import com.example.task.tracker.model.entity.TrackerUser;
import com.example.task.tracker.repository.TrackerRoleRepository;
import com.example.task.tracker.repository.TrackerTaskRepository;
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
    private final TrackerTaskRepository trackerTaskRepository;

    private final TrackerRole admin = new TrackerRole("ROLE_ADMIN");
    private final TrackerRole user = new TrackerRole("ROLE_USER");
    private static final String PASS = "123456";
    private final List<TrackerUser> userList = new ArrayList<>();
    private final List<TrackerTask> taskList = new ArrayList<>();

    public DataLoader(TrackerUserRepository trackerUserRepository, TrackerRoleRepository trackerRoleRepository, TrackerTaskRepository trackerTaskRepository) {
        this.trackerUserRepository = trackerUserRepository;
        this.trackerRoleRepository = trackerRoleRepository;
        this.trackerTaskRepository = trackerTaskRepository;
    }

    @Override
    public void run(ApplicationArguments args){
        insertRoles();
        insertUser();
        insertTask();
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

    void insertTask(){
        TrackerTask task = new TrackerTask("Test","Test task for user1", Status.IN_PROGRESS);
        task.setUser(trackerUserRepository.getOne(1L));
        taskList.add(task);
        TrackerTask task2 = new TrackerTask("TestTwo","Test task for user", Status.DONE);
        task2.setUser(trackerUserRepository.getOne(1L));
        taskList.add(task2);
        TrackerTask task3 = new TrackerTask("Test3","Test tassdfsk for uszxczxcer", Status.DONE);
        task3.setUser(trackerUserRepository.getOne(3L));
        taskList.add(task3);
        TrackerTask task4 = new TrackerTask("TestFour","Test tsvsdvwsask for user", Status.VIEW);
        task4.setUser(trackerUserRepository.getOne(1L));
        taskList.add(task4);
        TrackerTask task5 = new TrackerTask("Test5","Test task fsdfsdor user", Status.VIEW);
        task5.setUser(trackerUserRepository.getOne(5L));
        taskList.add(task5);
        trackerTaskRepository.saveAll(taskList);
    }
}
