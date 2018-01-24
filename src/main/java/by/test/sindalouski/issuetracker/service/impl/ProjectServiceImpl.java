package by.test.sindalouski.issuetracker.service.impl;

import by.test.sindalouski.issuetracker.dto.ProjectDto;
import by.test.sindalouski.issuetracker.entity.Build;
import by.test.sindalouski.issuetracker.entity.Project;
import by.test.sindalouski.issuetracker.entity.User;
import by.test.sindalouski.issuetracker.repository.BuildRepository;
import by.test.sindalouski.issuetracker.repository.ProjectRepository;
import by.test.sindalouski.issuetracker.repository.UserRepository;
import by.test.sindalouski.issuetracker.service.ProjectService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ProjectServiceImpl implements ProjectService {

    private ProjectRepository projectRepository;
    private UserRepository userRepository;
    private BuildRepository buildRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository,
                              UserRepository userRepository,
                              BuildRepository buildRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.buildRepository = buildRepository;
    }

    @Override
    public Page<Project> listProjects(int page, String sort) {
        Pageable pageable = new PageRequest(page - 1, 10, new Sort(sort));
        return projectRepository.findAll(pageable);
    }

    @Override
    public void addProject(ProjectDto projectDto) throws IOException {

        Project project = new Project();
        project.setDescription(projectDto.getDescription());
        project.setProjectName(projectDto.getProjectName());
        User user = userRepository.findOne(projectDto.getUserId());
        project.setUser(user);
        Build build = buildRepository.findOne(projectDto.getBuildId());
        project.setBuild(build);
        projectRepository.save(project);
    }

    @Override
    public void remove(Integer id) {

        projectRepository.delete(id);
    }

    @Override
    public void updateProject(ProjectDto projectDto) {

        Project project = projectRepository.findOne(projectDto.getId());
        project.setDescription(projectDto.getDescription());
        project.setProjectName(projectDto.getProjectName());
        User user = userRepository.findOne(projectDto.getUserId());
        project.setUser(user);
        Build build = buildRepository.findOne(projectDto.getBuildId());
        project.setBuild(build);
        projectRepository.save(project);
    }
}


