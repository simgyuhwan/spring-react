package com.sim.todo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class TodoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String userId;
    private String title;
    private boolean done;
}
