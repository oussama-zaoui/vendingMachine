package com.oussama.vendingmachine;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.oussama.vendingmachine.services.UserService;
import com.oussama.vendingmachine.utils.Constant;
import com.oussama.vendingmachine.utils.JsonMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class VendingmachineApplicationTests {

	@Autowired
	UserService userService;

	@Autowired
	MockMvc mockMvc;




	String login() throws Exception {
		Map<String,String> loginForm=new HashMap<>();
		loginForm.put("password","oussama");
		loginForm.put("username","oussama");
		MvcResult result=mockMvc.perform( MockMvcRequestBuilders.post("/login")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(loginForm))

		).andExpect(status().isOk()).andReturn();
		return result.getResponse().getContentAsString();
	}

	@Test
	@WithMockUser(username = "karim")
	void deposit_logic() {
		assertEquals("", Constant.OK,userService.deposit(100));
		assertEquals("", Constant.OK,userService.deposit(50));
		assertEquals("", Constant.OK,userService.deposit(20));
		assertEquals("", Constant.OK,userService.deposit(10));
		assertEquals("", Constant.OK,userService.deposit(5));
		//giving a forbidden value
		assertEquals("",Constant.FORBIDDEN,userService.deposit(12));
	}

	@Test
	void deposit_seller_authorization() throws Exception {
		JsonNode parent= new ObjectMapper().readTree(login());
		String token = parent.path("access_token").asText();
		mockMvc.perform( MockMvcRequestBuilders.patch("/user/deposit/100")
				.header("Authorization","Bearer "+token)


		).andExpect(status().isOk());
	}





}
