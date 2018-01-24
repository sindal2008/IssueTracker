package by.test.sindalouski.issuetracker.service.impl;

import by.test.sindalouski.issuetracker.dto.IssueDto;
import by.test.sindalouski.issuetracker.dto.IssueFilterDto;
import by.test.sindalouski.issuetracker.entity.*;
import by.test.sindalouski.issuetracker.repository.*;
import by.test.sindalouski.issuetracker.service.IssueService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class IssueServiceImpl implements IssueService {
    private IssueRepository issueRepository;
    private UserRepository userRepository;
    private String filesPath;
    private RestTemplate restTemplate;
    private JdbcTemplate jdbcTemplate;
    private PriorityRepository priorityRepository;
    private TypeRepository typeRepository;
    private StatusRepository statusRepository;
    private ProjectRepository projectRepository;
    private BuildRepository buildRepository;

    public IssueServiceImpl(IssueRepository issueRepository,
                            UserRepository userRepository,
                            RestTemplate restTemplate,
                            JdbcTemplate jdbcTemplate,
                            PriorityRepository priorityRepository,
                            TypeRepository typeRepository,
                            StatusRepository statusRepository,
                            ProjectRepository projectRepository,
                            BuildRepository buildRepository) {
        this.issueRepository = issueRepository;
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
        this.jdbcTemplate = jdbcTemplate;
        this.priorityRepository = priorityRepository;
        this.typeRepository = typeRepository;
        this.statusRepository = statusRepository;
        this.projectRepository = projectRepository;
        this.buildRepository = buildRepository;
    }

    @Override
    public Page<Issue> listIssues(int page, String search, String sort) {
        Pageable pageable = new PageRequest(page - 1, 10, new Sort(sort));
        if (search.isEmpty()) {
            return issueRepository.findAll(pageable);
        } else {
            return issueRepository.findAllBySummaryContainsOrDescriptionContains(search, search, pageable);
        }
    }

    @Override
    public void addIssue(IssueDto issueDto) throws IOException {

        Issue issue = new Issue();
        Priority priority = priorityRepository.findOne(issueDto.getPriorityId());
        Type type = typeRepository.findOne(issueDto.getTypeId());
        Status status = statusRepository.findOne(issueDto.getStatusId());
        Project project = projectRepository.findOne(issueDto.getProjectId());

        UserDetails userDetails = UserDetails.class.cast(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        String username = userDetails.getUsername();
        User currentUser = userRepository.findByUsername(username);

        Build build = buildRepository.findOne(issueDto.getBuildId());
        issue.setSummary(issueDto.getSummary());
        issue.setDescription(issueDto.getDescription());
        issue.setPriority(priority);
        issue.setType(type);
        issue.setStatus(status);
        issue.setProject(project);
        issue.setCreatedBy(currentUser);
        issue.setCreateDate(LocalDateTime.now());
        issue.setBuild(build);
        User assignee = userRepository.findOne(issueDto.getAssigneeUserId());
        issue.setAssignee(assignee);

        issueRepository.save(issue);

//        Files.write(Paths.get(filesPath, issue.getId().toString()),
//                issueDto.getFile().getBytes());
    }

    @Override
    public void updateIssue(IssueDto issueDto) {
        Issue issue = issueRepository.findOne(issueDto.getId());
        Priority priority = priorityRepository.findOne(issueDto.getPriorityId());
        Type type = typeRepository.findOne(issueDto.getTypeId());
        Status status = statusRepository.findOne(issueDto.getStatusId());
        Project project = projectRepository.findOne(issueDto.getProjectId());

        UserDetails userDetails = UserDetails.class.cast(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        String username = userDetails.getUsername();
        User currentUser = userRepository.findByUsername(username);

        User assignee = userRepository.findOne((issueDto.getAssigneeUserId()));
        Build build = buildRepository.findOne(issueDto.getBuildId());
        issue.setSummary(issueDto.getSummary());
        issue.setDescription(issueDto.getDescription());
        issue.setPriority(priority);
        issue.setType(type);
        issue.setStatus(status);
        issue.setProject(project);
        issue.setAssignee(assignee);
        issue.setModifiedBy(currentUser);
        issue.setModifyDate(LocalDateTime.now());
        issue.setBuild(build);

        issueRepository.save(issue);
    }

    @Override
    public List<Issue> search(IssueFilterDto filter) {
        return null;
    }

    @Override
    public void remove(Integer id) {
        issueRepository.delete(id);
    }

    @Override
    public void file(Integer id) throws IOException {
//        if (fileExists(id)) {
//            return Files.readAllBytes(Paths.get(filesPath, id.toString()));
//        } else {
//            ResponseEntity<byte[]> responseEntity = restTemplate.exchange(DEFAULT_IMAGE,
//                    HttpMethod.GET,
//                    new HttpEntity<Void>(new HttpHeaders()),
//                    byte[].class);
//            return responseEntity.getBody();
//        }
    }



    private boolean fileExists(Issue issue) {
        return fileExists(issue.getId());
    }

    private boolean fileExists(Integer id) {
        return Files.exists(Paths.get(filesPath, id.toString()));
    }
}
