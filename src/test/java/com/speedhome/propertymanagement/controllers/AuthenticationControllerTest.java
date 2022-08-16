package com.speedhome.propertymanagement.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Muhammad Danish Khan
 * created at: 22/5/21 - 7:55 PM
 */
@AutoConfigureMockMvc
@SpringBootTest
@Slf4j
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAuthenticateUser() throws Exception {
        String username = "admin";
        String password = "12345";

        String body = "{\"username\":\"" + username + "\", \"password\":\"" + password + "\"}";

        log.info("Testing Authentication With Wrong Credentials. Body: {}", body);

        mockMvc.perform(MockMvcRequestBuilders.post("/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isForbidden())
                .andReturn();

        log.info("Testing with wrong credentials executed as expected");

        password = "admin";
        body = "{\"username\":\"" + username + "\", \"password\":\"" + password + "\"}";

        log.info("Testing Authentication With Correct Credentials. Body: {}", body);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk())
                .andReturn();
       
        log.info("Testing with wrong credentials executed as expected. Returned Response: {}", result.getResponse().getContentAsString());

        ObjectNode jsonNode = new ObjectMapper().readValue(result.getResponse().getContentAsString(), ObjectNode.class);
        String token = jsonNode.get("token").asText();

        mockMvc.perform(MockMvcRequestBuilders.get("/lookup/floors")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }
}