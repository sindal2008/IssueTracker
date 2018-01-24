package by.test.sindalouski.issuetracker.controller;

import by.test.sindalouski.issuetracker.dto.IssueFilterDto;
import by.test.sindalouski.issuetracker.entity.Issue;
import by.test.sindalouski.issuetracker.service.IssueService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {
    private IssueService issueService;


    public IndexController(IssueService issueService) {
        this.issueService = issueService;

    }

    @GetMapping("/")
    public String index(Model model,
                        HttpSession session,
                        @RequestParam(name = "page", defaultValue = "1") int page,
                        @RequestParam(name="search", defaultValue = "") String search,
                        @RequestParam(name="sort", defaultValue = "id") String sort) {

        Page<Issue> issues = issueService.listIssues(page, search.trim(), sort);
        model.addAttribute("issues", issues.getContent());
        model.addAttribute("totalPages", issues.getTotalPages());
        model.addAttribute("page", page);
        model.addAttribute("search", search);
        model.addAttribute("filter", new IssueFilterDto());

        return "index";
    }


}
