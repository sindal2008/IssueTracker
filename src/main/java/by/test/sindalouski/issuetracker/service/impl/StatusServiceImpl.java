package by.test.sindalouski.issuetracker.service.impl;

import by.test.sindalouski.issuetracker.dto.StatusDto;
import by.test.sindalouski.issuetracker.entity.Status;
import by.test.sindalouski.issuetracker.repository.StatusRepository;
import by.test.sindalouski.issuetracker.service.StatusService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class StatusServiceImpl implements StatusService{

    private StatusRepository statusRepository;

    public StatusServiceImpl(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override
    public Page<Status> listStatuses(int page, String sort) {
        Pageable pageable = new PageRequest(page - 1, 10, new Sort(sort));
        return statusRepository.findAll(pageable);
    }

    @Override
    public void updateStatus(StatusDto statusDto) {

        Status status = statusRepository.findOne(statusDto.getId());
        status.setStatusName(statusDto.getStatusName());
        statusRepository.save(status);
    }
}
