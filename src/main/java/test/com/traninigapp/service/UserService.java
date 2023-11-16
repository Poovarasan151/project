package test.com.traninigapp.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;
import test.com.traninigapp.dto.UserDto;
import test.com.traninigapp.model.User;
import test.com.traninigapp.reository.UserRepo;

import java.util.*;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;


    public UserDto saveUser(UserDto dto)
    {
        User user = new User();
        user.setFullName(dto.getFullName());
        user.setId(dto.getId());
        user.setAge(dto.getAge());
        user.setEmail((dto.getEmail()));
        user.setPassword(dto.getPassword());
        user.setDateOfBirth(dto.getDateOfBirth());

        List<String> str = new ArrayList<>(dto.getHobbyList());
        user.setHobbyList(str);

        Set<String> string = new HashSet<>(dto.getSkillSet());
        user.setSkillSet(string);
        userRepo.save(user);
        return dto;
    }

    public UserDto updateUser(UserDto dto, String id) {
        User user= userRepo.findById(id).get();
        user.setFullName(dto.getFullName());
        user.setId(dto.getId());
        user.setAge(dto.getAge());
        user.setEmail((dto.getEmail()));
        user.setPassword(dto.getPassword());
        user.setDateOfBirth(dto.getDateOfBirth());

        List<String> str = new ArrayList<>(dto.getHobbyList());
        user.setHobbyList(str);

        Set<String> string = new HashSet<>(dto.getSkillSet());
        user.setSkillSet(string);
        userRepo.save(user);

 // UserDto user1 =userRepo.save(user);
        return dto;


    }

    public List<UserDto> getAllDetails() {
        List<User> users =userRepo.findAll();
        List<UserDto> userDtos= new ArrayList<>();
        for(User user1:users) {
            UserDto userDto = new UserDto();
            userDto.setFullName(user1.getFullName());
            userDto.setId(user1.getId());
            userDto.setAge(user1.getAge());
            userDto.setEmail((user1.getEmail()));
            userDto.setPassword(user1.getPassword());
            userDto.setDateOfBirth(user1.getDateOfBirth());

            List<String> str = new ArrayList<>(user1.getHobbyList());
            userDto.setHobbyList(str);

            Set<String> string = new HashSet<>(user1.getSkillSet());
            userDto.setSkillSet(string);
            userDtos.add(userDto);
        }
        return userDtos;
    }

    public UserDto getById(String id) {
        User user= userRepo.findById(id).get();
        UserDto userDto = new UserDto();
        userDto.setFullName(user.getFullName());
        userDto.setId(user.getId());
        userDto.setAge(user.getAge());
        userDto.setEmail((user.getEmail()));
        userDto.setPassword(user.getPassword());
        userDto.setDateOfBirth(user.getDateOfBirth());

        List<String> str = new ArrayList<>(user.getHobbyList());
        userDto.setHobbyList(str);

        Set<String> string = new HashSet<>(user.getSkillSet());
        userDto.setSkillSet(string);
        return userDto;
    }

    public UserDto updatePassword(UserDto dto,String id ) {
        User user= userRepo.findById(id).get();
//        user.setFullName(dto.getFullName());
//        user.setId(dto.getId());
//        user.setAge(dto.getAge());
//        user.setEmail((dto.getEmail()));
        user.setPassword(dto.getPassword());
//        user.setDateOfBirth(dto.getDateOfBirth());
//
//        List<String> str = new ArrayList<>(dto.getHobbyList());
//        user.setHobbyList(str);
//
//        Set<String> string = new HashSet<>(dto.getSkillSet());
//        user.setSkillSet(string);
        userRepo.save(user);
        return dto;



    }
}
