package by.test.sindalouski.issuetracker.repository;

import by.test.sindalouski.issuetracker.entity.Priority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriorityRepository extends JpaRepository<Priority, Integer> {
}
