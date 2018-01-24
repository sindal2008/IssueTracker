package by.test.sindalouski.issuetracker.repository;

import by.test.sindalouski.issuetracker.entity.Issue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IssueRepository extends JpaRepository<Issue, Integer> {
    Page<Issue> findAllBySummaryContainsOrDescriptionContains(String summary, String desc, Pageable pageable);
    Page<Issue> findAllById(String desc, Pageable pageable);


}
