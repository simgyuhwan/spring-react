package com.sim.todo.dto;

import com.sim.todo.entity.TodoEntity;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TodoDto {
    private String id;
    private String title;
    private boolean done;

    public TodoDto(final TodoEntity todoEntity){
        id = todoEntity.getId();
        title = todoEntity.getTitle();
        done = todoEntity.isDone();
    }
}
