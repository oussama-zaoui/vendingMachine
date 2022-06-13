package com.oussama.vendingmachine;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oussama.vendingmachine.request.BuyOrder;

import com.oussama.vendingmachine.services.ProductService;
import com.oussama.vendingmachine.services.UserService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class BuyTests {



    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;
    @Autowired
    MockMvc mockMvc;







	@Test
	void deposit_seller_authorization() throws Exception {
		JsonNode parent= new ObjectMapper().readTree(LoginPerform.login(mockMvc,"karim","karim"));
		String token = parent.path("access_token").asText();
		mockMvc.perform( MockMvcRequestBuilders.post("/user/buy")
				.header("Authorization","Bearer "+token)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(new BuyOrder(1,5)))


		).andExpect(status().isOk());

		 parent= new ObjectMapper().readTree(LoginPerform.login(mockMvc,"oussama","oussama"));
		 token = parent.path("access_token").asText();
		mockMvc.perform( MockMvcRequestBuilders.post("/user/buy")
				.header("Authorization","Bearer "+token)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(new BuyOrder(1,5)))


		).andExpect(status().isForbidden());


	}






}
