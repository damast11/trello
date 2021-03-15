package com.gmail.dkylish.persistance;

import com.gmail.dkylish.entity.Board;
import com.gmail.dkylish.persistance.utils.QueryContatiner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, String> {

    Board findBoardById(Long id);

    @Query(value = QueryContatiner.GET_BOARD_ID_AND_NAME, nativeQuery = true)
    Board getBoardIdAndNameById(@Param("boardId") Long boardId);

    @Query(value = QueryContatiner.GET_ALL_BOARDS_WITHOUT_RELATIONS, nativeQuery = true)
    List<Board> findAllWithoutRelations();

    @Query(value = QueryContatiner.INSERT_COLUMN_ID, nativeQuery = true)
    void insertColumnId(@Param("columnsId") Long columnId,@Param("boardId")Long boardId);
}
