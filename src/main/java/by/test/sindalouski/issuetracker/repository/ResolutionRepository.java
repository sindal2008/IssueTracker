package by.test.sindalouski.issuetracker.repository;

import by.test.sindalouski.issuetracker.entity.Resolution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResolutionRepository extends JpaRepository<Resolution, Integer> {
}
