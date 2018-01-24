package by.test.sindalouski.issuetracker.controller;

import by.test.sindalouski.issuetracker.dto.TypeDto;
import by.test.sindalouski.issuetracker.entity.Type;
import by.test.sindalouski.issuetracker.repository.TypeRepository;
import by.test.sindalouski.issuetracker.service.TypeService;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/type")
@PreAuthorize("hasAuthority('ADMIN')")
public class TypeController {

    private TypeService typeService;
    private TypeRepository typeRepository;

    public TypeController(TypeService typeService,
                          TypeRepository typeRepository) {
        this.typeService = typeService;
        this.typeRepository = typeRepository;
    }

    @GetMapping
    public String allTypes(Model model,
                              HttpSession session,
                              @RequestParam(name = "page", defaultValue = "1") int page,
                              @RequestParam(name="sort", defaultValue = "id") String sort) {
        Page<Type> types = typeService.listTypes(page, sort);
        model.addAttribute("types", types.getContent());
        model.addAttribute("totalPages", types.getTotalPages());
        model.addAttribute("page", page);

        return "type/types";
    }

    @GetMapping("/add")
    public String typeForm(Model model) {
        model.addAttribute("type", new TypeDto());
        return "type/addtype";
    }

    @PostMapping("/add")
    public String addType(@ModelAttribute("type") TypeDto typeDto) throws IOException {
        typeService.addType(typeDto);
        return "redirect:/type";
    }

    @GetMapping(path="edit/{id}")
    public String editType(@PathVariable("id") Integer id, Model model) throws IOException {
        model.addAttribute("type", typeRepository.findOne(id));
        return "type/edittype";
    }

    @PostMapping(path="/edit")
    public String editType(@ModelAttribute("type") TypeDto typeDto) throws IOException{
        typeService.updateType(typeDto);
        return "redirect:/type";
    }

}
