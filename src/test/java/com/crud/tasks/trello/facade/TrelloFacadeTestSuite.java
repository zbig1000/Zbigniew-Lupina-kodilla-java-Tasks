package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloFacadeTestSuite {

    @InjectMocks()
    private TrelloFacade trelloFacade;

    @Mock
    private TrelloService trelloService;

    @Spy
    private TrelloValidator trelloValidator;

    @Spy
    private TrelloMapper trelloMapper;

    @Before
    public void init() {
    }

    @Test
    public void shouldFetchTrelloBoards() throws URISyntaxException {
        //Given
        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoardDto("test_id","test",  new ArrayList<>()));

        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoards);

        //When
        List<TrelloBoardDto> fetchedTrelloBoards = trelloFacade.fetchTrelloBoards();

        //Then
        assertEquals(1, fetchedTrelloBoards.size());
        assertEquals("test_id", fetchedTrelloBoards.get(0).getId());
        assertEquals("test", fetchedTrelloBoards.get(0).getName());
        assertEquals(new ArrayList<>(), fetchedTrelloBoards.get(0).getLists());
    }

    @Test
    public void shouldCreateCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto(
                "Test task",
                "Test description",
                "top",
                "test_id"
        );

        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto(
                "1",
                "test",
                "http://test.com",
                null
        );

        when(trelloService.createdTrelloCard(trelloCardDto)).thenReturn(createdTrelloCardDto);

        //When
        CreatedTrelloCardDto newCard = trelloFacade.createCard(trelloCardDto);

        //Then
        assertEquals("1", newCard.getId());
        assertEquals("test", newCard.getName());
        assertEquals("http://test.com", newCard.getShortUrl());
    }
}
