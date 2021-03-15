package com.gmail.dkylish.service.impl;

import com.gmail.dkylish.entity.Board;
import com.gmail.dkylish.persistance.BoardRepository;
import com.gmail.dkylish.service.BoardService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    private BoardRepository boardRepository;

    public BoardServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public Board createBoard(String name) {
        Board board = new Board();
        board.setName(name);
        return boardRepository.save(board);
    }


    @Override
    public Board getFullBoardById(Long id) {
       return boardRepository.findBoardById(id);
    }

    @Override
    public Board findBoardIdAndNameById(Long id) {
        return boardRepository.getBoardIdAndNameById(id);

    }
    @Override
    public List<Board> getAllBoardsWithoutRelations(){
       return boardRepository.findAllWithoutRelations();
    }

    @Override
    public Board updateBoard(Long id, Board updatedBoard) {
        Board board = boardRepository.getBoardIdAndNameById(id);
        board.setName(updatedBoard.getName());
        return boardRepository.save(board);
    }

    @Override
    public Board deleteBoard(Long id) {
        Board board = boardRepository.findBoardById(id);
        boardRepository.delete(board);
        return board;
    }

}
