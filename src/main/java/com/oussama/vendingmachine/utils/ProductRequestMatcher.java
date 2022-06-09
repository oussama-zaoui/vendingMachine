package com.oussama.vendingmachine.utils;

import com.oussama.vendingmachine.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;

public class ProductRequestMatcher implements RequestMatcher {
    @Autowired
   private  ProductService productService;
    @Override
    public boolean matches(HttpServletRequest request) {
        String username=request.getParameter("username");
        return true;
    }
}
