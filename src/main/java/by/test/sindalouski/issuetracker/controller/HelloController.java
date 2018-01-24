package by.test.sindalouski.issuetracker.controller;

import by.test.sindalouski.issuetracker.entity.User;
import by.test.sindalouski.issuetracker.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/hello")
public class HelloController {

    private final UserRepository userRepository;

    public HelloController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String hello(Model model)  throws IOException {

        UserDetails userDetails = UserDetails.class.cast(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        String username = userDetails.getUsername();
        User currentUser = userRepository.findByUsername(username);
        Integer id = currentUser.getId();
        model.addAttribute("user", userRepository.findOne(id));

        return "hello";
    }

    @ModelAttribute("allUsers")
    public List<User> populateUsers() {
        return userRepository.findAll();
    }
}

