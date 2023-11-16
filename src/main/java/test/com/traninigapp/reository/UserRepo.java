package test.com.traninigapp.reository;

import org.springframework.data.mongodb.repository.MongoRepository;
import test.com.traninigapp.model.User;

public interface UserRepo extends MongoRepository<User,String> {
}
