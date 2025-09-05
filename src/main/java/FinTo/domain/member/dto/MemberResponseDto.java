package FinTo.domain.member.dto;

import FinTo.domain.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;

@Data
@AllArgsConstructor
public class MemberResponseDto {


    public static ResponseEntity<MemberResponseDto> toResponseEntity(Member member){
        return ResponseEntity.ok(of(member));
    }

    public static MemberResponseDto of(Member member){
        return new MemberResponseDto();
    }
}
