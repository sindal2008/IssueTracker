package by.test.sindalouski.issuetracker.repository;

import by.test.sindalouski.issuetracker.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Integer> {
}
