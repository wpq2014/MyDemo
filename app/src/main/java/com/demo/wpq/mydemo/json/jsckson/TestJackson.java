package com.demo.wpq.mydemo.json.jsckson;

import com.demo.wpq.mydemo.json.Person;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;

public class TestJackson {

    static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) {
        //
        String json = "{\"name\":\"Jackson\",\"age\":11}";
        try {
            Person p1 = mapper.readValue(json, Person.class);
            System.out.println(p1.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        //
        try {
            StringWriter stringWriter = new StringWriter();
            JsonGenerator g2 = mapper.getJsonFactory().createJsonGenerator(stringWriter);
            g2.writeStartObject();
            g2.writeStringField("name", "Jackson2");
            g2.writeNumberField("age", 22);
            g2.writeEndObject();
            g2.flush();
            g2.close();
            System.out.println(stringWriter.toString());
            Person p2 = mapper.readValue(stringWriter.toString(), Person.class);
            System.out.println(p2.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        //
        Person p3 = new Person();
        p3.setName("Jackson3");
        p3.setAge(33);
        try {
            String g3 = mapper.writeValueAsString(p3);
            System.out.println(g3);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
