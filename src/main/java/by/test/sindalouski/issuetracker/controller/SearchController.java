package by.test.sindalouski.issuetracker.controller;

import by.test.sindalouski.issuetracker.dto.IssueFilterDto;
import by.test.sindalouski.issuetracker.service.IssueService;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class SearchController {
    private IssueService issueService;

    public SearchController(IssueService issueService) {
        this.issueService = issueService;
    }

    @InitBinder
    public void bindingPreparation(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor orderDateEditor = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, orderDateEditor);
    }

    @PostMapping("/search")
    public String search(Model model, @ModelAttribute("filter") IssueFilterDto filter) {
        model.addAttribute("filter", filter);
        model.addAttribute("items", issueService.search(filter));
        return "search";
    }
}
