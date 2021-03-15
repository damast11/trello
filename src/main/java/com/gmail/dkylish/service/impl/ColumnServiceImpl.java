package com.gmail.dkylish.service.impl;

import com.gmail.dkylish.entity.Board;
import com.gmail.dkylish.entity.Columns;
import com.gmail.dkylish.persistance.BoardRepository;
import com.gmail.dkylish.persistance.ColumnRepository;
import com.gmail.dkylish.service.ColumnService;
import org.springframework.stereotype.Service;

@Service
public class ColumnServiceImpl implements ColumnService {

    private ColumnRepository columnRepository;
    private BoardRepository boardRepository;

    public ColumnServiceImpl(ColumnRepository columnRepository,
                             BoardRepository boardRepository) {
        this.columnRepository = columnRepository;
        this.boardRepository = boardRepository;
    }

    @Override
    public Columns createColumn(String title, Long boardId) {
        Board boardById = boardRepository.findBoardById(boardId);
        Columns column = new Columns();
        column.setTitle(title);
        column.setBoard(boardById);
       return columnRepository.save(column);
    }

    @Override
    public Columns updateColumn(Long id, Columns updatecolumn) {
        Columns column = columnRepository.findColumnsById(id);
        column.setId(id);
        column.setBoard(updatecolumn.getBoard());
        column.setTitle(updatecolumn.getTitle());
        column.setTasks(updatecolumn.getTasks());
       return columnRepository.save(column);
    }

    @Override
    public Columns deleteColumn(Long id) {
        Columns columnsById = columnRepository.findColumnsById(id);
        columnRepository.delete(columnsById);
        return columnsById;
    }
}
