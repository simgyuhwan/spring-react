package com.example.spa.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "pds")
public class Pds extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    private String itemName;

    private String description;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id")
    private List<PdsFile> pdsFiles = new ArrayList<>();

    @Transient
    private String[] files;

    private Integer viewCnt = 0;

    public void addItemFile(PdsFile itemFile){
        pdsFiles.add(itemFile);
    }

    public void createItemFile(){
        pdsFiles.clear();
    }

}
