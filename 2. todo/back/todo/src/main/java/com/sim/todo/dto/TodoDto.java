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

    public TodoDto(final TodoEntity entity){
        id = entity.getId();
        title = entity.getTitle();
        done = entity.isDone();
    }

    public static TodoEntity toEntity(final TodoDto dto){
        return TodoEntity.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .done(dto.isDone())
                .build();
    }
}
