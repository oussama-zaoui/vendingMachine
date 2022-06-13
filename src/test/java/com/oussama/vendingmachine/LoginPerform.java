package com.oussama.vendingmachine;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LoginPerform {

    static String login(MockMvc mockMvc,String username,String password) throws Exception {
        Map<String,String> loginForm=new HashMap<>();
        loginForm.put("password",password);
        loginForm.put("username",username);
        MvcResult result=mockMvc.perform( MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(loginForm))

        ).andExpect(status().isOk()).andReturn();
        return result.getResponse().getContentAsString();
    }
}
