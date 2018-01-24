package by.test.sindalouski.issuetracker.service;

import by.test.sindalouski.issuetracker.dto.ResolutionDto;
import by.test.sindalouski.issuetracker.dto.StatusDto;
import by.test.sindalouski.issuetracker.entity.Resolution;
import by.test.sindalouski.issuetracker.entity.Status;
import org.springframework.data.domain.Page;

import java.io.IOException;

public interface ResolutionService {

    Page<Resolution> listResolutions(int page, String sort);

    void addResolution(ResolutionDto resolutionDto) throws IOException;

    void updateResolution(ResolutionDto resolutionDto);
}
