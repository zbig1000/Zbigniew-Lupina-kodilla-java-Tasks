package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@AllArgsConstructor
@Getter
public class TrelloCardDto {

    private String name;
    private String description;
    private String pos;
    private String listId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TrelloCardDto)) return false;
        TrelloCardDto that = (TrelloCardDto) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(pos, that.pos) &&
                Objects.equals(listId, that.listId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, description, pos, listId);
    }
}
