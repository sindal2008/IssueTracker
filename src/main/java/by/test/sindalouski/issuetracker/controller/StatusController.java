package by.test.sindalouski.issuetracker.controller;

import by.test.sindalouski.issuetracker.dto.StatusDto;
import by.test.sindalouski.issuetracker.entity.Status;
import by.test.sindalouski.issuetracker.repository.StatusRepository;
import by.test.sindalouski.issuetracker.service.StatusService;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/status")
@PreAuthorize("hasAuthority('ADMIN')")
public class StatusController {

    private StatusService statusService;
    private StatusRepository statusRepository;

    public StatusController(StatusService statusService,
                            StatusRepository statusRepository) {
        this.statusService = statusService;
        this.statusRepository = statusRepository;
    }

    @GetMapping
    public String allStatuses(Model model,
                              HttpSession session,
                              @RequestParam(name = "page", defaultValue = "1") int page,
                              @RequestParam(name="sort", defaultValue = "id") String sort) {

        Page<Status> statuses = statusService.listStatuses(page, sort);
        model.addAttribute("statuses", statuses.getContent());
        model.addAttribute("totalPages", statuses.getTotalPages());
        model.addAttribute("page", page);

        return "status/statuses";
    }

    @GetMapping(path="edit/{id}")
    public String editStatus(@PathVariable("id") Integer id, Model model) throws IOException {
        model.addAttribute("status", statusRepository.findOne(id));
        return "status/editstatus";
    }

    @PostMapping(path="/edit")
    public String editStatus(@ModelAttribute("status") StatusDto statusDto) throws IOException{
        statusService.updateStatus(statusDto);
        return "redirect:/status";
    }
}
