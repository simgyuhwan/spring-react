package com.example.spa.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(value = "hibernateLazyInitializer")
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "userNo")
@Entity
@NoArgsConstructor
@Table(name = "member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_no")
    private Long userNo;

    @NotBlank
    @Column(length = 50, nullable = false)
    private String userId;

    @NotBlank
    @Column(length = 200, nullable = false)
    private String userPw;

    @Column(length = 3, nullable = false)
    private String job;

    @NotBlank
    @Column(length = 100, nullable = false)
    private String userName;

    private int coin;

    // JoinColumn 을 통해서 외래키 지정
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="user_no")
    private List<MemberAuth> authList = new ArrayList<>();

    public void addAuth(MemberAuth auth){
        authList.add(auth);
    }

    public void clearAuthList(){
        authList.clear();
    }

}
