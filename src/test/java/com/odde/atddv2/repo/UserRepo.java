package com.odde.atddv2.repo;

import com.odde.atddv2.entity.User;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface UserRepo extends Repository<User, Long> {

    Optional<User> findByUserNameAndPassword(String userName, String password);

    User save(User user);
}
