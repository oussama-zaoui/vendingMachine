package com.oussama.vendingmachine;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oussama.vendingmachine.models.Product;
import com.oussama.vendingmachine.models.User;
import com.oussama.vendingmachine.services.ProductService;
import com.oussama.vendingmachine.services.UserService;
import com.oussama.vendingmachine.utils.Constant;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DepositTests {

	@Autowired
	UserService userService;

	@Autowired
	ProductService productService;

	@Autowired
	MockMvc mockMvc;

	/** this methode is executed before all tests to populate db with some users; **/
	@BeforeAll
	 void populateDb(){
		User seller_user=new User("oussama","oussama","ROLE_seller");
		User buyer_user=new User("karim","karim","ROLE_buyer");
		userService.insertUser(seller_user);
		userService.insertUser(buyer_user);
		Product product=new Product("coca",40,5.0);
		product.setUser(seller_user);
		productService.newProduct(product);
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
		JsonNode parent= new ObjectMapper().readTree(LoginPerform.login(mockMvc,"karim","karim"));
		String token = parent.path("access_token").asText();
		mockMvc.perform( MockMvcRequestBuilders.patch("/user/deposit/100")
				.header("Authorization","Bearer "+token)


		).andExpect(status().isOk());
	}





}
