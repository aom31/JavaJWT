package com.example.springsecurityjwt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USER_log")
public class User {

    @Id
    private int id;

    private  String username;
    private String password;
    private String email;
}
