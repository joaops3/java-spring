package java_spring_basic.api.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import java_spring_basic.api.domain.user.User;
import java_spring_basic.api.domain.user.UserRequestDto;
import java_spring_basic.api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Service
@RequiredArgsConstructor
public class UserService {


    private UserRepository userRepository;

    public User Create(UserRequestDto userDto){
        var newUser = new User();
        newUser.setEmail(userDto.email());
        newUser.setPassword(userDto.password());
        userRepository.save(newUser);
        return newUser;
    }
  
    public List<User> FindAll(int current, int pageSize){
        Pageable pageable = PageRequest.of(current, pageSize);
        return userRepository.findAll(pageable).getContent();
    }


    public Optional<User> FindById(UUID id){
        // var user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        var user = userRepository.findById(id);
        return user;
    }

    public User Update(UUID id, UserRequestDto userDto){
        var user = userRepository.findById(id).orElse(null);
        if(user == null){
            return null;
        }
        user.setEmail(userDto.email());
        user.setPassword(userDto.password());
        userRepository.save(user);
        return user;
    }

    public void Delete(UUID id){
        userRepository.deleteById(id);
    }
    
}
