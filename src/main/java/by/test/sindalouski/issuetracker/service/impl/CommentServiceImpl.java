package by.test.sindalouski.issuetracker.service.impl;

import by.test.sindalouski.issuetracker.dto.CommentDto;
import by.test.sindalouski.issuetracker.entity.Comment;
import by.test.sindalouski.issuetracker.repository.CommentRepository;
import by.test.sindalouski.issuetracker.repository.IssueRepository;
import by.test.sindalouski.issuetracker.service.CommentService;
import by.test.sindalouski.issuetracker.service.SecurityService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private IssueRepository issueRepository;
    private SecurityService securityService;

    public CommentServiceImpl(CommentRepository commentRepository,
                              IssueRepository issueRepository,
                              SecurityService securityService) {
        this.commentRepository = commentRepository;
        this.issueRepository = issueRepository;
        this.securityService = securityService;
    }

    @Override
    public void addComment(CommentDto commentDto) {

        Comment comment = new Comment();
        comment.setText(commentDto.getText());
        comment.setUser(securityService.getCurrentUser());
        comment.setIssue(issueRepository.findOne(commentDto.getIssueId()));
        commentRepository.save(comment);
    }
}
