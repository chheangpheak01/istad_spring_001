package com.sopheak.restfulapi001.repository;
import com.sopheak.restfulapi001.entities.User;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserRepositoryTemp {
    public static List<User> users = new ArrayList<>(
            List.of(new User(UUID.randomUUID().toString(), "karina", "karina@gmail.com", "#@!!"))
    );
}
