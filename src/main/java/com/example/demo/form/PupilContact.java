package com.example.demo.form;

public class PupilContact {
    private String code;

    private String name;

    private String field;

    private Contact contact;

    public PupilContact(){}

    public PupilContact(Pupil pupil, Contact contact) {
        this.code = pupil.getCode();
        this.name = pupil.getName();
        this.field = pupil.getField();
        this.contact = contact;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
