package com.speedhome.propertymanagement.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.speedhome.propertymanagement.dtos.AddressDto;
import com.speedhome.propertymanagement.dtos.PropertyDto;
import com.speedhome.propertymanagement.dtos.PropertyStatusDto;
import com.speedhome.propertymanagement.dtos.SearchCriteria;
import com.speedhome.propertymanagement.services.PropertyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Muhammad Danish Khan
 * @created 22/5/21 - 6:22 PM
 */
@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class PropertyControllerTest {
    @MockBean
    PropertyService propertyService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private List<PropertyDto> properties;

    private String jwtToken;

    @BeforeEach
    void setUp() throws Exception {
        this.properties = createProperties();
        MockitoAnnotations.openMocks(this);
        this.jwtToken = "Bearer " + getToken();
    }

    @Test
    void testCreateProperty() throws Exception {
        PropertyDto property = properties.get(0);
        property.setPropertyId(null);
        property.getAddress().setAddressId(null);
        when(propertyService.createAndUpdateProperty(any(PropertyDto.class))).thenReturn(properties.get(0));
        mockMvc.perform(MockMvcRequestBuilders.post("/properties/create")
                .header("Authorization", jwtToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(property))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(header().string("Content-Type", is("application/json")))
                .andExpect(jsonPath("$.propertyName", is(properties.get(0).getPropertyName())))
                .andExpect(jsonPath("$.address.addressId", is(properties.get(0).getAddress().getAddressId())));
    }

    @Test
    void testUpdateProperty() throws Exception {
        when(propertyService.createAndUpdateProperty(any(PropertyDto.class))).thenReturn(properties.get(1));
        mockMvc.perform(MockMvcRequestBuilders.put("/properties/update")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", jwtToken)
                .content(objectMapper.writeValueAsString(properties.get(1)))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", is("application/json")))
                .andExpect(jsonPath("$.propertyName", is(properties.get(1).getPropertyName())))
                .andExpect(jsonPath("$.address.addressId", is(properties.get(1).getAddress().getAddressId())));
    }

    @Test
    void testUpdatePropertyStatus() throws Exception {
        PropertyStatusDto propertyStatus = new PropertyStatusDto();
        propertyStatus.setPropertyId(1);
        propertyStatus.setPropertyStatus(true);

        Mockito.doNothing().when(propertyService).approveProperty(1, true);
        mockMvc.perform(MockMvcRequestBuilders.put("/properties/approve")
                .header("Authorization", jwtToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(propertyStatus))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", is("application/json")))
                .andExpect(jsonPath("$.status", is("OK")))
                .andExpect(jsonPath("$.message", is("Property Approved")));
    }

    @Test
    void testSearchProperties() throws Exception {
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setPropertyName("ouse");
        searchCriteria.setPageSize(10);
        searchCriteria.setPageNumber(0);

        when(propertyService.searchProperty(any(SearchCriteria.class))).thenReturn(properties);
        mockMvc.perform(MockMvcRequestBuilders.put("/properties/search")
                .header("Authorization", jwtToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(searchCriteria))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", is("application/json")))
                .andExpect(jsonPath("$.size()", is(properties.size())))
                .andExpect(jsonPath("$.[0].propertyId", is(properties.get(0).getPropertyId())));
    }

    @Test
    void testGetAllProperties() throws Exception {
        when(propertyService.getAllProperties()).thenReturn(properties);
        mockMvc.perform(MockMvcRequestBuilders.get("/properties/all")
                .header("Authorization", jwtToken)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", is("application/json")))
                .andExpect(jsonPath("$.size()", is(properties.size())))
                .andExpect(jsonPath("$.[0].propertyId", is(properties.get(0).getPropertyId())));
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

    private List<PropertyDto> createProperties() {

        List<PropertyDto> propertyList = new ArrayList<>();

        PropertyDto property = new PropertyDto();
        property.setPropertyId(1);
        property.setPropertyName("House 30 New York");
        property.setBathroom(2);
        property.setBedroom(2);
        property.setBuildupSize(200);
        property.setFloorLevelId(2);
        property.setFurnishingTypeId(2);
        property.setParking(0);
        property.setStatus(true);
        property.setCreatedBy(1);
        property.setCreationDate(new Date());

        AddressDto address = new AddressDto();
        address.setAddressId(1);
        address.setFullAddress("New York USA");
        address.setPostCode(44000);

        property.setAddress(address);

        PropertyDto property1 = new PropertyDto();
        property1.setPropertyId(2);
        property1.setPropertyName("House 56 Alaska");
        property1.setBathroom(1);
        property1.setBedroom(1);
        property1.setBuildupSize(100);
        property1.setFloorLevelId(1);
        property1.setFurnishingTypeId(1);
        property1.setParking(0);
        property1.setStatus(true);
        property1.setCreatedBy(1);
        property1.setCreationDate(new Date());

        AddressDto address1 = new AddressDto();
        address1.setAddressId(2);
        address1.setFullAddress("Alaska USA");
        address1.setPostCode(12345);

        property1.setAddress(address1);


        PropertyDto property2 = new PropertyDto();
        property2.setPropertyId(3);
        property2.setPropertyName("House 80 Alaska");
        property2.setBathroom(3);
        property2.setBedroom(3);
        property2.setBuildupSize(300);
        property2.setFloorLevelId(3);
        property2.setFurnishingTypeId(3);
        property2.setParking(1);
        property2.setStatus(true);
        property2.setCreatedBy(1);
        property2.setCreationDate(new Date());

        AddressDto address2 = new AddressDto();
        address2.setAddressId(3);
        address2.setFullAddress("Alaska USA");
        address2.setPostCode(12345);

        property2.setAddress(address2);


        propertyList.add(property);
        propertyList.add(property1);
        propertyList.add(property2);

        return propertyList;
    }
}