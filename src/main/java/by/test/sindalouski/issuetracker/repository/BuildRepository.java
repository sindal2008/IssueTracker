package by.test.sindalouski.issuetracker.repository;

import by.test.sindalouski.issuetracker.entity.Build;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildRepository extends JpaRepository<Build, Integer> {
}
