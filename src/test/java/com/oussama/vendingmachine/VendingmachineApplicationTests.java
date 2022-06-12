package com.oussama.vendingmachine;

import com.oussama.vendingmachine.services.UserService;
import com.oussama.vendingmachine.utils.Constant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
class VendingmachineApplicationTests {

	@Autowired
	UserService userService;

	@Test
	void deposit() {
		System.out.println("this is a test");
		System.out.println("this message is coming from teste "+userService.deposit(50));
		assertEquals("", Constant.OK,userService.deposit(100));
	}

}
