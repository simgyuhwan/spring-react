package com.example.spa.controller;

import com.example.spa.common.security.domain.CustomUser;
import com.example.spa.domain.Board;
import com.example.spa.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {
    private final BoardService service;

    @PreAuthorize("hasRole('MEMBER')")
    @PostMapping
    public ResponseEntity<Board> register(@Validated @RequestBody Board board, @AuthenticationPrincipal CustomUser customUser)throws Exception{
        String userId = customUser.getUserId();
        log.info("register userId = "+ userId);

        board.setWriter(userId);
        service.register(board);

        Board createBoard = service.read(board.getBoardNo());
        return new ResponseEntity<>(createBoard, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Board>> list()throws Exception{
        log.info("list");
        return new ResponseEntity<>(service.list(), HttpStatus.OK);
    }

    @GetMapping("/{boardNo}")
    public ResponseEntity<Board> read(@PathVariable("boardNo")Long boardNo)throws Exception{
            log.info("read");
            Board board = service.read(boardNo);
            return new ResponseEntity<>(board, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('MEMBER') and principal.username == #board.writer")
    @PutMapping("/{boardNo}")
    public ResponseEntity<Board> modify(@PathVariable("boardNo")Long boardNo, @Validated @RequestBody Board board) throws Exception{
        log.info("modify");

        board.setBoardNo(boardNo);
        service.modify(board);
        return new ResponseEntity<>(board, HttpStatus.OK);
    }

    @PreAuthorize("(hasRole('MEMBER') and principal.username == #writer) or hasRole('ADMIN')")
    @DeleteMapping("/{boardNo}")
    public ResponseEntity<Void> remove(@PathVariable("boardNo")Long boardNo, @RequestParam("writer")String writer) throws Exception{
        service.remove(boardNo);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
