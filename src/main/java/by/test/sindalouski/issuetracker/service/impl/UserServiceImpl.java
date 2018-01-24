package by.test.sindalouski.issuetracker.service.impl;

import by.test.sindalouski.issuetracker.dto.RegistrationDto;
import by.test.sindalouski.issuetracker.dto.UserDto;
import by.test.sindalouski.issuetracker.entity.Role;
import by.test.sindalouski.issuetracker.entity.User;
import by.test.sindalouski.issuetracker.repository.RoleRepository;
import by.test.sindalouski.issuetracker.repository.UserRepository;
import by.test.sindalouski.issuetracker.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private UserDto userDto;
    private RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userDto = userDto;
    }

    @Override
    public void register(RegistrationDto registrationDto) {
        User user = new User();
        user.setUsername(registrationDto.getLogin());
        user.setPassword(registrationDto.getPassword());
        userRepository.save(user);
    }

    @Override
    public Page<User> listUsers(int page, String search, String sort) {
        Pageable pageable = new PageRequest(page - 1, 10, new Sort(sort));
        return userRepository.findAll(pageable);
    }

    @Override
    public void remove(Integer id) {

    }

    @Override
    public void updateUser(UserDto userDto) {

        User user = userRepository.findOne(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(userDto.getPassword());
        user.setUsername(userDto.getUsername());
        Role role = roleRepository.findOne(userDto.getRoleId());
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public void addUser(UserDto userDto) throws IOException {

        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(userDto.getPassword());
        user.setUsername(userDto.getUsername());
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findOne(userDto.getRoleId()));
        user.setRoles(roles);
        userRepository.save(user);
    }

}
