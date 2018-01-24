package by.test.sindalouski.issuetracker.repository;

import by.test.sindalouski.issuetracker.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
