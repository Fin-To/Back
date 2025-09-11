package FinTo.domain.nationality.controller;

import FinTo.domain.nationality.dto.response.NationalityResponseDto;
import FinTo.domain.nationality.service.NationalityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nationalities")
@RequiredArgsConstructor
public class NationalityController {

    private final NationalityService nationalityService;

    @GetMapping
    public ResponseEntity<List<NationalityResponseDto>> getAllNationalities() {
        return ResponseEntity.ok(nationalityService.getAllNationalities());
    }
}
