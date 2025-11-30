package com.sopheak.restfulapi001.model;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class User {
    String uuid;
    String name;
    String email;
    String password;
}
