package com.gmail.dkylish.service;

import com.gmail.dkylish.entity.Board;

import java.util.List;

/**
 * Service for boards.
 */
public interface BoardService {

    /**
     * Create Board
     * @param name name
     * @return created board
     */
    Board createBoard(String name);

    /**
     * Get Board by Id
     * @param id Board id
     * @return full info with relations about board
     */
    Board getFullBoardById(Long id);

    /**
     * Get Board by Id
     * @param id Board id
     * @return info about board without relations
     */
    Board findBoardIdAndNameById(Long id);

    /**
     * Get All Boards
     * @return List of Boards
     */
    List<Board> getAllBoardsWithoutRelations();

    /**
     * Update Board
     * @param id  Board id
     * @param updatedBoard  Board
     * @return updated board
     */
    Board updateBoard(Long id, Board updatedBoard);

    /**
     * Delete Board
     * @param id delete Board by id
     * @return deleted Board
     */
    Board deleteBoard(Long id);

//    Board addColumnsToBoard(Long boardId, List<Long> columnsIds);



}
