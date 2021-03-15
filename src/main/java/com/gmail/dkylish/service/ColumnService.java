package com.gmail.dkylish.service;

import com.gmail.dkylish.entity.Columns;

/**
 * Service for columns.
 */
public interface ColumnService {

    /**
     * Create Column without relations
     * @param title title
     * @return created column
     */

    Columns createColumn(String title, Long boardId);

    /**
     * Update Column without relations
     * @param id id
     * @param column column
     * @return updated column
     */
    Columns updateColumn(Long id, Columns column);

    /**
     * Delete Column with relations
     * @param id Column id
     * @return delete column without relations
     */
    Columns deleteColumn(Long id);
}
