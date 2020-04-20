package com.example.demo.service;

import com.example.demo.form.Contact;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class ContactsApiService {

    public static Contact[] fetchAll() {
        String baseHref = "http://localhost:5000/contacts";
        return new RestTemplate().getForObject(baseHref, Contact[].class);
    }

    public static Contact fetchOne(Long id) {
        String baseHref = "http://localhost:5000/contacts/" + id.toString();
        try {
            return new RestTemplate().getForObject(baseHref, Contact.class);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }

    public static void update(Long id, Contact contact) {
        String baseHref = "http://localhost:5000/contacts/" + id.toString();
        try {
            new RestTemplate().put(baseHref, contact);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void delete(Long id) {
        String baseHref = "http://localhost:5000/contacts/" + id.toString();
        try {
            new RestTemplate().delete(baseHref);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        Arrays.stream(fetchAll()).forEach(a -> System.out.println(a.getId()));
//        Arrays.stream(fetchAll()).forEach(a-> System.out.println(a.getEmail()));
        System.out.println("dasdasdsadasdsad");
        delete(1l);
        Arrays.stream(fetchAll()).forEach(a -> System.out.println(a.getId()));
//        System.out.println(fetchOne(1l).getName());
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
