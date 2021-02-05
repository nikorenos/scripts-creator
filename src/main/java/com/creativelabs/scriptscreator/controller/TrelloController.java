package com.creativelabs.scriptscreator.controller;

import com.creativelabs.scriptscreator.domain.trello.CreatedTrelloCard;
import com.creativelabs.scriptscreator.domain.trello.CreatedTrelloList;
import com.creativelabs.scriptscreator.dto.trello.*;
import com.creativelabs.scriptscreator.service.TrelloService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/trello")
@RequiredArgsConstructor
public class TrelloController {
    private final TrelloService trelloService;

    @GetMapping
    public List<TrelloBoardDto> getTrelloBoards() {
        return trelloService.fetchTrelloBoards();
    }

    @GetMapping("boards/{boardId}/lists")
    public List<TrelloBoardListDto> getTrelloBoardLists(@PathVariable String boardId) {
        return trelloService.fetchTrelloBoardLists(boardId);
    }
    @PostMapping("boards/{boardId}/lists")
    public CreatedTrelloList createTrelloListOnBoard(@PathVariable String boardId,
                                                     @RequestBody TrelloListDto trelloListDto) {
        return trelloService.createTrelloList(boardId, trelloListDto);
    }
    @PutMapping("lists/{listId}")
    public void updateTrelloListOnBoard(@PathVariable String listId,
                                                     @RequestBody TrelloListDto trelloListDto) {
        trelloService.updateTrelloList(listId, trelloListDto);
    }

    @GetMapping("cards/{cardId}")
    public TrelloCardDto getTrelloCard(@PathVariable String cardId) {
        return trelloService.fetchTrelloCard(cardId);
    }

    @PostMapping
    public CreatedTrelloCard createTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
        return trelloService.createTrelloCard(trelloCardDto);
    }

    @PutMapping("cards/{cardId}")
    public void updateTrelloCard(@PathVariable String cardId,
                                        @RequestBody TrelloCardDto trelloCardDto) {
        trelloService.updateTrelloCard(cardId, trelloCardDto);
    }

    @GetMapping("cards/{cardId}/attachments")
    public List<TrelloCardAttachmentsDto> getTrelloCardAttachments(@PathVariable String cardId) {
        return trelloService.fetchTrelloCardAttachments(cardId);
    }

    @PostMapping("cards/{cardId}/attachments")
    public TrelloCardAttachmentsDto createTrelloCardAttachment(@PathVariable String cardId, @RequestParam String url) {
        return trelloService.createTrelloCardAttachment(cardId, url);
    }
}
