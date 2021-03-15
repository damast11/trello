package com.gmail.dkylish.converters;

import com.gmail.dkylish.dto.BoardDTO;
import com.gmail.dkylish.entity.Board;
import org.springframework.stereotype.Component;

@Component
public class BoardConverter {

    public BoardDTO boardEntityToDTO(Board board){
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(board.getId());
        boardDTO.setName(board.getName());
        return boardDTO;
    }

}
