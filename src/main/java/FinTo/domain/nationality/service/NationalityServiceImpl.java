package FinTo.domain.nationality.service;

import FinTo.domain.nationality.domain.Nationality;
import FinTo.domain.nationality.dto.response.NationalityResponseDto;
import FinTo.domain.nationality.repository.NationalityRepository;
import FinTo.domain.nationality.exception.NationalityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class NationalityServiceImpl implements NationalityService {

    private final NationalityRepository nationalityRepository;

    @Override
    public Nationality getById(Long nationalityId) {
        return nationalityRepository.findById(nationalityId).orElseThrow(NationalityNotFoundException::new);
    }

    @Override
    public List<NationalityResponseDto> getAllNationalities() {
        return nationalityRepository.findAll().stream()
                .map(n -> new NationalityResponseDto(
                        n.getId(),
                        n.getName(),
                        n.getCode(),
                        n.getEmoji()
                ))
                .collect(Collectors.toList());
    }
}
