package com.gmail.dkylish.dto;

import java.util.Objects;

public class BoardDTO {
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardDTO boardDTO = (BoardDTO) o;
        return Objects.equals(id, boardDTO.id) &&
                Objects.equals(name, boardDTO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
