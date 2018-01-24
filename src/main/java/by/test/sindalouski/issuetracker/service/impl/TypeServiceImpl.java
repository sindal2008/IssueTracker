package by.test.sindalouski.issuetracker.service.impl;

import by.test.sindalouski.issuetracker.dto.TypeDto;
import by.test.sindalouski.issuetracker.entity.Type;
import by.test.sindalouski.issuetracker.repository.TypeRepository;
import by.test.sindalouski.issuetracker.service.TypeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class TypeServiceImpl implements TypeService {

    private TypeRepository typeRepository;

    public TypeServiceImpl(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    @Override
    public Page<Type> listTypes(int page, String sort) {
        Pageable pageable = new PageRequest(page - 1, 10, new Sort(sort));
        return typeRepository.findAll(pageable);

    }

    @Override
    public void addType(TypeDto typeDto) throws IOException {

        Type type = new Type();
        type.setTypeName(typeDto.getTypeName());
        typeRepository.save(type);
    }

    @Override
    public void updateType(TypeDto typeDto) {

        Type type = typeRepository.findOne(typeDto.getId());
        type.setTypeName(typeDto.getTypeName());
        typeRepository.save(type);
    }
}


