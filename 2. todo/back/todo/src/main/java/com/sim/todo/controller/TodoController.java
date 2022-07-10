package com.sim.todo.controller;

import com.sim.todo.dto.ResponseDTO;
import com.sim.todo.dto.TodoDto;
import com.sim.todo.entity.TodoEntity;
import com.sim.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/todo")
public class TodoController {

    @Autowired
    private TodoService service;

    @PostMapping
    public ResponseEntity<?> createTodo(@RequestBody TodoDto dto){
        try{
            // 보안 적용전 임시 ID
            String temporaryUserId = "temporary-user";
            TodoEntity entity = TodoDto.toEntity(dto);
            entity.init(temporaryUserId);

            List<TodoEntity> entities = service.create(entity);

            List<TodoDto> dtos = entities.stream()
                                        .map(TodoDto::new)
                                        .collect(Collectors.toList());

            ResponseDTO<TodoDto> response = ResponseDTO.<TodoDto>builder()
                                                        .data(dtos)
                                                        .build();
            return ResponseEntity.ok().body(response);

        } catch (Exception e){
            String error = e.getMessage();
            ResponseDTO<TodoDto> response = ResponseDTO.<TodoDto>builder()
                    .error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
   }

   @GetMapping
   public ResponseEntity<?> retrieveTodoList(){
     String temporaryUserId = "temporary-user";

     List<TodoEntity> entities = service.retrieve(temporaryUserId);

     List<TodoDto> dtos = entities.stream().map(TodoDto::new)
               .collect(Collectors.toList());

     ResponseDTO<TodoDto> response = ResponseDTO.<TodoDto>builder().data(dtos)
               .build();

     return ResponseEntity.ok().body(response);
   }

   @PutMapping
   public ResponseEntity<?> updateTodo(@RequestBody TodoDto dto){
       String temporaryUserId = "temporary-user";
       TodoEntity entity = TodoDto.toEntity(dto);

       entity.initUserId(temporaryUserId);

       List<TodoEntity> entities = service.update(entity);

       List<TodoDto> dtos = entities.stream().map(TodoDto::new).collect(Collectors.toList());

       ResponseDTO<TodoDto> response = ResponseDTO.<TodoDto>builder().data(dtos).build();

       return ResponseEntity.ok().body(response);
   }

   @DeleteMapping
    public ResponseEntity<?> deleteTodo(@RequestBody TodoDto dto){
        try {
            String temporaryUserId = "temporary-user";
            TodoEntity entity = TodoDto.toEntity(dto);

            entity.initUserId(temporaryUserId);

            List<TodoEntity> entities = service.delete(entity);

            List<TodoDto> dtos = entities.stream().map(TodoDto::new).collect(Collectors.toList());
            ResponseDTO<TodoDto> response = ResponseDTO.<TodoDto>builder().data(dtos).build();
            return ResponseEntity.ok().body(response);
        }catch (Exception e){
            String error = e.getMessage();
            ResponseDTO<TodoDto> response = ResponseDTO.<TodoDto>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
   }
}
