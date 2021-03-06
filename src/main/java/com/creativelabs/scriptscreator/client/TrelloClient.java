package com.creativelabs.scriptscreator.client;


import com.creativelabs.scriptscreator.config.TrelloConfig;
import com.creativelabs.scriptscreator.domain.trello.CreatedTrelloList;
import com.creativelabs.scriptscreator.dto.trello.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Optional.ofNullable;

@RequiredArgsConstructor
@Component
public class TrelloClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(TrelloClient.class);

    private final RestTemplate restTemplate;
    private final TrelloConfig trelloConfig;

    public List<TrelloBoardDto> getTrelloBoards() {
        URI url = UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + "/members/" + trelloConfig.getTrelloUsername() + "/boards")
                .queryParam("key", trelloConfig.getTrelloAppKey())
                .queryParam("token", trelloConfig.getTrelloToken())
                .queryParam("fields", "name,id")
                .queryParam("lists", "all").build().encode().toUri();

        try {
            TrelloBoardDto[] boardsResponse = restTemplate.getForObject(url, TrelloBoardDto[].class);
            return Arrays.asList(ofNullable(boardsResponse).orElse(new TrelloBoardDto[0]));
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(),e);
            return new ArrayList<>();
        }
    }
    public List<TrelloListDto> getTrelloBoardLists(String boardId) {
        URI url = UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + "/boards/" + boardId + "/lists")
                .queryParam("key", trelloConfig.getTrelloAppKey())
                .queryParam("token", trelloConfig.getTrelloToken())
                .queryParam("fields", "id,name,pos,closed").build().encode().toUri();

        try {
            TrelloListDto[] boardsResponse = restTemplate.getForObject(url, TrelloListDto[].class);
            return Arrays.asList(ofNullable(boardsResponse).orElse(new TrelloListDto[0]));
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(),e);
            return new ArrayList<>();
        }
    }

    public CreatedTrelloList createList(String boardId, TrelloListDto trelloListDto) {
        URI url = UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + "/boards/" + boardId + "/lists")
                .queryParam("key", trelloConfig.getTrelloAppKey())
                .queryParam("token", trelloConfig.getTrelloToken())
                .queryParam("name", trelloListDto.getName())
                .queryParam("pos", trelloListDto.getPos()).build().encode().toUri();
        return restTemplate.postForObject(url, null, CreatedTrelloList.class);
    }

    public void updateList(String listId, final TrelloListDto trelloListDto) {
        URI url = UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + "/lists/" + listId)
                .queryParam("key", trelloConfig.getTrelloAppKey())
                .queryParam("token", trelloConfig.getTrelloToken()).build().encode().toUri();
        restTemplate.put(url, trelloListDto);
    }

    public TrelloCardDto getCard(String cardId) {
        URI url = UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + "/cards/" + cardId)
                .queryParam("key", trelloConfig.getTrelloAppKey())
                .queryParam("token", trelloConfig.getTrelloToken())
                .queryParam("fields", "name,desc,pos,shortUrl,idAttachmentCover,idList,idBoard").build().encode().toUri();
        try {
            TrelloCardDto cardResponse = restTemplate.getForObject(url, TrelloCardDto.class);
            return (ofNullable(cardResponse).orElse(new TrelloCardDto()));
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(),e);
            return new TrelloCardDto();
        }
    }

    public CreatedTrelloCardDto createNewCard(TrelloCardDto trelloCardDto) {
        URI url = UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + "/cards")
                .queryParam("key", trelloConfig.getTrelloAppKey())
                .queryParam("token", trelloConfig.getTrelloToken())
                .queryParam("name", trelloCardDto.getName())
                .queryParam("desc", trelloCardDto.getDesc())
                .queryParam("pos", trelloCardDto.getPos())
                .queryParam("idList", trelloCardDto.getIdList()).build().encode().toUri();
        return restTemplate.postForObject(url, null, CreatedTrelloCardDto.class);
    }

    public void updateCard(String cardId, TrelloCardDto trelloCardDto) {
        URI url = UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + "/cards/" + cardId)
                .queryParam("key", trelloConfig.getTrelloAppKey())
                .queryParam("token", trelloConfig.getTrelloToken())
                .queryParam("name", trelloCardDto.getName())
                .queryParam("desc", trelloCardDto.getDesc())
                .queryParam("pos", trelloCardDto.getPos())
                .queryParam("idList", trelloCardDto.getIdList()).build().encode().toUri();
        restTemplate.put(url, trelloCardDto);
    }

    public void deleteCard(String cardId) {
        URI url = UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + "/cards/" + cardId)
                .queryParam("key", trelloConfig.getTrelloAppKey())
                .queryParam("token", trelloConfig.getTrelloToken()).build().encode().toUri();
        restTemplate.delete(url);
    }

    public List<TrelloCardAttachmentsDto> getCardAttachments(String cardId) {
        URI url = UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + "/cards/" + cardId + "/attachments")
                .queryParam("key", trelloConfig.getTrelloAppKey())
                .queryParam("token", trelloConfig.getTrelloToken())
                .queryParam("fields", "id,name,bytes,url,filename").build().encode().toUri();

        try {
            TrelloCardAttachmentsDto[] cardResponse = restTemplate.getForObject(url, TrelloCardAttachmentsDto[].class);
            return Arrays.asList(ofNullable(cardResponse).orElse(new TrelloCardAttachmentsDto[0]));
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(),e);
            return new ArrayList<>();
        }
    }

    public TrelloCardAttachmentsDto createCardAttachment(String cardId, String url) {
        URI urlPath = UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + "/cards/" + cardId + "/attachments")
                .queryParam("key", trelloConfig.getTrelloAppKey())
                .queryParam("token", trelloConfig.getTrelloToken())
                .queryParam("url", url).build().encode().toUri();
        return restTemplate.postForObject(urlPath, null, TrelloCardAttachmentsDto.class);
    }
}
