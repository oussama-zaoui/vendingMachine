package com.oussama.vendingmachine;

import com.oussama.vendingmachine.services.UserService;
import com.oussama.vendingmachine.utils.Constant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
class VendingmachineApplicationTests {

	@Autowired
	UserService userService;

	@Autowired
	MockMvc mockMvc;



	@Test
	@WithMockUser(username = "karim",authorities = "seller")
	void deposit() {
		assertEquals("", Constant.OK,userService.deposit(100));
	}

}
