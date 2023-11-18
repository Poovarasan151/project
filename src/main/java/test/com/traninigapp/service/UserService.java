package test.com.traninigapp.service;
import com.mongodb.DuplicateKeyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.mongodb.core.MongoDataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import test.com.traninigapp.dto.UserDto;
import test.com.traninigapp.model.User;
import test.com.traninigapp.reository.UserRepo;

import java.util.*;

@Service
public class UserService {

  private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepo userRepo;


    public ResponseEntity<Object> saveUser(UserDto dto)
    {
        User user = new User();
        if(dto.getFullName()==null || dto.getFullName().isEmpty()) {
            return new ResponseEntity<>("Full name required",HttpStatus.BAD_REQUEST);
        }
        user.setFullName(dto.getFullName());

        if(dto.getAge()==0) {
            return new ResponseEntity<>("Age required",HttpStatus.BAD_REQUEST);
        }
        user.setAge(dto.getAge());

        if(dto.getEmail()==null || dto.getEmail().isEmpty()) {
            return new ResponseEntity<>("Email required",HttpStatus.BAD_REQUEST);
        }

            user.setEmail((dto.getEmail()));


        if(dto.getPassword()==null || dto.getPassword().isEmpty()) {
            return new ResponseEntity<>("Password required",HttpStatus.BAD_REQUEST);
        }
        user.setPassword(dto.getPassword());

        if(dto.getDateOfBirth()==null) {
            return new ResponseEntity<>("Date Of Birth required",HttpStatus.BAD_REQUEST);
        }
        user.setDateOfBirth(dto.getDateOfBirth());

        if(dto.getHobbyList()==null || dto.getHobbyList().isEmpty()) {
            return new ResponseEntity<>("password required",HttpStatus.BAD_REQUEST);
        }
        List<String> str = new ArrayList<>(dto.getHobbyList());
        user.setHobbyList(str);

        if(dto.getSkillSet()==null || dto.getSkillSet().isEmpty()) {
            return new ResponseEntity<>("password required",HttpStatus.BAD_REQUEST);
        }
        Set<String> string = new HashSet<>(dto.getSkillSet());
        user.setSkillSet(string);
        try{
            userRepo.save(user);
        }
        catch(org.springframework.dao.DuplicateKeyException e ) {
               return new ResponseEntity<>("mail already exists", HttpStatus.BAD_REQUEST);
        }


        return new ResponseEntity(user.getFullName()+" user is created successfully", HttpStatus.CREATED);
    }

    public  ResponseEntity<Object> updateUser(UserDto dto, String id) {
        User user= userRepo.findById(id).get();
        if(dto.getId()!=null) {
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
        }
        else {
            return new ResponseEntity<>("User Resource Not Found",HttpStatus.NOT_FOUND);
        }


        return new ResponseEntity<>(dto, HttpStatus.OK);


    }

    public ResponseEntity <List<UserDto>> getAllDetails() {
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
        return new ResponseEntity<>(userDtos,HttpStatus.OK);
    }


    public ResponseEntity<UserDto> getById(String id) {
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
        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }

    public ResponseEntity<UserDto> updatePassword(UserDto dto,String id ) {
        User user= userRepo.findById(id).get();
        user.setPassword(dto.getPassword());
        userRepo.save(user);
        return new ResponseEntity<>(dto,HttpStatus.OK);



    }
}
