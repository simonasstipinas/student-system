package com.example.demo.service;

import com.example.demo.form.Contact;
import com.example.demo.form.Pupil;
import com.example.demo.form.Response;
import com.example.demo.form.SchoolClass;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class ManagingService {
    SchoolClass schoolClass = new SchoolClass();
    private final ContactsApiService contactsApiService;

    public ManagingService(ContactsApiService contactsApiService) {
        this.contactsApiService = contactsApiService;

        schoolClass.getSchoolClass().add(new Pupil("CLASSCODE1", "SIMONAS", 1l));
        Object o = contactsApiService.insertContact(new Contact(4, "Stipinas", "Simonas", "+366", "1115165"));
        schoolClass.getSchoolClass().add(new Pupil("CLASSCODE2", "KRISTUPAS"));

    }

    public Response insert(Pupil pupil) {
        if (exist(pupil)) {
            return new Response(400, "Already Exist");
        } else {
            schoolClass.getSchoolClass().add(pupil);
            return new Response(201, "Successfully added pupil");
        }
    }

    private boolean exist(Pupil pupil) {
        return schoolClass.getSchoolClass()
                .stream()
                .anyMatch(a -> a.getCode()
                        .equalsIgnoreCase(pupil.getCode()));
    }

    private boolean exist(String code) {
        return schoolClass.getSchoolClass()
                .stream()
                .anyMatch(a -> a.getCode().equalsIgnoreCase(code));
    }

    public Response update(String code, Pupil pupil) {
        schoolClass.getSchoolClass()
                .forEach(a -> {
                    if (a.getCode().equalsIgnoreCase(pupil.getCode())) {
                        System.out.println("Changing name from " + a.getName() + " to " + pupil.getName());
                        a.setName(pupil.getName());
                    }
                });
        if (exist(pupil)) {
            return new Response(200, "Successfully updated");
        } else {
            return new Response(404, "Ppil not found");
        }
    }

    public Response delete(String code) {
        schoolClass.setSchoolClass(
            schoolClass.getSchoolClass().stream().filter(a -> a.getCode().equals(code)).collect(toList())
        );
        return new Response(204, "Deleted successfully");
    }

    public Response fetchClass() {
        return new Response(200, "Class found", schoolClass);
    }

    public Response findById(String code) {
        if (exist(code)) {
            List<Pupil> pupils = schoolClass.getSchoolClass()
                    .stream()
                    .filter(a -> a.getCode().equalsIgnoreCase(code))
                    .collect(toList());
            if (pupils.size() > 1) {
               return new Response(404, "Pupil not found");
            } else {
               return new Response(200, "Pupil found", pupils.get(0));
            }
        } else {
            return new Response(404, "Pupil not found");
        }
    }
}
