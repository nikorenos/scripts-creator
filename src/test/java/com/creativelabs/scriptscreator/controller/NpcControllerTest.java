package com.creativelabs.scriptscreator.controller;


import com.creativelabs.scriptscreator.controller.NpcController;
import com.creativelabs.scriptscreator.domain.Npc;
import com.creativelabs.scriptscreator.dto.NpcDto;
import com.creativelabs.scriptscreator.mapper.NpcMapper;
import com.creativelabs.scriptscreator.service.NpcServiceImpl;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(NpcController.class)
public class NpcControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private NpcServiceImpl service;

    @MockBean
    private NpcMapper mapper;

    @Test
    public void shouldFetchEmptyNpcsList() throws Exception {
        // Given
        List<Npc> npcList = new ArrayList<>();
        when(service.getAllNpcs()).thenReturn(npcList);
        when(mapper.mapToNpcDtoList(npcList)).thenReturn(new ArrayList<>());

        // When & Then
        mockMvc.perform(get("/v1/npcs").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200)) //or isOk()
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void shouldFetchNpcsList() throws Exception {
        // Given
        List<Npc> npcList = new ArrayList<>();
        npcList.add(new Npc(1L, "Npc 1", "Npc 1 description"));
        List<NpcDto> npcListDto = new ArrayList<>();
        npcListDto.add(new NpcDto(1L, "Npc 1", "Npc 1 description"));

        when(service.getAllNpcs()).thenReturn(npcList);
        when(mapper.mapToNpcDtoList(npcList)).thenReturn(npcListDto);

        // When & Then
        mockMvc.perform(get("/v1/npcs").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Npc 1")))
                .andExpect(jsonPath("$[0].description", is("Npc 1 description")));
    }

    @Test
    public void shouldFetchNpc() throws Exception {
        // Given
        NpcDto npcDto = new NpcDto(1L, "Npc 1", "Npc 1 description");
        Optional<Npc> npc = Optional.of(new Npc(1L, "Npc 1", "Npc 1 description"));
        long npcId = npcDto.getId();

        when(service.getNpc(npcId)).thenReturn(npc);
        when(mapper.mapToNpcDto(npc.get())).thenReturn(npcDto);

        // When & Then
        mockMvc.perform(get("/v1/Npcs/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Npc 1")))
                .andExpect(jsonPath("$.content", is("Npc 1 description")));
    }

    @Test
    public void shouldDeleteNpc() throws Exception {
        // Given
        Npc npc = new Npc(1L, "Npc 1", "Npc 1 description");
        Long npcId = npc.getId();
        Optional<Npc> foundNpc = Optional.of(new Npc(1L, "Npc 1", "Npc 1 description"));

        when(service.getNpc(npcId)).thenReturn(foundNpc);

        // When & Then
        mockMvc.perform(delete("/v1/npcs/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldCreateNpc() throws Exception {
        // Given
        Npc npc = new Npc(1L, "Npc 1", "Npc 1 description");
        NpcDto npcDto = new NpcDto(1L, "Npc 1", "Npc 1 description");

        when(mapper.mapToNpc(npcDto)).thenReturn(npc);
        when(service.saveNpc(npc)).thenReturn(npc);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(npcDto);

        // When & Then
        mockMvc.perform(post("/v1/Npcs")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUpdateNpc() throws Exception {
        // Given
        Npc npc = new Npc(1L, "Npc 1", "Npc 1 description");
        NpcDto npcDto = new NpcDto(1L, "Npc 1", "Npc 1 description");

        when(mapper.mapToNpc(any(NpcDto.class))).thenReturn(npc);
        when(service.saveNpc(npc)).thenReturn(npc);
        when(mapper.mapToNpcDto(any(Npc.class))).thenReturn(npcDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(npcDto);

        // When & Then
        mockMvc.perform(put("/v1/npcs")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Npc 1")))
                .andExpect(jsonPath("$.content", is("Npc 1 description")));
    }

}