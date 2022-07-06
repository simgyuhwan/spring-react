package com.example.spa.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@JsonIgnoreProperties(value = "hibernateLazyInitializer")
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "groupCode")
@Table(name = "code_group")
@NoArgsConstructor
public class CodeGroup extends BaseEntity{

    @Id
    @Column(length = 3)
    private String groupCode;

    @Column(length = 30, nullable = false)
    private String groupName;

    @Column(length = 1)
    private String useYn ="Y";

    @JsonIgnore
    @OneToMany
    @JoinColumn(name="groupCode")
    private List<CodeDetail> codeDetails;


}
