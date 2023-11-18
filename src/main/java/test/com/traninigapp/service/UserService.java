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
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
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
        if(!StringUtils.hasLength(dto.getFullName())) {
            return badRequestExceptionResponse("Full name required");
        }
        user.setFullName(dto.getFullName());

        if(dto.getAge()==0) {
            return badRequestExceptionResponse("Age required");
        }
        user.setAge(dto.getAge());

        if(dto.getEmail()==null || dto.getEmail().isEmpty()) {
            return badRequestExceptionResponse("Email required");
        }

            user.setEmail((dto.getEmail()));


        if(dto.getPassword()==null || dto.getPassword().isEmpty()) {
            return badRequestExceptionResponse("Password required");
        }
        user.setPassword(dto.getPassword());

        if(dto.getDateOfBirth()==null) {
            return badRequestExceptionResponse("Date Of Birth required");
        }
        user.setDateOfBirth(dto.getDateOfBirth());

        if(CollectionUtils.isEmpty(dto.getHobbyList())) {
            return badRequestExceptionResponse("password required");
        }
        List<String> str = new ArrayList<>(dto.getHobbyList());
        user.setHobbyList(str);

        if(dto.getSkillSet()==null || dto.getSkillSet().isEmpty()) {
            return badRequestExceptionResponse("password required");
        }
        Set<String> string = new HashSet<>(dto.getSkillSet());
        user.setSkillSet(string);
        try{
            userRepo.save(user);
        }
        catch(org.springframework.dao.DuplicateKeyException e ) {
               return badRequestExceptionResponse("mail already exists");
        }


        return new ResponseEntity(user.getFullName()+" user is created successfully", HttpStatus.CREATED);
    }

    
    private ResponseEntity badRequestExceptionResponse(String error) {
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
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
        Optional<User> optionalUser= userRepo.findById(id);
        if(optionalUser.isEmpty()) {
            return badRequestExceptionResponse("Requested user not found");
        }
        User user = optionalUser.get();
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
