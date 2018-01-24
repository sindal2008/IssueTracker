package by.test.sindalouski.issuetracker.controller;

import by.test.sindalouski.issuetracker.dto.RegistrationDto;
import by.test.sindalouski.issuetracker.dto.UserDto;
import by.test.sindalouski.issuetracker.entity.Role;
import by.test.sindalouski.issuetracker.entity.User;
import by.test.sindalouski.issuetracker.repository.RoleRepository;
import by.test.sindalouski.issuetracker.repository.UserRepository;
import by.test.sindalouski.issuetracker.service.UserService;
//import by.test.sindalouski.issuetracker.validators.RegistrationValidator;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
public class UserController {
    private final UserService userService;
//    private final RegistrationValidator registrationValidator;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public UserController(UserService userService,
//                          RegistrationValidator registrationValidator,
                          RoleRepository roleRepository, UserRepository userRepository) {
        this.userService = userService;
//        this.registrationValidator = registrationValidator;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

//    регистрируем валидатор для формы регистрации
//    @InitBinder
//    public void addRegistrationValidator(WebDataBinder webDataBinder) {
//        webDataBinder.addValidators(registrationValidator);
//    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String registrationForm(Model model) {
        model.addAttribute("user", new RegistrationDto());
        return "registration";
    }

    //валидируем форму и в случае ошибки опять показываем страницу с формой
    @PostMapping("/registration")
    public String register(@ModelAttribute("user") @Valid RegistrationDto registrationDto,
            /* хранит результаты валидации*/ BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userService.register(registrationDto);
        return "redirect:/login";
    }

    @GetMapping("/user")
    public String allUsers(Model model,
                              HttpSession session,
                              @RequestParam(name = "page", defaultValue = "1") int page,
                              @RequestParam(name="search", defaultValue = "") String search,
                              @RequestParam(name="sort", defaultValue = "id") String sort) {

        Page<User> users = userService.listUsers(page, search.trim(), sort);
        model.addAttribute("users", users.getContent());
        model.addAttribute("totalPages", users.getTotalPages());
        model.addAttribute("page", page);

        return "user/users";
    }

    @GetMapping("user/add")
    public String userForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "user/adduser";
    }

    @PostMapping("user/add")
    public String addUser(@ModelAttribute("user") UserDto userDto) throws IOException {
        userService.addUser(userDto);
        return "redirect:/user";
    }

    @GetMapping(path="user/edit/{id}")
    public String editProject(@PathVariable("id") Integer id, Model model) throws IOException {
        model.addAttribute("user", userRepository.findOne(id));
        return "user/edituser";
    }

    @PostMapping(path="user/edit")
    public String editUser(@ModelAttribute("user") UserDto userDto) throws IOException{
        userService.updateUser(userDto);
        return "redirect:/user";
    }

    @GetMapping("/layout")
    public String allProjects(Model model) {

        UserDetails userDetails = UserDetails.class.cast(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        String username = userDetails.getUsername();
        User currentUser = userRepository.findByUsername(username);
        Integer id = currentUser.getId();
        model.addAttribute("user", userRepository.findOne(id));

        return "layout";
    }


    @GetMapping(path="/editowninfo")
    public String editOwnInfo(Model model) throws IOException {
        UserDetails userDetails = UserDetails.class.cast(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        String username = userDetails.getUsername();
        model.addAttribute("user", userRepository.findByUsername(username));
        return "user/editowninfo";
    }

    @PostMapping(path="/editowninfo")
    public String editOwnInfo(@ModelAttribute("user") UserDto userDto) throws IOException{
        userService.updateUser(userDto);
        return "redirect:/";
    }

    @ModelAttribute("allRoles")
    public List<Role> populateRoles() {
        return roleRepository.findAll();
    }

}
