package by.test.sindalouski.issuetracker.repository;

import by.test.sindalouski.issuetracker.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
