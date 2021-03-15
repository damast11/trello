package com.gmail.dkylish.controller;

import com.gmail.dkylish.entity.Columns;
import com.gmail.dkylish.service.ColumnService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ColumnRest {

    ColumnService columnService;

    public ColumnRest(ColumnService columnService) {
        this.columnService = columnService;
    }

    @PostMapping("/board/{boardId}/column")
    public Columns createColumn(@RequestParam(value = "title") String title,
                                @PathVariable("boardId") Long boardId){
       return columnService.createColumn(title,boardId);
    }

    @PutMapping("/board/{boardId}/column/{columnId}")
    public Columns updateColumn(@PathVariable(value = "columnId") Long columnId,
                                @RequestBody Columns columns,
                                @PathVariable(value = "boardId") Long boardId){
        return columnService.updateColumn(columnId,columns);
    }

    @DeleteMapping("/board/{boardId}/column/{columnId}")
    public Columns deleteColumn(@PathVariable(value = "columnId") Long columnId,
                                @PathVariable(value = "boardId") Long boardId){
        return columnService.deleteColumn(columnId);
    }

}
