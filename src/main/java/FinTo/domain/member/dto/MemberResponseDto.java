package FinTo.domain.member.dto;

import FinTo.domain.member.domain.Gender;
import FinTo.domain.member.domain.Member;
import FinTo.domain.nationality.domain.Nationality;
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
    private String profileImg;
    private String name;
    private String nationality;

    public static ResponseEntity<MemberResponseDto> toResponseEntity(Member member){
        return ResponseEntity.ok(of(member));
    }

    public static MemberResponseDto of(Member member){
        return MemberResponseDto.builder()
                .id(member.getId())
                .profileImg(member.getProfileImg())
                .name(member.getName())
                .nationality(
                        Optional.ofNullable(member.getNationality())
                                .map(Nationality::getName)
                                .orElse(null)
                )
                .build();
    }
}
