package by.test.sindalouski.issuetracker.service;

import by.test.sindalouski.issuetracker.dto.StatusDto;
import by.test.sindalouski.issuetracker.entity.Status;
import org.springframework.data.domain.Page;

public interface StatusService{

    Page<Status> listStatuses(int page, String sort);

    void updateStatus(StatusDto statusDto);
}
