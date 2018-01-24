package by.test.sindalouski.issuetracker.controller;


import by.test.sindalouski.issuetracker.dto.ProjectDto;
import by.test.sindalouski.issuetracker.entity.Build;
import by.test.sindalouski.issuetracker.entity.Project;
import by.test.sindalouski.issuetracker.entity.User;
import by.test.sindalouski.issuetracker.repository.BuildRepository;
import by.test.sindalouski.issuetracker.repository.ProjectRepository;
import by.test.sindalouski.issuetracker.repository.UserRepository;
import by.test.sindalouski.issuetracker.service.ProjectService;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/project")
@PreAuthorize("hasAuthority('ADMIN')")
public class ProjectController {

    private ProjectService projectService;
    private UserRepository userRepository;
    private BuildRepository buildRepository;
    private ProjectRepository projectRepository;

    public ProjectController(ProjectService projectService,
                             UserRepository userRepository,
                             BuildRepository buildRepository,
                             ProjectRepository projectRepository) {
        this.projectService = projectService;
        this.userRepository = userRepository;
        this.buildRepository = buildRepository;
        this.projectRepository = projectRepository;
    }

    @GetMapping
    public String allProjects(Model model,
                        HttpSession session,
                        @RequestParam(name = "page", defaultValue = "1") int page,
                        @RequestParam(name="sort", defaultValue = "id") String sort) {

        Page<Project> projects = projectService.listProjects(page, sort);
        model.addAttribute("projects", projects.getContent());
        model.addAttribute("totalPages", projects.getTotalPages());
        model.addAttribute("page", page);

        return "project/projects";
    }

    @GetMapping("/add")
    public String projectForm(Model model) {
        model.addAttribute("project", new ProjectDto());
        return "project/addproject";
    }

    @PostMapping("/add")
    public String addProject(@ModelAttribute("project") ProjectDto projectDto) throws IOException {
        projectService.addProject(projectDto);
        return "redirect:/project";
    }

    @GetMapping(path="edit/{id}")
    public String editProject(@PathVariable("id") Integer id, Model model) throws IOException {
        model.addAttribute("project", projectRepository.findOne(id));
        return "project/editproject";
    }

    @PostMapping(path="/edit")
    public String editProject(@ModelAttribute("project") ProjectDto projectDto) throws IOException{
        projectService.updateProject(projectDto);
        return "redirect:/project";
    }

    @GetMapping("/{id}/remove")
    public String remove(@PathVariable Integer id) {
        projectService.remove(id);
        return "redirect:/";
    }

    @ModelAttribute("allUsers")
    public List<User> populateStatuses() {
        return userRepository.findAll();
    }

    @ModelAttribute("allBuilds")
    public List<Build> populateBuilds() {
        return buildRepository.findAll();
    }

}
