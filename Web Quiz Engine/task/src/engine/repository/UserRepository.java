package engine.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import engine.security.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);
    boolean existsByEmail(String email);
}
