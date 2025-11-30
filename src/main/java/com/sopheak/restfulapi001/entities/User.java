package com.sopheak.restfulapi001.entities;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    String uuid;
    String name;
    String email;
    String password;
}
