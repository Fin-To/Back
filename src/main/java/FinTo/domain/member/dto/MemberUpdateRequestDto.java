package FinTo.domain.member.dto;

import FinTo.domain.member.domain.Gender;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MemberUpdateRequestDto {
    private String name;
    private LocalDate birthDate;
    private Gender gender;
    private Long nationalityId;
}
