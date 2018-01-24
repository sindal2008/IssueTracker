package by.test.sindalouski.issuetracker.controller;

import by.test.sindalouski.issuetracker.dto.ResolutionDto;
import by.test.sindalouski.issuetracker.entity.Resolution;
import by.test.sindalouski.issuetracker.repository.ResolutionRepository;
import by.test.sindalouski.issuetracker.service.ResolutionService;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/resolution")
@PreAuthorize("hasAuthority('ADMIN')")
public class ResolutionController {

    private ResolutionService resolutionService;
    private ResolutionRepository resolutionRepository;

    public ResolutionController(ResolutionService resolutionService,
                                ResolutionRepository resolutionRepository) {
        this.resolutionService = resolutionService;
        this.resolutionRepository = resolutionRepository;
    }

    @GetMapping
    public String allProjects(Model model,
                              HttpSession session,
                              @RequestParam(name = "page", defaultValue = "1") int page,
                              @RequestParam(name="sort", defaultValue = "id") String sort) {

        Page<Resolution> projects = resolutionService.listResolutions(page, sort);
        model.addAttribute("resolutions", projects.getContent());
        model.addAttribute("totalPages", projects.getTotalPages());
        model.addAttribute("page", page);

        return "resolution/resolutions";
    }

    @GetMapping("/add")
    public String resolutionForm(Model model) {
        model.addAttribute("resolution", new ResolutionDto());
        return "resolution/addresolution";
    }

    @PostMapping("/add")
    public String addResolution(@ModelAttribute("resolution") ResolutionDto resolutionDto) throws IOException {
        resolutionService.addResolution(resolutionDto);
        return "redirect:/resolution";
    }

    @GetMapping(path="edit/{id}")
    public String editResolution(@PathVariable("id") Integer id, Model model) throws IOException {
        model.addAttribute("resolution", resolutionRepository.findOne(id));
        return "resolution/editresolution";
    }

    @PostMapping(path="/edit")
    public String editResolution(@ModelAttribute("resolution") ResolutionDto resolutionDto) throws IOException{
        resolutionService.updateResolution(resolutionDto);
        return "redirect:/resolution";
    }

}
