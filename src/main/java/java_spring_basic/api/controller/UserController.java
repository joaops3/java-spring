package java_spring_basic.api.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java_spring_basic.api.domain.user.User;
import java_spring_basic.api.domain.user.UserRequestDto;
import java_spring_basic.api.service.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private UserService userService;

    
    @PostMapping("/")
    public ResponseEntity<User> create(@RequestBody UserRequestDto userDto){

        var newUser = userService.Create(userDto);

        return ResponseEntity.ok(newUser);
    }


    @GetMapping("/")
    public ResponseEntity<List<User>> findAll(@RequestParam(defaultValue = "0") int current, @RequestParam(defaultValue = "10") int pageSize){
        var users = userService.FindAll(current, pageSize);

        return ResponseEntity.ok(users);
    }


    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable UUID id){

        var user = userService.FindById(id);

        return user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){

        userService.Delete(id);

        return ResponseEntity.noContent().build();
    }
    
}
