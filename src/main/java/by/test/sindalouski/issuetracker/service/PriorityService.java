package by.test.sindalouski.issuetracker.service;

import by.test.sindalouski.issuetracker.dto.PriorityDto;
import by.test.sindalouski.issuetracker.entity.Priority;
import org.springframework.data.domain.Page;

import java.io.IOException;

public interface PriorityService {

    Page<Priority> listPriorities(int page, String sort);

    void addPriority(PriorityDto priorityDto) throws IOException;

    void updatePriority(PriorityDto priorityDto);
}
