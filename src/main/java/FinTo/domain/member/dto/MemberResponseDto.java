package FinTo.domain.member.dto;

import FinTo.domain.member.domain.Gender;
import FinTo.domain.member.domain.Member;
import FinTo.domain.nationality.Nationality;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Optional;

@Data
@AllArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
public class MemberResponseDto {

    private Long id;
    private String name;
    private LocalDate birthDate;
    private Gender gender;
    private String nationality;

    public static ResponseEntity<MemberResponseDto> toResponseEntity(Member member){
        return ResponseEntity.ok(of(member));
    }

    public static MemberResponseDto of(Member member){
        return MemberResponseDto.builder()
                .id(member.getId())
                .name(member.getName())
                .birthDate(member.getBirthDate())
                .gender(member.getGender())
                .nationality(
                        Optional.ofNullable(member.getNationality())
                                .map(Nationality::getName)
                                .orElse(null)
                )
                .build();
    }
}
