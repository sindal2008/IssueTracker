package by.test.sindalouski.issuetracker.service;


import by.test.sindalouski.issuetracker.dto.RegistrationDto;
import by.test.sindalouski.issuetracker.dto.UserDto;
import by.test.sindalouski.issuetracker.entity.User;
import org.springframework.data.domain.Page;

import java.io.IOException;

public interface UserService {
    void register(RegistrationDto registrationDto);

    Page<User> listUsers(int page, String search, String sort);

    void addUser(UserDto userDto) throws IOException;

    void remove(Integer id);

    void updateUser(UserDto userDto);


}
