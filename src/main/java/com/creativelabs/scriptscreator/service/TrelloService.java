package com.creativelabs.scriptscreator.service;

import com.creativelabs.scriptscreator.domain.trello.CreatedTrelloCard;
import com.creativelabs.scriptscreator.domain.trello.CreatedTrelloList;
import com.creativelabs.scriptscreator.dto.trello.TrelloBoardDto;
import com.creativelabs.scriptscreator.dto.trello.TrelloBoardListDto;
import com.creativelabs.scriptscreator.dto.trello.TrelloCardDto;
import com.creativelabs.scriptscreator.dto.trello.TrelloListDto;
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

    public List<TrelloBoardListDto> fetchTrelloBoardLists(final String boardId) {
        return trelloClient.getTrelloBoardLists(boardId);
    }

    public CreatedTrelloList createTrelloList(final String boardId, TrelloListDto trelloListDto) {
        CreatedTrelloList newList = trelloClient.createNewList(boardId, trelloListDto);
        return newList;
    }

    public void updateTrelloList(final String listId, final TrelloListDto trelloListDto) {
        trelloClient.updateList(listId, trelloListDto);
    }

    public TrelloCardDto fetchTrelloCard(final String cardId) {
        return trelloClient.getCard(cardId);
    }

    public CreatedTrelloCard createTrelloCard(final TrelloCardDto trelloCardDto) {
        CreatedTrelloCard newCard = trelloClient.createNewCard(trelloCardDto);
        return newCard;
    }

    public void updateTrelloCard(final String cardId, final TrelloCardDto trelloCardDto) {
        trelloClient.updateCard(cardId, trelloCardDto);
    }
}