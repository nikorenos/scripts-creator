package com.creativelabs.scriptscreator.service;

import com.creativelabs.scriptscreator.domain.trello.CreatedTrelloList;
import com.creativelabs.scriptscreator.dto.trello.*;
import com.creativelabs.scriptscreator.client.TrelloClient;
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

    public List<TrelloListDto> fetchTrelloBoardLists(final String boardId) {
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

    public CreatedTrelloCardDto createTrelloCard(final TrelloCardDto trelloCardDto) {
        CreatedTrelloCardDto newCard = trelloClient.createNewCard(trelloCardDto);
        return newCard;
    }

    public void updateTrelloCard(final String cardId, final TrelloCardDto trelloCardDto) {
        trelloClient.updateCard(cardId, trelloCardDto);
    }

    public void deleteTrelloCard(final String cardId) {
        trelloClient.deleteCard(cardId);
    }

    public List<TrelloCardAttachmentsDto> fetchTrelloCardAttachments(final String cardId) {
        return trelloClient.getCardAttachments(cardId);
    }
    public TrelloCardAttachmentsDto createTrelloCardAttachment(final String cardId, final String url) {
        return trelloClient.createCardAttachment(cardId, url);
    }
}
