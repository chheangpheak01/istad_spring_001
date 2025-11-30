package com.sopheak.restfulapi001.controller;
import com.sopheak.restfulapi001.entities.User;
import com.sopheak.restfulapi001.exception.customexception.UserException;
import com.sopheak.restfulapi001.model.dto.CreateUserDto;
import com.sopheak.restfulapi001.model.dto.UserResponseDto;
import com.sopheak.restfulapi001.repository.UserRepositoryTemp;
import com.sopheak.restfulapi001.utils.ResponseTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/tests")
public class UserController {

    @GetMapping()
    public ResponseTemplate<Object> getAllUsers(){

        List<UserResponseDto> userResponseDto = UserRepositoryTemp.users.stream().map(u -> new UserResponseDto(u.getUuid(), u.getName(), u.getEmail())).toList();

        return ResponseTemplate
                .builder()
                .staus(HttpStatus.OK.toString())
                .date(Date.from(Instant.now()))
                .message("Get all users data successfully")
                .data(userResponseDto)
                .build();
    }

    @PostMapping
    public ResponseTemplate<Object> createNewUser(@RequestBody CreateUserDto createUserDto){
        // Convert from CreateUserDto to User
        User user = new User(UUID.randomUUID().toString(),createUserDto.name(),createUserDto.email(),createUserDto.password());
        UserRepositoryTemp.users.add(user);
        return ResponseTemplate
                .builder()
                .staus(HttpStatus.OK.toString())
                .date(Date.from(Instant.now()))
                .message("Created new user successfully")
                .data(user)
                .build();
    }

    @DeleteMapping("")
    //@ResponseStatus(HttpStatus.NO_CONTENT)

    public ResponseTemplate<Object> deleteUserByUUID(@RequestParam(name = "uuid") String uuid){

        // check if the user is existing
        Optional<User> user = UserRepositoryTemp.users.stream().filter(u -> u.getUuid().equals(uuid)).findAny();

        if(user.isEmpty()){
            throw new UserException("User data not found");
        }
        // remove user by uuid
        UserRepositoryTemp.users.removeIf(u -> u.getUuid().equals(uuid));

        return ResponseTemplate
                .builder()
                .staus(HttpStatus.NO_CONTENT.toString())
                .date(Date.from(Instant.now()))
                .message("User data is deleted successfully")
                .data(uuid)
                .build();
    }

    @PutMapping("/{uuid}")
    public ResponseTemplate<Object> updateUserByUUID(@PathVariable String uuid, @RequestBody CreateUserDto createUserDto){

        User existingUser = UserRepositoryTemp.users.stream().filter(u -> u.getUuid().equals(uuid)).findFirst().orElse(null);

        if(existingUser == null){
            return ResponseTemplate
                    .builder()
                    .staus(HttpStatus.NOT_FOUND.toString())
                    .date(Date.from(Instant.now()))
                    .message("User data is not found with this uuid: " + uuid)
                    .data(null)
                    .build();
        }
        existingUser.setName(createUserDto.name());
        existingUser.setEmail(createUserDto.email());
        existingUser.setPassword(createUserDto.password());

        return ResponseTemplate
                .builder()
                .staus(HttpStatus.OK.toString())
                .date(Date.from(Instant.now()))
                .message("User data is updated successfully")
                .data(existingUser)
                .build();
    }
}
