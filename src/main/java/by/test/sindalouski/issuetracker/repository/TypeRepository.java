package by.test.sindalouski.issuetracker.repository;

import by.test.sindalouski.issuetracker.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type, Integer> {
}
