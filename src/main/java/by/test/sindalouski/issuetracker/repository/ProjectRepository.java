package by.test.sindalouski.issuetracker.repository;

import by.test.sindalouski.issuetracker.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
}
