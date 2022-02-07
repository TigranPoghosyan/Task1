package com.example.task1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
    private long id;

    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "userType")
    private String userType;
    @Column(name = "position")
    private String position;
    @Column(name = "department")
    private String department;

    public User(String name, String surname, String userType, String position, String department) {
        this.name = name;
        this.surname = surname;
        this.userType = userType;
        this.position = position;
        this.department = department;
    }
}
