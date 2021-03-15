package com.gmail.dkylish.persistance.utils;

public class QueryContatiner {

    public static final String GET_BOARD_ID_AND_NAME = "select id, name " +
            "from boards where id = :boardId";
    public static final String GET_ALL_BOARDS_WITHOUT_RELATIONS = "select boards.id, boards.name from boards ";
    public static final String CREATE_COLUMN_WITHOUT_RELATIONS = "insert into columns (title, board_id) values (:title,:boardId)";
    public static final String INSERT_COLUMN_ID = "update boards set columns=:columnsId where id=:boardId";
    public static final String SAVE_COLUMN = "insert into columns (title) values (':title')";
}
