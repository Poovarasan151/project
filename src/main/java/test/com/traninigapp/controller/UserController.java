package test.com.traninigapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import test.com.traninigapp.dto.UserDto;
import test.com.traninigapp.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService useservice;

    @GetMapping
    public List<UserDto> getDetails()
    {
        return useservice.getAllDetails();
    }
    @GetMapping("/{id}")
    public UserDto getId(@PathVariable String id)
    {

        return useservice.getById(id);
    }
    @PostMapping
    public UserDto saveUser(@RequestBody UserDto data)
    {

        return useservice.saveUser(data);
    }
    @PutMapping("/{id}")
    public UserDto updateUser(@RequestBody UserDto data,@PathVariable String id)
    {

        return useservice.updateUser(data,id);
    }

    @PatchMapping("/{id}")
    public UserDto updatePassword(@RequestBody UserDto pass,@PathVariable String id )
    {

        return useservice.updatePassword(pass,id);
    }

}
