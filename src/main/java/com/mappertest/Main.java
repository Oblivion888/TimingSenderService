package com.mappertest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Report;

import java.util.List;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "[Report{id=3, date=2021-11-08, userId='157364928', tasks=[Task{id=5, timeSpended=3, description='acid theory', reportId=3}, Task{id=6, timeSpended=2, description='tomcat server', reportId=3}]}, Report{id=4, date=2021-11-08, userId='157364928', tasks=[]}, Report{id=5, date=2021-11-08, userId='157364928', tasks=[]}, Report{id=6, date=2021-11-08, userId='157364928', tasks=[]}, Report{id=7, date=2021-11-08, userId='157364928', tasks=[]}, Report{id=8, date=2021-11-08, userId='157364928', tasks=[]}, Report{id=9, date=2021-11-08, userId='157364928', tasks=[]}, Report{id=10, date=2021-11-08, userId='157364928', tasks=[]}, Report{id=11, date=2021-11-08, userId='157364928', tasks=[]}, Report{id=12, date=2021-11-08, userId='157364928', tasks=[]}, Report{id=13, date=2021-11-08, userId='157364928', tasks=[]}, Report{id=14, date=2021-11-08, userId='157364928', tasks=[Task{id=27, timeSpended=3, description='acid theory', reportId=14}, Task{id=28, timeSpended=2, description='tomcat server', reportId=14}]}]";
        ObjectMapper mapper = new ObjectMapper();
        Report[] reports = mapper.readValue(json, Report[].class);


    }
}
