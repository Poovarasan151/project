package test.com.traninigapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import test.com.traninigapp.dto.UserDto;
import test.com.traninigapp.service.UserService;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService useservice;

    @GetMapping
    public ResponseEntity<List<UserDto>> getDetails()
    {
        return useservice.getAllDetails();
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getId(@PathVariable String id)
    {

        return useservice.getById(id);
    }
    @PostMapping
    public ResponseEntity<Object> saveUser(@RequestBody UserDto data)
    {

        return useservice.saveUser(data);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@RequestBody UserDto data,@PathVariable String id)
    {

        return useservice.updateUser(data,id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDto> updatePassword(@RequestBody UserDto pass,@PathVariable String id )
    {

        return useservice.updatePassword(pass,id);
    }

}
