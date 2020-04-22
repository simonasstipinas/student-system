package com.example.demo.service;

import com.example.demo.form.Contact;
import com.example.demo.form.Pupil;
import com.example.demo.form.PupilContact;
import com.example.demo.form.Response;
import com.example.demo.form.SchoolClass;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class ManagingService {
    private SchoolClass schoolClass = new SchoolClass();
    private final ContactsApiService contactsApiService;

    public ManagingService(ContactsApiService contactsApiService) {
        this.contactsApiService = contactsApiService;

        schoolClass.getSchoolClass().add(new Pupil("CLASSCODE1", "JOHN", "Biology", 12345));
        schoolClass.getSchoolClass().add(new Pupil("CLASSCODE2", "KRISTUPAS", "History", 74638));

    }

    public Response insert(PupilContact entity) {
        if (exist(entity.getCode())) {
            return new Response(400, "Already Exist");
        } else {
            int ll = entity.getContact() != null ? entity.getContact().getId() : 0;
            schoolClass.getSchoolClass().add(new Pupil(entity.getCode(), entity.getName(), entity.getField(), ll));
            Contact contact = entity.getContact();
            if (contact != null) {
                contactsApiService.insertContact(contact);
            }
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

    public Response update(String code, PupilContact entity) {
        if (exist(code)) {
            try {
                schoolClass.getSchoolClass()
                        .forEach(a -> {
                            if (a.getCode().equalsIgnoreCase(entity.getCode())) {
                                a.setName(entity.getName());
                                a.setField(entity.getField());
                                if (a.getFatherContact() == 0 && entity.getContact() != null) {
                                    contactsApiService.insertContact(entity.getContact());
                                } else if (a.getFatherContact() != 0 && entity.getContact() != null) {
                                    try {
                                        Contact contact = contactsApiService.fetchOne(a.getFatherContact());
                                        contactsApiService.update(contact.getId(), entity.getContact());
                                    } catch (Exception e) {
                                        contactsApiService.insertContact(entity.getContact());
                                    }
                                } else {
                                    if (entity.getContact() != null) {
                                        contactsApiService.insertContact(entity.getContact());
                                    }
                                }
                                a.setFatherContact(entity.getContact() != null ? entity.getContact().getId() : 0);
                            }
                        });
            } catch (Exception e) {
                return new Response(404, "Pupil not found");
            }
            return new Response(200, "Successfully updated");
        } else {
            return new Response(404, "Pupil not found");
        }
    }

    public Response delete(String code) {
        try {
            schoolClass.setSchoolClass(
                    schoolClass.getSchoolClass()
                            .stream()
                            .filter(a -> !a.getCode().equals(code))
                            .collect(toList())
            );
        } catch (Exception e) {
            return new Response(404, "Not Found");
        }
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
                    int fatherContact = pupils.get(0).getFatherContact();
                    contact = fatherContact == 0 ? null : contactsApiService.fetchOne(fatherContact);
                } catch (Exception e) {
                    contact = null;
                }
                return new Response(200, "Pupil found", new PupilContact(pupils.get(0), contact));
            }
        } else {
            return new Response(404, "Pupil not found");
        }
    }
}
