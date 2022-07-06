package com.example.spa.service;

import com.example.spa.domain.Board;
import com.example.spa.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    private final BoardRepository repository;

    @Override
    public void register(Board board) throws Exception {
        repository.save(board);
    }

    @Override
    public Board read(Long boardNo) throws Exception {
        return repository.findById(boardNo).orElseThrow(EntityExistsException::new);
    }

    @Override
    public List<Board> list() throws Exception {
        return repository.findAll(Sort.by(Sort.Direction.DESC,"boardNo"));
    }

    @Override
    public void modify(Board board) throws Exception {
        Board findBoard = repository.findById(board.getBoardNo()).orElseThrow(EntityExistsException::new);
        findBoard.setTitle(board.getTitle());
        findBoard.setContent(board.getContent());
        repository.save(findBoard);
    }

    @Override
    public void remove(Long boardNo) throws Exception {
        repository.deleteById(boardNo);
    }
}
