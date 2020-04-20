package com.example.demo.form;

public class Pupil {

    String code;

    String name;

    Long contactId;

    public Pupil(
            String code,
            String name) {

        this.code = code;
        this.name = name;
    }

    public Pupil(
            String code,
            String name,
            Long contactId) {

        this.code = code;
        this.name = name;
        this.contactId = contactId;
    }

    public Pupil() {
        super();
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
}
