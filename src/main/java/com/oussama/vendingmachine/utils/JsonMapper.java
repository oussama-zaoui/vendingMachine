package com.oussama.vendingmachine.utils;


import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.stream.Collectors;

public class JsonMapper {


   public static Object stringToJson(Object mappingForm, HttpServletRequest request){
       ObjectMapper mapper=new ObjectMapper();
       try {
           String target=request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
           mappingForm=mapper.readValue(target,mappingForm.getClass());
       } catch (IOException e) {
           throw new RuntimeException(e);
       }

       return mappingForm;
   }


}
