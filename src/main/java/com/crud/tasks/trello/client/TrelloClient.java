package com.crud.tasks.trello.client;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.Array;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class TrelloClient {
    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;

    @Value("${trello.app.key}")
    private String trelloKey;

    @Value("${trello.app.token}")
    private String trelloToken;

    @Value("${trello.app.username}")
    private String username;

    @Autowired
    private RestTemplate restTemplate;

    public List<TrelloBoardDto> getTrelloBoards() {

        URI url = getURL(trelloApiEndpoint, trelloKey, trelloToken, username);
        TrelloBoardDto[] boardsRespone = restTemplate.getForObject(url, TrelloBoardDto[].class);

        return Optional.ofNullable(boardsRespone).map(Arrays::asList).orElseGet(ArrayList::new);
    }

    private URI getURL(String serverAddress, String key, String token, String user) {
        return UriComponentsBuilder.fromHttpUrl(serverAddress + "/members/" + user + "/boards")
                .queryParam("key", key)
                .queryParam("token", token)
                .queryParam("lists", "all")
                .queryParam("fields", "name,id").build().encode().toUri();
    }

    public CreatedTrelloCard createNewCard(TrelloCardDto trelloCardDto) {

        URI url = UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/cards/")
                .queryParam("key", trelloKey)
                .queryParam("token", trelloToken)
                .queryParam("name",trelloCardDto.getName())
                .queryParam("desc",trelloCardDto.getDescription())
                .queryParam("pos",trelloCardDto.getPos())
                .queryParam("idList",trelloCardDto.getListId()).build().encode().toUri();

        return restTemplate.postForObject(url, null, CreatedTrelloCard.class);
    }
}
