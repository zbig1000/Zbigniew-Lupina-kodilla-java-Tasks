package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreatedTrelloCard {

    private String votes;
    private String board;
    private String card;

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("shortUrl")
    private String shortUrl;

    @SuppressWarnings("unchecked")
    @JsonProperty("badges")
    private void unpackNested(Map<String,Object> badges) {
        this.votes = badges.get("votes").toString();
        Map<String,Object> attachmentsByType = (Map<String,Object>)badges.get("attachmentsByType");
        Map<String,Object> trello = (Map<String,Object>)attachmentsByType.get("trello");
        this.board = trello.get("board").toString();
        this.card = trello.get("card").toString();
    }
}
