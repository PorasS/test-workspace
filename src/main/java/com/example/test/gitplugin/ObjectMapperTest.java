package com.example.test.gitplugin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectMapperTest {

    public static void main(String[] args) throws JsonProcessingException {
        String jsonString = "  [{\"name\":\"pratik\"},{\"name\":\"gopal\"},{\"name\":\"shital\"},{\"name\":\"rupali\"},{\"name\":\"katrina\"}]";

        List<Map<String, String>> map = null;

       map  = new ObjectMapper().readValue(jsonString, List.class);

        System.out.println(map.toString());
    }
}
