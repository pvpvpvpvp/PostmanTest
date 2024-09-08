package com.example.postmantestapi.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@Entity(name = "member")
@Getter @Setter @Builder
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    @Column(name="nickName",unique = true)
    private String nickName;
    private String name;
    private String password;

    @ConstructorBinding
    public Member(Long id, String name, String nickName,String password) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.nickName = nickName;
    }
}