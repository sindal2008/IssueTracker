package by.test.sindalouski.issuetracker.service.impl;

import by.test.sindalouski.issuetracker.entity.User;
import by.test.sindalouski.issuetracker.repository.UserRepository;
import by.test.sindalouski.issuetracker.service.SecurityService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {
    private UserRepository userRepository;

    public SecurityServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getCurrentUser() {
        UserDetails userDetails = UserDetails.class.cast(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        String username = userDetails.getUsername();
        return userRepository.findByUsername(username);
    }
}
