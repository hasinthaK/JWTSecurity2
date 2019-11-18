package lk.jwtsecurity.method2.repositories;

import lk.jwtsecurity.method2.models.user;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepository extends MongoRepository<user, String> {
    user findUserByUsername(String username);
}
