package com.example.demo.service;

import com.example.demo.form.Contact;
import com.example.demo.form.Pupil;
import com.example.demo.form.PupilContact;
import com.example.demo.form.Response;
import com.example.demo.form.SchoolClass;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class ManagingService {
    private SchoolClass schoolClass = new SchoolClass();
    private final ContactsApiService contactsApiService;

    public ManagingService(ContactsApiService contactsApiService) {
        this.contactsApiService = contactsApiService;

        schoolClass.getSchoolClass().add(new Pupil("CLASSCODE1", "JOHN", "Biology", 74638));
        schoolClass.getSchoolClass().add(new Pupil("CLASSCODE2", "KRISTUPAS", "History", 87014));

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
        if (exist(code)) {
            return new Response(200, "Successfully updated");
        } else {
            return new Response(404, "Pupil not found");
        }
    }

    public Response delete(String code) {
        schoolClass.setSchoolClass(
                schoolClass.getSchoolClass().stream().filter(a -> a.getCode().equals(code)).collect(toList())
        );
        return new Response(204, "Deleted successfully");
    }

    public Response fetchClass() {
        List<PupilContact> answer = new ArrayList<>();
        List<PupilContact> list = new ArrayList<>();
        for (Pupil pupil : schoolClass.getSchoolClass()) {
            Contact contact;
            try {
                contact = contactsApiService.fetchOne(pupil.getFatherContact());
            } catch (Exception e) {
                contact = null;
            }
            PupilContact pupilContact = new PupilContact(pupil, contact);
            list.add(pupilContact);
        }
        answer = list;

        return new Response(200, "Class found", answer);
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
                Contact contact;
                try {
                    contact = contactsApiService.fetchOne(pupils.get(0).getFatherContact());
                } catch (Exception e) {
                    contact = null;
                }
                return new Response(200, "Pupil found", new PupilContact(pupils.get(0), contact));
            }
        } else {
            return new Response(404, "Pupil not found");
        }
    }

    public Response addContact(String code, Contact contact) {
        if (exist(code)) {
            schoolClass.getSchoolClass()
                    .forEach(a -> a.setFatherContact(contact.getId()));
            try {
                contactsApiService.update(contact.getId(), contact);
                return new Response(200, "Successfully updated");
            } catch (Exception e) {
                System.out.println(e);
                return new Response(404, "Not found");
            }
        } else {
            return new Response(404, "Not found");
        }
    }

    public Response updateContact(String code, Contact contact) {
        if (exist(code)) {
            schoolClass.getSchoolClass()
                    .forEach(a -> a.setFatherContact(contact.getId()));
            try {
                contactsApiService.insertContact(contact);
                return new Response(201, "Successfully added");
            } catch (Exception e) {
                System.out.println(e);
                return new Response(500, "Insert Failed");
            }
        } else {
            return new Response(404, "Not found");
        }
    }
}
