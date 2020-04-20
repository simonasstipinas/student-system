package com.example.demo.service;

import com.example.demo.form.Contact;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ContactsApiService {
    public Object fake() {
        String baseHref = "http://localhost:5000/contacts";
        return new RestTemplate().getForObject(baseHref, Object.class);
    }

    public Object insertContact(Contact contact) {
        String baseHref = "http://localhost:5000/contacts";
        try {
            return new RestTemplate().postForObject(baseHref, contact, Void.class);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
