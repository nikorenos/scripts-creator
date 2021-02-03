package com.creativelabs.scriptscreator.controller;

import com.creativelabs.scriptscreator.domain.trello.CreatedTrelloCard;
import com.creativelabs.scriptscreator.dto.trello.TrelloBoardDto;
import com.creativelabs.scriptscreator.dto.trello.TrelloCardDto;
import com.creativelabs.scriptscreator.service.TrelloService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/trello")
@RequiredArgsConstructor
public class TrelloController {
    private final TrelloService trelloService;

    @RequestMapping(method = RequestMethod.GET, value = "/getTrelloBoards")
    public List<TrelloBoardDto> getTrelloBoards() {
        return trelloService.fetchTrelloBoards();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/createTrelloCard")
    public CreatedTrelloCard createTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
        return trelloService.createTrelloCard(trelloCardDto);
    }
}
