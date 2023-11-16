package test.com.traninigapp.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
public class UserDto {

    private String id;
    private String fullName;
    private String email;
    private String password;
    private int age;
    private Date dateOfBirth;
    private List<String> hobbyList;
    private Set<String> skillSet;
}
