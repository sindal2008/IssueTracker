package by.test.sindalouski.issuetracker.service;

import by.test.sindalouski.issuetracker.dto.IssueDto;
import by.test.sindalouski.issuetracker.dto.IssueFilterDto;
import by.test.sindalouski.issuetracker.entity.Issue;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.List;

public interface IssueService {
    Page<Issue> listIssues(int page, String search, String sort);

    void addIssue(IssueDto issueDto) throws IOException;

    void remove(Integer id);

    void file(Integer id) throws IOException;

    void updateIssue(IssueDto issueDto);

    List<Issue> search(IssueFilterDto filter);

  }
