package by.test.sindalouski.issuetracker.service.impl;

import by.test.sindalouski.issuetracker.dto.PriorityDto;
import by.test.sindalouski.issuetracker.entity.Priority;
import by.test.sindalouski.issuetracker.repository.PriorityRepository;
import by.test.sindalouski.issuetracker.service.PriorityService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PriorityServiceImpl implements PriorityService {

    private PriorityRepository priorityRepository;

    public PriorityServiceImpl(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    @Override
    public Page<Priority> listPriorities(int page, String sort) {
        Pageable pageable = new PageRequest(page - 1, 10, new Sort(sort));
        return priorityRepository.findAll(pageable);
    }

    @Override
    public void addPriority(PriorityDto priorityDto) throws IOException {

        Priority priority = new Priority();
        priority.setPriorityName(priorityDto.getPriorityName());
        priorityRepository.save(priority);
    }

    @Override
    public void updatePriority(PriorityDto priorityDto) {
        Priority priority = priorityRepository.findOne(priorityDto.getId());
        priority.setPriorityName(priorityDto.getPriorityName());
        priorityRepository.save(priority);
    }
}
