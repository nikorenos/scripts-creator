package com.creativelabs.scriptscreator.service;

import com.creativelabs.scriptscreator.domain.trello.CreatedTrelloCard;
import com.creativelabs.scriptscreator.dto.trello.TrelloBoardDto;
import com.creativelabs.scriptscreator.dto.trello.TrelloBoardListDto;
import com.creativelabs.scriptscreator.dto.trello.TrelloCardDto;
import com.creativelabs.scriptscreator.trello.TrelloClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TrelloService {
    private final TrelloClient trelloClient;

    public List<TrelloBoardDto> fetchTrelloBoards() {
        return trelloClient.getTrelloBoards();
    }

    public List<TrelloBoardListDto> fetchTrelloBoardLists(String boardId) {
        return trelloClient.getTrelloBoardLists(boardId);
    }

    public CreatedTrelloCard createTrelloCard(final TrelloCardDto trelloCardDto) {
        CreatedTrelloCard newCard = trelloClient.createNewCard(trelloCardDto);
        return newCard;
    }
}
