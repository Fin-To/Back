package FinTo.domain.nationality.service;

import FinTo.domain.nationality.domain.Nationality;
import FinTo.domain.nationality.repository.NationalityRepository;
import FinTo.domain.nationality.exception.NationalityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class NationalityServiceImpl implements NationalityService {

    private final NationalityRepository nationalityRepository;

    @Override
    public Nationality getById(Long nationalityId) {
        return nationalityRepository.findById(nationalityId).orElseThrow(NationalityNotFoundException::new);
    }
}
