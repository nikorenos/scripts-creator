package com.creativelabs.scriptscreator.service;

import com.creativelabs.scriptscreator.client.TrelloClient;
import com.creativelabs.scriptscreator.dto.trello.TrelloBoardDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTest {

    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private TrelloClient trelloClient;


    @Test
    public void fetchTrelloBoards() {
        //Given
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto();
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        trelloBoardDtoList.add(trelloBoardDto);

        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardDtoList);

        // When
        List<TrelloBoardDto> receivedTrelloBoardDtoList = trelloService.fetchTrelloBoards();
        // Then
        assertEquals(1, receivedTrelloBoardDtoList.size());
    }
}