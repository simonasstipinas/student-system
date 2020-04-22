package com.example.demo.service;

import com.example.demo.form.Contact;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ContactsApiService {

    public Contact[] fetchAll() {
        String baseHref = "http://contacts:5000/contacts";
        return new RestTemplate().getForObject(baseHref, Contact[].class);
    }

    public Contact fetchOne(int id) {
        String baseHref = "http://contacts:5000/contacts/" + String.valueOf(id);
        System.out.println(baseHref);
        try {
            return new RestTemplate().getForObject(baseHref, Contact.class);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public void update(int id, Contact contact) {
        String baseHref = "http://contacts:5000/contacts/" + String.valueOf(id);
        try {
            new RestTemplate().put(baseHref, contact);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void delete(int id) {
        String baseHref = "http://contacts:5000/contacts/" + String.valueOf(id);
        try {
            new RestTemplate().delete(baseHref);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Object insertContact(Contact contact) {
        String baseHref = "http://contacts:5000/contacts";
        try {
            return new RestTemplate().postForObject(baseHref, contact, Object.class);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
