package com.suchaos.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageControllerTest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    private void saveMessages() {
        for (int i = 1; i < 10; i++) {
            final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("text", "text" + i);
            params.add("summary", "summary" + i);
            try {
                MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/message")
                        .params(params)).andReturn();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        saveMessages();
    }

    @Test
    public void saveMessage() throws Exception {
        final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("text", "text");
        params.add("summary", "summary");
        String mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/message")
                .params(params)).andReturn().getResponse().getContentAsString();
        System.out.println("saveMessage:  " + mvcResult);
    }

    @Test
    public void saveMessageWithJson() throws Exception {
        final Map<String, String> params = new HashMap<>();
        params.put("text", "text");
        params.put("summary", "summary");
        String jsonParams = objectMapper.writeValueAsString(params);
        String mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/messageJson").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(jsonParams)).andReturn().getResponse().getContentAsString();
        System.out.println("saveMessageWithJson:  " + mvcResult);
    }

    @Test
    public void getAllMessages() throws Exception {
        String mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/messages")).andReturn()
                .getResponse().getContentAsString();
        System.out.println("getAllMessages: " + mvcResult);
    }

    @Test
    public void getMessage() throws Exception {
        String mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/message/4")).andReturn()
                .getResponse().getContentAsString();
        System.out.println("getMessage: " + mvcResult);
    }

    @Test
    public void modifyMessage() throws Exception {
        final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("id", "6");
        params.add("text", "text");
        params.add("summary", "summary");
        String mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/message").params(params))
                .andReturn().getResponse().getContentAsString();
        System.out.println("modifyMessage: " + mvcResult);
    }

    @Test
    public void patchMessage() throws Exception {
        final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("id", "6");
        params.add("text", "text");
        String mvcResult = mockMvc.perform(MockMvcRequestBuilders.patch("/message/text").params(params))
                .andReturn().getResponse().getContentAsString();
        System.out.println("patchMessage: " + mvcResult);
    }

    @Test
    public void deleteMessage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/message/6")).andReturn();
        String mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/messages"))
                .andReturn().getResponse().getContentAsString();
        System.out.println("deleteMessage: " + mvcResult);
    }
}