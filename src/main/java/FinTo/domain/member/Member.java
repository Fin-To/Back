package FinTo.domain.member;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Member {
    @Id
    @GeneratedValue
    private Long id;
    @Enumerated(EnumType.STRING)
    private MemberRole role;
}
