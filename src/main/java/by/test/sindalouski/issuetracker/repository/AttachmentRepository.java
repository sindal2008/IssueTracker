package by.test.sindalouski.issuetracker.repository;

import by.test.sindalouski.issuetracker.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository<Attachment, Integer>{
}
