package com.sim.todo.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity @Table(name ="Todo")
public class TodoEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    private String userId;
    private String title;
    private boolean done;

    public void init(String userId){
        id = null;
        this.userId = userId;
    }

    public void initUserId(String userId){
        this.userId = userId;
    }

    public TodoEntity update(TodoEntity entity){
        this.title = entity.getTitle();
        this.done = entity.isDone();
        return this;
    }
}
