package com.example.task.tracker.model.entity;

import javax.persistence.*;
import java.util.List;


@Entity
public class TrackerRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "role")
    private List<TrackerUser> users;

    public TrackerRole() {
    }

    public TrackerRole(String name) {
        this.name = name;
    }

    public TrackerRole(String name, List<TrackerUser> users) {
        this.name = name;
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TrackerUser> getUsers() {
        return users;
    }

    public void setUsers(List<TrackerUser> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "TrackerRole{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", users=" + users +
                '}';
    }
}
