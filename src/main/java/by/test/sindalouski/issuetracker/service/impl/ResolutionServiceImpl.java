package by.test.sindalouski.issuetracker.service.impl;

import by.test.sindalouski.issuetracker.dto.ResolutionDto;
import by.test.sindalouski.issuetracker.entity.Resolution;
import by.test.sindalouski.issuetracker.repository.ResolutionRepository;
import by.test.sindalouski.issuetracker.service.ResolutionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ResolutionServiceImpl implements ResolutionService {
    private ResolutionRepository resolutionRepository;

    public ResolutionServiceImpl(ResolutionRepository resolutionRepository) {
        this.resolutionRepository = resolutionRepository;
    }

    @Override
    public Page<Resolution> listResolutions(int page, String sort) {
        Pageable pageable = new PageRequest(page - 1, 10, new Sort(sort));
        return resolutionRepository.findAll(pageable);
    }

    @Override
    public void addResolution(ResolutionDto resolutionDto) throws IOException {

        Resolution resolution = new Resolution();
        resolution.setResolutionName(resolutionDto.getResolutionName());
        resolutionRepository.save(resolution);
    }

    @Override
    public void updateResolution(ResolutionDto resolutionDto) {

        Resolution resolution = resolutionRepository.findOne(resolutionDto.getId());
        resolution.setResolutionName(resolutionDto.getResolutionName());
        resolutionRepository.save(resolution);
    }
}
