package com.speedhome.propertymanagement.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.speedhome.propertymanagement.dtos.FloorLevelDto;
import com.speedhome.propertymanagement.dtos.FurnishingTypesDto;
import com.speedhome.propertymanagement.services.FloorLevelService;
import com.speedhome.propertymanagement.services.FurnishingService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Muhammad Danish Khan
 * created 22/5/21 - 7:15 PM
 */
@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@RequiredArgsConstructor
class LookupControllerTest {
    @MockBean
    FloorLevelService floorLevelService;

    @MockBean
    FurnishingService furnishingService;

    private final MockMvc mockMvc;

    private String jwtToken;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        this.jwtToken = "Bearer " + getToken();
    }

    @Test
    void testGetAllFloorLevels() throws Exception {
        when(floorLevelService.getAllFloors()).thenReturn(getAllFloorLevels());
        mockMvc.perform(MockMvcRequestBuilders.get("/lookup/floors")
                .header("Authorization", jwtToken)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", is("application/json")))
                .andExpect(jsonPath("$.size()", is(getAllFloorLevels().size())))
                .andExpect(jsonPath("$.[0].floorLevelId", is(getAllFloorLevels().get(0).getFloorLevelId())));
    }

    @Test
    void testGetAllFurnishingTypes() throws Exception {
        when(furnishingService.getAllFurnishingTypes()).thenReturn(getFurnishingTypes());
        mockMvc.perform(MockMvcRequestBuilders.get("/lookup/furnishingtypes")
                .header("Authorization", jwtToken)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", is("application/json")))
                .andExpect(jsonPath("$.size()", is(getFurnishingTypes().size())))
                .andExpect(jsonPath("$.[0].furnishingId", is(getFurnishingTypes().get(0).getFurnishingId())));
    }

    private String getToken() throws Exception {
        String username = "admin";
        String password = "admin";

        String body = "{\"username\":\"" + username + "\", \"password\":\"" + password + "\"}";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk())
                .andReturn();

        ObjectNode jsonNode = new ObjectMapper().readValue(result.getResponse().getContentAsString(), ObjectNode.class);
        return jsonNode.get("token").asText();
    }

    private List<FloorLevelDto> getAllFloorLevels() {
        List<FloorLevelDto> floorLevels = new ArrayList<>();
        FloorLevelDto floorLevel = new FloorLevelDto();
        floorLevel.setFloorLevelId(1);
        floorLevel.setFloorLevelName("Single");

        FloorLevelDto floorLevel1 = new FloorLevelDto();
        floorLevel1.setFloorLevelId(2);
        floorLevel1.setFloorLevelName("Double");

        FloorLevelDto floorLevel2 = new FloorLevelDto();
        floorLevel2.setFloorLevelId(3);
        floorLevel2.setFloorLevelName("More tyhan 2 stories");

        floorLevels.add(floorLevel);
        floorLevels.add(floorLevel1);
        floorLevels.add(floorLevel2);

        return floorLevels;
    }

    private List<FurnishingTypesDto> getFurnishingTypes() {
        List<FurnishingTypesDto> furnishingTypes = new ArrayList<>();
        FurnishingTypesDto furnishingType = new FurnishingTypesDto();
        furnishingType.setFurnishingId(1);
        furnishingType.setFurnishingType("Unfurnished");

        FurnishingTypesDto furnishingType1 = new FurnishingTypesDto();
        furnishingType1.setFurnishingId(2);
        furnishingType1.setFurnishingType("Partially Furnished");

        FurnishingTypesDto furnishingType2 = new FurnishingTypesDto();
        furnishingType2.setFurnishingId(3);
        furnishingType2.setFurnishingType("Furnished");

        furnishingTypes.add(furnishingType);
        furnishingTypes.add(furnishingType1);
        furnishingTypes.add(furnishingType2);

        return furnishingTypes;
    }
}