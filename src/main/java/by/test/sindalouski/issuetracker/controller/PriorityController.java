package by.test.sindalouski.issuetracker.controller;

import by.test.sindalouski.issuetracker.dto.PriorityDto;
import by.test.sindalouski.issuetracker.dto.ResolutionDto;
import by.test.sindalouski.issuetracker.entity.Priority;
import by.test.sindalouski.issuetracker.repository.PriorityRepository;
import by.test.sindalouski.issuetracker.service.PriorityService;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/priority")
@PreAuthorize("hasAuthority('ADMIN')")
public class PriorityController {

    private PriorityService priorityService;
    private PriorityRepository priorityRepository;

    public PriorityController(PriorityService priorityService,
                              PriorityRepository priorityRepository) {
        this.priorityService = priorityService;
        this.priorityRepository = priorityRepository;
    }

    @GetMapping
    public String allProjects(Model model,
                              HttpSession session,
                              @RequestParam(name = "page", defaultValue = "1") int page,
                              @RequestParam(name="sort", defaultValue = "id") String sort) {

        Page<Priority> priorities = priorityService.listPriorities(page, sort);
        model.addAttribute("priorities", priorities.getContent());
        model.addAttribute("totalPages", priorities.getTotalPages());
        model.addAttribute("page", page);

        return "priority/priorities";
    }

    @GetMapping("/add")
    public String priorityForm(Model model) {
        model.addAttribute("priority", new PriorityDto());
        return "priority/addpriority";
    }

    @PostMapping("/add")
    public String addPriority(@ModelAttribute("priority") PriorityDto priorityDto) throws IOException {
        priorityService.addPriority(priorityDto);
        return "redirect:/priority";
    }

    @GetMapping(path="edit/{id}")
    public String editPriority(@PathVariable("id") Integer id, Model model) throws IOException {
        model.addAttribute("priority", priorityRepository.findOne(id));
        return "priority/editpriority";
    }

    @PostMapping(path="/edit")
    public String editPriority(@ModelAttribute("priority") PriorityDto priorityDto) throws IOException{
        priorityService.updatePriority(priorityDto);
        return "redirect:/priority";
    }
}
