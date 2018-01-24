package by.test.sindalouski.issuetracker.repository;

import by.test.sindalouski.issuetracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);

}
