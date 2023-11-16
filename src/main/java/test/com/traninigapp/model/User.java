package test.com.traninigapp.model;

import com.fasterxml.jackson.annotation.JsonTypeId;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.Set;
@Data
@Document(collection="user")
public class User {
        @Id
        private String id;
        private String fullName;
        private String email;
        private String password;
        private int age;
        private Date dateOfBirth;
        private List<String> hobbyList;
        private Set<String> skillSet;
    }

