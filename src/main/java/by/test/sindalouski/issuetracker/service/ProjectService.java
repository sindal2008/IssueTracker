package by.test.sindalouski.issuetracker.service;

import by.test.sindalouski.issuetracker.dto.ProjectDto;
import by.test.sindalouski.issuetracker.entity.Project;
import org.springframework.data.domain.Page;

import java.io.IOException;

public interface ProjectService {

    Page<Project> listProjects(int page, String sort);

    void addProject(ProjectDto projectDto) throws IOException;

    void remove(Integer id);

    void updateProject(ProjectDto projectDto);

}
