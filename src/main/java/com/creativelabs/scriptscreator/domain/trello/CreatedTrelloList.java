package com.creativelabs.scriptscreator.domain.trello;

import com.creativelabs.scriptscreator.dto.trello.BadgesDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreatedTrelloList {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;
}