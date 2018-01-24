package by.test.sindalouski.issuetracker.service;

import by.test.sindalouski.issuetracker.dto.TypeDto;
import by.test.sindalouski.issuetracker.entity.Type;
import org.springframework.data.domain.Page;

import java.io.IOException;

public interface TypeService {

    Page<Type> listTypes(int page, String sort);

    void addType(TypeDto typeDto) throws IOException;

    void updateType(TypeDto typeDto);
}
