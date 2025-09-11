package FinTo.domain.nationality.service;

import FinTo.domain.nationality.domain.Nationality;
import FinTo.domain.nationality.dto.response.NationalityResponseDto;

import java.util.List;

public interface NationalityService {
    Nationality getById(Long nationalityId);
    List<NationalityResponseDto> getAllNationalities();
}
